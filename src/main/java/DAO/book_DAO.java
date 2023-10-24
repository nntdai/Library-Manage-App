package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.sach_DTO;
import DTO.supply_Card;
import connection.ConnectDB;

public class book_DAO {
	private ConnectDB connectDB;
	public book_DAO(ConnectDB connectDB){
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
    public void saveInfo(sach_DTO b) {
        String query = "INSERT INTO Book (name, status) VALUES (?,0)";
        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            if(connection!=null)
            {
            	try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, b.getName());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
				connectDB.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
