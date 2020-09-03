package com.agus.java.resource.userManagement;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.model.userManagement.UserDao;
import com.agus.java.model.userManagement.UserEntity;
import com.agus.java.resource.core.DataProcess;

@Service
public class GetUser implements DataProcess {

	@Autowired
	UserDao userDao;

	@SuppressWarnings("unchecked")
	public JSONObject processBo(JSONObject serviceInput) {
		JSONObject jsonOutput = new JSONObject();

		try {

			List<UserEntity> userData = userDao.getAll();

			jsonOutput.put("serviceOutput", userData);

		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = e.getCause().toString();
			errorMessage = errorMessage.substring(
					(errorMessage.indexOf(':') + 1), errorMessage.length());
			errorMessage = errorMessage.trim();
			jsonOutput = new JSONObject();
			jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
			jsonOutput.put("errorMessage", errorMessage);
		}
		return jsonOutput;

	}

}
