package com.agus.java.model;

import java.math.BigInteger;

import org.json.simple.JSONObject;

public class ServiceOutput {

    private BigInteger id;
    private String process;
    
    private JSONObject serviceContent;

    public JSONObject getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(JSONObject serviceContent) {
		this.serviceContent = serviceContent;
	}

	public ServiceOutput() {

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

}
