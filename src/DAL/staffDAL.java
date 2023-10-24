package DAL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Vector;

import DTO.Readers;
import DTO.Staff;
import connection.ConnectDB;

public class staffDAL extends ConnectDB{

    public staffDAL() throws ClassNotFoundException, SQLException {
        super();
        //TODO Auto-generated constructor stub
    }

    public Vector<Staff> getAllAD() throws Exception{
        Vector<Staff> arr = new Vector<Staff>();
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
                String sql="select Staff.id as sId,Staff.name as sName,tel,address,userName,Roles.name as rName from Staff join Account on Staff.id=Account.userID join Roles on Account.positionID=Roles.id where Staff.status=1;";
                Statement stmt = ConnectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    Staff u = new Staff();
                    u.setId(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    u.setUsername(rs.getString(5));
                    u.setRole(rs.getString(6));
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
    
    public Vector<Staff> getAllQL() throws Exception{
        Vector<Staff> arr = new Vector<Staff>();
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
                String sql="select Staff.id as sId,Staff.name as sName,tel,address,userName,Roles.name as rName from Staff join Account on Staff.id=Account.userID join Roles on Account.positionID=Roles.id where Staff.status=1 and Roles.id!='AD';";
                Statement stmt = ConnectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    Staff u = new Staff();
                    u.setId(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    u.setUsername(rs.getString(5));
                    u.setRole(rs.getString(6));
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
    
    public Vector<String> getRoleAD() throws Exception{
    	Vector<String> role=new Vector<String>();
    	ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="select Roles.name from Roles where Roles.id!='AD'";
                Statement stmt = ConnectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	role.add(rs.getString(1));
                }
            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
    	return role;
    }
    
    public Vector<String> getRoleAll() throws Exception{
    	Vector<String> role=new Vector<String>();
    	ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="select Roles.name from Roles";
                Statement stmt = ConnectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	role.add(rs.getString(1));
                }
            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
    	return role;
    }
    
    public Vector<String> getRoleQL() throws Exception{
    	Vector<String> role=new Vector<String>();
    	ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="select Roles.name from Roles where Roles.id!='AD' AND Roles.id!='QL'";
                Statement stmt = ConnectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	role.add(rs.getString(1));
                }
            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
    	return role;
    }
    
    public boolean checkTel(String tel) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
        boolean flag=true;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="select * from Staff where tel=?";
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
            	String sql="select * from Staff where tel=? and id!=?";
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
    
    public String hashPassword(String password) throws Exception{
    	MessageDigest digest=MessageDigest.getInstance("SHA-256");
    	byte[] bytes=password.getBytes(StandardCharsets.UTF_8);
    	byte[] hash=digest.digest(bytes);
    	String hashPass=Base64.getEncoder().encodeToString(hash);
    	return hashPass;
    }
    
    public boolean addStaff(Staff a) throws Exception{
        boolean flag=false;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	ConnectDB.conn.setAutoCommit(false);
                String sql="Insert into Staff(name,tel,address,managerID) values (?,?,?,?)";
                PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,a.getName());
                preparedStatement.setString(2,a.getTel());
                preparedStatement.setString(3,a.getAddress());
                preparedStatement.setInt(4, a.getManagerId());
                if(preparedStatement.executeUpdate()>0){
                	int generatedID=-1; 
                	ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        generatedID = generatedKeys.getInt(1);
                    }
                	String sql1="Insert into Account(userID,userName,password,positionID,daycreate) values (?,?,?,?,?)";
                	PreparedStatement prep1= ConnectDB.conn.prepareStatement(sql1);
                	prep1.setInt(1, generatedID);
                	prep1.setString(2,a.getUsername());
                	prep1.setString(3,hashPassword(a.getPassword()));
                	LocalDate ld=LocalDate.now();
                	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                	String formattedDate = ld.format(formatter); 
                	prep1.setString(5,formattedDate);
                	String sql2="Select id from Roles where name=N'"+a.getRole()+"'";
                	String roleID="";
                	Statement stmt = ConnectDB.conn.createStatement();
                	ResultSet rs2=stmt.executeQuery(sql2);
                	while(rs2.next()) {
                    	roleID=rs2.getString(1);
                    }
                	prep1.setString(4,roleID);
                	if(prep1.executeUpdate()>0){
                		flag=true;
                	}
                }
                ConnectDB.conn.commit();
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
    
