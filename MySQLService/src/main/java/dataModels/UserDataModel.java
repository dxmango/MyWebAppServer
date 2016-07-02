package dataModels;

public class UserDataModel {
	private String PassWord;
	private String LastName;
	private String FirstName;
	private String Email;

	public String getPassWord() {
		return PassWord;
	}

	public UserDataModel(String passWord, String lastName, String firstName, String email) {
		super();
		PassWord = passWord;
		LastName = lastName;
		FirstName = firstName;
		Email = email;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

}
