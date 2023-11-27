/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import connection.ConnectDB;
import DTO.entities.Book1;
import DTO.entities.Book;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class BookDAO extends ConnectDB {
    protected Book1 book1;
    ConnectDB connectDB;
    public BookDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }
    public ArrayList<Book1> getAll() throws ClassNotFoundException, SQLException, IOException {
        ArrayList<Book1> result = new ArrayList<>();
        connectDB.connect();
        if (connectDB.conn != null) {
            try {
                String sql = "SELECT cp_book.*,book.id,book.name,publisher.id,publisher.name,author.name FROM cp_book,book,publisher,author,book_author WHERE cp_book.bookID=book.id AND cp_book.isActive=1 AND cp_book.publisherID=publisher.id AND cp_book.ISBN=book_author.ISBN AND book_author.authorID=author.id";
                
                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connectDB.conn.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    String ISBN= rs.getString("ISBN");
                    String tenSach=rs.getString("name");
                    int storeNum=rs.getInt("storeNum");
                    String publisher=rs.getString(13);
                    String author=rs.getString(14);
                    long Cost=rs.getLong("Cost");
                    String img=rs.getString("img");
                    
                    Book1 book=new Book1(ISBN,tenSach,storeNum,publisher,author,Cost,img);
                    result.add(book);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                connectDB.disconnect();
            }
        }
        return result;
}
    public Book1 getBookByISBN(String ISBN) throws ClassNotFoundException, SQLException, IOException
    {
       for (Book1 x :this.getAll())
       {
           if (x.getISBN().equals(ISBN.trim()))
               return x;
       }
       return null;
    }
     public ArrayList<Book1> getAllByCondition(String nameBook,String nameAuthor,String namePublisher) throws ClassNotFoundException, SQLException, IOException {
        ArrayList<Book1> result = new ArrayList<>();
        connectDB.connect();
        if (connectDB.conn != null) {
            try {
                String sql = "SELECT cp_book.*,book.name,publisher.name,author.name FROM cp_book,book,publisher,author,book_author WHERE cp_book.bookID=book.id AND cp_book.isActive=1 AND cp_book.publisherID=publisher.id AND cp_book.ISBN=book_author.ISBN AND book_author.authorID=author.id ";
                String sqlBook ="AND book.name LIKE N'%"+nameBook+"%'";
                String sqlAuthor="AND publisher.name LIKE N'%"+nameAuthor+"%'";
                String sqlPub="AND author.name LIKE N'%"+namePublisher+"%'";
                if ((!nameBook.equals("")))
                {
                    sql=sql+sqlBook;
                }
                if ((!nameAuthor.equals("")))
                {
                    sql=sql+sqlAuthor;
                }
                if ((!namePublisher.equals("")))
                {
                    sql=sql+sqlPub;
                }
                    //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connectDB.conn.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    String ISBN= rs.getString("ISBN");
                    String tenSach=rs.getString("name");
                    int storeNum=rs.getInt("storeNum");
                    String publisher=rs.getString(11);
                    String author=rs.getString(12);
                    long Cost=rs.getLong("Cost");
                    String img=rs.getString("img");
                   
                    Book1 book=new Book1(ISBN,tenSach,storeNum,publisher,author,Cost,img);
                  
                    result.add(book);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    public void saveInfo(Book1 b) throws ClassNotFoundException, IOException {
        String query = "INSERT INTO book (name) VALUES (?)";
        try {
            connectDB.connect();
            if(connectDB.conn!=null)
            {
            	try (PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query)) {
                    preparedStatement.setString(1, b.getTenSach());
                    preparedStatement.executeUpdate();
                }
            }
            connectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
