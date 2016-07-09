package controllers;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dataModels.AdsModel;
import dataModels.CreateAccountResponseModel;
import dataModels.NormalHttpResponse;
import dataModels.ProfileDataModel;
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
	public @ResponseBody CreateAccountResponseModel createAccount(@RequestParam("email") String email,
			@RequestParam("password") String passWord, @RequestParam("lastname") String lastName,
			@RequestParam("firstname") String firstName) {
		CreateAccountResponseModel circleStandardHttpResponse = new CreateAccountResponseModel();
		UserDataModel userDataModel = new UserDataModel(passWord, lastName, firstName, email);
		circleStandardHttpResponse.setIsSucceed(webAppDatabaseServices.CreateNewAccount(userDataModel));
		circleStandardHttpResponse.setInformation("Null");
		return circleStandardHttpResponse;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public @ResponseBody NormalHttpResponse updatePassword(@RequestParam("email") String email,
			@RequestParam("oldPassword") String oldPassWord, @RequestParam("newPassword") String newPassWord,
			@RequestParam("conformNewPassword") String conformNewPassword) {
		NormalHttpResponse normalHttpResponse = new NormalHttpResponse();
		normalHttpResponse
				.setSucceed(webAppDatabaseServices.UpdataPassWord(email, oldPassWord, newPassWord, conformNewPassword));
		normalHttpResponse.setInformation("Null");
		return normalHttpResponse;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody NormalHttpResponse login(@RequestParam("email") String email,
			@RequestParam("password") String password) {
		NormalHttpResponse normalHttpResponse = new NormalHttpResponse();
		normalHttpResponse.setSucceed(webAppDatabaseServices.Login(email, password));
		normalHttpResponse.setInformation("Null");
		return normalHttpResponse;
	}

	@RequestMapping(value = "/PostAds", method = RequestMethod.POST)
	public @ResponseBody NormalHttpResponse PostAds(@RequestParam("PostEmail") String PostEmail,
			@RequestParam("Category") String Category, @RequestParam("Contents") String Contents,
			@RequestParam("ContactEmail") String ContactEmail, @RequestParam("ContactCell") Long ContactCell) {
		System.out.println(Contents);
		NormalHttpResponse normalHttpResponse = new NormalHttpResponse();
		normalHttpResponse
				.setSucceed(webAppDatabaseServices.PostAd(PostEmail, Category, Contents, ContactEmail, ContactCell));
		normalHttpResponse.setInformation("Null");
		return normalHttpResponse;
	}

	@RequestMapping(value = "/FindAds", method = RequestMethod.POST)
	public @ResponseBody NormalHttpResponse PostAds(@RequestParam("Table") String Table,
			@RequestParam("Category") String Category) {
		NormalHttpResponse normalHttpResponse = new NormalHttpResponse();
		ArrayList<AdsModel> result = webAppDatabaseServices.FindAd(Table, Category);
		if (result == null) {
			normalHttpResponse.setSucceed(false);
		} else {
			normalHttpResponse.setSucceed(true);
		}
		normalHttpResponse.setInformation(webAppDatabaseServices.FindAd(Table, Category));
		return normalHttpResponse;
	}

	@RequestMapping(value = "/Profile", method = RequestMethod.POST)
	public @ResponseBody NormalHttpResponse PostAds(@RequestParam("PostEmail") String PostEmail) {
		NormalHttpResponse normalHttpResponse = new NormalHttpResponse();
		ProfileDataModel result = webAppDatabaseServices.SearchAdofOneUser(PostEmail);
		if (result == null) {
			System.out.print("lll");
		}
		if (result.getUserDataModel() == null) {
			normalHttpResponse.setSucceed(false);
		} else {
			normalHttpResponse.setSucceed(true);
		}
		normalHttpResponse.setInformation(webAppDatabaseServices.SearchAdofOneUser(PostEmail));
		return normalHttpResponse;
	}

	@RequestMapping(value = "/DeleteAd", method = RequestMethod.POST)
	public @ResponseBody NormalHttpResponse PostAds(@RequestParam("PostID") int PostID,
			@RequestParam("Table") String Table) {
		System.out.println(Table);
		NormalHttpResponse normalHttpResponse = new NormalHttpResponse();
		normalHttpResponse.setSucceed(webAppDatabaseServices.DeleteOneAd(PostID, Table));
		normalHttpResponse.setInformation("Null");
		return normalHttpResponse;
	}

}