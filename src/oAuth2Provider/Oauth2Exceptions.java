package oAuth2Provider;

import com.fasterxml.jackson.databind.JsonNode;

import models.App;

public class Oauth2Exceptions {

	public String invalidAuthCodeRequest(JsonNode json) {
		String response = "";
		String responseType = null;
		String clientId = null;
		String redirectUri = null;
		try {
			responseType = json.get("response_type").asText();
			clientId = json.get("client_id").asText();
			redirectUri = json.get("redirect_uri").asText();

		} catch (NullPointerException e) {
			System.out.println(e);
		} finally {
			if (responseType == null)
				response = response + "response_type, ";
			if (clientId == null)
				response = response + "client_id, ";
			if (redirectUri == null)
				response = response + "redirect_uri ";
			if (response != null)
				response = response + "must not be null.";
			return response;
		}
	}

	public String invalidTokenRequest(JsonNode json) {
		String grantType = json.get("grant_type").asText();
		String clientId = json.get("client_id").asText();
		String code = json.get("code").asText();
		String redirectUri = json.get("redirect_uri").asText();
		String response = "";
		if (grantType == null)
			response = response + "grant_type, ";
		if (clientId == null)
			response = response + "client_id, ";
		if (redirectUri == null)
			response = response + "redirect_uri, ";
		if (code == null)
			response = response + "code ";
		if (redirectUri != null)
			response = response + "must not be null.";
		if (response == "")
			response = "false";

		return response;
	}


	public boolean unauthorizedClient() {

		return false;
	}

	public boolean accessDenied() {

		return false;
	}

	public boolean InvalidGrant(JsonNode json) {
		String grantType = json.get("grant_type").asText();
		if (grantType.equalsIgnoreCase("authorization_code")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean RedirectUriMismatch(JsonNode json, App app) {
		String clientId = json.get("client_id").asText();
		if (app.getRedirectUri().equals(json.get("redirect_uri"))) {
			return false;
		} else {
			return true;
		}
	}

	public boolean unsupportedResponseType(JsonNode json) {
		if (json.get("response_type").asText().equals("code")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean invalidScope() {

		return false;
	}

	public boolean ExpiredToken() {

		return false;
	}

}
