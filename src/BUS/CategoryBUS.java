/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CategoryDAO;
import DTO.entities.Category;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WIN 10
 */
public class CategoryBUS {
    private  CategoryDAO cdao;
    private ConnectDB connectDB;
    public CategoryBUS() throws SQLException, IOException{
        ConnectDB connectDB = null;
        try {
            connectDB = new ConnectDB();
            cdao = new CategoryDAO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(CategoryBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Category> getAllName() throws SQLException{
        return cdao.getAllName();
    }
    public void saveInfo(Category c) throws SQLException, IOException, ClassNotFoundException
    {
            cdao = new CategoryDAO();
            cdao.saveProvider(c);

    }
    public String getByNameCategory(String name) throws SQLException, IOException, ClassNotFoundException
    {
            cdao = new CategoryDAO();
            return cdao.getByNameCategory(name);
    }
    public boolean deleteByCategoryName (String name) throws SQLException, IOException, ClassNotFoundException {
        cdao = new CategoryDAO();
        cdao.deleteByName(name);
        return true;
    }
    public void disconnect() {
            cdao.disconnect();
    }
}
