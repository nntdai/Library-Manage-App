/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import DTO.entities.Permission;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author WIN 10
 */
public class PermissionDAO {
    protected ArrayList<Permission> list = new ArrayList<>();
    protected Permission per = new Permission();
    private ConnectDB connectDB;
    
    public PermissionDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
        read();
    }
    
    public ArrayList<Permission> getList() {
        return list;
    }
    
    public ArrayList<Permission> read() throws IOException, ClassNotFoundException, SQLException{
        String context = this.getClass().getName();
        connectDB.connect(context);
        System.out.println("check");
        try {
            String sql = "Select * from permissions";
            Statement stmt = ConnectDB.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Permission per = new Permission();
                per.setPermissionID(rs.getInt(1));
                per.setPermissionName(rs.getString(2));   
                list.add(per);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectDB.disconnect(context);
        return list;
    }
    
    public boolean create(Permission per) throws SQLException {
        String context = this.getClass().getName();
        connectDB.connect(context);
        try {
            String sql = "INSERT INTO permissions(name) "
                    + "VALUES (?)";
            PreparedStatement pstmt = ConnectDB.conn.prepareStatement(sql);
            pstmt.setString(2, per.getPermissionName());
            pstmt.executeUpdate();
            list.add(per);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectDB.disconnect(context);
        return false;
    }
}
