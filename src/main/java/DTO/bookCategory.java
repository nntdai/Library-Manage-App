package DTO;

public class bookCategory {
	private int categoryID;
	private String ISBN;
	public bookCategory(int categoryID, String iSBN) {
		super();
		this.categoryID = categoryID;
		ISBN = iSBN;
	}
	public bookCategory()
	{
		super();
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	@Override
	public String toString() {
		return "bookCategory [categoryID=" + categoryID + ", ISBN=" + ISBN + "]";
	}
	
}
