package com.agus.java.resource.userManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.controller.entitySwagger.ActivationEntity;
import com.agus.java.model.roleManagement.Role;
import com.agus.java.model.roleManagement.RoleDao;
import com.agus.java.model.userManagement.UserDao;
import com.agus.java.model.userManagement.UserEntity;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.DateUtil;
import com.agus.java.resource.core.ValidationUtil;

@Service
public class ActivationUserById implements DataProcess {

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	GetUserById getUserById;

	private Session session;

	@Override
	@SuppressWarnings("unchecked")
	public JSONObject processBo(JSONObject serviceInput) {

		// Validation
		Field[] fields = ActivationEntity.class.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for (Field field : fields) {

			field.setAccessible(true);
			validationFieldList.add(field.getName());
		}
		ValidationUtil.checkForUnsupportedParameters(serviceInput,
				validationFieldList);
		ValidationUtil.valBlankOrNullList(serviceInput, validationFieldList);

		// prepare data
		Long updateId = Long.valueOf(String.valueOf(serviceInput
				.get("updateId")));
		Long userId = Long.valueOf(String.valueOf(serviceInput.get("userId")));
		String status = String.valueOf(serviceInput.get("status"));
		String datetime = DateUtil.getDatetimeNow();

		UserEntity userData = new UserEntity();

		String errorMessage = "";
		JSONObject jsonOutput = new JSONObject();
		JSONObject resultOutput = new JSONObject();

		try {

			if (userId != updateId) {
				UserEntity getUser = userDao.find(updateId);
				Role getRole = roleDao.find(Long.parseLong(getUser
						.getUserRole()));

				if (getRole.getActive().equals(MasterGeneralConstants.YES)) {

					if (!getUser.getUsername().isEmpty()) {
						if (getUser.getStatusLogin().equals(
								MasterGeneralConstants.NO)) {
							Query query = userDao.createQuery("UPDATE "
									+ UserEntity.ENTITY_NAME
									+ " SET active = :active "
									+ " ,updateUserId = :updateUserId"
									+ " ,updateDatetime = :updateDatetime"
									+ " WHERE id = :userId");
							query.setParameter("userId", updateId);
							query.setParameter("updateUserId", userId);
							query.setParameter("updateDatetime", datetime);
							query.setParameter("active", status);
							query.executeUpdate();

							userDao.closeSessionCreateQuery();
							jsonOutput.put("status",
									MasterGeneralConstants.STATUS_SUCCESS);
						} else {
							jsonOutput.put("status",
									MasterGeneralConstants.STATUS_FAILED);
							jsonOutput.put("errorMessage", "user sedang aktif");
						}

					} else {
						jsonOutput.put("status",
								MasterGeneralConstants.STATUS_FAILED);
						jsonOutput.put("errorMessage", "user tidak di temukan");
					}

				} else {
					jsonOutput.put("status",
							MasterGeneralConstants.STATUS_FAILED);
					jsonOutput.put("errorMessage", "Role tidak aktif");
				}

			}

			else {
				jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
				jsonOutput.put("errorMessage", "user sedang aktif");
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
