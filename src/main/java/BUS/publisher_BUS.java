package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.publisher_DAO;
import DTO.publisher;
import connection.ConnectDB;

public class publisher_BUS {
	private  publisher_DAO pdao;
	private ConnectDB connectDB;
	public publisher_BUS()
	{
		ConnectDB connectDB = null;
		try {
            connectDB = new ConnectDB();
            pdao = new publisher_DAO(connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}
	public List<publisher> getAllName(){
        return pdao.getAllName();
    }
	public void saveInfo(publisher p)
	{
		pdao = new publisher_DAO(connectDB);
		pdao.savePublisher(p);
	}
	public String getByName(String name)
	{
		pdao = new publisher_DAO(connectDB);
		return pdao.getByName(name);
	}
	public void disconnect() {
		pdao.disconnect();
	}
}
