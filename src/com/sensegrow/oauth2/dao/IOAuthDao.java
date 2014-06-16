package com.sensegrow.oauth2.dao;

import com.sensegrow.oauth2.models.AccessToken;
import com.sensegrow.oauth2.models.ClientCredential;
import com.sensegrow.oauth2.models.AuthorizationCode;

public interface IOAuthDao {

	public abstract boolean validateClient(String clientId, String clientSecret,
			String grantType);

	public abstract AuthorizationCode createOrUpdateAuthorizationCode(String clientId,
			Long userId, String scope);

	public abstract AccessToken createOrUpdateAccessToken(AuthorizationCode authInfo);

	public abstract AuthorizationCode getAuthInfoByCode(String code);

	public abstract AuthorizationCode getAuthInfoByRefreshToken(String refreshToken);

	public abstract String getClientUserId(String clientId, String clientSecret);

	public abstract boolean validateClientByClientId(String clientId);

	public abstract ClientCredential findAppByClientId(String clientId);

	public abstract ClientCredential findAppByClientIdAndClientSecret(String clientId,
			String clientSecret);
	public abstract String getClientGrantType(String clientId);

	/**
	 * Validate the user specified by the user ID. This method is used to check
	 * the user at accessing a protected resource. When the access token passed
	 * from the client is valid, the user status may be invalid or may be left
	 * in the OAuth provider side. In these case, this method must return false
	 * to refuse the access to all API endpoints.
	 * 
	 * @param userId
	 *            The user's ID.
	 * @return If the user's status is invalid, return false, otherwise, return
	 *         true.
	 */
	public abstract boolean validateUserById(String userId);

	/**
	 * Retrieve the access token from the token string. This method is used at
	 * accessing a protected resource. This sub class should fetch the access
	 * token information from your database or etc and return it. If the access
	 * token has been revoked by the user or there is other reason, this method
	 * must return the null value to refuse the access to all API endpoints.
	 * 
	 * @param token
	 *            The access token string.
	 * @return The object which has the information for the access token.
	 */
	public abstract AccessToken getAccessToken(String token);

	/**
	 * Retrieve the authorization information by the ID. This method is used at
	 * accessing a protected resource. The getAccessTkoken() method is called
	 * before this method calling. The result has a ID of the authorization
	 * information. The ID is passed to this method as an argument. This sub
	 * class must return the authorization information instance to the client.
	 * If the ID has already been invalid or there is other reason, this
	 * implementation must return the null value.
	 * 
	 * @param id
	 *            The ID to specify the authorization information.
	 * @return The object which has the information about the authorization.
	 */
	public abstract AuthorizationCode getAuthInfoById(String id);

}
