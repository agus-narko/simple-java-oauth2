package com.agus.java.resource.userManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.controller.entitySwagger.userManagement.CreateUserEntity;
import com.agus.java.model.roleManagement.Role;
import com.agus.java.model.userManagement.UserDao;
import com.agus.java.model.userManagement.UserEntity;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.DateUtil;
import com.agus.java.resource.core.Md5Util;
import com.agus.java.resource.core.ValidationUtil;
import com.agus.java.resource.roleManagement.GetRoleById;



@Service
public class CreateUser implements DataProcess {

	@Autowired
	UserDao userDao;

	@Autowired
	CreateClient createClient;

	@Autowired
	GetRoleById inquiryRoleManagementDetail;

	@Override
	@SuppressWarnings("unchecked")
	public JSONObject processBo(JSONObject serviceInput) {

		// Validation

		Field[] fields = CreateUserEntity.class.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for(Field field : fields) {                           

		    field.setAccessible(true);                       
		    validationFieldList.add(field.getName());
		}
		
		ValidationUtil.checkForUnsupportedParameters(serviceInput,
				validationFieldList);
		ValidationUtil.valBlankOrNullList(serviceInput, validationFieldList);

		// prepare data
		UserEntity userAddData = new UserEntity();
		String username = String.valueOf(serviceInput.get("username"));
		String password = String.valueOf(serviceInput.get("password"));
		String email = String.valueOf(serviceInput.get("email"));
		String phone = String.valueOf(serviceInput.get("phone"));
		String address = String.valueOf(serviceInput.get("address"));
		Long roleIdAdd = Long
				.valueOf(String.valueOf(serviceInput.get("roleId")));
		Long createUserId = Long.valueOf(String.valueOf(serviceInput
				.get("userId")));
		Long updateUserId = Long.valueOf(String.valueOf(serviceInput
				.get("userId")));
		String userFullname = String.valueOf(serviceInput.get("userFullname"));
		String datetime = DateUtil.getDatetimeNow();
	
		String errorMessage;
		
		JSONObject jsonOutput = new JSONObject();
		JSONObject roleOutput = new JSONObject();
		JSONObject roleInput = new JSONObject();
		JSONObject jsonInput = new JSONObject();
		JSONObject serviceOutput = new JSONObject();

		try {
			Query checkData = userDao.createQuery("Select count(1) from "
					+ UserEntity.ENTITY_NAME
					+ " where username = :username AND active = :active");
			checkData.setParameter("username", username);
			checkData.setParameter("active", MasterGeneralConstants.YES);
			Long result = (Long) checkData.uniqueResult();

			userDao.closeSessionCreateQuery();
			if (result < 1) {
				roleInput.put("userId", createUserId);
				roleInput.put("parameter", roleIdAdd);
				roleOutput = inquiryRoleManagementDetail.processBo(roleInput);
				
				if(!roleOutput.isEmpty()){
					// ada client
					Role role = (Role) roleOutput
							.get("serviceOutput");
					jsonInput.put("clientId", username);
					jsonInput.put("scope", role.getScope());
					jsonInput.put("clientSecret", Md5Util.getMD5(password));
					
					createClient.processBo(jsonInput);

					// add m_user
					// mapping data
					userAddData.setId(null);
					userAddData.setUsername(username);
					userAddData.setPassword(Md5Util.getMD5(password));
					userAddData.setEmail(email);
					userAddData.setUserFullname(userFullname);
					userAddData.setPhone(phone);
					userAddData.setAddress(address);
					userAddData.setActive(MasterGeneralConstants.YES);
					userAddData.setUserRole(String.valueOf(roleIdAdd));
					userAddData.setCreateUserId(createUserId);
					userAddData.setCreateDatetime(datetime);
					userAddData.setUpdateUserId(updateUserId);
					userAddData.setUpdateDatetime(datetime);
					userAddData.setStatusLogin(MasterGeneralConstants.NO);

					// insert ke dalam table
					userDao.add(userAddData);

					serviceOutput.put("userAddData", userAddData);
					jsonOutput.put("status", MasterGeneralConstants.STATUS_SUCCESS);
					// jsonOutput.put("serviceOutput", "OK");
				}
				else {
					jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
					jsonOutput.put("errorMessage", "role tidak ditemukan");
				}

				
			} else {
				jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
				jsonOutput.put("errorMessage", "username sudah dipakai");
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
