package DTO;
import java.util.Date;
public class supply_Card {
	private int id;
	private Date supDate;
	private String provider;
	private int staffID;
		
	public supply_Card(int id, Date supDate, String provider, int staffID) {
		super();
		this.id = id;
		this.supDate = supDate;
		this.provider = provider;
		this.staffID = staffID;
	}

	public supply_Card()
	{
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getSupDate() {
		return supDate;
	}

	public void setSupDate(Date supDate) {
		this.supDate = supDate;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	@Override
	public String toString() {
		return "supply_Card [id=" + id + ", supDate=" + supDate + ", provider=" + provider + ", staffID=" + staffID
				+ "]";
	}
	
}
