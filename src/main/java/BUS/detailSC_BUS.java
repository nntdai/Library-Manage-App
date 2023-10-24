package BUS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import DAO.detailSC_DAO;
import DTO.detailSC;
import DTO.supply_Card;
import connection.ConnectDB;

public class detailSC_BUS {
	private  detailSC_DAO dd;
	private detailSC dsc;
	private ConnectDB connectDB;
	public detailSC_BUS()
	{
		ConnectDB connectDB = null;
		try {
            connectDB = new ConnectDB();
            dd = new detailSC_DAO(connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}
//	public List<detailSC> getAllDSC(){
//		List<detailSC> list = new ArrayList<>();
//		String query = "SELECT supplyCard.supDate, supplyCard.provider, Staff.name FROM supplyCard INNER JOIN Staff ON (supplyCard.staffID = Staff.id) AND (supplyCard.supDate LIKE ?)";
//
//        try (Connection connection = connectDB.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//            while (resultSet.next()) {
//                supply_Card sc = new supply_Card();
//                sc.setId(resultSet.getInt("id"));
//                sc.setId(resultSet.getInt("id"));
//                sc.setSupDate(resultSet.getDate("supDate"));
//                sc.setProvider(resultSet.getString("provider"));
//                sc.setStaffID(resultSet.getInt("staffID"));
//                list.add(sc);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
	public void saveDSC(int num)
	{
		dsc = new detailSC(); 
		dd.saveInfo(dsc);
	}
}
