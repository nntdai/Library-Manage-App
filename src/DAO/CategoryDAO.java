/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.entities.Category;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WIN 10
 */
public class CategoryDAO {
    private ConnectDB connectDB;
	public CategoryDAO() throws SQLException, IOException, ClassNotFoundException{
        connectDB = new ConnectDB();
	}
	public String getByNameCategory(String name) throws ClassNotFoundException, SQLException, IOException {
	    String status = null;
	    String query = "SELECT name FROM category WHERE name COLLATE Latin1_General_CI_AI = ?";
        connectDB.connect();
	    try {
	        Connection connection = connectDB.getConnection();
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, name);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    status = resultSet.getString("name");
	                }
	            }
	        } 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return status;
	}
    public void saveProvider(Category c) {
        String query = "INSERT INTO category (name) VALUES (?)";
        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            if(connection!=null)
            {
            	try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, c.getName());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Category> getAllName() throws SQLException {
        List<Category> list = new ArrayList<>();
        String query = "SELECT name FROM category";
        connectDB.connect();
        try (Connection connection = connectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
            	Category c = new Category();
                c.setName(resultSet.getString("name"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void deleteByName (String name){
        String query = "UPDATE category SET isActive = 0 WHERE name = ?";
        try{
            connectDB.connect();
            if(connectDB.conn != null){
                PreparedStatement pstmt = connectDB.conn.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void disconnect() {
            try {
                 connectDB.disconnect();
         } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
         }
   }
}
