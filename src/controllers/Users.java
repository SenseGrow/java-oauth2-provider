package controllers;

import models.User;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import repository.UsersRepository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Users extends Controller {
	@Transactional
	public static Result index() {
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson.toJson(UsersRepository.getUserList());
		return ok(jsonOutput);
	}
	
	@Transactional
	public static Result show(Long id) {
		User user = new User(id);
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson.toJson(UsersRepository.get(user));
		return ok(jsonOutput);
	}

	@Transactional
	public static Result create() {
		String params = request().body().asJson().toString();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		User user = gson.fromJson(params, User.class);
		UsersRepository.create(user);
		return ok(params);
	}

	@Transactional
	public static Result update(Long id) {
		String params = request().body().asJson().toString();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		User user = gson.fromJson(params, User.class);
		UsersRepository.update(user, id);
		String jsonOutput = gson.toJson(user);
		return ok(jsonOutput);
	}

	@Transactional
	public static Result delete(Long id) {
		return ok("Requested user deleted successfully.");
	}

	@Transactional
	public static Result managedusers() {
		return ok();
	}
}
