package DTO;
public class sach_DTO{
	private int id = 0;
	private String name = "";
	private boolean status;
	public sach_DTO(int id, String name, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}
	public sach_DTO()
	{
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "sach_DTO [id=" + id + ", name=" + name + ", status=" + status + "]";
	}
	
	
}