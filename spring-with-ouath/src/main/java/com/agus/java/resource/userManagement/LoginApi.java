package com.agus.java.resource.userManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.controller.entitySwagger.LoginApiEntity;
import com.agus.java.model.userManagement.LoginUser;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.Md5Util;
import com.agus.java.resource.core.ValidationUtil;



@Service
public class LoginApi implements DataProcess {

	@Autowired
	GetUserbyUsername login;

	@Autowired
	EditUserLoginStatus editUserLoginStatus;

	@Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject processBo(JSONObject serviceInput) {

		 //Validation
		LoginApiEntity inputUser = new LoginApiEntity();
		Field[] fields = LoginApiEntity.class.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for(Field field : fields) {                           

		    field.setAccessible(true);                       
		    validationFieldList.add(field.getName());
		}
        ValidationUtil.checkForUnsupportedParameters(serviceInput,validationFieldList);
        ValidationUtil.valBlankOrNullList(serviceInput,validationFieldList);


		// prepare data
		String username = String.valueOf(serviceInput.get("username"));
		String password = String.valueOf(serviceInput.get("password"));


		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject resultOutput = new JSONObject();

		JSONObject roleTaskJson = new JSONObject();

		String errorMessage = new String();
		String usernameResult = new String();
		String passwordResult = new String();
		String randomString = new String();
		Long ID;
		JSONObject jsonOutput = null;
		JSONObject jsonInput = new JSONObject();

		// output var
		boolean status;
		LoginUser loginUser = null;
		try {

			loginUser = login.getUserDetails(username);
			usernameResult = loginUser.getUsername();
			passwordResult = loginUser.getPassword();
			randomString = loginUser.getRandomString();
			if (username.equals(usernameResult)
					&& Md5Util.getMD5(password).equals(passwordResult)) {
				if (randomString.equals(MasterGeneralConstants.NO)
						|| randomString.isEmpty()) {
					// get token
					Map<?, ?> map = getAccessToken(username, Md5Util.getMD5(password));
					jsonOutput = new JSONObject(map);
					randomString = jsonOutput.get("refresh_token").toString();
					
					//upadte status login
					jsonInput.put("randomString", randomString);
					jsonInput.put("username", username);
					editUserLoginStatus.processBo(jsonInput);
					jsonOutput.put("ID",loginUser.getID());
					jsonOutput.put("status",
							MasterGeneralConstants.STATUS_SUCCESS);
					return jsonOutput;
				} else {
					jsonOutput = new JSONObject();
					jsonOutput.put("ID",loginUser.getID());
					jsonOutput.put("status",
							MasterGeneralConstants.STATUS_FAILED);
					 jsonOutput.put("randomString", randomString);
					jsonOutput.put("errorMessage", "username sudah login");
					return jsonOutput;
				}
			}

			else {
				jsonOutput = new JSONObject();
				jsonOutput.put("ID",loginUser.getID());
				jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
				jsonOutput.put("errorMessage", "username atau password salah");

				return jsonOutput;
			}

		} catch (Exception e) {
			e.printStackTrace();

			errorMessage = e.getCause().toString();
			errorMessage = errorMessage.substring(
					(errorMessage.indexOf(':') + 1), errorMessage.length());
			errorMessage = errorMessage.trim();
			jsonOutput = new JSONObject();
			jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
			jsonOutput.put("errorMessage", errorMessage);
			return jsonOutput;
		}

	}

	private Map<?, ?> getAccessToken(String username, String password)
			throws Exception {

		// Hit Auth2 Server
		RestTemplate restTemplate = new RestTemplate();

		// client credential
		String clientCredentials = username+":"+password;
		String encodedCredentials = new String(
				Base64.encodeBase64(clientCredentials.getBytes()));

		// header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);

		// request
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<String> response = null;

		String url = "http://localhost:8881/oauth/token";
		String grantPassword = "?grant_type=password&username=" + username
				+ "&password=" + password;

		response = restTemplate.exchange(url + grantPassword, HttpMethod.POST,
				request, String.class);

		Map<?, ?> mapResponse = new ObjectMapper().readValue(
				response.getBody(), Map.class);

		return mapResponse;
	}

}

