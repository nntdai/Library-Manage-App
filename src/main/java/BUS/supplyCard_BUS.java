package BUS;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;

import DAO.Kho_DAO;
import DAO.publisher_DAO;
import DAO.supplyCard_DAO;
import DTO.kho_DTO;
import DTO.publisher;
import DTO.supply_Card;
import connection.ConnectDB;
import java.util.List;

import javax.swing.JTable;

public class supplyCard_BUS {
	private  supplyCard_DAO scd;
	private  Kho_DAO kdao;
	private ConnectDB connectDB;
	public supplyCard_BUS()
	{
		ConnectDB connectDB = null;
		try {
            connectDB = new ConnectDB();
            scd = new supplyCard_DAO(connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}
//	public kho_DTO getByImg(String name)
//	{
//		return kdao.getByImg(name);
//	}
	
	public String getByImg(String name)
	{
		kdao = new Kho_DAO(connectDB);
		return kdao.getByImg(name);
	}
	public int getByNameBook(String name)
	{
		kdao = new Kho_DAO(connectDB);
		return kdao.getByNameBook(name);
	}
	public int getByPublisherID(String pub)
	{
		kdao = new Kho_DAO(connectDB);
		return kdao.getByPublisherID(pub);
	}
	public List<supply_Card> getAllSC(){
		return scd.getAllSC();
	}
	public supply_Card getBySupDate(String supDate)
	{
		return scd.getBySupDate(supDate);
	}
	public void saveSC(Date supDate, String provider)
	{
		supply_Card supplyCard = new supply_Card();
		scd.saveInfo(supplyCard);
	}
	public List<supply_Card> getAllName(){
        return scd.getProvider();
    }
	public void disconnect() {
		scd.disconnect();
	}
}
