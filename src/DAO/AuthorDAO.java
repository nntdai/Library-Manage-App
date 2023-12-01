/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.entities.Author;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WIN 10
 */
public class AuthorDAO {
    private ConnectDB connectDB;
    public AuthorDAO(ConnectDB connectDB) throws SQLException, IOException{
            try {
                    this.connectDB = new ConnectDB();
            } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }

    public String getByNameAuthor(String name) throws SQLException {
        String status = null;
        String query = "SELECT name FROM author WHERE name COLLATE Latin1_General_CI_AI = ?";
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
	
    public List<Author> getAllName() throws SQLException {
        List<Author> list = new ArrayList<>();
        String query = "SELECT name FROM author";
        connectDB.connect();
        try (Connection connection = connectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
            	Author a = new Author();
                a.setName(resultSet.getString("name"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void saveAuthor(Author a) {
         String query = "INSERT INTO author (name) VALUES (?)";
         try {
             connectDB.connect();
             Connection connection = connectDB.getConnection();
             if(connection!=null)
             {
                 try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                     preparedStatement.setString(1, a.getName());
                     preparedStatement.executeUpdate();
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
    public  void deleteByName (String name){
        String query = "UPDATE author SET isActive = 0 WHERE name = ?";
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
