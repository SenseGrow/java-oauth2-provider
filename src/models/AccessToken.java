package models;

import java.sql.Date;


public class AccessToken {

	private Long id;
	private String access_token;
	private Long auth_info_id;
	private Long expires_in;
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

	public String getAccessToken() {
		return access_token;
	}

	public void setAaccessToken(String accessToken) {
		this.access_token = accessToken;
	}

	public Long getAuthInfoId() {
		return auth_info_id;
	}

	public void setAuthInfoId(Long authInfoId) {
		this.auth_info_id = authInfoId;
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
