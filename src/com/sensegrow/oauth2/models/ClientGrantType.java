package com.sensegrow.oauth2.models;

import java.util.Date;


public class ClientGrantType {
	private Long id;
	private String client_id;
	private String grant_type;
	private Date created_at;
	private Date updated_at;

	public ClientGrantType(Long id) {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientId() {
		return client_id;
	}

	public void setClientId(String clientId) {
		this.client_id = clientId;
	}

	public String getGrantType() {
		return grant_type;
	}

	public void setGrantType(String grantType) {
		this.grant_type = grantType;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}

}
