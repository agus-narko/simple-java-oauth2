package com.agus.java.resource.core;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.agus.java.model.ServiceOutput;

public class PrepareServiceOutput {

	private static Map<BigInteger, ServiceOutput> finalServiceOutput;
	static ServiceOutput serviceOutput = new ServiceOutput();

	public static Map<BigInteger, ServiceOutput> prepareServiceOutput(JSONObject serviceInput, String process, BigInteger ID) {
	
		serviceOutput.setServiceContent(serviceInput);
		
        finalServiceOutput = new HashMap<BigInteger, ServiceOutput>();
        
        serviceOutput.setId(ID);
        serviceOutput.setProcess(process);
        finalServiceOutput.put(serviceOutput.getId(), serviceOutput);
        return finalServiceOutput;
    }
	
}
