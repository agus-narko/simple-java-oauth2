package com.agus.java.controller.entitySwagger.userManagement;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class EditUserManagementDetailEntity {

	@Schema(required = true, example = "70", description = "id user yg digunakan login")
	Long userId;

	@Schema(required = true, example = "71", description = "id user yg akan di update")
	Long updateId;

	@Schema(required = true, example = "adminz", description = "username baru")
	String username;

	@Schema(required = true, example = "adminz@email.com",description = "email baru")
	String email;

	@Schema(required = true, example = "admin zed",description = "name baru")
	String userFullname;

	@Schema(required = true, example = "123456789",description = "no telp baru")
	private String phone;

	@Schema(required = true, example = "jakarta", description = "alamat baru")
	private String address;

	@Schema(required = true, example = "43",description = "id role baru")
	Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUserFullname() {
		return userFullname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}
}
