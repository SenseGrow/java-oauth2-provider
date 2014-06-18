package com.sensegrow.oauth2.grant;

import java.util.Map;

import com.sensegrow.oauth2.models.AccessToken;
import com.sensegrow.oauth2.models.AuthorizationCode;
import com.sensegrow.oauth2.request.Request;

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

	public void getCodeResponse() {

	}

	public Map getTokenResponse() {
		return null;
	}

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
