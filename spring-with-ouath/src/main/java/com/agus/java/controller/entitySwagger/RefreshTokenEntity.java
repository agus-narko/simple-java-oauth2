package com.agus.java.controller.entitySwagger;

import io.swagger.v3.oas.annotations.media.Schema;

public class RefreshTokenEntity {

    @Schema(required=true, description = "refresh token yg didapat setalah login")
    String refreshToken;

    @Schema(required=true, example = "70", description = "id user yg digunakan login")
    Long userId;

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }




}
