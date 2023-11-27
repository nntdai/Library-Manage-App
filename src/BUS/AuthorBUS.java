package BUS;

import DAO.AuthorDAO;
import DTO.entities.Author;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author WIN 10
 */
public class AuthorBUS {
    private  AuthorDAO ad;
    private ConnectDB connectDB;
    public AuthorBUS() throws SQLException, IOException
    {
        ConnectDB connectDB = null;
        try {
            connectDB = new ConnectDB();
            ad = new AuthorDAO(connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public String getByNameAuthor(String name) throws SQLException, IOException
    {
            ad = new AuthorDAO(connectDB);
            return ad.getByNameAuthor(name);
    }
    public List<Author> getAllName() throws SQLException{
    return ad.getAllName();
}
    public void saveInfo(Author a) throws SQLException, IOException
    {
            ad = new AuthorDAO(connectDB);
            ad.saveAuthor(a);

    }
    public boolean deteleByAuthorName(String name) throws  SQLException, IOException{
        ad = new AuthorDAO(connectDB);
        ad.deleteByName(name);
        return true;
    }
    public void disconnect() {
        ad.disconnect();
    }
}
