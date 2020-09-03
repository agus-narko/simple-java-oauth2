package com.agus.java.resource.roleManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
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
public class GetRoleByActive implements DataProcess {

	@Autowired
	RoleDao roleDao;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject processBo(JSONObject serviceInput) {

		// Validation
		InquiryEntity inputUser = new InquiryEntity();
		Field[] fields = InquiryEntity.class.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for (Field field : fields) {

			field.setAccessible(true);
			validationFieldList.add(field.getName());
		}
		ValidationUtil.checkForUnsupportedParameters(serviceInput,
				validationFieldList);
		ValidationUtil.valBlankOrNullList(serviceInput, validationFieldList);

		// prepare data
		String activeCode = (String) serviceInput.get("parameter");

		JSONObject jsonOutput = new JSONObject();
		List<Object[]> result = null;
		List<JSONObject> outputBo = new ArrayList<JSONObject>();

		try {
			Query query = roleDao
					.createQuery("SELECT e.id, e.roleName, e.scope FROM "
							+ Role.ENTITY_NAME + " e WHERE e.active = :active");
			query.setParameter("active", activeCode);
			result = query.list();

			Iterator iter = result.iterator();
			while (iter.hasNext()) {
				Object[] obj = (Object[]) iter.next();
				JSONObject tempJO = new JSONObject();
				Long id = null != obj[0] ? Long.valueOf(obj[0].toString())
						: null;
				String roleName = null != obj[1] ? obj[1].toString() : null;
				String scope = null != obj[2] ? obj[2].toString() : null;
				tempJO.put("roleId", id);
				tempJO.put("roleName", roleName);
				tempJO.put("scope", scope);
				outputBo.add(tempJO);
			}
			jsonOutput.put("status", MasterGeneralConstants.STATUS_SUCCESS);
			jsonOutput.put("serviceOutput", outputBo);

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
