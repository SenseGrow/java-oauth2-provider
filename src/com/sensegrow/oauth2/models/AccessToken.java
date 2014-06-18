package com.sensegrow.oauth2.models;

import java.sql.Date;


public class AccessToken {

	private Long id;
	private String access_token;
	private Long authorization_code_id;
	private Long expires_in;
	private String refresh_token;
	private Date created_at;
	private Date updated_at;

	public AccessToken(Long id) {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return access_token;
	}

	public void setToken(String accessToken) {
		this.access_token = accessToken;
	}
	
	public Long getAuthorizationCodeId() {
		return authorization_code_id;
	}

	public void setAuthInfoId(Long authorizationCodeId) {
		this.authorization_code_id = authorizationCodeId;
	}

	public String getRefreshToken() {
		return refresh_token;
	}

	public void setRefreshToken(String refreshToken) {
		this.refresh_token = refreshToken;
	}
	
	public Long getExpiresIn() {
		return expires_in;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expires_in = expiresIn;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}

}
