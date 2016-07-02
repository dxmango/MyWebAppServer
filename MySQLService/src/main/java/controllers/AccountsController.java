package controllers;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dataModels.CreateAccountResponseModel;
import dataModels.UserDataModel;
import services.WebAppDatabaseServices;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController

public class AccountsController {

	@Autowired
	@Qualifier("DatabaseServices")
	WebAppDatabaseServices webAppDatabaseServices;

	@RequestMapping(value = "/create-new-account", method = RequestMethod.POST)
	public @ResponseBody CreateAccountResponseModel setTest(@RequestParam("email") String email,
			@RequestParam("password") String passWord, @RequestParam("lastname") String lastName,
			@RequestParam("firstname") String firstName) {
		CreateAccountResponseModel circleStandardHttpResponse = new CreateAccountResponseModel();
		UserDataModel userDataModel = new UserDataModel(passWord, lastName, firstName, email);
		circleStandardHttpResponse.setIsSucceed(webAppDatabaseServices.CreateNewAccount(userDataModel));
		circleStandardHttpResponse.setInformation("Null");
		return circleStandardHttpResponse;
	}
}