package controllers;

import java.util.HashMap;
import java.util.Map;

import services.IoeyeOauth2DataHandler;
import services.IoeyeOauth2Request;
import oAuth2Provider.Oauth2Exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
import models.AccessToken;
import models.App;
import models.AuthInfo;

public class OAuth2Controller extends Controller {

	@Transactional
	public Result authorize() {
		Long userId = Long.valueOf(1);
		Request request = request();
		JsonNode json = request.body().asJson();
		IoeyeOauth2Request ioeyeOauth2Request = new IoeyeOauth2Request(request);
		Oauth2Exceptions oauth2Exceptions = new Oauth2Exceptions();
		String invalidAuthrequest = oauth2Exceptions
				.invalidAuthCodeRequest(json);
		Map<String, String> params = new HashMap<String, String>();
		String jsonOutput = "";
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		if (ioeyeOauth2Request.validateAuthorizationCodeRequest()) {
			String redirect_uri = request.getQueryString("redirect_uri");
			String scope = json.get("scope").asText();
			String clientId = json.get("client_id").asText();
			IoeyeOauth2DataHandler ioeyeOauth2DataHandler = new IoeyeOauth2DataHandler();
			if (!oauth2Exceptions.unsupportedResponseType(json)) {
				if (!ioeyeOauth2DataHandler.validateClientByClientId(clientId)) {
					AuthInfo authInfo = ioeyeOauth2DataHandler
							.createOrUpdateAuthInfo(clientId, userId, scope);
					return Results.redirect(redirect_uri + "?code="
							+ authInfo.getAuthorizationCode());
				} else {
					params.put("error", "invaid client_id");
					params.put(
							"error_description",
							"There is no App with "
									+ json.get("client_id")
									+ " client id. Please check your cllient id and try again.");
					jsonOutput = gson.toJson(params);
					return ok(jsonOutput);
				}
			} else {
				params.put("error", "unsupported response_type");
				params.put("error_description", "Response code must be code.");
				jsonOutput = gson.toJson(params);
				return ok(jsonOutput);
			}

		} else {
			params.put("error", "invaid request");
			params.put("error_description", invalidAuthrequest);
			jsonOutput = gson.toJson(params);
			return ok(jsonOutput);
		}
	}

	@Transactional
	public Result accessToken() {
		Request request = request();
		JsonNode json = request.body().asJson();
		IoeyeOauth2Request ioeyeOauth2Request = new IoeyeOauth2Request(request);
		IoeyeOauth2DataHandler ioeyeOauth2DataHandler = new IoeyeOauth2DataHandler();
		String grantType = json.get("grant_type").asText();
		String clientId = json.get("client_id").asText();
		String clientSecret = json.get("client_secret").asText();
		String code = json.get("code").asText();
		String redirectUri = json.get("redirect_uri").asText();
		AuthInfo authInfo = ioeyeOauth2DataHandler.getAuthInfoByCode(code);
		String appClientId = authInfo.getClientId();
		App app = App.findByIdAndSecret(clientId, clientSecret);

		Oauth2Exceptions oauth2Exceptions = new Oauth2Exceptions();
		String invalidTokenRequest = oauth2Exceptions.invalidTokenRequest(json);
		String jsonOutput = "";
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		Map<String, String> params = new HashMap<String, String>();
		if (ioeyeOauth2Request.validateAccessTokenRequest()) {
			if (oauth2Exceptions.InvalidGrant(json) == false) {
				if (oauth2Exceptions.RedirectUriMismatch(json) == false) {
					if (appClientId.equals(app.getClientId())) {
						AccessToken accessToken = ioeyeOauth2DataHandler
								.createOrUpdateAccessToken(authInfo);
						jsonOutput = gson.toJson(accessToken);
						return ok(jsonOutput);
					} else {
						params.put("error", "invalid code");
						params.put("error_description",
								"Authorization code is invalid, please try again.");
						jsonOutput = gson.toJson(params);
						return ok(jsonOutput);
					}
				} else {
					params.put("error", "redirect_uri mismatch");
					params.put(
							"error_description",
							"redirect_uri that is present in param and the redirect_url which is stored for this app on databse does not match.");
					jsonOutput = gson.toJson(params);
					return ok(jsonOutput);
				}
			} else {
				params.put("error", "invaid grant_type");
				params.put("error_description",
						" Grant Type must be set to authorization_code.");
				jsonOutput = gson.toJson(params);
				return ok(jsonOutput);
			}
		} else {
			return ok(invalidTokenRequest);
		}
	}

	@Transactional
	public boolean validateAccessToken(Request request) {
		JsonNode json = request.body().asJson();
		String Token = json.get("access_token").asText();
		IoeyeOauth2DataHandler ioeyeOauth2DataHandler = new IoeyeOauth2DataHandler();
		AccessToken accesToken = ioeyeOauth2DataHandler.getAccessToken(Token);
		if (accesToken == null) {
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean authenticateApiRequest(Request request) {
		JsonNode json = request.body().asJson();
		String Token = json.get("access_token").asText();
		IoeyeOauth2DataHandler ioeyeOauth2DataHandler = new IoeyeOauth2DataHandler();
		AccessToken accesToken = ioeyeOauth2DataHandler.getAccessToken(Token);
		if (accesToken == null) {
			return false;
		}
		return true;
	}
}
