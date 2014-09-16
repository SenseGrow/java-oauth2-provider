package com.sensegrow.oauth2provider.models;

import java.sql.Date;


public class AccessToken {

	private Long id;
	private String access_token;
	private Long authorization_code_id;
	private Long expires_in;
	private String refresh_token;
	private Date created_at;
	private Date updated_at;

	public AccessToken() {
	}
	
	public AccessToken(Long authorizationCodeId) {
		this.authorization_code_id = authorizationCodeId;
	}
	
//	public AccessToken(Long id) {
//		this.id = id;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessToken() {
		return access_token;
	}

	public void setAccessToken(String accessToken) {
		this.access_token = accessToken;
	}
	
	public Long getAuthCodeId() {
		return authorization_code_id;
	}
	
	public void setAuthCodeId(Long authorizationCodeId) {
		this.authorization_code_id = authorizationCodeId;
	}

	public String getRefreshToken() {
		return refresh_token;
	}

	public void setRefreshToken(String refreshToken) {
		this.refresh_token = refreshToken;
	}
	
	public Long getExpiresIn() {
		return (Long) expires_in;
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
