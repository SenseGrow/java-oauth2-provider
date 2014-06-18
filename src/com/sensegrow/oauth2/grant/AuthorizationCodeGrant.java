package com.sensegrow.oauth2.grant;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.sensegrow.oauth2.dao.IOAuthDao;
import com.sensegrow.oauth2.models.AccessToken;
import com.sensegrow.oauth2.models.AuthorizationCode;
import com.sensegrow.oauth2.request.Request;

public class AuthorizationCodeGrant extends AbstractGrant {

	@Override
	public AuthorizationCode createCode() {
		Request request = GrantTypeFactory.request;
		IOAuthDao iOAuthDao = GrantTypeFactory.iOAuthDao;
		AuthorizationCode authorizationCode = iOAuthDao.createOrUpdateAuthorizationCode(
				request.getParameter("client_id"),
				Long.valueOf(request.getParameter("user_id")),
				request.getParameter("redirect_uri"),
				request.getParameter("scope"));
		return authorizationCode;
	}

	@Override
	public String getGrantType() {
		return "authorization_code";
	}

	@Override
	public AccessToken getAccessToken() {
		AuthorizationCode authInfo = GrantTypeFactory.iOAuthDao
				.getAuthInfoByCode(GrantTypeFactory.request
						.getParameter("code"));
		AccessToken accessToken = GrantTypeFactory.iOAuthDao.createOrUpdateAccessToken(authInfo);
		return accessToken;
	}
	@Override
	public void getCodeResponse(){
		AuthorizationCode authorizationCode =createCode();
		Request request = GrantTypeFactory.request;
		String url=request.getParameter("redirect_uri");
		Gson gson = new Gson();
		Map<String, String> param = new HashMap<String, String>();
		param.put("code", authorizationCode.getAuthorizationCode());
		request.redirectTOUrl(url, gson.toJson(param));
	}
	
	public Map<String,String> getTokenResponse(String code){
		AccessToken accessToken =getAccessToken();
		Map<String, String> param = new HashMap<String, String>();
		param.put("access_token", accessToken.getToken());
		param.put("token_type", "bearer");
		param.put("expires_in", accessToken.getExpiresIn().toString());
		param.put("access_token", accessToken.getRefreshToken());
		return param;
	}

}
