package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import DTO.detailSC;
import DTO.supply_Card;
import connection.ConnectDB;

public class detailSC_DAO {
	private ConnectDB connectDB;
	public detailSC_DAO(ConnectDB connectDB){
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
	public void saveInfo(detailSC detailsc) {
        String query = "INSERT INTO detailSC (ISBN, num) VALUES ('1', ?)";

        try {
        	connectDB.connect();
            Connection connection = connectDB.getConnection();

            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(2, detailsc.getNum());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
