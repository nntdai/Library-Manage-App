package BLL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import DAL.readerDAL;
import DTO.Readers;

public class readerBLL {
    private readerDAL readDAL;

    public readerBLL() throws ClassNotFoundException, SQLException{
        readDAL=new readerDAL();
    }

    public Vector<Readers> getAll() throws Exception{
        return readDAL.getAll();
    }
    
    public void updateFineDate() throws ClassNotFoundException, SQLException {
    	readDAL.updateFineDate();
    }

    public boolean checkTel(String tel) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
    	return readDAL.checkTel(tel);
    }
    
    public boolean checkTelExcept(String tel,int id) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
    	return readDAL.checkTelExcept(tel,id);
    }
    
    public Readers findbyID(int id) throws Exception {
    	return readDAL.findbyID(id);
    }
    
    public String addReader(Readers a) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
        if(readDAL.addReader(a)){
            return "Thêm độc giả thành công";
        }else{
            return "Lỗi! Thêm độc giả không thành công";
        }
    }
    
    public String updateOneReader(Readers a) throws Exception {
    	if(readDAL.updateOneReader(a)){
            return "Sửa thông tin thành công";
        }else{
            return "Lỗi! Sửa thông tin không thành công";
        }
    }
    
    public String eraShowReader(Readers a) throws Exception {
    	if(readDAL.eraShowReader(a)){
            return "Xoá độc giả thành công";
        }else{
            return "Lỗi! Xoá độc giả không thành công";
        }
    }
    
    public Vector<Readers> allOutSearch(String fStr) throws Exception{
        return readDAL.allOutSearch(fStr);
    }
}
