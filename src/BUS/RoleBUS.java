/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PermissionDAO;
import DAO.RoleDAO;
import DTO.entities.Permission;
import DTO.entities.Role;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WIN 10
 */
public class RoleBUS {
    protected static ArrayList<Role> list;
    protected static RoleDAO roleDAO;
    protected static Role role;
    private static int quantity = 0;

    public RoleBUS() throws ClassNotFoundException, SQLException, IOException {
        roleDAO = new RoleDAO();
        list = new ArrayList<>(roleDAO.getList());
        quantity = list.size();
    }

    public static ArrayList<Role> getList() {
        return list;
    }
    
    public static int getQuantity() {
        quantity = list.size();
        return quantity;
    }
    public static void deleteRoleByID(String roleName) throws ClassNotFoundException, SQLException, IOException{
        roleDAO = new RoleDAO();
        role = roleDAO.getRole(roleName);
        roleDAO.delete(role);
    }
    public boolean addRoleName(String name) throws Exception {
        roleDAO = new RoleDAO();
    	return roleDAO.ckNewRole(name);
    }
    
    public boolean addRoleNameID(String id) throws Exception {
        roleDAO = new RoleDAO();
    	return roleDAO.ckNewRoleID(id);
    }
    public String addBrandNewRole(Role role) throws Exception {
    	if(roleDAO.create(role)){
            return "Thêm vai trò mới thành công";
        }else{
            return "Lỗi! Thêm vai trò mới không thành công";
        }
    }
}
