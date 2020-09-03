package com.agus.java.resource.roleManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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
public class ActivationRoleById implements DataProcess {

	@Autowired
	RoleDao roleDao;

	@Autowired
	UserDao userDao;

	@Autowired
	GetRoleById getRoleById;

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

		Long roleId = Long.valueOf(String.valueOf(serviceInput.get("updateId")));
		String status = String.valueOf(serviceInput.get("status"));
		Long userId = Long.valueOf(String.valueOf(serviceInput.get("userId")));
		String datetime = DateUtil.getDatetimeNow();

		JSONObject jsonOutput = new JSONObject();
		try {

			JSONObject requestJSON = new JSONObject();
			JSONObject resultOutput = new JSONObject();
			requestJSON.put("userId", userId);
			requestJSON.put("parameter", roleId.toString());

			resultOutput = getRoleById.processBo(requestJSON);

			if (resultOutput.get("serviceOutput") != null) {

				Query checkData = userDao
						.createQuery("Select count(1) from "
								+ UserEntity.ENTITY_NAME
								+ " where userRole = :roleId and statusLogin != :login " );
				checkData.setParameter("roleId", roleId.toString());
				checkData.setParameter("login", MasterGeneralConstants.NO);
				Long result = (Long) checkData.uniqueResult();
				userDao.closeSessionCreateQuery();
				
				if (result < 1) {

					StringBuilder deleteRole = new StringBuilder();
					deleteRole.append(" UPDATE ").append(Role.ENTITY_NAME)
							.append(" SET active = :active  ")
							.append(" ,updateDatetime = :updateDatetime  ")
							.append(" ,updateUserId = :updateUserId  ")
							.append(" WHERE id = :roleId ");
					Query query = roleDao.createQuery(deleteRole.toString());
					query.setParameter("roleId", roleId);
					query.setParameter("updateDatetime", datetime);
					query.setParameter("updateUserId", userId);
					query.setParameter("active", status);
					query.executeUpdate();
					roleDao.closeSessionCreateQuery();
					
					
					 StringBuilder deleteUser = new StringBuilder();
					 deleteUser.append(" UPDATE ").append(UserEntity.ENTITY_NAME)
					 .append(" SET active = :active  ")
					 .append(" ,updateDatetime = :updateDatetime  ")
					 .append(" ,updateUserId = :updateUserId  ")
					 .append(" WHERE userRole = :roleId ");
					 Query query1 = userDao.createQuery(deleteUser.toString());
					 query1.setParameter("roleId", roleId.toString());
					 query1.setParameter("updateDatetime", datetime);
					 query1.setParameter("updateUserId", userId);
					 query1.setParameter("active", status);
					 query1.executeUpdate();
					 userDao.closeSessionCreateQuery();

				} else {
					jsonOutput.put("status",
							MasterGeneralConstants.STATUS_FAILED);
					jsonOutput.put("errorMessage", "user masih aktif");

				}
			} else {
				jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
				jsonOutput.put("errorMessage", "role tidak di temukan");
			}

			

			jsonOutput.put("status", MasterGeneralConstants.STATUS_SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = e.getCause().toString();
			errorMessage = errorMessage.substring(
					(errorMessage.indexOf(':') + 1), errorMessage.length());
			errorMessage = errorMessage.trim();

			jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
			jsonOutput.put("errorMessage", errorMessage);
			;
		}
		return jsonOutput;
	}

}
