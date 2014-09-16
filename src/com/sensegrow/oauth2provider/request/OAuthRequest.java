package com.sensegrow.oauth2provider.request;

import java.util.Map;

public abstract class OAuthRequest {

	protected String clientId;
	protected String clientSecret;

	public void setClientCredential(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public String getclientId() {
		return clientId;
	}

	public String getclientSecret() {
		return clientSecret;
	}

	public String getuserId() {
		return null;
	}

	public abstract String redirectToUrl(String url, String param);

	/**
	 * redirect to a url with param.
	 */
	public abstract String getRedirectUrl(String url, String param);

	/**
	 * @return The request object.
	 */
	public abstract OAuthRequest getRequest();

	/**
	 * Retrieve the parameter value specified by the parameter name from the
	 * request.
	 * 
	 * @param name
	 *            The parameter name.
	 * @return The value against the name.
	 * @throws ExecutionException
	 */
	public abstract String getParameter(String name);

	/**
	 * Retrieve all parameter names and values from the request as a Map
	 * instance.
	 * 
	 * @return The map instance which has all parameter names and values.
	 */
	public abstract Map<String, String> getParameterMap();

	/**
	 * Retrieve the request header value from the request.
	 * 
	 * @param name
	 *            The header's name.
	 * @return The value against the name.
	 */
	public abstract String getHeader(String name);
}
