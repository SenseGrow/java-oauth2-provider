package com.sensegrow.oauth2.endpoints;

import com.sensegrow.oauth2.dao.IOAuthDao;
import com.sensegrow.oauth2.exceptions.OAuthError;
import com.sensegrow.oauth2.models.AccessToken;
import com.sensegrow.oauth2.request.Request;
import com.sensegrow.oauth2.request.RequestFactory;

public class Token {


	public void  getToken(Request request,IOAuthDao iOAuthDao) {
		try {
			RequestFactory RequestFactory =new RequestFactory(request, iOAuthDao);
			String type = request.getParameter("grant_type");
			if (type == null || type == "") {
				throw new OAuthError.InvalidRequest("'grant_type' not found");
			}
		} catch (OAuthError e) {
			// return new Response(e.getCode(), Util.toJson(e));
		}
	}

}
