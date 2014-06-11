package controllers;

import models.AccessToken;
import models.Account;
import models.AuthInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import play.mvc.Controller;
import play.mvc.Result;
import play.db.jpa.Transactional;
import repository.AccessTokensRepository;
import repository.AccountsRepository;
import repository.AuthInfosRepository;

public class AuthInfos extends Controller {

	@Transactional
	public static Result index() {
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson.toJson(AuthInfosRepository.authInfoList());
		return ok(jsonOutput);
	}

	@Transactional
	public static Result show(Long id) {
		AuthInfo authInfo = new AuthInfo(id);
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson.toJson(AuthInfosRepository.get(authInfo));
		return ok(jsonOutput);
	}

	@Transactional
	public void create() {
		String params = request().body().asJson().toString();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		AuthInfo authInfo = gson.fromJson(params, AuthInfo.class);
		AuthInfosRepository.create(authInfo);
	}

	@Transactional
	public static Result update(Long id) {
		String params = request().body().asJson().toString();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		AuthInfo authInfo = gson.fromJson(params, AuthInfo.class);
		AuthInfosRepository.update(authInfo, id);
		String jsonOutput = gson.toJson(authInfo);
		return ok(jsonOutput);
	}

	@Transactional
	public static Result delete(Long id) {
		AuthInfo authInfo = new AuthInfo(id);
		AuthInfosRepository.delete(AuthInfosRepository.get(authInfo));
		return ok("Requested auth info has been successfully deleted.");
	}
}
