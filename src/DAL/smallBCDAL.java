package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

import connection.ConnectDB;
import DTO.Readers;
import DTO.smallBC;
import DTO.smallBook;

public class smallBCDAL extends ConnectDB{

    public smallBCDAL() throws ClassNotFoundException, SQLException {
        super();
        //TODO Auto-generated constructor stub
    }
    
    public Vector<smallBC> getAll(int id) throws Exception{
    	Vector<smallBC> arr=new Vector<smallBC>();
    	 ConnectDB db=new ConnectDB();
         if(ConnectDB.conn!=null){
             try{
                 String sql="select borrowCard.id,startDate,realReDate,Staff.name\r\n"
                 		+ "from Reader join borrowCard on Reader.id=borrowCard.readerID\r\n"
                 		+ "			join Staff on borrowCard.staffID=Staff.id\r\n"
                 		+ "where Reader.id=?";
                PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
             	preparedStatement.setInt(1, id);
             	ResultSet rs=preparedStatement.executeQuery();
             	while(rs.next()) {
             		smallBC a=new smallBC();
                	a.setId(rs.getInt(1));
             		a.setStartDate(rs.getDate(2).toLocalDate());
                	java.sql.Date realRE=rs.getDate(3);
                	if(realRE==null) {
                		a.setRealReDate(null);
                	}else {
                		a.setRealReDate(realRE.toLocalDate());
                	}
                	a.setStaffName(rs.getString(4));
                	arr.add(a);
                }
             	for(int i=0;i<arr.size();i++) {
             		String tmp="select Book.name,detailBC.num,detailBC.lost,cpBook.ISBN\r\n"
             				+ "from borrowCard join detailBC on borrowCard.id=detailBC.bcID \r\n"
             				+ "				join cpBook on detailBC.ISBN=cpBook.ISBN \r\n"
             				+ "				join Book on cpBook.bookID=Book.id \r\n"
             				+ "where borrowCard.id=?";
             		PreparedStatement prep1= ConnectDB.conn.prepareStatement(tmp);
             		prep1.setInt(1, arr.get(i).getId());
                 	ResultSet rs1=prep1.executeQuery();
                 	Vector<smallBook> sb=new Vector<smallBook>();
                 	while(rs1.next()) {
                 		smallBook b=new smallBook();
                 		b.setName(rs1.getString(1));
                 		b.setNum(rs1.getInt(2));
                 		b.setLost(rs1.getInt(3));
                 		b.setISBN(rs1.getString(4));
                 		String t="select Author.name\r\n"
                 				+ "from cpBook join bookAuthor on cpBook.ISBN=bookAuthor.ISBN \r\n"
                 				+ "			join Author on bookAuthor.authorID=Author.id\r\n"
                 				+ "where cpBook.ISBN=?";
                 		PreparedStatement prep2= ConnectDB.conn.prepareStatement(t);
                 		prep2.setString(1,b.getISBN());
                     	ResultSet rs2=prep2.executeQuery();
                     	Vector<String> au=new Vector<String>();
                     	while(rs2.next()) {
                     		au.add(rs2.getString(1));
                     	}
                     	b.setAuthor(au);
                 		sb.add(b);
                 	}
                 	arr.get(i).setNum(sb.size());
                 	arr.get(i).setList(sb);
             	}
             } catch(SQLException ex){
                 ex.printStackTrace();
             } finally {
                 db.disconnect();
             }
         }
    	return arr;
    }
}