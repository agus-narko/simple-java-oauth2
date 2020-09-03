package com.agus.java.controller.oauth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import com.agus.java.controller.entitySwagger.LoginApiEntity;
import com.agus.java.model.ServiceOutput;
import com.agus.java.resource.core.BoInterceptor;
import com.agus.java.resource.core.JSONUtil;
import com.agus.java.resource.core.PrepareServiceOutput;
import com.agus.java.resource.userManagement.LoginApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

@RestController
@EnableResourceServer
@Tag(name = "OAUTH", description = "OAUTH")
public class LoginServiceController {

	@Autowired
	BoInterceptor commandProcessingService;

	private static Map<BigInteger, ServiceOutput> finalServiceOutput;
	public static final String restUrlAddress = ServiceConstants.BASE_REST_URL
			+ ServiceConstants.LOGIN_SERVICE;
	private static JSONObject boOutput;
	static ServiceOutput serviceOutput = new ServiceOutput();

	@Operation(summary = "Login API", description = " API untuk login ", tags = { "login" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated the stream configuration", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceOutput.class))) })
	@RequestMapping(value = restUrlAddress, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ServiceOutput>> callService(
			@Parameter(required = true, schema = @Schema(implementation = LoginApiEntity.class)) @Valid @RequestBody LoginApiEntity inputUser)
			throws JsonProcessingException {

		Gson gson = new Gson();
		String jsonStr = gson.toJson(inputUser);
		JSONObject requestJSON = JSONUtil.parseStringtoJSON(jsonStr);

		
		finalServiceOutput = commandProcessingService.processAndLogCommand(
				MasterGeneralConstants.OAUTH, restUrlAddress,
				BoConstants.Login, MasterGeneralConstants.DATAPROCESS,
				requestJSON);

		Collection<ServiceOutput> webServiceOutput = finalServiceOutput
				.values();
		return new ResponseEntity<Collection<ServiceOutput>>(webServiceOutput,
				HttpStatus.OK);

	}

}
