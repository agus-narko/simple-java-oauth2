package com.agus.java.resource.userManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.controller.entitySwagger.userManagement.ChangePasswordEntity;
import com.agus.java.model.userManagement.UserDao;
import com.agus.java.model.userManagement.UserEntity;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.DateUtil;
import com.agus.java.resource.core.Md5Util;
import com.agus.java.resource.core.ValidationUtil;


@Service
public class ChangePassword implements DataProcess {

	@Autowired
	UserDao userDao;

	@Autowired
	EditClient editClient;

	@Autowired
	Logout logout;

	@Override
	public JSONObject processBo(JSONObject serviceInput) {

		Field[] fields = ChangePasswordEntity.class.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for(Field field : fields) {                           

		    field.setAccessible(true);                       
		    validationFieldList.add(field.getName());
		}
		ValidationUtil.checkForUnsupportedParameters(serviceInput,
				validationFieldList);
		ValidationUtil.valBlankOrNullList(serviceInput, validationFieldList);

		List<Object[]> result = null;

		Long userId = Long.valueOf(serviceInput.get("userId").toString());
		String prevPassword = serviceInput.get("prevPassword").toString();
		String newPassword = serviceInput.get("newPassword").toString();

		JSONObject outputJson = new JSONObject();
		JSONObject jsonInput = new JSONObject();
		try {
			// Validasi apakah ada user dengan informasi tersebut
			StringBuilder findUserString = new StringBuilder();
			findUserString.append(" SELECT username, password ")
					.append(" FROM ").append(UserEntity.TABLE_NAME)
					.append(" A ").append(" WHERE user_id = :userId ");

			Query findUserQuery = userDao.createSQLQuery(findUserString
					.toString());
			findUserQuery.setParameter("userId", userId);
			result = findUserQuery.list();

			userDao.closeSessionCreateQuery();

			String username = MasterGeneralConstants.EMPTY_VALUE;
			String password = MasterGeneralConstants.EMPTY_VALUE;

			Iterator iter = result.iterator();
			while (iter.hasNext()) {
				Object[] obj = (Object[]) iter.next();

				username = obj[0].toString();
				password = obj[1].toString();

			}

			if (null == username
					|| username.equals(MasterGeneralConstants.EMPTY_VALUE)) {
				throw new Exception(" Username not Found ");
			}

			
			if (!Md5Util.getMD5(prevPassword).equals(password)) {
				outputJson.put("status", MasterGeneralConstants.STATUS_FAILED);
				outputJson.put("message", "invalid current password");
				return outputJson;
			} else if (password.equals(Md5Util.getMD5(newPassword))) {
				outputJson.put("status", MasterGeneralConstants.STATUS_FAILED);
				outputJson.put("message", "samePassword");
				return outputJson;
			} else {

				// logout untuk clear token
				logout.processBo(null);

				
				jsonInput.put("clientId", username);
				jsonInput.put("newClientId", username);
				jsonInput.put("newClientSecret", Md5Util.getMD5(newPassword));
				

				// upadte password m_user
				outputJson = updateData(serviceInput);
				// upadate client secret
				editClient.processBo(jsonInput);
				return outputJson;

			}

		} catch (Exception e) {
			e.printStackTrace();

			String errorMessage = e.getCause().toString();
			errorMessage = errorMessage.substring(
					(errorMessage.indexOf(':') + 1), errorMessage.length());
			errorMessage = errorMessage.trim();

			outputJson.put("status", MasterGeneralConstants.STATUS_FAILED);
			outputJson.put("errorMessage", errorMessage);
			return outputJson;
		}

	}

	public JSONObject updateData(JSONObject serviceInput) throws Exception {

		Long userId = Long.valueOf(serviceInput.get("userId").toString());
		String prevPassword = serviceInput.get("prevPassword").toString();
		String newPassword = serviceInput.get("newPassword").toString();
		
		JSONObject outputJson = new JSONObject();
		try {
			Query updatePasswordQuery = userDao
					.createQuery(" UPDATE "
							+ UserEntity.ENTITY_NAME
							+ " SET password = :newPassword, updateDatetime = :datetime, updateUserId = :userId "
							+ " WHERE id = :userId ");
			updatePasswordQuery.setParameter("newPassword",
					Md5Util.getMD5(newPassword));
			updatePasswordQuery.setParameter("datetime",
					DateUtil.getDatetimeNow());
			updatePasswordQuery.setParameter("userId", userId);
			updatePasswordQuery.executeUpdate();

			userDao.closeSessionCreateQuery();

			outputJson.put("status", MasterGeneralConstants.STATUS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();

			String errorMessage = e.getCause().toString();
			errorMessage = errorMessage.substring(
					(errorMessage.indexOf(':') + 1), errorMessage.length());
			errorMessage = errorMessage.trim();

			outputJson.put("status", MasterGeneralConstants.STATUS_FAILED);
			outputJson.put("errorMessage", errorMessage);
		}

		return outputJson;
	}

}
