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
import com.agus.java.controller.entitySwagger.InquiryEntity;
import com.agus.java.model.userManagement.UserDao;
import com.agus.java.model.userManagement.UserEntity;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.ValidationUtil;



@Service
public class GetUserByActive implements DataProcess{

	@Autowired
	UserDao userDao;

	@Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject processBo(JSONObject serviceInput) {

	    //Validation
		InquiryEntity inputUser = new InquiryEntity();
		Field[] fields = InquiryEntity.class.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for(Field field : fields) {                           

		    field.setAccessible(true);                       
		    validationFieldList.add(field.getName());
		}
        ValidationUtil.checkForUnsupportedParameters(serviceInput,validationFieldList);
        ValidationUtil.valBlankOrNullList(serviceInput,validationFieldList);

		// prepare data
		String activeCode = (String) serviceInput.get("parameter");

		List<Object[]> result = null;
        List<JSONObject> outputBo = new ArrayList<JSONObject>();
		JSONObject jsonOutput = new JSONObject();

		try {
			Query query = userDao.createQuery("SELECT e.id AS user_id, e.username AS username FROM "
					+ UserEntity.ENTITY_NAME
					+ " e WHERE e.active = :active");
			query.setParameter("active", activeCode);
			result = query.list();

			Iterator iter = result.iterator();
			while(iter.hasNext()){
				Object[] obj = (Object[]) iter.next();
				JSONObject tempJO = new JSONObject();
				Long id = null != obj[0] ? Long.valueOf(obj[0].toString())
						: null;
				String username = null != obj[1] ? obj[1].toString()
						: null;
				tempJO.put("userId", id);
				tempJO.put("username", username);
				outputBo.add(tempJO);
			}

			jsonOutput.put("serviceOutput", outputBo);
			jsonOutput.put("status", MasterGeneralConstants.STATUS_SUCCESS);
			

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
