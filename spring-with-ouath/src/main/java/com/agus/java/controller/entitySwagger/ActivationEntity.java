package com.agus.java.controller.entitySwagger;

import io.swagger.v3.oas.annotations.media.Schema;

public class ActivationEntity {


    @Schema(required=true, example = "70",description = "id user yg digunakan login")
    Long userId;

    @Schema(required=true, example = "71",description = "id yg akan di update")
    Long updateId;
    

    @Schema(required=true, example = "Y", description = "active code. Y = active, N = Deactive. input kapital")
    String status;


    public Long getUserId() {
        return userId;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}




}
