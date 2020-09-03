package com.agus.java.controller.entitySwagger;

import io.swagger.v3.oas.annotations.media.Schema;

public class LogoutEntity {

	@Schema(required = true, example = "70", description = "id user yg digunakan login")
	Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
