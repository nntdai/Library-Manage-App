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
import java.sql.Statement;
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
                    boolean exist=false;
                   
                    for (Book1 i:result)
                    {
                        if (i.getISBN().equals(rs.getString("ISBN"))==true)
                        {
                            i.setAuthor(rs.getString(14));
                            exist=true;
                        }
                    }
                    if (exist==false)
                    {
                    String ISBN= rs.getString("ISBN");
                    String tenSach=rs.getString("name");
                    int storeNum=rs.getInt("storeNum");
                    int borrowNum=rs.getInt("borrowNum");
                    String publisher=rs.getString(13);
                    String author=rs.getString(14);
                    long Cost=rs.getLong("Cost");
                    String img=rs.getString("img");
                    
                    Book1 book=new Book1(ISBN,tenSach,storeNum,borrowNum,publisher,Cost,img);
                    book.setAuthor(author);
                    result.add(book);
                    }
                }
            } catch (SQLException ex) {
                //Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                connectDB.disconnect();
            }
        }
        return result;
}
    public void updateStoreNumBooks(String ISBN,int storeNum,int borrowNum) throws ClassNotFoundException, SQLException
    {
       connectDB.connect();
        
        if(connectDB.conn != null){
            try{
                Statement st = connectDB.conn.createStatement();
             String sql ="Update cp_Book SET storeNum="+storeNum+" ,borrowNum="+borrowNum+" WHERE ISBN='"+ISBN+"'";
                st.executeUpdate(sql);
            }catch(SQLException e){
                System.out.println(e);
            }finally{
               connectDB.disconnect();
               
            }
        }
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
        result = getAll();
         ArrayList<Book1> temp=new ArrayList<>();
        if ((!nameBook.trim().equals("")))
                {
                  
                 for (Book1 i : result)
                 {
                     if (i.getTenSach().toLowerCase().contains(nameBook.trim().toLowerCase()))
                     {
                         temp.add(i);
                         
                     }
                 }result=temp;
                 
                 temp=new ArrayList<>();
                 
                 
                }
        if ((!nameAuthor.trim().equals("")))
                {                 
                   for (Book1 i:result)
                 {
                     if (i.getAuthor().toString().toLowerCase().contains(nameAuthor.trim().toLowerCase())==true)
                     {                      
                         temp.add(i);
                        
                     }
                     
                 }
                   result=temp;
                   temp=new ArrayList<>();
                 
                  
                }     
        if ((!namePublisher.trim().equals("")))
                {
                    
                   for (Book1 i:result)
                 {
                     
                     if (i.getPublisher().toLowerCase().contains(namePublisher.trim().toLowerCase())==true)
                     {
                         temp.add(i);
                         
                     }
                 } 
                   result=temp;
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
