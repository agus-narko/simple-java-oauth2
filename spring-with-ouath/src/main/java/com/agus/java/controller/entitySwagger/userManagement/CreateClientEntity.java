package com.agus.java.controller.entitySwagger.userManagement;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import javax.persistence.Column;

public class CreateClientEntity {

	@Schema(required = true)
	String clientId;

	@Schema(required = true)
	String clientSecret;

	@Schema(required = true)
	String scope;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
}
