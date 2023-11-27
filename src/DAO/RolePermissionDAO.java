/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.entities.RolePermission;
import DTO.entities.Account;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WIN 10
 */
public class RolePermissionDAO {
    protected ArrayList<RolePermission> list = new ArrayList<>();
    protected RolePermission rolePermission = new RolePermission();
    private ConnectDB connectDB;

    public RolePermissionDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
        read();
    }

    public ArrayList<RolePermission> getList() {
        return list;
    }

    public RolePermission getRolePermission() {
        return (RolePermission) rolePermission;
    }
    
    public ArrayList<RolePermission> read() throws IOException, ClassNotFoundException, SQLException{
        String context = this.getClass().getName();
        connectDB.connect(context);
        try {
            String sql = "Select positionID,authorityID from role_permissions";
            Statement stmt = connectDB.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleID(rs.getString(1));                
                rolePermission.setPermissionID(rs.getInt(2));
                list.add(rolePermission);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectDB.disconnect(context);
        return list;
    }
    public boolean create(int permissionID, String roleID) throws ClassNotFoundException, SQLException {
        String context = this.getClass().getName();
        connectDB.connect(context);
        try {
            String sql = "INSERT INTO role_permissions(positionID,authorityID)"
                    + "VALUES (?, ?)";
            PreparedStatement pstmt = connectDB.conn.prepareStatement(sql);
            pstmt.setString(1, roleID);
            pstmt.setInt(2, permissionID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RolePermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectDB.disconnect(context);
        }
        return false;
    }    
    public boolean delete(String roleID) throws SQLException {
        String context = this.getClass().getName();
        connectDB.connect(context);
        try {
            String sql = "DELETE FROM role_permissions WHERE positionID= ?;";
            PreparedStatement pstmt = connectDB.conn.prepareStatement(sql);
            pstmt.setString(1, roleID);
            pstmt.executeUpdate();
            searchByID(roleID);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RolePermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public ArrayList<RolePermission> searchByID(String ID) { 
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRoleID().equals(ID)) {
                list.remove(i);
            }
        }
        return list;
    }
}
