package com.agus.java.resource.userManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.controller.entitySwagger.userManagement.EditUserManagementDetailEntity;
import com.agus.java.model.userManagement.UserDao;
import com.agus.java.model.userManagement.UserEntity;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.DateUtil;
import com.agus.java.resource.core.ValidationUtil;

@Service
public class EditUserManagementDetail implements DataProcess {

	@Autowired
	UserDao userDao;

	@Autowired
	EditClient editClient;

	@Autowired
	GetUserById getUserById;

	@Autowired
	Logout logout;

	@Override
	@SuppressWarnings("unchecked")
	public JSONObject processBo(JSONObject serviceInput) {


		// Validation
		Field[] fields = EditUserManagementDetailEntity.class
				.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for (Field field : fields) {

			field.setAccessible(true);
			validationFieldList.add(field.getName());
		}
		ValidationUtil.checkForUnsupportedParameters(serviceInput,
				validationFieldList);
		ValidationUtil.valBlankOrNullList(serviceInput, validationFieldList);

		// prepare data
		String username = String.valueOf(serviceInput.get("username"));
		String email = String.valueOf(serviceInput.get("email"));
		String phone = String.valueOf(serviceInput.get("phone"));
		String address = String.valueOf(serviceInput.get("address"));
		Long roleId = Long.valueOf(String.valueOf(serviceInput.get("roleId")));
		Long userId = Long.valueOf(String.valueOf(serviceInput.get("userId")));
		String userFullname = String.valueOf(serviceInput.get("userFullname"));
		Long updateId = Long.valueOf(String.valueOf(serviceInput.get("updateId")));
		
		String datetime = DateUtil.getDatetimeNow();
		String errorMessage = "";
		JSONObject jsonOutput = new JSONObject();
		JSONObject jsonInput = new JSONObject();
		JSONObject resultOutput = new JSONObject();
		UserEntity user = new UserEntity();
		
		try {
			UserEntity getUser = userDao.find(updateId);
			
			if (!getUser.getUsername().isEmpty()) {
			
				// update data client
				if (!getUser.getUsername().equals(username) && !username.isEmpty() ) {
					if (updateId == userId) {
						// logout untuk clear token
						logout.processBo(null);
					}
					jsonInput.put("clientId", getUser.getUsername());
					jsonInput.put("newClientId", username);
					jsonInput.put("newClientSecret", getUser.getPassword());
					editClient.processBo(jsonInput);
				}

				// update data user (m_user)
				StringBuilder sb = new StringBuilder();
				sb.append("UPDATE ")
						.append(UserEntity.ENTITY_NAME)
						.append(" SET username = :userName, "
								+ "email = :email, "
								+ "phone = :phone, "
								+ "address = :address, "
								+ "userRole = :userRole, "
								+ "updateDatetime = :updateDatetime, "
								+ "updateUserId = :updateUserId, "
								+ "userFullname = :userFullname, "
								+ "statusLogin =:statusLogin ")
						.append(" WHERE id = :userId ");

				Query query = userDao.createQuery(sb.toString());
				query.setParameter("userId", updateId);
				query.setParameter("userName", username);
				query.setParameter("email", email);
				query.setParameter("phone", phone);
				query.setParameter("address", address);
				query.setParameter("userRole", roleId.toString());
				query.setParameter("updateUserId", userId);
				query.setParameter("updateDatetime", datetime);
				query.setParameter("userFullname", userFullname);
				query.setParameter("statusLogin", MasterGeneralConstants.NO);

				query.executeUpdate();
				userDao.closeSessionCreateQuery();

				jsonOutput.put("status", MasterGeneralConstants.STATUS_SUCCESS);
			} else {
				jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
				jsonOutput.put("errorMessage", "username tidak di temukan");
			}

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