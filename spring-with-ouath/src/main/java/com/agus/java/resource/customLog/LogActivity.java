package com.agus.java.resource.customLog;
import com.agus.java.model.customLog.CustomLog;
import com.agus.java.model.customLog.CustomLogDao;
import java.util.Date;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

@Service
public class LogActivity {

    @Autowired
    CustomLogDao logDao;

    private CustomLog clog = new CustomLog();

    public boolean writeLog(String action,String uri,String commandJson,JSONObject responseJson) {

        try{
            
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2Authentication oAuth2 = ((OAuth2Authentication) authentication);

            String clientId = oAuth2.getOAuth2Request().getClientId();
            String username = oAuth2.getName();


            Date requestDate = new Date();
            clog.setRequestDate(requestDate);
            clog.setActionName(action);
            clog.setClienId(clientId);
            clog.setUserId(username);
            clog.setApiGetUri(uri);
            clog.setCommandJson(commandJson);
            
            clog.setResponseJson(responseJson.toJSONString());

            //current date
            Date dateResponse = new Date();
            clog.setResponseDate(dateResponse);

            //fail = 0, success = 1
            if("S".equalsIgnoreCase((String)responseJson.get("status"))){
                clog.setResultRemark(1);
            }else{
                clog.setResultRemark(0);
            }

            //Save the employee in database
            logDao.add(clog);


        }catch(Exception e){

            e.printStackTrace();
            return false;
        }

        return true;
    }


}