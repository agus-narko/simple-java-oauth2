package com.agus.java.controller.entitySwagger.userManagement;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import javax.persistence.Column;

public class CreateUserEntity {

	
	@Schema(required = true, example = "70", description = "id user yg digunakan login")
	Long userId;

	@Schema(required = true, example = "admin1", description = "username ")
	String password;
	

	@Schema(required = true, example = "admin1", description = "username ")
	String username;

	@Schema(required = true, example = "admin1@email.com",description = "email ")
	String email;

	@Schema(required = true, example = "admin satu",description = "full name ")
	String userFullname;

	@Schema(required = true, example = "123456789",description = "no telp ")
	private String phone;

	@Schema(required = true, example = "jakarta", description = "alamat ")
	private String address;

	@Schema(required = true, example = "43",description = "id role ")
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

	public String getPassword() {
		return password;
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

	public void setPassword(String password) {
		this.password = password;
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

}
