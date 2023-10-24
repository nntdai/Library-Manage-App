package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.category_DAO;
import DTO.Category;
import connection.ConnectDB;

public class category_BUS {
	private  category_DAO cdao;
	private ConnectDB connectDB;
	public category_BUS()
	{
		ConnectDB connectDB = null;
		try {
            connectDB = new ConnectDB();
            cdao = new category_DAO(connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}
	public List<Category> getAllName(){
        return cdao.getAllName();
    }
	public void saveInfo(Category c)
	{
		cdao = new category_DAO(connectDB);
		cdao.saveProvider(c);
	
	}
	public void disconnect() {
		cdao.disconnect();
	}
}
