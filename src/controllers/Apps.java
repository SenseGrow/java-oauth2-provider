package controllers;

import models.App;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import play.mvc.Controller;
import play.mvc.Result;
import play.db.jpa.Transactional;
import repository.AppsRepository;

public class Apps extends Controller {

	@Transactional
	public static Result index() {
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson.toJson(AppsRepository.AppList());
		return ok(jsonOutput);
	}

	@Transactional
	public static Result show(Long id) {
		App app = new App(id);
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		String jsonOutput = gson.toJson(AppsRepository.get(app));
		return ok(jsonOutput);
	}

	@Transactional
	public static Result create() {
		String params = request().body().asJson().toString();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		App app = gson.fromJson(params, App.class);
		AppsRepository.create(app);
		return ok(params);
	}

	@Transactional
	public static Result update(Long id) {
		String params = request().body().asJson().toString();
		Gson gson = new GsonBuilder().setVersion(1.0).create();
		App app = gson.fromJson(params, App.class);
		AppsRepository.update(app, id);
		String jsonOutput = gson.toJson(app);
		return ok(jsonOutput);
	}

	@Transactional
	public static Result delete(Long id) {
		App app = new App(id);
		AppsRepository.delete(AppsRepository.get(app));
		return ok("Requested app has been successfully deleted.");
	}
}
