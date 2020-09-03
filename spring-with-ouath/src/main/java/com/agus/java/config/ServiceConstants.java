package com.agus.java.config;




public class ServiceConstants {

	public static final String BASE_REST_URL  = "/service/api/";
	public static final String BASE_PARAM_URL = "/{userId}";

	// user
    public static final String CHANGE_PASSWORD_SERVICE = "changePasswordService";
    public static final String ADD_USER_SERVICE = "addUserService";
    public static final String CREATE_USER_SERVICE = "createUserService";
    public static final String GET_ALL_USER_SERVICE = "getAllUserService";
    public static final String ACTIVATION_USER_SERVICE = "activationUserService";
    public static final String EDIT_USER_MANAGEMENT_DETAIL_SERVICE = "editUserManagementDetailService";
    public static final String GET_USER_SERVICE = "getUserService";

    // role
    public static final String CREATE_ROLE_SERVICE = "createRoleService";
    public static final String ACTIVATION_ROLE_SERVICE = "activationRoleService";
    public static final String EDIT_ROLE_MANAGEMENT_DETAIL_SERVICE = "editRoleManagementDetailService";
    public static final String GET_ROLE_SERVICE = "getRoleService";


    // login
    public static final String LOGIN_SERVICE = "loginService";
    public static final String LOGOUT_SERVICE = "logoutService";
    public static final String REFRESH_TOKEN_SERVICE = "refreshTokenService";

}