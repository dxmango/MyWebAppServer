package services;

import repositories.MySQLRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dataModels.UserDataModel;
import dataModels.AdsModel;
import dataModels.ProfileDataModel;

@Service("DatabaseServices")
public class WebAppDatabaseServices {

	@Autowired
	@Qualifier("MySQLRepository")
	MySQLRepository mySQLRepository;

	WebAppDatabaseServices() {

	}

	public boolean CreateNewAccount(UserDataModel userDataModel) {
		return mySQLRepository.InsertOnePersonRecord(userDataModel);
	}

	public boolean UpdataPassWord(String email, String oldPassWord, String newPassWord, String conformNewPassword) {
		return mySQLRepository.UpdataPassWord(email, oldPassWord, newPassWord, conformNewPassword);
	}

	public boolean Login(String email, String PassWord) {
		return mySQLRepository.Login(email, PassWord);
	}

	public boolean PostAd(String PostEmail, String Category, String Contents, String ContactEmail, Long ContactCell) {
		return mySQLRepository.PostAd(PostEmail, Category, Contents, ContactEmail, ContactCell);
	}

	public ArrayList<AdsModel> FindAd(String Table, String Category) {
		return mySQLRepository.FindAd(Table, Category);
	}

	public ProfileDataModel SearchAdofOneUser(String PostEmail) {
		return mySQLRepository.SearchAdofOneUser(PostEmail);
	}

	public boolean DeleteOneAd(int PostID, String Category) {
		return mySQLRepository.DeleteOneAd(PostID, Category);
	}
}
