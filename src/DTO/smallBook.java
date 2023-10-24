package DTO;

import java.util.Vector;

public class smallBook {
	private String ISBN;
	private String name;
	private Vector<String> author;
	private int num;
	private int lost;
	public smallBook() {}
	private smallBook(String name,Vector<String> author,int num,int lost) {
		this.name=name;
		this.author=author;
		this.num=num;
		this.lost=lost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<String> getAuthor() {
		return author;
	}
	public void setAuthor(Vector<String> author) {
		this.author = author;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getLost() {
		return lost;
	}
	public void setLost(int lost) {
		this.lost = lost;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
}

