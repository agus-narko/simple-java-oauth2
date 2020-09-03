package com.agus.java.resource.userManagement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.agus.java.controller.entitySwagger.RefreshTokenEntity;
import com.agus.java.model.userManagement.UserDao;
import com.agus.java.model.userManagement.UserEntity;
import com.agus.java.resource.core.DataProcess;
import com.agus.java.resource.core.ValidationUtil;

@Service
public class RefreshToken implements DataProcess {

	@Autowired
	GetUserById getUserById;
	
	@Autowired
	UserDao userDao;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject processBo(JSONObject serviceInput) {

		// Validation
		Field[] fields = RefreshTokenEntity.class.getDeclaredFields();
		List<String> validationFieldList = new ArrayList<String>();

		for (Field field : fields) {

			field.setAccessible(true);
			validationFieldList.add(field.getName());
		}
		ValidationUtil.checkForUnsupportedParameters(serviceInput,
				validationFieldList);
		ValidationUtil.valBlankOrNullList(serviceInput, validationFieldList);

		// prepare data
		String refreshToken = String.valueOf(serviceInput.get("refreshToken"));
		Long userId = Long.valueOf(String.valueOf(serviceInput.get("userId")));

		JSONObject resultOutput = new JSONObject();
		String errorMessage = new String();
		JSONObject jsonOutput = null;

		try {

			
			UserEntity getUser = userDao.find(userId);

		
			if (!getUser.getUsername().isEmpty()) {


				String usernameResult = getUser.getUsername();
				String passwordResult = getUser.getPassword();
				String refreshTokenResult = getUser.getStatusLogin();
				
				if (refreshTokenResult.equals(refreshToken)) {
					Map<?, ?> map = getAccessToken(usernameResult,
							passwordResult, refreshToken);
					jsonOutput = new JSONObject(map);
					jsonOutput.put("ID", userId);
					jsonOutput.put("status",
							MasterGeneralConstants.STATUS_SUCCESS);
					return jsonOutput;
				}

				else {
					jsonOutput = new JSONObject();
					jsonOutput.put("status",
							MasterGeneralConstants.STATUS_FAILED);
					jsonOutput.put("errorMessage", "invalid refresh token");
					return jsonOutput;
				}
			}

			else {
				jsonOutput = new JSONObject();
				jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
				jsonOutput.put("errorMessage", "user tidak di temukan");
				return jsonOutput;
			}

		} catch (Exception e) {
			jsonOutput = new JSONObject();
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

	private Map<?, ?> getAccessToken(String username, String password,
			String token) throws Exception {

		// Hit Auth2 Server
		RestTemplate restTemplate = new RestTemplate();

		// client credential
		String clientCredentials = username + ":" + password;
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
		String grantRefreshToken = "?grant_type=refresh_token&refresh_token="
				+ token;

		response = restTemplate.exchange(url + grantRefreshToken,
				HttpMethod.POST, request, String.class);

		Map<?, ?> mapResponse = new ObjectMapper().readValue(
				response.getBody(), Map.class);

		return mapResponse;
	}

}
