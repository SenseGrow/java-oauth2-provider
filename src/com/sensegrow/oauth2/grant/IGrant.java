package com.sensegrow.oauth2.grant;

public interface IGrant {

	/**
	 * Retrieve the grant type.
	 * 
	 * @return The issued token's type.
	 */
	public String getGrantType();
	/**
	 * Retrieve the access token.
	 * 
	 * @return The issued access token string.
	 */
	public String getAccessToken();

	/**
	 * Set the expires_in parameter's value. An unit of this value is second.
	 * 
	 * @param expiresIn
	 *            The expires_in value.
	 */
	public void setExpiresIn(Long expiresIn);

	/**
	 * Retrieve the expires_in value.
	 * 
	 * @return The expires_in value (this unit is second).
	 */
	public Long getExpiresIn() ;

	/**
	 * Set the refresh token. If a grant type which issues the refresh token is
	 * specified, the issued refresh token is passed to the client via this
	 * method.
	 * 
	 * @param refreshToken
	 *            The refresh token string.
	 */
	public void setRefreshToken(String refreshToken);

	/**
	 * Retrieve the refresh token.
	 * 
	 * @return The issued refresh token string.
	 */
	public String getRefreshToken() ;

	/**
	 * Set the scope string. This scope string specified by the client is passed
	 * to this method untouched.
	 * 
	 * @param scope
	 *            The scope string.
	 */
	public void setScope(String scope) ;

	/**
	 * Retrieve the scope string.
	 * 
	 * @return The scope string.
	 */
	public String getScope();
	
	}

