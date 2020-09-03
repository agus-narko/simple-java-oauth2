package com.agus.java.resource.userManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.controller.entitySwagger.InquiryEntity;
import com.agus.java.model.userManagement.UserDao;
import com.agus.java.model.userManagement.UserEntity;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.ValidationUtil;



@Service
public class GetUserById implements DataProcess {

	@Autowired
	UserDao userDao;

	@Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject processBo(JSONObject serviceInput) {

	    //Validation
		Field[] fields = InquiryEntity.class.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for(Field field : fields) {                           

		    field.setAccessible(true);                       
		    validationFieldList.add(field.getName());
		}
        ValidationUtil.checkForUnsupportedParameters(serviceInput,validationFieldList);
        ValidationUtil.valBlankOrNullList(serviceInput,validationFieldList);

        //prepare data
		Long id = Long.valueOf(String.valueOf(serviceInput.get("parameter")));

		JSONObject jsonOutput = new JSONObject();
		try {

			UserEntity user = userDao.find(id);

			jsonOutput.put("serviceOutput", user);
			jsonOutput.put("status", MasterGeneralConstants.STATUS_SUCCESS);

			return jsonOutput;
		} catch (Exception e) {
			e.printStackTrace();

			String errorMessage = e.getCause().toString();
			errorMessage = errorMessage.substring(
					(errorMessage.indexOf(':') + 1), errorMessage.length());
			errorMessage = errorMessage.trim();
			jsonOutput = new JSONObject();
			jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
			jsonOutput.put("errorMessage", errorMessage);
			return jsonOutput;
		}
	}
}
