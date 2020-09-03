package com.agus.java.resource.userManagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.model.roleManagement.Role;
import com.agus.java.model.roleManagement.RoleDao;
import com.agus.java.model.userManagement.LoginUser;
import com.agus.java.model.userManagement.UserDao;
import com.agus.java.model.userManagement.UserEntity;


@Service
public class GetUserbyUsername {
	
	private Logger log = LoggerFactory.getLogger(GetUserbyUsername.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LoginUser getUserDetails(String username) throws Exception {
	
    	
    	Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject resultOutput = new JSONObject();
		
		JSONObject roleTaskJson = new JSONObject();
		
		log.info(" >> LOGGIN IN << ");
		
    	String randomString = "";
    	String usernameResult = "";
		String passwordResult = "";
		String oauthPasswordResult = "";
		String role = "";
		Long ID ;
    	List<Object[]> result = null;
    	LoginUser user = new LoginUser();		
    	Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<GrantedAuthority>();


    	//output var
    	boolean status;
    	
    	try {
    		status=false;
    		
//    			password = Md5Util.getMD5(password);
    			StringBuilder queryStr = new StringBuilder();
    			queryStr.append(" SELECT A.username, A.password, B.roleName, A.statusLogin,A.id ")
    			.append(" FROM ").append(UserEntity.ENTITY_NAME).append(" A , ")
    			.append(Role.ENTITY_NAME).append(" B ")
    			.append(" WHERE A.userRole = B.id AND B.active = :YES ")
    			.append(" AND A.username = :username AND A.active = :YES AND A.userRole is not null ");
    			
    			Query query = userDao.createQuery(queryStr.toString());
        		query.setParameter("username",username);
        		query.setParameter("YES",MasterGeneralConstants.YES);
        		result = query.list();
        		
        		
        		userDao.closeSessionCreateQuery();
        		
    			Iterator iter = result.iterator();
    			while(iter.hasNext()){
    				Object[] obj = (Object[]) iter.next();
    				
    				usernameResult = null != obj[0] ? obj[0].toString() : null;
    				passwordResult = null != obj[1] ? obj[1].toString() : null;
    				role = null != obj[2] ? obj[2].toString() : null;
    				randomString = null != obj[3] ? obj[3].toString() : null;
    				ID = null != obj[4] ? Long.valueOf(obj[4].toString()) : null;
    				oauthPasswordResult = "{noop}"+passwordResult;
    				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
        					role);
        			grantedAuthoritiesList.add(grantedAuthority);
        			user.setGrantedAuthoritiesList(grantedAuthoritiesList);
        			user.setUsername(usernameResult);
        			user.setPassword(passwordResult);
        			user.setOauthPassword(oauthPasswordResult);
        			user.setRole(role);
        			user.setID(ID);
        			user.setRandomString(randomString);

    			}
    			
		} catch (Exception e) {
			e.printStackTrace();

			String errorMessage = e.getCause().toString();
			errorMessage=errorMessage.substring((errorMessage.indexOf(':')+1),errorMessage.length());
			errorMessage = errorMessage.trim();
			
		}
    	
		
	
    	return user;
    }

    
}
