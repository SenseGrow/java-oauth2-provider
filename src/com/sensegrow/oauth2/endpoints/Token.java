package com.sensegrow.oauth2.endpoints;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.sensegrow.oauth2.dao.IOAuthDao;
import com.sensegrow.oauth2.exceptions.OAuthError;
import com.sensegrow.oauth2.grant.AbstractGrant;
import com.sensegrow.oauth2.grant.GrantTypeFactory;
import com.sensegrow.oauth2.request.OAuthRequest;
import com.sensegrow.oauth2.response.Response;

public class Token {


	public static Response  getToken(OAuthRequest request,IOAuthDao iOAuthDao) throws ExecutionException, ClassNotFoundException, IOException {
		Gson gson = new Gson();
		try {
			GrantTypeFactory grantTypeFactory = new GrantTypeFactory(request, iOAuthDao);
			AbstractGrant tokenGrantType = grantTypeFactory.getTokenGrantType();
			Map<String,String>  accessToken = tokenGrantType.getTokenResponse();
			return new Response(200, gson.toJson(accessToken));
		} catch (OAuthError e) {
			return new Response(e.getCode(), gson.toJson(e));
		}
		}
	}