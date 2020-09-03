package com.agus.java.resource.core;

import com.agus.java.resource.exception.ParsingFailedException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtil {

    public static JSONObject parseStringtoJSON(String strJson){

        try{

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(strJson);

            return json;

        }catch(ParseException e ){
            throw new ParsingFailedException("Failed to parse JSON.");
        }

    }


}
