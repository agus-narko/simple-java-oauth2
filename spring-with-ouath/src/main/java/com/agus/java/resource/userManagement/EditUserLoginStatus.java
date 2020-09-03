package com.agus.java.resource.userManagement;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.model.userManagement.UserDao;
import com.agus.java.model.userManagement.UserEntity;
import com.agus.java.resource.core.ValidationUtil;

@Service
public class EditUserLoginStatus {

	@Autowired
	UserDao userDao;

	private Session session;

	@SuppressWarnings("unchecked")
	public JSONObject processBo(JSONObject serviceInput) {

		// Validation
		List<String> validationFieldList = new ArrayList<String>();
		validationFieldList.add("randomString");
		validationFieldList.add("username");
		ValidationUtil.checkForUnsupportedParameters(serviceInput,
				validationFieldList);
		ValidationUtil.valBlankOrNullList(serviceInput, validationFieldList);

		// prepare data
		String username = String.valueOf(serviceInput.get("username"));
		String randomString = (String) serviceInput.get("randomString");

		String errorMessage = "";
		JSONObject jsonOutput = new JSONObject();

		try {
			Query query = userDao
					.createQuery("UPDATE "
							+ UserEntity.ENTITY_NAME
							+ " e SET e.statusLogin = :randomString WHERE e.username = :username");
			query.setParameter("username", username);
			query.setParameter("randomString", randomString);
			query.executeUpdate();

			userDao.closeSessionCreateQuery();

			jsonOutput.put("serviceOutput", "OK");
			jsonOutput.put("status", "S");

		} catch (Exception e) {
			e.printStackTrace();

			errorMessage = e.getCause().toString();
			errorMessage = errorMessage.substring(
					(errorMessage.indexOf(':') + 1), errorMessage.length());
			errorMessage = errorMessage.trim();

			jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
			jsonOutput.put("errorMessage", errorMessage);
		}
		return jsonOutput;
	}

}
