/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.entities.Staff;
import DTO.entities.SupplyCard;
import DTO.entities.SupplyCardWithStaff;
import connection.ConnectDB;
import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author WIN 10
 */
public class SupplyCardDAO {
    private ConnectDB connectDB;
    public SupplyCardDAO(ConnectDB connectDB) throws SQLException, IOException{
            try {
                    this.connectDB = new ConnectDB();
            } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }
    public SupplyCard getBySupDate(String supDate) {
        SupplyCard sc = null;
        String query = "SELECT supply_card.id, supply_card.supDate, supply_card.provider, supply_card.staffID, Staff.name " +
                     "FROM supply_card " +
                     "INNER JOIN staff ON supply_card.staffID = staff.id " +
                     "WHERE CAST(supply_card.supDate AS DATE) = ?";

        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, supDate);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        sc = new SupplyCard();
                        sc.setId(resultSet.getInt("id"));
                        sc.setSupDate(resultSet.getTimestamp("supDate"));
                        sc.setProvider(resultSet.getString("provider"));
                        sc.setStaffID(resultSet.getInt("staffID"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sc;
    }
    public int getByID() {
        int id = 0;
        String query = "SELECT TOP 1 id FROM supply_card " +
                      "ORDER BY id DESC";

        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("id");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return id;
    }

    public void saveProvider(SupplyCard sc) {
        String query = "INSERT INTO supply_card (provider) VALUES (?)";
        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, sc.getProvider());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getByNameProvider(String name) {
        String status = null;
        String query = "SELECT providerID FROM supply_Card WHERE provider COLLATE Latin1_General_CI_AI = ?";

        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        status = resultSet.getString("providerID");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return status;
    }
	public void saveInfo(SupplyCard supplyCard) {
        String query = "INSERT INTO supply_card (supDate, providerID, staffID, feePaid) VALUES (?, ?, 1004, ?)";
        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setTimestamp(1, supplyCard.getSupDate());
                    preparedStatement.setString(2, supplyCard.getProvider());
                    preparedStatement.setLong(3, supplyCard.getTongchi());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
    public List<SupplyCard> getAllSC() {
        List<SupplyCard> list = new ArrayList<>();
		String query = "SELECT * FROM supply_card";

        try (Connection connection = connectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                SupplyCard sc = new SupplyCard();
                sc.setId(resultSet.getInt("id"));
                Timestamp supDate = resultSet.getTimestamp("supDate");
                sc.setSupDate(supDate);
                sc.setProvider(resultSet.getString("provider"));
                sc.setStaffID(resultSet.getInt("staffID"));
                sc.setTongchi(resultSet.getLong("feePaid"));
                list.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<SupplyCardWithStaff> getAllSupplyCardWithStaff() {
        List<SupplyCardWithStaff> supplyCardsWithStaff = new ArrayList<>();
        String query = "SELECT SC.supDate, SC.providerID, SC.feePaid, S.name FROM supply_card SC INNER JOIN staff S ON SC.staffID = S.id";

        try {
        	connectDB.connect();
        	Connection connection = connectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SupplyCard supplyCard = new SupplyCard();
                supplyCard.setSupDate(resultSet.getTimestamp("supDate"));
                supplyCard.setProvider(resultSet.getString("providerID"));
                supplyCard.setTongchi(resultSet.getLong("feePaid"));

                Staff staff = new Staff();
                staff.setName(resultSet.getString("name"));

                SupplyCardWithStaff supplyCardWithStaff = new SupplyCardWithStaff(supplyCard, staff);
                supplyCardsWithStaff.add(supplyCardWithStaff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplyCardsWithStaff;
    }

    public List<SupplyCardWithStaff> searchBySupDate(Timestamp selectedTimestamp) {
        List<SupplyCardWithStaff> result = new ArrayList<>();
        
        List<SupplyCardWithStaff> allSupplyCards = getAllSupplyCardWithStaff();
        
        for (SupplyCardWithStaff supplyCardWithStaff : allSupplyCards) {
            Timestamp supDate = supplyCardWithStaff.getSupply_Card().getSupDate();
            if (supDate != null && supDate.equals(selectedTimestamp)) {
                result.add(supplyCardWithStaff);
            }
        }
        
        return result;
    }
    
    public List<SupplyCardWithStaff> searchByProvider(String nameToSearch) {
        List<SupplyCardWithStaff> result = new ArrayList<>();
        
        List<SupplyCardWithStaff> allSupplyCards = getAllSupplyCardWithStaff();
        
        for (SupplyCardWithStaff supplyCardWithStaff : allSupplyCards) {
            String name = supplyCardWithStaff.getSupply_Card().getProvider();
            if (name.equalsIgnoreCase(nameToSearch)) {
                result.add(supplyCardWithStaff);
            }
        }
        
        return result;
    }

    
    public List<SupplyCardWithStaff> searchByStaff(String nameToSearch) {
        List<SupplyCardWithStaff> result = new ArrayList<>();
        
        List<SupplyCardWithStaff> allSupplyCards = getAllSupplyCardWithStaff();
        
        for (SupplyCardWithStaff supplyCardWithStaff : allSupplyCards) {
            String name = supplyCardWithStaff.getStaff().getName();
            if (name.equalsIgnoreCase(nameToSearch)) {
                result.add(supplyCardWithStaff);
            }
        }
        
        return result;
    }
    
    public List<SupplyCard> getProvider() throws SQLException {
        List<SupplyCard> list = new ArrayList<>();
		String query = "SELECT DISTINCT providerID FROM supply_card";
        connectDB.connect();
        try (Connection connection = connectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                SupplyCard sc = new SupplyCard();
                sc.setProvider(resultSet.getString("providerID"));
                list.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void disconnect() {
            try {
                 connectDB.disconnect();
         } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
         }
   }
}
