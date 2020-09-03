package com.agus.java.model.userManagement;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity(name=UserRole.ENTITY_NAME)
@Table(name=UserRole.TABLE_NAME)
/*@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})*/
public class UserRole implements Serializable{

	private static final long serialVersionUID = -1L;
	public static final String TABLE_NAME = "t_user_role";
	public static final String ENTITY_NAME = "com.agus.java.model.userManagement.UserRole";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	@Column(name="user_role_id")
	private Long id;
	
	@Column(name="role_id")
	private Long roleId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="create_datetime")
	private String createDatetime;
	
	@Column(name="create_user_id")
	private Long createUserId;
	
	@Column(name="update_datetime")
	private String updateDatetime;
	
	@Column(name="update_user_id")
	private Long updateUserId;
	
	@Column(name="flg_default_role")
	private String flgDefaultRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getFlgDefaultRole() {
		return flgDefaultRole;
	}

	public void setFlgDefaultRole(String flgDefaultRole) {
		this.flgDefaultRole = flgDefaultRole;
	}
	

}
