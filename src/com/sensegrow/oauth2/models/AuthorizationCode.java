package com.sensegrow.oauth2.models;

import java.util.Date;


public class AuthorizationCode {

	private Long id;
	private String authorization_code;
	private String client_id;
	private String redirect_uri;
	private Long user_id;
	private String scope;
	private Date created_at;
	private Date updated_at;

	public AuthorizationCode(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorizationCode() {
		return authorization_code;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorization_code = authorizationCode;
	}

	public String getClientId() {
		return client_id;
	}

	public void setClientId(String clientId) {
		this.client_id = clientId;
	}

	public String getRedirectUri() {
		return redirect_uri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirect_uri = redirectUri;
	}

	public Long getUserId() {
		return user_id;
	}

	public void setUserId(Long userId) {
		this.user_id = userId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
