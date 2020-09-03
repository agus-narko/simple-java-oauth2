package com.agus.java.controller.entitySwagger;

import io.swagger.v3.oas.annotations.media.Schema;

public class InquiryEntity {


    @Schema(required=true, example = "70", description = "id user yg digunakan login")
    Long userId;

    @Schema(required=true, example = "Y", description = "parameter dapat berupa id dan active code")
    String parameter;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}




}
