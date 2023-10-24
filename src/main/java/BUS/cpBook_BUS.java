package BUS;

import java.sql.SQLException;

import DAO.Kho_DAO;
import DTO.kho_DTO;
import connection.ConnectDB;

public class cpBook_BUS {
	private Kho_DAO kdao;
	private ConnectDB connectDB;
	public cpBook_BUS()
	{
		ConnectDB connectDB = null;
		try {
            connectDB = new ConnectDB();
            kdao = new Kho_DAO(connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } 
	}
	public void savecpB(int bookID, int StoreNum, int publisherID, long cost)
	{
		kho_DTO khoDTO = new kho_DTO();
		kdao.saveInfo(khoDTO);
	}
}
