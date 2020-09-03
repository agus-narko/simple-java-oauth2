package com.agus.java.controller.roleManagement;

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

import com.agus.java.config.BoConstants;
import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.config.ServiceConstants;
import com.agus.java.controller.entitySwagger.roleManagement.EditRoleEntity;
import com.agus.java.model.ServiceOutput;
import com.agus.java.resource.core.BoInterceptor;
import com.agus.java.resource.core.JSONUtil;
import com.google.gson.Gson;

@RestController
@EnableResourceServer
@Tag(name = "Role Management", description = "Role Management")
public class EditRoleManagementDetailServiceController {

    @Autowired
    BoInterceptor commandProcessingService;

    private static Map<BigInteger, ServiceOutput> finalServiceOutput;
    public static final String restUrlAddress = ServiceConstants.BASE_REST_URL + ServiceConstants.EDIT_ROLE_MANAGEMENT_DETAIL_SERVICE;
    static ServiceOutput serviceOutput = new ServiceOutput();

    @Operation(summary = "Update Role Management API",
			description = " API untuk update data Role ",
            security = { @SecurityRequirement(name = "bearer-key") },
			tags = { "update Role" })
    @RequestMapping(
            value = restUrlAddress,
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ServiceOutput>> callService(
            @Parameter(
                    required=true,
                    schema=@Schema(implementation = EditRoleEntity.class))
            @Valid @RequestBody EditRoleEntity inputUser) {


        Gson gson = new Gson();
        String jsonStr = gson.toJson(inputUser);
        JSONObject requestJSON =  JSONUtil.parseStringtoJSON(jsonStr);

        finalServiceOutput = commandProcessingService.processAndLogCommand(
                MasterGeneralConstants.UPDATE,
                restUrlAddress, BoConstants.EditRoleManagementDetail, MasterGeneralConstants.DATAPROCESS,
                requestJSON);

        Collection<ServiceOutput> webServiceOutput = finalServiceOutput.values();
        return new ResponseEntity<Collection<ServiceOutput>>(webServiceOutput, HttpStatus.OK);
    }

}
