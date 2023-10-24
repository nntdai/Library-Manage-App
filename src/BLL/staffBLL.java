package BLL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import DAL.staffDAL;
import DTO.Readers;
import DTO.Staff;

public class staffBLL {
    private staffDAL stDAL;

    public staffBLL() throws ClassNotFoundException, SQLException{
    	stDAL=new staffDAL();
    }
    
    public Vector<Staff> getAllAD() throws Exception{
        return stDAL.getAllAD();
    }
    
    public Vector<Staff> getAllQL() throws Exception{
        return stDAL.getAllQL();
    }
    
    public Vector<String> getRoleAD() throws Exception{
    	return stDAL.getRoleAD();
    }
    
    public Vector<String> getRoleQL() throws Exception{
    	return stDAL.getRoleQL();
    }
    
    public Vector<String> getRoleAll() throws Exception{
    	return stDAL.getRoleAll();
    }
    
    public boolean checkTel(String tel) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
    	return stDAL.checkTel(tel);
    }
    
    public boolean checkTelExcept(String tel,int id) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
    	return stDAL.checkTelExcept(tel,id);
    }
    
    public String addStaff(Staff a) throws Exception{
        if(stDAL.addStaff(a)){
            return "Thêm nhân viên thành công";
        }else{
            return "Lỗi! Thêm nhân viên không thành công";
        }
    }
    
    public String updateOneStaff(Staff a) throws Exception{
        if(stDAL.updateOneStaff(a)){
            return "Sửa nhân viên thành công";
        }else{
            return "Lỗi! Sửa nhân viên không thành công";
        }
    }
    
    public String eraShowStaff(Staff a) throws Exception {
    	if(stDAL.eraShowStaff(a)){
            return "Xoá nhân viên thành công";
        }else{
            return "Lỗi! Xoá nhân viên không thành công";
        }
    }
    
    public String addBrandNewRole(String name,String id) throws Exception {
    	if(stDAL.addBrandNewRole(name, id)){
            return "Thêm vai trò mới thành công";
        }else{
            return "Lỗi! Thêm vai trò mới không thành công";
        }
    }
    
    public Vector<Staff> allOutSearchAD(String str) throws Exception{
        return stDAL.allOutSearchAD(str);
    }
    
    public Vector<Staff> allOutSearchQL(String str) throws Exception{
        return stDAL.allOutSearchQL(str);
    }
    
    public boolean addRoleName(String name) throws Exception {
    	return stDAL.ckNewRole(name);
    }
    
    public boolean addRoleNameID(String id) throws Exception {
    	return stDAL.ckNewRoleID(id);
    }
    
    public Staff findbyID(int id) throws Exception {
    	return stDAL.findbyID(id);
    }
}