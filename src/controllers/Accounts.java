package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.db.jpa.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Account;
import repository.AccountsRepository;

public class Accounts extends Controller {

	@Transactional
	public static Result index() {
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson.toJson(AccountsRepository.accountList());
		AccountsRepository.accountList();
		return ok(jsonOutput);
	}

	@Transactional
	public static Result show(Long id) {
		Account account = new Account(id);
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson.toJson(AccountsRepository.get(account));
		return ok(jsonOutput);
	}

	@Transactional
	public static Result create() {
		String params = request().body().asText();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		Account account = gson.fromJson(params, Account.class);
		AccountsRepository.create(account);
		return ok(params);
	}

	@Transactional
	public static Result update(Long id) {
		String params = request().body().asJson().toString();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		Account account = gson.fromJson(params, Account.class);
		AccountsRepository.update(account, id);
		String jsonOutput = gson.toJson(account);
		return ok(jsonOutput);
	}

	@Transactional
	public static Result delete(Long id) {
		return ok("Requested company deleted successfully.");
	}

	@Transactional
	public static Result managedAccounts() {
		return ok();
	}

}
