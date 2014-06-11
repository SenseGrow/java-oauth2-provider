package controllers;

import models.AccessToken;
import play.mvc.Controller;
import play.mvc.Result;
import play.db.jpa.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import repository.AccessTokensRepository;

public class AccessTokensController extends Controller {

	@Transactional
	public static Result index() {
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson.toJson(AccessTokensRepository
				.accessTokenList());
		return ok(jsonOutput);
	}

	@Transactional
	public static Result show(Long id) {
		AccessToken accessToken = new AccessToken(id);
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson
				.toJson(AccessTokensRepository.get(accessToken));
		return ok(jsonOutput);
	}

	@Transactional
	public static Result create() {
		String params = request().body().asJson().toString();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		AccessToken accessToken = gson.fromJson(params, AccessToken.class);
		AccessTokensRepository.create(accessToken);
		return ok(params);
	}

	@Transactional
	public static Result update(Long id) {
		String params = request().body().asJson().toString();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		AccessToken accessToken = gson.fromJson(params, AccessToken.class);
		AccessTokensRepository.update(accessToken, id);
		String jsonOutput = gson.toJson(accessToken);
		return ok(jsonOutput);
	}

	@Transactional
	public static Result delete(Long id) {
		AccessToken accessToken = new AccessToken(id);
		AccessTokensRepository.delete(AccessTokensRepository.get(accessToken));
		return ok("Requested acces token has been successfully deleted.");
	}
}
