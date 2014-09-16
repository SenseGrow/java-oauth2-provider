package com.sensegrow.oauth2provider.grant;

import java.util.Map;

import com.sensegrow.oauth2provider.models.AccessToken;
import com.sensegrow.oauth2provider.models.AuthorizationCode;
import com.sensegrow.oauth2provider.request.OAuthRequest;

public abstract class AbstractGrant {

	protected String clientId;
	protected String clientSecret;

	public void ClientCredential(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public String getclientId() {
		return clientId;
	}

	public String getclientSecret() {
		return clientSecret;
	}

	public AuthorizationCode createCode() {
		return null;
	}

	public String getuserId() {
		return null;
	}

	public String getCodeResponse() {
		return null;
	}

	public abstract Map getTokenResponse();

	/**
	 * Retrieve the access token.
	 * 
	 * @return The issued access token string.
	 */
	public abstract AccessToken getAccessToken();

	/**
	 * Retrieve the grant type.
	 * 
	 * @return The issued token's type.
	 */
	public abstract String getGrantType();

}
