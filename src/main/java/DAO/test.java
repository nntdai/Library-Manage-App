package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.kho_DTO;
import connection.ConnectDB;

public class test {
	public static void main(String[] args) {
		ConnectDB connectDB = null;
        try {
            connectDB = new ConnectDB();
            Kho_DAO khodao = new Kho_DAO(connectDB);

            // Lấy thông tin người dùng theo ID
            int userIdToQuery = 1; // Thay đổi ID người dùng tùy ý
            kho_DTO kho = khodao.getISBN("8");
            
            for (kho_DTO kho_DTO : khodao.list)
			{
                System.out.println("Thông tin người dùng:");
                System.out.println("ISBN: " + kho_DTO.getIsbn());
                System.out.println("bookID: " + kho_DTO.getBookID());
                System.out.println("borrowNum: " + kho_DTO.getBorrowNum());
                System.out.println("storeNum: "+ kho_DTO.getStoreNum());
                System.out.println("edition: "+ kho_DTO.getEdition());
                System.out.println("publisherID: "+ kho_DTO.getPublisherID());
                System.out.println("status: "+ kho_DTO.isStatus());
                System.out.println("Cost: "+ kho_DTO.getCost());
                System.out.println("img: "+ kho_DTO.getImg());
                System.out.println("===============================================================================");
            } if(kho==null) {
                System.out.println("Không tìm thấy người dùng có ID " + userIdToQuery);
            }

//            List<kho_DTO> userList = khodao.getAllUsers();
//            if (!userList.isEmpty()) {
//                System.out.println("Danh sách tất cả người dùng:");
//                for (UserDTO u : userList) {
//                    System.out.println("ID: " + u.getId() + ", Username: " + u.getUsername() + ", Email: " + u.getEmail());
//                }
//            } else {
//                System.out.println("Không có người dùng nào trong cơ sở dữ liệu.");
//            }

//            // Thêm người dùng mới
//            UserDTO newUser = new UserDTO();
//            newUser.setUsername("newUser");
//            newUser.setEmail("newuser@example.com");
//            userDAO.saveUser(newUser);
//            System.out.println("Người dùng mới đã được thêm vào cơ sở dữ liệu.");

            // Đóng kết nối cơ sở dữ liệu
            connectDB.disconnect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connectDB != null) {
                try {
                    connectDB.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
