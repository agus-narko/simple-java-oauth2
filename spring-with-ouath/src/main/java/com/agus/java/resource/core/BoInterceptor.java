package com.agus.java.resource.core;

import java.math.BigInteger;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.model.ServiceOutput;
//import com.agus.java.resource.approvalManagement.CheckApproval;
import com.agus.java.resource.customLog.LogActivity;
import com.agus.java.resource.exception.UnrecognizedBoParamException;

@Service
public class BoInterceptor {

	@Autowired
	LogActivity logActivity;

	@Autowired
	private final ApplicationContext applicationContext;
	private static Map<BigInteger, ServiceOutput> output;
	private static JSONObject boOutput;

	@Autowired
	public BoInterceptor(final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public Map<BigInteger, ServiceOutput> processAndLogCommand(String action,
			String url, String boName, String processDataMode,
			JSONObject serviceInput) {

		BigInteger ID;
		if (serviceInput.containsKey("userId")) {
			ID = new BigInteger(serviceInput.get("userId").toString());
		}

		else {
			ID = new BigInteger("0");
		}

		// call BO
	
		switch (processDataMode) {
		case "DataProcess":
			final DataProcess boProces = callBoProcess(boName);
			boOutput = boProces.processBo(serviceInput);
			output = PrepareServiceOutput.prepareServiceOutput(boOutput,
					MasterGeneralConstants.DATAPROCESS, ID);
			break;

		case "DataTransaction":
			final DataTransaction boTransaction = callBoTransaction(boName);
			boOutput = boTransaction.process(serviceInput);
			output = PrepareServiceOutput.prepareServiceOutput(boOutput,
					MasterGeneralConstants.DATATRANSACTION, ID);
			break;

		}

		if (!action.equals(MasterGeneralConstants.OAUTH)) {
			// log
			logActivity.writeLog(action, url, serviceInput.toJSONString(),
					boOutput);

		}

		return output;

	}

	// -----------------------------BO
	// SELECTOR-----------------------------------
	private DataProcess callBoProcess(String beanName) {
		try {
			DataProcess bo = this.applicationContext.getBean(beanName,
					DataProcess.class);
			return bo;

		} catch (NoSuchBeanDefinitionException e) {
			e.printStackTrace();
			throw new UnrecognizedBoParamException(beanName);
		}

	}

	private DataTransaction callBoTransaction(String beanName) {
		try {
			DataTransaction bo = this.applicationContext.getBean(beanName,
					DataTransaction.class);
			return bo;

		} catch (NoSuchBeanDefinitionException e) {
			e.printStackTrace();
			throw new UnrecognizedBoParamException(beanName);
		}

	}

}
