package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.publisher;
import connection.ConnectDB;

public class publisher_DAO {
	private ConnectDB connectDB;
	public publisher_DAO(ConnectDB connectDB){
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
	public void savePublisher(publisher p) {
	    String query = "INSERT INTO Publisher (name, status) VALUES (?, 0)";
	    try {
	        connectDB.connect();
	        Connection connection = connectDB.getConnection();
	        if (connection != null) {
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setString(1, p.getName());
	                preparedStatement.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

    public List<publisher> getAllName() {
        List<publisher> list = new ArrayList<>();
        String query = "SELECT name FROM Publisher";

        try (Connection connection = connectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
            	publisher p = new publisher();
                p.setName(resultSet.getString("name"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
	public String getByName(String name) {
	    String query = "SELECT name FROM Publisher WHERE name = ?";
	    String s = null;
	    try {
	        Connection connection = connectDB.getConnection();
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, name);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    s = resultSet.getString("name");
	                }
	            }
	        } 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return s;
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
