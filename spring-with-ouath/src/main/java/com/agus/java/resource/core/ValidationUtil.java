package com.agus.java.resource.core;

import com.agus.java.resource.exception.InvalidParameterException;
import com.agus.java.resource.exception.UnsupportedParameterException;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public  class ValidationUtil {


    //================= Unsupport Parameter Checker===================
    private static List<String> unsupportedParameterList;

    //unsuport parameter Checker
    public static void checkForUnsupportedParameters(final JSONObject jsonInput, final List<String> supportedParams) {

        //null json
        if (jsonInput == null || jsonInput.isEmpty() ) {
            throw  new InvalidParameterException("JSON Must Contain Keys ".concat(supportedParams.toString()).concat(" and Cannot Be Empty"));
        }

        //check json key
        List<String> keysJson = new ArrayList<String>(jsonInput.keySet());
       

        keysJson.removeAll(supportedParams);
        unsupportedParameterList = keysJson;
        

        //any unsupport parameter?
        if (!unsupportedParameterList.isEmpty()) {
            throw new UnsupportedParameterException(unsupportedParameterList.toString());
        }
    }

    //unsupport Nested Param
    public static void checkForUnsupportedNestedParameters(final String parentPropertyName, final JSONObject jsonInput,
            final List<String> supportedParams) {

        try {
            checkForUnsupportedParameters(jsonInput, supportedParams);
        } catch (UnsupportedParameterException upex) {

            List<String> updatedUnsupportedParameters = new ArrayList<>();

            for (String unsupportedParameter : unsupportedParameterList) {
                String updatedUnsupportedParameter = parentPropertyName + "." + unsupportedParameter;
                updatedUnsupportedParameters.add(updatedUnsupportedParameter);
            }

            throw new UnsupportedParameterException(updatedUnsupportedParameters.toString());

        } catch (InvalidParameterException ipex) {

            List<String> keyJsonwithParent = new ArrayList<String>();
            for(int i=0; i<supportedParams.size(); i++ ){
                keyJsonwithParent.add( parentPropertyName.concat(".").concat( supportedParams.get(i)) );
            }

            throw  new InvalidParameterException("JSON Must Contain Keys ".concat(keyJsonwithParent.toString()).concat(" and Cannot Be Empty"));

        }

    }


    //================= Blank/Null Checker ===========================
    public static void valBlankOrNullList(JSONObject inputJson, List<String> keyIndex) {

        List<String> listField = new ArrayList<String>();
        for (int i = 0; i < keyIndex.size(); i++) {
        	
            if(!inputJson.containsKey(keyIndex.get(i)) || String.valueOf(inputJson.get(keyIndex.get(i))).isEmpty()){
                listField.add(keyIndex.get(i));
            }
        }
        

        if(!listField.isEmpty()){
            throw  new InvalidParameterException("Field ".concat(listField.toString()).concat(" cannot be empty"));
        }

    }















}
