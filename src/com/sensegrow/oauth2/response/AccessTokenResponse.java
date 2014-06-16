package com.sensegrow.oauth2.response;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.sensegrow.oauth2.dao.IOAuthDao;
import com.sensegrow.oauth2.models.AccessToken;
import com.sensegrow.oauth2.models.AuthorizationCode;

public class AccessTokenResponse {

	public void authorize(String clientId, Long userId) {
		IOAuthDao iOauthDAO =new IOAuthDao();
		AuthorizationCode authInfo = iOauthDAO
				.createOrUpdateAuthInfo(clientId, userId, scope);
		return Results.redirect(redirect_uri + "?code="
				+ authInfo.getAuthorizationCode());
	}

	public JSONObject issueAccessToken(IOAuthDao iOauthDAO,
			AuthorizationCode authInfo) throws JSONException {
		AccessToken accessToken = iOauthDAO
				.createOrUpdateAccessToken(authInfo);
		JSONObject result = new JSONObject();
		result.put("token_type", "Bearer");
		result.put("access_token", accessToken.getToken());
		if (accessToken.getExpiresIn() > 0) {
			result.put("expires_in", accessToken.getExpiresIn());
		}
		if (authInfo.getRefreshToken()==null || authInfo.getRefreshToken()=="" ) {
			result.put("refresh_token", authInfo.getRefreshToken());
		}
		if (authInfo.getScope()==null || authInfo.getScope()=="") {
			result.put("scope", authInfo.getScope());
		}
		return result;
	}
}
