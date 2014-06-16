package com.sensegrow.oauth2.endpoints;

import com.sensegrow.oauth2.dao.IOAuthDao;
import com.sensegrow.oauth2.exceptions.OAuthError;
import com.sensegrow.oauth2.request.Request;

public class Code {

	public void  getCode(Request request,IOAuthDao iOAuthDao) {
		try {
			String type = request.getParameter("response_type");
			if (type == null || type == "") {
				throw new OAuthError.InvalidRequest("'response_type' not found");
				
			}
		} catch (OAuthError e) {
			// return new Response(e.getCode(), Util.toJson(e));
		}
	}

}
