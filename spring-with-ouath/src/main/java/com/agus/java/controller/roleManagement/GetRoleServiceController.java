package com.agus.java.controller.roleManagement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agus.java.config.BoConstants;
import com.agus.java.config.MasterGeneralConstants;
import com.agus.java.config.ServiceConstants;
import com.agus.java.controller.entitySwagger.InquiryEntity;
import com.agus.java.model.ServiceOutput;
import com.agus.java.resource.core.BoInterceptor;
import com.agus.java.resource.core.CheckParameter;
import com.agus.java.resource.core.JSONUtil;
import com.agus.java.resource.exception.UnsupportedParameterException;
import com.google.gson.Gson;

@RestController
@EnableResourceServer
@Tag(name = "Role Management", description = "Role Management")
public class GetRoleServiceController {

    @Autowired
    BoInterceptor commandProcessingService;


	private static Map<BigInteger, ServiceOutput> finalServiceOutput;
	public static final String restUrlAddress = ServiceConstants.BASE_REST_URL
			+ ServiceConstants.GET_ROLE_SERVICE
			;

	static ServiceOutput serviceOutput = new ServiceOutput();

	@Operation(summary = "Get Role API", description = "get role berdasarkan parameter yg dikirim."
			+ " parameter dapat berupa Active Code ", security = { @SecurityRequirement(name = "bearer-key") }, tags = { "get role" })
	@RequestMapping(value = restUrlAddress, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Collection<ServiceOutput>> callService(
			@Parameter(description = "tidak boleh kosong dan diisi dengan user Id ", required = true) @RequestParam(value="userId") Long userId,
			@Parameter(description = "isi active code,role id atau blank ") @RequestParam(required=false) String parameter) {


		InquiryEntity inputUser = new InquiryEntity();
		inputUser.setUserId(userId);
		inputUser.setParameter(parameter);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(inputUser);
		JSONObject requestJSON = JSONUtil.parseStringtoJSON(jsonStr);
		if (CheckParameter.checkParameter(parameter).equals(MasterGeneralConstants.ACTIVE_PARAM)) {

			finalServiceOutput = commandProcessingService.processAndLogCommand(
	                    MasterGeneralConstants.READ,
	                    restUrlAddress, BoConstants.GetRoleByActive, MasterGeneralConstants.DATAPROCESS,
	                    requestJSON);
		}
		else if (CheckParameter.checkParameter(parameter).equals(MasterGeneralConstants.ID_PARAM)) {

			finalServiceOutput = commandProcessingService.processAndLogCommand(
	                    MasterGeneralConstants.READ,
	                    restUrlAddress, BoConstants.GetRoleById, MasterGeneralConstants.DATAPROCESS,
	                    requestJSON);

		}else if (CheckParameter.checkParameter(parameter).equals(MasterGeneralConstants.NO_PARAM)) {

			finalServiceOutput = commandProcessingService.processAndLogCommand(
                     MasterGeneralConstants.READ,
                     restUrlAddress, BoConstants.GetRole, MasterGeneralConstants.DATAPROCESS,
                     requestJSON);

		}else {
		    throw new UnsupportedParameterException(MasterGeneralConstants.INVALID_PARAM);
		}

		Collection<ServiceOutput> webServiceOutput = finalServiceOutput.values();

		return new ResponseEntity<Collection<ServiceOutput>>(webServiceOutput,
				HttpStatus.OK);
	}

}
