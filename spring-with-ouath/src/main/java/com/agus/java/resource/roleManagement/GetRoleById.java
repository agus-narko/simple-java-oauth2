package com.agus.java.resource.roleManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.controller.entitySwagger.InquiryEntity;
import com.agus.java.model.roleManagement.Role;
import com.agus.java.model.roleManagement.RoleDao;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.ValidationUtil;



@Service
public class GetRoleById implements DataProcess{

	@Autowired
	RoleDao roleDao;

	@Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject processBo(JSONObject serviceInput) {

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

			Role role = roleDao.find(id);

			jsonOutput.put("serviceOutput", role);
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
