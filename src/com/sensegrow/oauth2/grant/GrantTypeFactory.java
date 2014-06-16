package com.sensegrow.oauth2.grant;

import java.util.HashMap;
import java.util.Map;

public class GrantTypeFactory  {

	private static final Map<String, String> grantTypeMap = new HashMap<String, String>();
	static {
		grantTypeMap.put("code", "AuthorizationCode");
		grantTypeMap.put("token", "ImplicitGrant");
		grantTypeMap.put("password", "PasswordCredential");
		grantTypeMap.put("client_credentials", "ClientCredential");
	}

	private String grantType = null;
	private String accessToken = null;
	private Long expiresIn = null;
	private String refreshToken = null;
	private String scope = null;

	public GrantTypeFactory(String grantType) {
		this.grantType = grantType;
	}

	}
