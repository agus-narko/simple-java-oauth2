package com.agus.java.resource.userManagement;

import org.springframework.security.core.userdetails.User;

import com.agus.java.model.userManagement.LoginUser;



public class CustomUser extends User {
   private static final long serialVersionUID = 1L;
   public CustomUser(LoginUser loginUser) {
      super(loginUser.getUsername(), loginUser.getOauthPassword(), loginUser.getGrantedAuthoritiesList());
   }
}