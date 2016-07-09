package dataModels;

import java.sql.Date;

public class AdsModel {
	private int PostID;
	private String PostEmail;
	private String Category;
	private String Contents;
	private String ContactEmail;
	private String Table;
	private Long ContactCell;
	private Date PostDate;

	public AdsModel(int postID, String postEmail, String category, String contents, String contactEmail,
			Long contactCell, Date postDate, String table) {
		super();
		PostID = postID;
		PostEmail = postEmail;
		Category = category;
		Contents = contents;
		ContactEmail = contactEmail;
		ContactCell = contactCell;
		PostDate = postDate;
		Table = table;
	}

	public int getPostID() {
		return PostID;
	}

	public void setPostID(int postID) {
		PostID = postID;
	}

	public String getPostEmail() {
		return PostEmail;
	}

	public void setPostEmail(String postEmail) {
		PostEmail = postEmail;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getContents() {
		return Contents;
	}

	public void setContents(String contents) {
		Contents = contents;
	}

	public String getContactEmail() {
		return ContactEmail;
	}

	public void setContactEmail(String contactEmail) {
		ContactEmail = contactEmail;
	}

	public Long getContactCell() {
		return ContactCell;
	}

	public void setContactCell(Long contactCell) {
		ContactCell = contactCell;
	}

	public Date getPostDate() {
		return PostDate;
	}

	public void setPostDate(Date postDate) {
		PostDate = postDate;
	}

	public String getTable() {
		return Table;
	}

	public void setTable(String table) {
		this.Table = table;
	}

}
