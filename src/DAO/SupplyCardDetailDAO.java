/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.entities.SupplyCardDetail;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author WIN 10
 */
public class SupplyCardDetailDAO {
    private ConnectDB connectDB;
    public SupplyCardDetailDAO() throws ClassNotFoundException, SQLException, IOException{
        connectDB = new ConnectDB();
    }
    public void saveInfo(SupplyCardDetail supplyCardDetail){
       String query = "INSERT INTO detail_supply_card (scID, ISBN, num) VALUES (?,?,?)";

	    try {
	        connectDB.connect();
	        Connection connection = connectDB.getConnection();

	        if (connection != null) {
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setInt(1, supplyCardDetail.getScID());
	                preparedStatement.setString(2, supplyCardDetail.getISBN());
	                preparedStatement.setInt(3, supplyCardDetail.getNum());
	                preparedStatement.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
    
}


