package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Category;
import DTO.publisher;
import connection.ConnectDB;

public class category_DAO {
	private ConnectDB connectDB;
	public category_DAO(ConnectDB connectDB){
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
    public void saveProvider(Category c) {
        String query = "INSERT INTO Category (name,status) VALUES (?, 0)";
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
    public List<Category> getAllName() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT name FROM Category";

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
	   public void disconnect() {
		   try {
			connectDB.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
