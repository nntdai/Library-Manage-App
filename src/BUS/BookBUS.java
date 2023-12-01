/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.BookDAO;
import DTO.entities.Book;
import DTO.entities.Book1;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class BookBUS {
    protected static BookDAO bookDAO ;
    public BookBUS() throws ClassNotFoundException, SQLException {
        
    }   
    public ArrayList<Book1> getAll() throws ClassNotFoundException, SQLException, IOException
    {   
        bookDAO =new BookDAO();
        return bookDAO.getAll();
    }
    public Book1 getBookByISBN(String ISBN) throws ClassNotFoundException, SQLException, IOException
    {
        bookDAO =new BookDAO();
        return bookDAO.getBookByISBN(ISBN);
    }
     public ArrayList<Book1> getAllByCondition(String nameBook,String nameAuthor,String namePublisher) throws ClassNotFoundException, SQLException, IOException
     {
         bookDAO =new BookDAO();
         return bookDAO.getAllByCondition(nameBook, nameAuthor, namePublisher);
     }
    public void saveInfo(Book1 b) throws ClassNotFoundException, SQLException, IOException{
        bookDAO = new BookDAO();
        bookDAO.saveInfo(b);
    }
    public void updateStoreNumBooks(String ISBN,int storeNum,int borrowNum) throws ClassNotFoundException, SQLException
     {
         bookDAO.updateStoreNumBooks(ISBN, storeNum, borrowNum);
     }

}
