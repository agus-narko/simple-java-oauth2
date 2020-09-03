package com.agus.java.resource.userManagement;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.resource.core.DataProcess;

import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Service;



@Service
public class Logout implements DataProcess{


	@Autowired
	EditUserLoginStatus editUserLoginStatus;

	@Autowired
	DataSource ds;

	@Bean
	public TokenStore tokenStore2() {
		return new JdbcTokenStore(ds);
	}

	@SuppressWarnings("unchecked")
	public JSONObject processBo(JSONObject serviceInput) {

		String errorMessage = "";
		JSONObject jsonOutput = new JSONObject();
		JSONObject jsonInput = new JSONObject();
		

		try {
			//get the current authentication
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			OAuth2Authentication oAuth2 = ((OAuth2Authentication) authentication);

			String username = oAuth2.getName();

			//remove access token
			OAuth2AccessToken accessToken = tokenStore2().getAccessToken(oAuth2);
			tokenStore2().removeAccessToken(accessToken);


			//remove refresh token
			OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
			tokenStore2().removeRefreshToken(refreshToken);

			//update status login
			jsonInput.put("username", username);
			jsonInput.put("randomString", MasterGeneralConstants.NO);
			editUserLoginStatus.processBo(jsonInput);

			jsonOutput.put("serviceOutput", username);
			jsonOutput.put("status", MasterGeneralConstants.STATUS_SUCCESS);
			return jsonOutput;

		} catch (Exception e) {
			e.printStackTrace();

			errorMessage = e.getCause().toString();
			errorMessage=errorMessage.substring((errorMessage.indexOf(':')+1),errorMessage.length());
			errorMessage = errorMessage.trim();

			jsonOutput.put("status", MasterGeneralConstants.STATUS_FAILED);
			jsonOutput.put("errorMessage", errorMessage);
			return jsonOutput;
		}

	}

}