    public boolean updateOneStaff(Staff a) throws Exception {
    	boolean flag=false;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	ConnectDB.conn.setAutoCommit(false);
            	String sql="update Staff set name=? , tel=? , address=? where id=? ";
            	PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
            	preparedStatement.setString(1,a.getName());
                preparedStatement.setString(2,a.getTel());
                preparedStatement.setString(3,a.getAddress());
                preparedStatement.setInt(4, a.getId());
                if(preparedStatement.executeUpdate()>0){
                	String sql2="Select id from Roles where name=N'"+a.getRole()+"'";
                	String roleID="";
                	Statement stmt = ConnectDB.conn.createStatement();
                	ResultSet rs2=stmt.executeQuery(sql2);
                	while(rs2.next()) {
                    	roleID=rs2.getString(1);
                    }
                	if(a.getPassword().trim()=="") {
                		String sql1="update Account set userName=? , positionID=?  where userID=? ";
                		PreparedStatement prep1= ConnectDB.conn.prepareStatement(sql1);
                    	prep1.setString(1,a.getUsername());
                    	prep1.setString(2,roleID);
                    	prep1.setInt(3,a.getId());
                    	if(prep1.executeUpdate()>0){
                    		flag=true;
                    	}
                	}else {
                		String sql1="update Account set userName=? , password=? ,positionID=?  where userID=? ";
                		PreparedStatement prep1= ConnectDB.conn.prepareStatement(sql1);
                		prep1.setString(1,a.getUsername());
                    	prep1.setString(2,hashPassword(a.getPassword()));
                    	prep1.setString(3,roleID);
                    	prep1.setInt(4,a.getId());
                    	if(prep1.executeUpdate()>0){
                    		flag=true;
                    	}
                	}	
                }
                ConnectDB.conn.commit();
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<Staff> allOutSearchAD(String fStr) throws ClassNotFoundException, SQLException{
    	Vector<Staff> arr = new Vector<Staff>();
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String[] str = fStr.split(" ");
            	StringBuilder query = new StringBuilder("select Staff.id ,Staff.name,tel,address,userName,Roles.name from Staff join Account on Staff.id=Account.userID join Roles on Account.positionID=Roles.id where Staff.status=1 ");
                for (int i = 0; i < str.length; i++) {
                	query.append("AND CONCAT(Staff.id, ' ', Staff.name, ' ', tel, ' ', address, ' ',userName) LIKE CONCAT( '%',?,'%') ");
                }
                PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(query.toString());
                for (int i = 0; i < str.length; i++) {
                     preparedStatement.setString(i+1, str[i]);
                }
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                	Staff u = new Staff();
                    u.setId(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    u.setUsername(rs.getString(5));
                    u.setRole(rs.getString(6));
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
    
    public Vector<Staff> allOutSearchQL(String fStr) throws ClassNotFoundException, SQLException{
    	Vector<Staff> arr = new Vector<Staff>();
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String[] str = fStr.split(" ");
            	StringBuilder query = new StringBuilder("select Staff.id,Staff.name,tel,address,userName,Roles.name from Staff join Account on Staff.id=Account.userID join Roles on Account.positionID=Roles.id where Staff.status=1 and Roles.id!='AD' ");
                for (int i = 0; i < str.length; i++) {
                	query.append("AND CONCAT(Staff.id, ' ', Staff.name, ' ', tel, ' ', address, ' ',userName) LIKE CONCAT( '%',?,'%') ");
                }
                PreparedStatement preparedStatement = ConnectDB.conn.prepareStatement(query.toString());
                for (int i = 0; i < str.length; i++) {
                     preparedStatement.setString(i+1, str[i]);
                }
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                	Staff u = new Staff();
                    u.setId(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    u.setUsername(rs.getString(5));
                    u.setRole(rs.getString(6));
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
    
    public Staff findbyID (int id) throws Exception {
    	Staff a=new Staff();
    	ConnectDB db=new ConnectDB();
    	if(ConnectDB.conn!=null){
            try{
            	String sql="select Staff.id as sId,Staff.name as sName,tel,address,userName,Roles.name as rName,Roles.id as rId from Staff join Account on Staff.id=Account.userID join Roles on Account.positionID=Roles.id where Staff.id=?;";
            	PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
            	preparedStatement.setInt(1, id);
            	ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()) {
                	a.setId(rs.getInt(1));
                	a.setName(rs.getString(2));
                	a.setTel(rs.getString(3));
                    a.setAddress(rs.getString(4));
                    a.setUsername(rs.getString(5));
                    a.setRole(rs.getString(6));
                    a.setRoleID(rs.getString(7));
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
    	return a;
    }
    
    public boolean eraShowStaff(Staff a) throws Exception{
    	boolean flag=true;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	ConnectDB.conn.setAutoCommit(false);
            	String sql="update Staff set status=0 where id=? ";
            	PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
                preparedStatement.setInt(1, a.getId());
                if(preparedStatement.executeUpdate()>0){
                	String sql1="update Account set status=0 where userID=? ";
                	PreparedStatement prep1= ConnectDB.conn.prepareStatement(sql1);
                	prep1.setInt(1, a.getId());
                	if(prep1.executeUpdate()>0){
                		flag=true;
                	}
                }
                ConnectDB.conn.commit();
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
    
    public boolean ckNewRole(String name) throws Exception {
    	boolean flag=true;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="select name from Roles";
            	Statement stmt = ConnectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	if(name.toUpperCase().equals(rs.getString(1).toUpperCase())) {
                		flag=false;
                	}
                }                
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
    
    public boolean ckNewRoleID(String id) throws Exception {
    	boolean flag=true;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="select id from Roles";
            	Statement stmt = ConnectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	if(id.toUpperCase().equals(rs.getString(1).toUpperCase())) {
                		flag=false;
                	}
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
    
    public boolean addBrandNewRole(String name,String id) throws Exception {
    	boolean flag=false;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="Insert into Roles(id,name) values (?,?)";
            	PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, name);
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
}
