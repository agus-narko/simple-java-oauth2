package com.agus.java.resource.userManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.controller.entitySwagger.userManagement.CreateClientEntity;
import com.agus.java.model.userManagement.Client;
import com.agus.java.model.userManagement.ClientDao;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.ValidationUtil;



@Service
public class CreateClient implements DataProcess {

	@Autowired
	ClientDao clientDao;

	@Override
    @SuppressWarnings("unchecked")
	public JSONObject processBo(JSONObject serviceInput) {

		System.err.println("create client");

		//Validation
		Field[] fields = CreateClientEntity.class.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for(Field field : fields) {                           

		    field.setAccessible(true);                       
		    validationFieldList.add(field.getName());
		}
		
		ValidationUtil.checkForUnsupportedParameters(serviceInput,
				validationFieldList);
		ValidationUtil.valBlankOrNullList(serviceInput, validationFieldList);

		// prepare data
		String clientId = String.valueOf(serviceInput.get("clientId"));
		String clientSecret = String.valueOf(serviceInput.get("clientSecret"));
		String scope = String.valueOf(serviceInput.get("scope"));

		Long accessTokenValidity = new Long(1000);
		Long refreshTokenValidity = new Long(100000000);

		String errorMessage = "";
		JSONObject jsonOutput = new JSONObject();

		try {
			Client newClient = new Client();

			newClient.setClientId(clientId);
			newClient.setClientSecret("{noop}"+clientSecret);
			newClient.setScope(scope);
			newClient.setAuthorizedGrantTypes("authorization_code,check_token,refresh_token,password");
			newClient.setAccessTokenValidity(accessTokenValidity);
			newClient.setRefreshTokenValidity(refreshTokenValidity);
			clientDao.add(newClient);
		} catch (Exception e) {
			e.printStackTrace();

			errorMessage = e.getCause().toString();
			errorMessage=errorMessage.substring((errorMessage.indexOf(':')+1),errorMessage.length());
			errorMessage = errorMessage.trim();

			jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
			jsonOutput.put("errorMessage", errorMessage);
		}

		return jsonOutput;
	}

}
