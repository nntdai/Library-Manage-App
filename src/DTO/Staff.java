package DTO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class Staff extends Human{
    private int managerID;
    private String managerName;
    private String username;
    private String role;
    private String roleID;
    private String password;

    public Staff(){}

    public Staff(int id,String tel,String name,String address,int managerID,String managerName){
        super(managerID, tel, managerName, address);
        this.managerID=managerID;
        this.managerName=managerName;
    }

    public Staff(int id,String tel,String name,String address,String username,String role){
        super(id, tel, name, address);
        this.username=username;
        this.role=role;
    }
    
    public Staff(String name,String tel,String address,String username,String password,int managerID,String role){
        super(name, tel, address);
        this.username=username;
        this.password=password;
        this.managerID=managerID;
        this.role=role;
    }

    public int getManagerId(){
        return managerID;
    }

    public void setManagerId(int managerID){
        this.managerID=managerID;
    }

    public void setManagerName(String managerName){
        this.managerName=managerName;
    }

    public String getManagerName(){
        return managerName;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String hashPassword(String password) throws Exception{
    	MessageDigest digest=MessageDigest.getInstance("SHA-256");
    	byte[] bytes=password.getBytes(StandardCharsets.UTF_8);
    	byte[] hash=digest.digest(bytes);
    	String hashPass=Base64.getEncoder().encodeToString(hash);
    	return hashPass;
    }
	
	public boolean compare(String name,String tel,String address,String username,String password,String role) throws Exception {
		if(!this.getName().toUpperCase().equals(name.toUpperCase())) {
			return false;
		}
		if(!this.getTel().equals(tel)) {
			return false;
		}
		if(!this.getAddress().toUpperCase().equals(address.toUpperCase())) {
			return false;
		}
		if(!this.getAddress().toUpperCase().equals(address.toUpperCase())) {
			return false;
		}
		if(!this.getUsername().equals(username)) {
			return false;
		}
		if(!this.getRole().equals(role)) {
			return false;
		}
		if(password.trim()!="") {
			return false;
		}
		return true;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
}