package DTO;

public class bookAuthor {
	private int authorID;
	private String ISBN;
	public bookAuthor(int authorID, String iSBN) {
		super();
		this.authorID = authorID;
		ISBN = iSBN;
	}
	public bookAuthor()
	{
		super();
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	@Override
	public String toString() {
		return "bookAuthor [authorID=" + authorID + ", ISBN=" + ISBN + "]";
	}

}
