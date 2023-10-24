package BLL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import DAL.smallBCDAL;
import DTO.smallBC;

public class smallBCBLL {
    private smallBCDAL sbc;
    
    public smallBCBLL() throws ClassNotFoundException, SQLException{
    	sbc=new smallBCDAL();
    }
    
    public Vector<smallBC> getAll(int id) throws Exception{
    	return sbc.getAll(id);
    }
    
    public Vector<smallBC> getReturn(int id) throws Exception{
    	Vector<smallBC> tmp= sbc.getAll(id);
    	Vector<smallBC> arr=new Vector<smallBC>();
    	for(int i=0;i<tmp.size();i++) {
    		if(tmp.get(i).getRealReDate()!=null) {
    			arr.add(tmp.get(i));
    		}
    	}
    	return arr;
    }
    
    public Vector<smallBC> getNoReturn(int id) throws Exception{
    	Vector<smallBC> tmp= sbc.getAll(id);
    	Vector<smallBC> arr=new Vector<smallBC>();
    	for(int i=0;i<tmp.size();i++) {
    		if(tmp.get(i).getRealReDate()==null) {
    			arr.add(tmp.get(i));
    		}
    	}
    	return arr;
    }
}