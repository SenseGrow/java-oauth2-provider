package com.sensegrow.oauth2.grant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.sensegrow.oauth2.dao.IOAuthDao;
import com.sensegrow.oauth2.models.AccessToken;
import com.sensegrow.oauth2.models.AuthorizationCode;
import com.sensegrow.oauth2.request.OAuthRequest;

public class AuthorizationCodeGrant extends AbstractGrant {

	@Override
	public AuthorizationCode createCode() {
		try {
			OAuthRequest request = GrantTypeFactory.request;
			IOAuthDao iOAuthDao = GrantTypeFactory.iOAuthDao;
			AuthorizationCode authorizationCode = iOAuthDao
					.createOrUpdateAuthorizationCode(
							request.getParameter("client_id"),
							Long.valueOf(request.getParameter("user_id")),
							request.getParameter("redirect_uri"),
							request.getParameter("scope"));
			return authorizationCode;
		} catch (ExecutionException e) {
			return null;
		}
	}

	@Override
	public String getGrantType() {
		return "authorization_code";
	}

	@Override
	public AccessToken getAccessToken() {
		try {
			AuthorizationCode authInfo = GrantTypeFactory.iOAuthDao
					.getAuthInfoByCode(GrantTypeFactory.request
							.getParameter("code"));
			AccessToken accessToken = GrantTypeFactory.iOAuthDao
					.createOrUpdateAccessToken(authInfo);
			return accessToken;
		} catch (ExecutionException e) {
			return null;
		}
	}

	@Override
	public String getCodeResponse() {
		try {
			AuthorizationCode authorizationCode = createCode();
			OAuthRequest request = GrantTypeFactory.request;
			String url = request.getParameter("redirect_uri");
			Gson gson = new Gson();
			Map<String, String> param = new HashMap<String, String>();
			param.put("code", authorizationCode.getAuthorizationCode());
			return request.getRedirectUrl(url, gson.toJson(param));
		} catch (ExecutionException e) {
			return null;
		}
	}

	@Override
	public Map<String, String> getTokenResponse() {
		AccessToken accessToken = getAccessToken();
		Map<String, String> param = new HashMap<String, String>();
		param.put("access_token", accessToken.getAccessToken());
		param.put("token_type", "bearer");
		param.put("expires_in", accessToken.getExpiresIn().toString());
		param.put("refresh_token", accessToken.getRefreshToken());
		return param;

	}

}
