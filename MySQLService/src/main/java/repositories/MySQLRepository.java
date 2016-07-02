package repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import dataModels.UserDataModel;

@Repository("MySQLRepository")
public class MySQLRepository {
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;

	public MySQLRepository() {
		String url;
		String user;
		String password;

		try {
			String home = System.getProperty("user.home");
			BufferedReader br = new BufferedReader(new FileReader(home + "/mysqlCredentials"));
			try {
				StringBuilder sb = new StringBuilder();
				url = br.readLine();
				user = br.readLine();
				password = br.readLine();
			} finally {
				br.close();
			}

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
		} catch (SQLException ex) {
			System.out.println("hello" + ex.getMessage());
		} catch (Exception e) {

		}
	}

	public boolean InsertOnePersonRecord(UserDataModel userDataModel) {

		try {
			st.executeUpdate("INSERT INTO Users (Email, PassWord, LastName, FirstName) VALUES ( '"
					+ userDataModel.getEmail() + "','" + userDataModel.getPassWord() + "','"
					+ userDataModel.getLastName() + "','" + userDataModel.getFirstName() + "')");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean UpdataOnePersonProfile_PassWord(String email, String PassWord) {
		try {
			st.executeUpdate("UPDATE Users SET PassWord='" + PassWord + "' WHERE Email ='" + email + "'");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}