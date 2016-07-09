package repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.stereotype.Repository;
import dataModels.AdsModel;
import dataModels.UserDataModel;
import dataModels.ProfileDataModel;

@Repository("MySQLRepository")
public class MySQLRepository {
	String url;
	String user;
	String password;
	Connection con = null;
	Statement st = null;

	public MySQLRepository() {
		try {
			String home = System.getProperty("user.home");
			BufferedReader br = new BufferedReader(new FileReader(home + "/mysqlCredentials"));
			try {
				url = br.readLine();
				user = br.readLine();
				password = br.readLine();
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, user, password);
				st = con.createStatement();
			} finally {
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean InsertOnePersonRecord(UserDataModel userDataModel) {
		try {
			st.executeUpdate("INSERT INTO Users (Email, PassWord, LastName, FirstName) VALUES ( '"
					+ userDataModel.getEmail() + "','" + userDataModel.getPassWord() + "','"
					+ userDataModel.getLastName() + "','" + userDataModel.getFirstName() + "')");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean UpdataPassWord(String email, String oldPassWord, String newPassWord, String conformNewPassword) {
		try {
			System.out.println(email);
			System.out.println(oldPassWord);
			System.out.println(newPassWord);
			System.out.println(conformNewPassword);
			ResultSet rs = st.executeQuery("SELECT PassWord FROM Users WHERE Email = '" + email + "'");
			if (rs.next()) {
				if (rs.getString(1).equals(oldPassWord) && newPassWord.equals(conformNewPassword)) {
					st.executeUpdate("UPDATE Users SET PassWord='" + newPassWord + "' WHERE Email ='" + email + "'");
					System.out.println("sign up is ok");
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean Login(String email, String PassWord) {
		try {
			ResultSet rs = st.executeQuery("SELECT PassWord FROM Users WHERE Email = '" + email + "'");
			if (rs.next()) {
				if (rs.getString(1).equals(PassWord)) {
					// System.out.println("log in is ok");
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean PostAd(String PostEmail, String Category, String Contents, String ContactEmail, Long ContactCell) {
		try {
			java.util.Date uDate = new java.util.Date();
			java.sql.Date date = new java.sql.Date(uDate.getTime());
			System.out.println(Category);
			String[] list = Category.split("-");
			String table = list[0];
			Category = list[1];

			Random rand = new Random();
			int n = 0;
			ResultSet rs;
			do {
				n = rand.nextInt(100000) + 1;
				rs = st.executeQuery("SELECT * FROM " + table + " WHERE PostID = '" + n + "'");
			} while (rs.wasNull());
			System.out.println("INSERT INTO " + table
					+ "(PostID, PostEmail, Category, Contents, ContactEmail, ContactCell, PostDate) VALUES ('" + n
					+ "','" + PostEmail + "','" + Category + "','" + Contents + "','" + ContactEmail + "','"
					+ ContactCell + "','" + date + "')");
			st.executeUpdate("INSERT INTO " + table
					+ "(PostID, PostEmail, Category, Contents, ContactEmail, ContactCell, PostDate) VALUES ( '" + n
					+ "','" + PostEmail + "','" + Category + "','" + Contents + "','" + ContactEmail + "','"
					+ ContactCell + "','" + date + "')");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<AdsModel> FindAd(String Table, String Category) {
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM " + Table + " WHERE Category='" + Category + "'");
			ArrayList<AdsModel> list = new ArrayList<AdsModel>();
			while (rs.next()) {
				System.out.print("Column 1 returned: ");
				AdsModel adsModel = new AdsModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getLong(6), rs.getDate(7), Table);
				list.add(adsModel);
				System.out.println(rs.getDate(7));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ProfileDataModel SearchAdofOneUser(String PostEmail) {
		try {
			System.out.print("yesssss");
			ArrayList<AdsModel> list = new ArrayList<AdsModel>();
			String[] tableLists = new String[6];
			tableLists[0] = "Jobs";
			tableLists[1] = "House";
			tableLists[2] = "Electronics";
			tableLists[3] = "Merchandise";
			tableLists[4] = "Restuarant";
			tableLists[5] = "Resorts";
			AdsModel adsModel = null;
			for (String s : tableLists) {
				ResultSet rs = st.executeQuery("SELECT * FROM " + s + " WHERE PostEmail='" + PostEmail + "'");
				while (rs.next()) {
					System.out.print("Column 1 returned: ");
					adsModel = new AdsModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getLong(6), rs.getDate(7), s);
					list.add(adsModel);
				}
			}

			UserDataModel userDataModel = null;
			ResultSet rs = st.executeQuery("SELECT * FROM Users WHERE Email='" + PostEmail + "'");
			while (rs.next()) {
				userDataModel = new UserDataModel("***", rs.getString(3), rs.getString(4), rs.getString(1));
			}
			ProfileDataModel profileDataModel = new ProfileDataModel(list, userDataModel);
			return profileDataModel;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean DeleteOneAd(int PostID, String Table) {
		try {
			System.out.print("DELETE FROM " + Table + " WHERE PostID='" + PostID + "'");
			int num = st.executeUpdate("DELETE FROM " + Table + " WHERE PostID='" + PostID + "'");
			if (num == 1) {
				return true;
			}
			System.out.print("yes");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}