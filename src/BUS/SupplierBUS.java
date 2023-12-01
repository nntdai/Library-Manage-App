/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.SupplierDAO;
import DTO.entities.Supplier;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author WIN 10
 */
public class SupplierBUS {
    private  SupplierDAO ad;
    public SupplierBUS() throws SQLException, IOException
    {
        ConnectDB connectDB = null;
        try {
            ad = new SupplierDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getByNameAuthor(String name) throws SQLException, IOException
    {
            ad = new SupplierDAO();
            return ad.getBySupplierName(name);
    }
    public List<Supplier> getAllName() throws SQLException{
    return ad.getAllName();
}
    public void saveInfo(Supplier a) throws SQLException, IOException
    {
            ad = new SupplierDAO();
            ad.saveAuthor(a);

    }
    public boolean deteleByAuthorName(String name) throws  SQLException, IOException{
        ad = new SupplierDAO();
        ad.deleteByName(name);
        return true;
    }
    public void disconnect() {
        ad.disconnect();
    }
}
