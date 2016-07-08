package dataModels;

import java.util.ArrayList;

public class ProfileDataModel {
	ArrayList<AdsModel> adsModel;
	UserDataModel userDataModel;

	public ProfileDataModel(ArrayList<AdsModel> adsModel, UserDataModel userDataModel) {
		super();
		this.adsModel = adsModel;
		this.userDataModel = userDataModel;
	}

	public ArrayList<AdsModel> getAdsModel() {
		return adsModel;
	}

	public void setAdsModel(ArrayList<AdsModel> adsModel) {
		this.adsModel = adsModel;
	}

	public UserDataModel getUserDataModel() {
		return userDataModel;
	}

	public void setUserDataModel(UserDataModel userDataModel) {
		this.userDataModel = userDataModel;
	}

}
