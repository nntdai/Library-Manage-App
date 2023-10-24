package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Author;
import DTO.supply_Card;
import connection.ConnectDB;

public class author_DAO {
	private ConnectDB connectDB;
	public author_DAO(ConnectDB connectDB){
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
//	public Author getByName(String name)
//	{
//		Author a = null;
//		String query = "SELECT name FROM Author";
//		try {
//            Connection connection = connectDB.getConnection();
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1,name);
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                    	a = new Author();
//                        a.setName(resultSet.getString("name"));
//                    } 
//                }
//            }
//            connectDB.disconnect();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } 
//        return a;
//    }
    public List<Author> getAllName() {
        List<Author> list = new ArrayList<>();
        String query = "SELECT name FROM Author";

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
	        String query = "INSERT INTO Author (name,status) VALUES (?, 0)";
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
	   public void disconnect() {
		   try {
			connectDB.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
}
