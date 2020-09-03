package com.agus.java.resource.roleManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.controller.entitySwagger.roleManagement.EditRoleEntity;
import com.agus.java.model.roleManagement.Role;
import com.agus.java.model.roleManagement.RoleDao;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.DateUtil;
import com.agus.java.resource.core.ValidationUtil;

@Service
public class EditRoleManagementDetail implements DataProcess {

	@Autowired
	GetRoleById getRoleById;

	@Autowired
	RoleDao roleDao;

	@Override
	@SuppressWarnings("unchecked")
	public JSONObject processBo(JSONObject serviceInput) {

		// Validation
		Field[] fields = EditRoleEntity.class.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for (Field field : fields) {

			field.setAccessible(true);
			validationFieldList.add(field.getName());
		}
		ValidationUtil.checkForUnsupportedParameters(serviceInput,
				validationFieldList);
		ValidationUtil.valBlankOrNullList(serviceInput, validationFieldList);

		String scope = String.valueOf(serviceInput.get("scope"));
		Long userId = Long.valueOf(String.valueOf(serviceInput.get("userId")));
		Long roleId = Long.valueOf(String.valueOf(serviceInput.get("roleId")));
		String datetime = DateUtil.getDatetimeNow();
		String newRoleName = String.valueOf(serviceInput.get("roleName"));

		JSONObject jsonOutput = new JSONObject();
		JSONObject resultOutput = new JSONObject();

		try {
			JSONObject requestJSON = new JSONObject();
			requestJSON.put("userId", userId);
			requestJSON.put("parameter", roleId.toString());

			resultOutput = getRoleById.processBo(requestJSON);
			if (resultOutput.get("serviceOutput") != null){
				// 1. Update m_role
				StringBuilder sb = new StringBuilder();
				sb.append("UPDATE ")
						.append(Role.ENTITY_NAME)
						.append(" SET roleName = :roleName, scope = :scope,  updateDatetime = :updateDatetime, updateUserId = :updateUserId ")
						.append(" WHERE id = :roleId ");

				Query query = roleDao.createQuery(sb.toString());
				query.setParameter("roleId", roleId);
				query.setParameter("roleName", newRoleName);
				query.setParameter("scope", scope);
				query.setParameter("updateUserId", userId);
				query.setParameter("updateDatetime", datetime);

				query.executeUpdate();
				roleDao.closeSessionCreateQuery();
				jsonOutput.put("status", MasterGeneralConstants.STATUS_SUCCESS);
			}
			
			else {
				jsonOutput = new JSONObject();
				jsonOutput.put("status",
						MasterGeneralConstants.STATUS_FAILED);
				jsonOutput.put("errorMessage", "Role tidak ditemukan");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			String errorMessage = e.getCause().toString();
			errorMessage = errorMessage.substring(
					(errorMessage.indexOf(':') + 1), errorMessage.length());
			errorMessage = errorMessage.trim();

			jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
			jsonOutput.put("errorMessage", errorMessage);
		}

		return jsonOutput;
	}

}