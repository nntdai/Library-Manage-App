package DTO;

import java.time.LocalDate;
import java.util.Vector;

public class smallBC {
	private int id;
	private int num;
	private LocalDate startDate;
	private LocalDate realReDate;
	private String staffName;
	private Vector<smallBook> list;
	public smallBC() {}
	public smallBC(int id,int num, LocalDate startDate,LocalDate realReDate,String staffName,Vector<smallBook> list) {
		this.id=id;
		this.num=num;
		this.startDate=startDate;
		this.realReDate=realReDate;
		this.staffName=staffName;
		this.list=list;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getRealReDate() {
		return realReDate;
	}
	public void setRealReDate(LocalDate realReDate) {
		this.realReDate = realReDate;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Vector<smallBook> getList() {
		return list;
	}
	public void setList(Vector<smallBook> list) {
		this.list = list;
	}
}

