/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.RoleDAO;
import DAO.RolePermissionDAO;
import DTO.entities.Role;
import DTO.entities.RolePermission;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WIN 10
 */
public class RolePermissionBUS {
    protected static ArrayList<RolePermission> list;
    protected static RolePermissionDAO rolePermissionDAO;
    protected static RoleDAO roleDAO;
    protected static Role role;
    protected static ArrayList<RolePermission> canAccess;
    private static int quantity = 0;

    public RolePermissionBUS() throws ClassNotFoundException, SQLException, IOException {
        rolePermissionDAO = new RolePermissionDAO();
        list = new ArrayList<>(rolePermissionDAO.read());
        quantity = list.size();
    }

    public static ArrayList<RolePermission> getList() {
        return list;
    }
    
    public static int getQuantity() {
        quantity = list.size();
        return quantity;
    }
    public boolean hasPermission(String roleName, int permissionID) throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException{
        try {
            roleDAO = new RoleDAO();
        } catch (IOException ex) {
            Logger.getLogger(RolePermissionBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
        role = roleDAO.getRole(roleName);
        ArrayList<RolePermission> listPermissions = canAccessForm(role.getRoleID());
        for (RolePermission rolePermission : listPermissions) {
            if (rolePermission.getPermissionID() == permissionID) {
                return true; // The role has the permission
            }
        }
        return false;
    }
    public static ArrayList<RolePermission> canAccessForm(String roleID) throws NoSuchAlgorithmException {
        RolePermission temp;
        canAccess= new ArrayList<>();
        for (RolePermission _rolePermission : list) {
            temp = (RolePermission) _rolePermission;
            if(roleID.equals(temp.getRoleID())){
                canAccess.add(temp);
            }
        }
        return canAccess;
    }
    
    public void updateRolePermissions(List<List<Object>> dataList, String roleName) throws ClassNotFoundException, SQLException, IOException {
        RolePermissionDAO rolePermissionDAO = null;
        try {
                rolePermissionDAO = new RolePermissionDAO();
                roleDAO = new RoleDAO();
            } catch (IOException ex) {
                Logger.getLogger(RolePermissionBUS.class.getName()).log(Level.SEVERE, null, ex);
            }
        role = roleDAO.getRole(roleName);
        rolePermissionDAO.delete(role.getRoleID());
        for (List<Object> rowData : dataList) {
            int permissionID = (int) rowData.get(0);
            rolePermissionDAO.create(permissionID,role.getRoleID());
        }
    }
}
