package com.sensegrow.oauth2.models;

import java.util.Date;

public class ClientCredential {

	private Long id;
	private String name;
	private String client_id;
	private String client_secret;
	private String redirect_uri;
	private String scope;
	private Date created_at;
	private Date updated_at;

	public ClientCredential() {
	}
	
	public ClientCredential(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClientId() {
		return client_id;
	}

	public void setClientId(String clientId) {
		this.client_id = clientId;
	}

	public String getClientSecret() {
		return client_secret;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}

	public void setClientSecret(String clientSecret) {
		this.client_secret = clientSecret;
	}

	public String getRedirectUri() {
		return redirect_uri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirect_uri = redirectUri;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	}
