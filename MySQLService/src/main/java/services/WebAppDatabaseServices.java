package services;

import repositories.MySQLRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dataModels.UserDataModel;

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

	public boolean UpdataOnePersonProfile_PassWord(String email, String PassWord) {
		return mySQLRepository.UpdataOnePersonProfile_PassWord(email, PassWord);
	}
}
