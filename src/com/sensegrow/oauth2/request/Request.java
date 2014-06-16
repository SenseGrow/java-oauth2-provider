package com.sensegrow.oauth2.request;

import java.util.Map;

public interface Request {
	/**
	 * @return The request object.
	 */
	public Request getRequest();

	/**
	 * Retrieve the parameter value specified by the parameter name from the
	 * request.
	 * 
	 * @param name
	 *            The parameter name.
	 * @return The value against the name.
	 */
	public String getParameter(String name);

	/**
	 * Retrieve all parameter names and values from the request as a Map
	 * instance.
	 * 
	 * @return The map instance which has all parameter names and values.
	 */
	public Map<String, String> getParameterMap();

	/**
	 * Retrieve the request header value from the request.
	 * 
	 * @param name
	 *            The header's name.
	 * @return The value against the name.
	 */
	public String getHeader(String name);
}
