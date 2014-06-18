package com.sensegrow.oauth2.response;

public class Response {
	private int code;
	private String body;

	/**
	 * Initialize this instance with arguments passed.
	 * @param code The status code as the result of issuing a token.
	 * @param body The JSON string which has a token information.
	 */
	public Response(int code, String body) {
		super();
		this.code = code;
		this.body = body;
	}

	/**
	 * Retrieve the status code value.
	 * This status code will be 200 when the issuing token is succeeded.
	 * If an error occurs, the code will be the 300 series value.
	 * @return The HTTP status code value.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Retrieve the JSON string which has a token information.
	 * The JSON string has the access token, refresh token, expires_in
	 * value and the scope string. If the issuing a token failed,
	 * this json string has the error type and description.
	 * @return The JSON string value.
	 */
	public String getBody() {
		return body;
	}
}
