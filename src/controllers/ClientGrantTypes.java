package controllers;

import models.AccessToken;
import models.Account;
import models.ClientGrantType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import play.mvc.Controller;
import play.mvc.Result;
import play.db.jpa.Transactional;
import repository.AccessTokensRepository;
import repository.AccountsRepository;
import repository.ClientGrantTypesRepository;

public class ClientGrantTypes extends Controller {

	@Transactional
	public static Result index() {
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson.toJson(ClientGrantTypesRepository
				.clientGrantTypeList());
		ClientGrantTypesRepository.clientGrantTypeList();
		return ok(jsonOutput);
	}

	@Transactional
	public static Result show(Long id) {
		ClientGrantType clientGrantType = new ClientGrantType(id);
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson.toJson(ClientGrantTypesRepository
				.get(clientGrantType));
		return ok(jsonOutput);
	}

	@Transactional
	public static Result create() {
		String params = request().body().asJson().toString();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		ClientGrantType clientGrantType = gson.fromJson(params,
				ClientGrantType.class);
		ClientGrantTypesRepository.create(clientGrantType);
		return ok(params);
	}

	@Transactional
	public static Result update(Long id) {
		String params = request().body().asJson().toString();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		ClientGrantType clientGrantType = gson.fromJson(params,
				ClientGrantType.class);
		ClientGrantTypesRepository.update(clientGrantType, id);
		String jsonOutput = gson.toJson(clientGrantType);
		return ok(jsonOutput);
	}

	@Transactional
	public static Result delete(Long id) {
		ClientGrantType clientGrantType = new ClientGrantType(id);
		ClientGrantTypesRepository.delete(ClientGrantTypesRepository
				.get(clientGrantType));
		return ok("Requested client type grant has been successfully deleted.");
	}
}
