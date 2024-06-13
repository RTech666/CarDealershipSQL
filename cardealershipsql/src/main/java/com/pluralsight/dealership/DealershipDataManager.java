package com.pluralsight.dealership;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DealershipDataManager {
    // Create database login variables.
    private static final String database = "jdbc:mysql://localhost:3306/cardealershipdatabase";
    private static final String databaseUser = "root";
    private static final String databasePass = "TC2%T@ajrGUhcB";

    // Create getDealershipByID method.
    public Dealership getDealershipByID(int dealershipID) {
        // Create the query.
        String sql = "SELECT * FROM dealerships WHERE DealershipID = ?";

        // Connect to the databse and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dealershipID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("Name");
                    String address = rs.getString("Address");
                    String phone = rs.getString("Phone");
                    return new Dealership(dealershipID, name, address, phone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create saveDealership method.
    public void saveDealership(Dealership dealership) {
        // Create the query.
        String sql = "INSERT INTO dealerships (DealershipID, Name, Address, Phone) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE Name = VALUES(Name), Address = VALUES(Address), Phone = VALUES(Phone)";

        // Connect to the database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dealership.getdealershipID());
            stmt.setString(2, dealership.getName());
            stmt.setString(3, dealership.getAddress());
            stmt.setString(4, dealership.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create deleteDealership method.
    public void deleteDealership(int dealershipID) {
        // Create the query.
        String sql = "DELETE FROM dealerships WHERE DealershipID = ?";
        
        // Connect to database and make the changes.
        try (Connection conn = DriverManager.getConnection(database, databaseUser, databasePass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dealershipID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
