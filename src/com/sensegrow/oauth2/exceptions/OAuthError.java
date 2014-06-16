package com.sensegrow.oauth2.exceptions;

/**
* This abstract class represents an OAuth error information.
* Actually, each error should be represented by creating this sub class.
* 
*
*/


public abstract class OAuthError extends Exception {

	private int code;

	
	private String description;

	/**
	 * Initialize this instance by two argument values.
	 * @param code The HTTP status code which should be returned to the client.
	 * @param description The human-readable string which describes the detail
	 * information regarding the error.
	 */
	protected OAuthError(int code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	/**
	 * Initialize this instance.
	 * This constructor should be used to represent the error of when basically
	 * the request is invalid. That means the HTTP status code is initialized
	 * as 400.
	 * @param description The human-readable string which describes the detail
	 * information regarding the error.
	 */
	protected OAuthError(String description) {
		this(400, description);
	}

	/**
	 * Retrieve the HTTP status code value.
	 * @return The HTTP status code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Retrieve the error description.
	 * The human-readable string which describes the detail information
	 * regarding the error. This string is returned as the error_description
	 * property value.
	 * @return The description string.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Retrieve the error type.
	 * This sub class must implement this method. This result is returned
	 * as the error property value.
	 * @return The error type string.
	 */
	
	public abstract String getType();

	/**
	 * This exception represents that the request is invalid.
	 * For instance, this error type is "invalid_request".
	 * 
	 *
	 */
	public static class InvalidRequest extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 400.
		 * @param description The error description string.
		 */
		public InvalidRequest(String description) {
			super(description);
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "invalid_request".
		 */
		@Override
		public String getType() {
			return "invalid_request";
		}

	}

	/**
	 * This exception represents that the client is invalid.
	 * For instance, this error type is "invalid_client".
	 * 
	 	 *
	 */
	public static class InvalidClient extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 401.
		 * @param description The error description string.
		 */
		public InvalidClient(String description) {
			super(401, description);
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "invalid_client".
		 */
		@Override
		public String getType() {
			return "invalid_client";
		}

	}

	/**
	 * This means that the client is not authorized to request an authorization
	 * code using this method. For instance, this error type is
	 * "unauthorized_client".
	 * 
	 *
	 */
	public static class UnauthorizedClient extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 401.
		 * @param description The error description string.
		 */
		public UnauthorizedClient(String description) {
			super(401, description);
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "unauthorized_client".
		 */
		@Override
		public String getType() {
			return "unauthorized_client";
		}

	}

	/**
	 * This means that the redirect_uri value does not match the value set in
	 * advance. For instance, this error type is "redirect_uri_mismatch".
	 * 
	 *
	 */
	public static class RedirectUriMismatch extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 401.
		 * @param description The error description string.
		 */
		public RedirectUriMismatch(String description) {
			super(401, description);
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "redirect_uri_mismatch".
		 */
		@Override
		public String getType() {
			return "redirect_uri_mismatch";
		}

	}

	/**
	 * This means that the result is denied. For instance, this error type is
	 * "access_denied".
	 * 
	 *
	 */
	public static class AccessDenied extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 401.
		 * @param description The error description string.
		 */
		public AccessDenied(String description) {
			super(401, description);
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "access_denied".
		 */
		@Override
		public String getType() {
			return "access_denied";
		}

	}

	/**
	 * This means that the authorization server does not support obtaining an
	 * authorization code using this method. For instance, this error type is
	 * "unsupported_response_type".
	 * 
	 *
	 */
	public static class UnsupportedResponseType extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 400.
		 * @param description The error description string.
		 */
		public UnsupportedResponseType(String description) {
			super(description);
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "unsupported_response_type".
		 */
		@Override
		public String getType() {
			return "unsupported_response_type";
		}

	}

	/**
	 * This means that the provided authorization grant (e.g., authorization
	 * code, resource owner credentials) or refresh token is
	 * invalid, expired, revoked, does not match the redirection
	 * URI used in the authorization request, or was issued to
	 * another client. For instance, this error type is
	 * "invalid_grant".
	 * 
	 *
	 */
	public static class InvalidGrant extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 401.
		 * @param description The error description string.
		 */
		public InvalidGrant(String description) {
			super(401, description);
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "invalid_grant".
		 */
		@Override
		public String getType() {
			return "invalid_grant";
		}

	}

	/**
	 * This means that the authorization grant type is not supported by the
	 * authorization server. For instance, this error type is
	 * "unsupported_grant_type".
	 * 
	 *
	 */
	public static class UnsupportedGrantType extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 400.
		 * @param description The error description string.
		 */
		public UnsupportedGrantType(String description) {
			super(description);
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "unsupported_grant_type".
		 */
		@Override
		public String getType() {
			return "unsupported_grant_type";
		}

	}

	/**
	 * This means that the requested scope is invalid, unknown, malformed, or
	 * exceeds the scope granted by the resource owner. For instance, this
	 * error type is "invalid_scope".
	 * 
	 *
	 */
	public static class InvalidScope extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 401.
		 * @param description The error description string.
		 */
		public InvalidScope(String description) {
			super(401, description);
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "invalid_scope".
		 */
		@Override
		public String getType() {
			return "invalid_scope";
		}

	}

	/**
	 * This means that the access token passed to the API's endpoint is invalid.
	 * For instance, this error type is "invalid_token".
	 * 
	 *
	 */
	public static class InvalidToken extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 401.
		 * @param description The error description string.
		 */
		public InvalidToken(String description) {
			super(401, description);
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "invalid_token".
		 */
		@Override
		public String getType() {
			return "invalid_token";
		}

	}

	/**
	 * This means that the access token passed to the API's endpoint has
	 * already been expired. For instance, this error type is "invalid_token".
	 * 
	 *
	 */
	public static class ExpiredToken extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 401.
		 */
		public ExpiredToken() {
			super(401, "The access token expired");
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "invalid_token".
		 */
		@Override
		public String getType() {
			return "invalid_token";
		}

	}

	/**
	 * This means that the specified scope is insufficient. For instance, this
	 * error type is "insufficient_scope".
	 * 
	  *
	 */
	public static class InsufficientScope extends OAuthError {

		/**
		 * Initialize this instance. The HTTP status code is set as 401.
		 * @param description The error description string.
		 */
		public InsufficientScope(String description) {
			super(401, description);
		}

		/**
		 * Retrieve the error type string.
		 * This method returns the fixed string "insufficient_scope".
		 */
		@Override
		public String getType() {
			return "insufficient_scope";
		}

	}


}
