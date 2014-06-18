package com.sensegrow.oauth2.endpoints;

import java.io.IOException;
import com.google.gson.Gson;
import com.sensegrow.oauth2.dao.IOAuthDao;
import com.sensegrow.oauth2.exceptions.OAuthError;
import com.sensegrow.oauth2.exceptions.OAuthError.InvalidClient;
import com.sensegrow.oauth2.exceptions.OAuthError.InvalidRequest;
import com.sensegrow.oauth2.exceptions.OAuthError.UnsupportedResponseType;
import com.sensegrow.oauth2.grant.AbstractGrant;
import com.sensegrow.oauth2.grant.GrantTypeFactory;
import com.sensegrow.oauth2.request.Request;
import com.sensegrow.oauth2.response.Response;

public class Authorization {

	public Response authorize(Request request,IOAuthDao iOAuthDao) throws ClassNotFoundException, InvalidRequest, InvalidClient, UnsupportedResponseType, IOException {
		try {
		GrantTypeFactory grantTypeFactory =new GrantTypeFactory(request, iOAuthDao);
		AbstractGrant authorizeGrantType =grantTypeFactory.getAuthorizeGrantType();
		authorizeGrantType.getCodeResponse();
		return null;
		} catch (OAuthError e) {
			 Gson gson = new Gson();
			return new Response(e.getCode(), gson.toJson(e));
		}
	}
}
