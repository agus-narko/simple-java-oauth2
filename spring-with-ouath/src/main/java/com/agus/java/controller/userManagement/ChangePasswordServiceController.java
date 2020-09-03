package com.agus.java.controller.userManagement;

import com.google.gson.Gson;
import com.agus.java.config.BoConstants;
import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.config.ServiceConstants;
import com.agus.java.controller.entitySwagger.userManagement.ChangePasswordEntity;
import com.agus.java.model.ServiceOutput;
import com.agus.java.resource.core.BoInterceptor;
import com.agus.java.resource.core.JSONUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableResourceServer
@Tag(name = "User Management", description = "User Management")
public class ChangePasswordServiceController {

    @Autowired
    BoInterceptor commandProcessingService;

    private static Map<BigInteger, ServiceOutput> finalServiceOutput;
    public static final String restUrlAddress = ServiceConstants.BASE_REST_URL + ServiceConstants.CHANGE_PASSWORD_SERVICE;
    static ServiceOutput serviceOutput = new ServiceOutput();

	@Operation(summary = "Change Password API",
			description = " API untuk edit password user ",
            security = { @SecurityRequirement(name = "bearer-key") },
			tags = { "Change Password" })
    @RequestMapping(
            value = restUrlAddress,
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ServiceOutput>> callService(
            @Parameter(
                    required=true,
                    schema=@Schema(implementation = ChangePasswordEntity.class))
            @Valid @RequestBody ChangePasswordEntity inputUser) {



        Gson gson = new Gson();
        String jsonStr = gson.toJson(inputUser);
        JSONObject requestJSON =  JSONUtil.parseStringtoJSON(jsonStr);

    	finalServiceOutput = commandProcessingService.processAndLogCommand(
                MasterGeneralConstants.UPDATE,
                restUrlAddress, BoConstants.ChangePassword, MasterGeneralConstants.DATAPROCESS,
                requestJSON);
        Collection<ServiceOutput> webServiceOutput = finalServiceOutput.values();

        return new ResponseEntity<Collection<ServiceOutput>>(webServiceOutput,
                HttpStatus.OK);
    }

}
