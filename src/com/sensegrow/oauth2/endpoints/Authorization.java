package com.sensegrow.oauth2.endpoints;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.sensegrow.oauth2.dao.IOAuthDao;
import com.sensegrow.oauth2.exceptions.OAuthError;
import com.sensegrow.oauth2.exceptions.OAuthError.InvalidClient;
import com.sensegrow.oauth2.exceptions.OAuthError.InvalidRequest;
import com.sensegrow.oauth2.exceptions.OAuthError.UnsupportedResponseType;
import com.sensegrow.oauth2.grant.AbstractGrant;
import com.sensegrow.oauth2.grant.GrantTypeFactory;
import com.sensegrow.oauth2.request.OAuthRequest;
import com.sensegrow.oauth2.response.Response;

public class Authorization {

	public static Response authorize(OAuthRequest request,IOAuthDao iOAuthDao) throws ExecutionException, ClassNotFoundException, InvalidRequest, InvalidClient, UnsupportedResponseType, IOException {
		try {
		GrantTypeFactory grantTypeFactory =new GrantTypeFactory(request, iOAuthDao);
		AbstractGrant authorizeGrantType =grantTypeFactory.getAuthorizeGrantType();
		String resp = authorizeGrantType.getCodeResponse();
		Response response = new Response(200,resp);
		return response;
		} catch (OAuthError e) {
			 Gson gson = new Gson();
			return new Response(e.getCode(), gson.toJson(e));
		}
	}
}
