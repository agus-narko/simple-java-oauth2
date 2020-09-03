package com.agus.java.resource.roleManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.controller.entitySwagger.roleManagement.CreateRoleEntity;
import com.agus.java.model.roleManagement.Role;
import com.agus.java.model.roleManagement.RoleDao;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.DateUtil;
import com.agus.java.resource.core.ValidationUtil;



@Service
public class CreateRole implements DataProcess {

	@Autowired
	RoleDao roleDao;

	
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject processBo(JSONObject serviceInput){

       	//Validation
    	Field[] fields = CreateRoleEntity.class.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for(Field field : fields) {                           

		    field.setAccessible(true);                       
		    validationFieldList.add(field.getName());
		}
        ValidationUtil.checkForUnsupportedParameters(serviceInput,validationFieldList);
        ValidationUtil.valBlankOrNullList(serviceInput,validationFieldList);

        //prepare data
        Role roleAddData = new Role();

    	Long roleIdAdd = new Long(0);
    	String scope = String.valueOf(serviceInput.get("scope"));
    	Long userId = Long.valueOf(String.valueOf(serviceInput.get("userId")));
    	String datetime = DateUtil.getDatetimeNow();
    	String newRoleName = String.valueOf(serviceInput.get("roleName"));

        JSONObject jsonOutput = new JSONObject();

    	try{

    
    		//additional data
    		Query checkData = roleDao
					.createQuery("Select count(1) from "+Role.ENTITY_NAME
							+ " where roleName = :roleName");
	    	checkData.setParameter("roleName", newRoleName);
			Long result = (Long) checkData.uniqueResult();

			roleDao.closeSessionCreateQuery();
			if(result<1){
	    		//mapping data
	    		roleAddData.setId(null);
	    		roleAddData.setRoleName(newRoleName);
	    		roleAddData.setScope(scope);
	    		roleAddData.setActive(MasterGeneralConstants.YES);
	    		roleAddData.setCreateUserId(userId);
	    		roleAddData.setCreateDatetime(datetime);
	    		roleAddData.setUpdateUserId(userId);
	    		roleAddData.setUpdateDatetime(datetime);

	    		//insert ke m_role
	    		roleDao.add(roleAddData);


	            jsonOutput.put("status", MasterGeneralConstants.STATUS_SUCCESS);
			}
			else{
	            jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
				jsonOutput.put("errorMessage", "role sudah ada");

			}

	} catch (Exception e) {
		e.printStackTrace();

		String errorMessage = e.getCause().toString();
		errorMessage=errorMessage.substring((errorMessage.indexOf(':')+1),errorMessage.length());
		errorMessage = errorMessage.trim();

		jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
		jsonOutput.put("errorMessage", errorMessage);
	}
	return jsonOutput;

    }

}
