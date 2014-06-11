package oAuth2Provider;

import java.util.Map;

public interface Oauth2Request {

	public boolean validateAuthorizationCodeRequest();

	public boolean validateAccessTokenRequest();

}
