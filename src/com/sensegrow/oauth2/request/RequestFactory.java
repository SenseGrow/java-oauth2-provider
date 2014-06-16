package com.sensegrow.oauth2.request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.sensegrow.oauth2.dao.IOAuthDao;
import com.sensegrow.oauth2.exceptions.OAuthError;
import com.sensegrow.oauth2.grant.IGrant;
import com.sensegrow.oauth2.models.AuthorizationCode;
import com.sensegrow.oauth2.models.AccessToken;
import com.sensegrow.oauth2.response.AccessTokenResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestFactory {

	public static Request request;
	public static IOAuthDao iOAuthDao;
	private static final Map<String, IGrant> grantTypeMap = new HashMap<String, IGrant>();
	static {
		grantTypeMap
				.put("authorization_code",  (IGrant) new com.sensegrow.oauth2.grant.AuthorizationCode());
		grantTypeMap.put("token", (IGrant) new com.sensegrow.oauth2.grant.ImplicitGrant());
		grantTypeMap.put("password", (IGrant) new com.sensegrow.oauth2.grant.PasswordCredential());
		grantTypeMap.put("client_credentials", (IGrant) new com.sensegrow.oauth2.grant.ClientCredential());
	}

	public RequestFactory(Request request,IOAuthDao iOAuthDao) {
		RequestFactory.request = request;
		RequestFactory.iOAuthDao =iOAuthDao;
	}

	public String createRequest() throws ClassNotFoundException {
		try {
			String grantType = request.getParameter("grant_type");
			if (grantType == null || grantType == "") {
				throw new OAuthError.InvalidRequest("'grant_type' not found");
			}
			IGrant grantTypeClass = grantTypeMap.get(grantType);
			if (grantTypeClass == null) {
				throw new OAuthError.UnsupportedGrantType("");
			}
			String clientId =request.getParameter("client_id");
			if (clientId == null || clientId == "") {
				throw new OAuthError.InvalidRequest("'client_id' not found");
			}
			String clientSecret =request.getParameter("client_secret");
			if (clientSecret == null || clientSecret == "") {
				throw new OAuthError.InvalidRequest("'client_secret' not found");
			}
			boolean validClient = iOAuthDao.validateClient(clientId, clientSecret, grantTypeClass.getGrantType());
			if (!validClient) {
				throw new OAuthError.InvalidClient("");
			}
			return grantTypeClass.getAccessToken();
			
		} catch (OAuthError e) {
			// return new Response(e.getCode(), Util.toJson(e));
		}
		return null;
	}

	/**
	 * Retrieve the parameter value against the parameter name.
	 * 
	 * @param request
	 *            The request object which has each parameters.
	 * @param name
	 *            The parameter name which you want to retrieve.
	 * @return The parameter value. This never be null.
	 * @throws OAuthError.InvalidRequest
	 *             If the parameter is not found or is empty string.
	 */
	protected String getParameter(Request request, String name)
			throws OAuthError.InvalidRequest {
		String value = request.getParameter(name);
		if (value == null || value == "") {
			throw new OAuthError.InvalidRequest("'" + name + "' not found");
		}
		return value;
	}

}
