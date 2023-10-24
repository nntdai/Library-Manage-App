package DAL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import DTO.Readers;
import connection.ConnectDB;

public class readerDAL extends ConnectDB{

    public readerDAL() throws ClassNotFoundException, SQLException {
        super();
        //TODO Auto-generated constructor stub
    }

    public Vector<Readers> getAll() throws Exception{
        Vector<Readers> arr = new Vector<Readers>();
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
                String sql="select * from Reader where status=1";
                Statement stmt = ConnectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    Readers u = new Readers();
                    u.setId(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    java.sql.Date lDate = rs.getDate(5);
                    if(lDate==null) {
                    	u.setFineDate(null);
                    }else {
                    	u.setFineDate(lDate.toLocalDate());
                    }
                    arr.add(u);
                }
            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return arr;
    }

    public boolean addReader(Readers a) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
        boolean flag=false;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
                String sql="Insert into Reader(name,tel,address) values (?,?,?)";
                PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
                preparedStatement.setString(1, a.getName());
                preparedStatement.setString(2, a.getTel());
                preparedStatement.setString(3, a.getAddress());
                if(preparedStatement.executeUpdate()>0){
                    flag=true;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
    
    public void updateFineDate() throws ClassNotFoundException, SQLException {
    	ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	LocalDate cuDate=LocalDate.now();
            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = cuDate.format(formatter);
            	String sql="update Reader set fineDate=null where fineDate<='"+formattedDate+"'";
            	Statement stm=ConnectDB.conn.createStatement();
            	stm.executeUpdate(sql);
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
    }
    
    public Readers findbyID (int id) throws Exception {
    	Readers a=new Readers();
    	ConnectDB db=new ConnectDB();
    	if(ConnectDB.conn!=null){
            try{
            	String sql="select * from Reader where id=?";
            	PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
            	preparedStatement.setInt(1, id);
            	ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()) {
                	a.setId(rs.getInt(1));
                	a.setName(rs.getString(2));
                	a.setTel(rs.getString(3));
                    a.setAddress(rs.getString(4));
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
    	return a;
    }

    public boolean checkTel(String tel) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
        boolean flag=true;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="select * from Reader where tel=?";
            	PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
                preparedStatement.setString(1, tel);
                ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()) {
                	flag=false;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
    
    public boolean checkTelExcept(String tel,int id) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
        boolean flag=true;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="select * from Reader where tel=? and id!=?";
            	PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
                preparedStatement.setString(1, tel);
                preparedStatement.setInt(2, id);
                ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()) {
                	flag=false;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
    
    public boolean updateOneReader(Readers a) throws Exception {
    	boolean flag=true;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="update Reader set name=? , tel=? , address=? where id=? ";
            	PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
                preparedStatement.setString(1, a.getName());
                preparedStatement.setString(2, a.getTel());
                preparedStatement.setString(3, a.getAddress());
                preparedStatement.setInt(4, a.getId());
                if(preparedStatement.executeUpdate()>0){
                    flag=true;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
    
    public boolean eraShowReader(Readers a) throws Exception{
    	boolean flag=false;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="update Reader set status=0 where id=? ";
            	PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
                preparedStatement.setInt(1, a.getId());
                if(preparedStatement.executeUpdate()>0){
                    flag=true;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<Readers> allOutSearch(String fStr) throws ClassNotFoundException, SQLException{
    	Vector<Readers> arr = new Vector<Readers>();
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String[] str = fStr.split(" ");
            	StringBuilder query = new StringBuilder("SELECT * FROM Reader WHERE status=1 ");
                for (int i = 0; i < str.length; i++) {
                	query.append("AND CONCAT(id, ' ', name, ' ', tel, ' ', address) LIKE CONCAT( '%',?,'%') ");
                }
                PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(query.toString());
                for (int i = 0; i < str.length; i++) {
                     preparedStatement.setString(i+1, str[i]);
                }
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                	Readers u = new Readers();
                    u.setId(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    java.sql.Date lDate = rs.getDate(5);
                    if(lDate==null) {
                    	u.setFineDate(null);
                    }else {
                    	u.setFineDate(lDate.toLocalDate());
                    }
                    arr.add(u);
                }

            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return arr;
    }
}
