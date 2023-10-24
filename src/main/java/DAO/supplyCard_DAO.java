package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import java.sql.Date;

import DTO.supply_Card;
import connection.ConnectDB;

public class supplyCard_DAO {
	private ConnectDB connectDB;
	public supplyCard_DAO(ConnectDB connectDB){
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
	public supply_Card getBySupDate(String supDate)
	{
		supply_Card sc = null;
		String query = "SELECT supplyCard.supDate, supplyCard.provider, Staff.name FROM supplyCard INNER JOIN Staff ON (supplyCard.staffID = Staff.id) AND (supplyCard.supDate LIKE ?)";
		try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1,"%-%-"+supDate);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                    	sc = new supply_Card();
                        sc.setId(resultSet.getInt("id"));
                        sc.setSupDate(resultSet.getDate("supDate"));
                        sc.setProvider(resultSet.getString("provider"));
                        sc.setStaffID(resultSet.getInt("staffID"));
                    } 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return sc;
    }
//    public void saveProvider(supply_Card sc) {
//        String query = "INSERT INTO supplyCard (provider) VALUES (?)";
//        try {
//            connectDB.connect();
//            Connection connection = connectDB.getConnection();
//            if(connection!=null)
//            {
//            	try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                    preparedStatement.setString(1, sc.getProvider());
//                    preparedStatement.executeUpdate();
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//				connectDB.disconnect();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
//    }
	public void saveInfo(supply_Card supplyCard) {
        String query = "INSERT INTO supplyCard (supDate, provider, staffID) VALUES (?, ?, 1004)";

        try {
        	connectDB.connect();
            Connection connection = connectDB.getConnection();

            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setDate(1, (Date) supplyCard.getSupDate());
                    preparedStatement.setString(2, supplyCard.getProvider());
//                    preparedStatement.setInt(3, supplyCard.getStaffID());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
    public List<supply_Card> getAllSC() {
        List<supply_Card> list = new ArrayList<>();
		String query = "SELECT supplyCard.id,supplyCard.supDate, supplyCard.provider, Staff.name FROM supplyCard INNER JOIN Staff ON (supplyCard.staffID = Staff.id) AND (supplyCard.supDate LIKE ?)";

        try (Connection connection = connectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                supply_Card sc = new supply_Card();
                sc.setId(resultSet.getInt("id"));
                sc.setSupDate(resultSet.getDate("supDate"));
                sc.setProvider(resultSet.getString("provider"));
                sc.setStaffID(resultSet.getInt("staffID"));
                list.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public List<supply_Card> getProvider() {
        List<supply_Card> list = new ArrayList<>();
		String query = "SELECT provider FROM supplyCard";

        try (Connection connection = connectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                supply_Card sc = new supply_Card();
                sc.setProvider(resultSet.getString("provider"));
                list.add(sc);
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
