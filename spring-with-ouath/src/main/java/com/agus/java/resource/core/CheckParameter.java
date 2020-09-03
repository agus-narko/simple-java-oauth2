package com.agus.java.resource.core;

import com.agus.java.config.MasterGeneralConstants;
import java.util.regex.Pattern;

public class CheckParameter {

    public static String checkParameter(String input) {

        Pattern patternDigit = Pattern.compile("-?\\d+(\\.\\d+)?");

    	 if (input == null || input.equals(" ")) {
    	        return MasterGeneralConstants.NO_PARAM;
    	 }
    	 else if (patternDigit.matcher(input).matches()){
    		 return MasterGeneralConstants.ID_PARAM;
    	 }
    	 else if (input.equals(MasterGeneralConstants.ACTIVATE) || input.equals(MasterGeneralConstants.DEACTIVATE) ){
    		 return MasterGeneralConstants.ACTIVE_PARAM;
    	 }
    	 else {
    		 return MasterGeneralConstants.OTHER_PARAM;
    	 }

    }

}