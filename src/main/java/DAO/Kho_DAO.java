package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connection.ConnectDB;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;

import DTO.kho_DTO;
import DTO.sach_DTO;
import DTO.supply_Card;
import DTO.detailSC;

public class Kho_DAO {
	ArrayList<kho_DTO> list = new ArrayList<>();
	private ConnectDB connectDB;
	public Kho_DAO(ConnectDB connectDB){
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
	public kho_DTO getISBN(String isbn)
	{
		kho_DTO kho = null;
		String query = "SELECT * FROM cpBook WHERE ISBN LIKE ?";
		try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1,isbn + "%");
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                    	kho = new kho_DTO();
                        kho.setIsbn(resultSet.getString("ISBN"));
                        kho.setBookID(resultSet.getInt("bookID"));
                        kho.setBorrowNum(resultSet.getInt("borrowNum"));
                        kho.setStoreNum(resultSet.getInt("storeNum"));
                        kho.setEdition(resultSet.getString("edition"));
                        kho.setPublisherID(resultSet.getInt("publisherID"));
                        kho.setStatus(resultSet.getBoolean("status"));
                        kho.setCost(resultSet.getLong("Cost"));
                        kho.setImg(resultSet.getString("img"));
                        list.add(kho);
                    } 
                }
            }
            connectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return kho;
    }
//	public kho_DTO getByImg(String name)
//	{
//		kho_DTO kho = null;
//		String query = "SELECT cpBook.img FROM Book INNER JOIN cpBook ON cpBook.bookID = Book.id AND Book.name = ?";
//		try {
//            Connection connection = connectDB.getConnection();
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setString(1,name);
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                    	kho = new kho_DTO();
//                        kho.setImg(resultSet.getString("img"));
//                    } 
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } 
//        return kho;
//    }
	
	public String getByImg(String name) {
	    String img = null;
	    String query = "SELECT cpBook.img FROM Book INNER JOIN cpBook ON cpBook.bookID = Book.id AND Book.name = ?";
	    
	    try {
	        Connection connection = connectDB.getConnection();
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, name);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    img = resultSet.getString("img");
	                }
	            }
	        } 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return img;
	}

	public int getByNameBook(String name)
	{
		int bookid=0;
		String query = "SELECT cpBook.bookID FROM cpBook INNER JOIN Book ON cpBook.bookID = Book.id AND Book.name = '"+name+"'";
		try {
			Connection connection = connectDB.getConnection();
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, name);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    bookid = resultSet.getInt("bookID");
	                }
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookid;
	}

	public int getByPublisherID(String pub)
	{
		int pubid=0;
		String query = "SELECT cpBook.publisherID FROM cpBook INNER JOIN Publisher ON cpBook.publisherID = Publisher.id AND Publisher.name = '"+pub+"'";
		try {
			Connection connection = connectDB.getConnection();
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, pub);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    pubid = resultSet.getInt("bookID");
	                }
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pubid;
	}
	
    public void saveInfo(kho_DTO kho) {
        String query = "INSERT INTO cpBook (ISBN, bookID, borrowNum, storeNum, editon, publisherID, status, Cost, img) VALUES ('1',?,0,?,'NULL',?,0,?,'NULL')";
        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            if(connection!=null)
            {
            	try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                    preparedStatement.setString(1, kho.getIsbn());
                    preparedStatement.setInt(2, kho.getBookID());
                    preparedStatement.setInt(4, kho.getStoreNum());
//                    preparedStatement.setString(5, kho.getEdition());
                    preparedStatement.setInt(6, kho.getPublisherID());
                    preparedStatement.setLong(8, kho.getCost());
//                    preparedStatement.setString(9, kho.getImg());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
