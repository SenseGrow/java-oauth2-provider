package com.sensegrow.oauth2.grant;

import java.io.IOException;

import sun.misc.BASE64Decoder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sensegrow.oauth2.dao.IOAuthDao;
import com.sensegrow.oauth2.exceptions.OAuthError;
import com.sensegrow.oauth2.exceptions.OAuthError.InvalidClient;
import com.sensegrow.oauth2.exceptions.OAuthError.InvalidRequest;
import com.sensegrow.oauth2.exceptions.OAuthError.UnsupportedGrantType;
import com.sensegrow.oauth2.exceptions.OAuthError.UnsupportedResponseType;
import com.sensegrow.oauth2.grant.*;
import com.sensegrow.oauth2.request.OAuthRequest;

public class GrantTypeFactory {
	private static final Pattern REGEXP_BASIC = Pattern
			.compile("^\\s*(Basic)\\s+(.*)$");
	public static OAuthRequest request;
	public static IOAuthDao iOAuthDao;
	private static final Map<String, AbstractGrant> grantTypeMap = new HashMap<String, AbstractGrant>();
	static {
		grantTypeMap.put("authorization_code", new AuthorizationCodeGrant());
		grantTypeMap.put("password", new PasswordGrant());
		grantTypeMap.put("client_credentials", new ClientCredentialGrant());
	}

	private static final Map<String, AbstractGrant> responseTypeMap = new HashMap<String, AbstractGrant>();
	static {
		responseTypeMap.put("code", new AuthorizationCodeGrant());
		responseTypeMap.put("token", new ImplicitGrant());
	}

	public GrantTypeFactory(OAuthRequest request, IOAuthDao iOAuthDao) {
		GrantTypeFactory.request = request;
		GrantTypeFactory.iOAuthDao = iOAuthDao;
	}

	public AbstractGrant getTokenGrantType() throws ExecutionException,
			ClassNotFoundException, IOException, UnsupportedGrantType,
			InvalidRequest, InvalidClient {
		AbstractGrant grantTypeClass = null;
		String grantType = getParameter("grant_type");
		grantTypeClass = grantTypeMap.get(grantType);
		if (grantTypeClass == null) {
			throw new OAuthError.UnsupportedGrantType("");
		}
		setRequestClientCredential(request);
		String clientId = request.getclientId();
		String clientSecret = request.getclientSecret();
		boolean validClient = iOAuthDao.validateClient(clientId, clientSecret,
				grantTypeClass.getGrantType());
		if (!validClient) {
			throw new OAuthError.InvalidClient("");
		}
		return grantTypeClass;
	}

	public AbstractGrant getAuthorizeGrantType() throws ExecutionException, ClassNotFoundException,
			IOException, InvalidRequest, InvalidClient, UnsupportedResponseType {
		AbstractGrant responseTypeClass = null;
		String responseType = getParameter("response_type");
		responseTypeClass = responseTypeMap.get(responseType);
		if (responseTypeClass == null) {
			throw new OAuthError.UnsupportedResponseType("");
		}
		String clientId = getParameter("client_id");
		boolean validClient = iOAuthDao.validateClientByClientId(clientId);
		if (!validClient) {
			throw new OAuthError.InvalidClient("");
		}
		return responseTypeClass;
	}

	public void setRequestClientCredential(OAuthRequest request)
			throws ExecutionException, IOException, InvalidRequest {
		String header = request.getHeader("Authorization");
		if (header != null) {
			Matcher matcher = REGEXP_BASIC.matcher(header);
			if (matcher.find()) {
				BASE64Decoder decoder = new BASE64Decoder();
				String decoded = new String(decoder.decodeBuffer(matcher
						.group(2)));
				if (decoded.indexOf(':') > 0) {
					String[] credential = decoded.split(":", 2);
					request.setClientCredential(credential[0], credential[1]);
				}
			}
		}
		request.setClientCredential(getParameter("client_id"),
				getParameter("client_secret"));
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
	protected String getParameter(String name) throws ExecutionException, OAuthError.InvalidRequest {
		String value = request.getParameter(name);
		if (value == null || value == "") {
			throw new OAuthError.InvalidRequest("'" + name + "' not found");
		}
		return value;
	}

}
