package com.agus.java.controller.entitySwagger.userManagement;

import io.swagger.v3.oas.annotations.media.Schema;

public class ChangePasswordEntity {

	@Schema(required = true, example = "70",description = "id user yg digunkan login")
	Long userId;

	@Schema(required = true, example = "admin",description = "password lama" )
	String prevPassword;

	@Schema(required = true, example = "admin1",description = "password baru")
	String newPassword;


	public Long getUserId() {
		return userId;
	}

	public String getPrevPassword() {
		return prevPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setPrevPassword(String prevPassword) {
		this.prevPassword = prevPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
