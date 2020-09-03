package com.agus.java.resource.roleManagement;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.model.roleManagement.Role;
import com.agus.java.model.roleManagement.RoleDao;
import com.agus.java.resource.core.DataProcess;



@Service
public class getRole implements DataProcess {

	@Autowired
	RoleDao roleDao;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject processBo(JSONObject serviceInput) {
		JSONObject jsonOutput = new JSONObject();
		
		try {
			List<Role> roleData = roleDao.getAll();
			jsonOutput.put("status", MasterGeneralConstants.STATUS_SUCCESS);
			jsonOutput.put("serviceOutput", roleData);

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
