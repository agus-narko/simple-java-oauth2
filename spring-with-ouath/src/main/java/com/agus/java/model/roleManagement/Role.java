package com.agus.java.model.roleManagement;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = Role.ENTITY_NAME)
@Table(name = Role.TABLE_NAME)
/* @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) */
public class Role implements Serializable {

	private static final long serialVersionUID = -1L;
	public static final String TABLE_NAME = "m_role";
	public static final String ENTITY_NAME = "com.agus.java.model.roleManagement.Role";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "role_id")
	private Long id;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "active")
	private String active;

	@Column(name = "scope")
	private String scope;

	@Column(name = "create_datetime")
	private String createDatetime;

	@Column(name = "create_user_id")
	private Long createUserId;

	@Column(name = "update_datetime")
	private String updateDatetime;

	@Column(name = "update_user_id")
	private Long updateUserId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
