package com.sensegrow.oauth2.grant;

import com.sensegrow.oauth2.dao.IOAuthDao;
import com.sensegrow.oauth2.models.AccessToken;
import com.sensegrow.oauth2.request.RequestFactory;

public class AuthorizationCode implements IGrant{

	public AccessToken accessToken;
	public com.sensegrow.oauth2.models.AuthorizationCode authInfo;
	@Override
	public String getGrantType() {
		return "AuthorizationCode";
	}

	@Override
	public String getAccessToken() {
		IOAuthDao iOAuthDao =RequestFactory.iOAuthDao;
		this.authInfo =iOAuthDao.getAuthInfoByCode(RequestFactory.request.getParameter("authorization_code"));
		this.accessToken =iOAuthDao.createOrUpdateAccessToken(authInfo);
		return accessToken.getToken();
	}

	@Override
	public void setExpiresIn(Long expiresIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getExpiresIn() {
		return accessToken.getExpiresIn();
	}

	@Override
	public void setRefreshToken(String refreshToken) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRefreshToken() {
		return authInfo.getRefreshToken();
	}

	@Override
	public void setScope(String scope) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getScope() {
		return authInfo.getScope();
	}

	
}
