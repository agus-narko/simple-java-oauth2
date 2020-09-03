package com.agus.java.resource.core;

import org.json.simple.JSONObject;

public interface DataTransaction {

	public abstract JSONObject prepare(JSONObject serviceInput);

	public abstract JSONObject process(JSONObject serviceInput);

}
