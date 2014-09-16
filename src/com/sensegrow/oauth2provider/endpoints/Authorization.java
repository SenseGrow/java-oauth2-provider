package com.sensegrow.oauth2provider.endpoints;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.sensegrow.oauth2provider.dao.IOAuthDao;
import com.sensegrow.oauth2provider.exceptions.OAuthError;
import com.sensegrow.oauth2provider.exceptions.OAuthError.InvalidClient;
import com.sensegrow.oauth2provider.exceptions.OAuthError.InvalidRequest;
import com.sensegrow.oauth2provider.exceptions.OAuthError.UnsupportedResponseType;
import com.sensegrow.oauth2provider.grant.AbstractGrant;
import com.sensegrow.oauth2provider.grant.GrantTypeFactory;
import com.sensegrow.oauth2provider.request.OAuthRequest;
import com.sensegrow.oauth2provider.response.Response;

public class Authorization {

	public static Response authorize(OAuthRequest request, IOAuthDao iOAuthDao)
			throws ExecutionException, ClassNotFoundException, InvalidRequest,
			InvalidClient, UnsupportedResponseType, IOException {
		try {
			GrantTypeFactory grantTypeFactory = new GrantTypeFactory(request,
					iOAuthDao);
			AbstractGrant authorizeGrantType = grantTypeFactory
					.getAuthorizeGrantType();
			String resp = authorizeGrantType.getCodeResponse();
			return new Response(200, resp);
		} catch (OAuthError e) {
			Gson gson = new Gson();
			return new Response(e.getCode(), gson.toJson(e));
		}
	}
}
