package DTO;

public class detailSC {
	private int scID;
	private String ISBN;
	private int num;
	public detailSC(int scID, String iSBN, int num) {
		super();
		this.scID = scID;
		ISBN = iSBN;
		this.num = num;
	}
	public detailSC()
	{
		super();
	}
	public int getScID() {
		return scID;
	}
	public void setScID(int scID) {
		this.scID = scID;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "detailSC [scID=" + scID + ", ISBN=" + ISBN + ", num=" + num + "]";
	}
	
}
