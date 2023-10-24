package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.author_DAO;
import DTO.Author;
import connection.ConnectDB;

public class author_BUS {
	private  author_DAO ad;
	private ConnectDB connectDB;
	public author_BUS()
	{
		ConnectDB connectDB = null;
		try {
            connectDB = new ConnectDB();
            ad = new author_DAO(connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}
	public List<Author> getAllName(){
        return ad.getAllName();
    }
	public void saveInfo(Author a)
	{
		ad = new author_DAO(connectDB);
		ad.saveAuthor(a);
	
	}
	public void disconnect() {
		ad.disconnect();
	}
}
