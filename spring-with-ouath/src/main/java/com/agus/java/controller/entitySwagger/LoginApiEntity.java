package com.agus.java.controller.entitySwagger;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginApiEntity {

	@Schema(required = true, example = "admin", description = "user untuk login")
	String username;

	@Schema(required = true, example = "admin", description = "password untuk login")
	String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
