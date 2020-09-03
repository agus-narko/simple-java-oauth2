package com.agus.java.controller.entitySwagger.roleManagement;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import javax.persistence.Column;

public class EditRoleEntity {

	@Schema(required = true, example= "70",description = "id user yg digunkan login")
	Long userId;

	@Schema(required = true, example= "44",description = "id role yg akan di edit")
	Long roleId;

	@Schema(required = true, example= "user-admin",description = "nama role baru")
	String roleName;

	@Schema(required = true, example= "read,write,scope",description = "scope baru.kombinasi dari read,write dan trust")
	private String scope;

	public Long getUserId() {
		return userId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
