/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BorrowCardDAO;
import DTO.entities.BorrowCard;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author WIN 10
 */
public class PayBUS {
    private BorrowCardDAO borrowCardDAO;
    public PayBUS() throws ClassNotFoundException {
        
    }
    
     public Vector<BorrowCard> getAll() throws Exception{
         borrowCardDAO = new BorrowCardDAO();
    	return borrowCardDAO.getAll();
    }
    public long CacluteDate(Date startDate, Date realDate) {
        return TimeUnit.DAYS.convert(realDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);
    }
     
     public void RecoverBook(BorrowCard bc) throws ClassNotFoundException, SQLException, IOException{
         borrowCardDAO = new BorrowCardDAO();
         borrowCardDAO.RecoverBook(bc);
         
     } 
    public void BooksLost(BorrowCard bc) throws ClassNotFoundException, SQLException, IOException{
        borrowCardDAO = new BorrowCardDAO();
        borrowCardDAO.BooksLost(bc);
    }

}
