package com.irrelevxnce;

import java.awt.Dimension;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JFrame;

public class EmbeddedDatabaseConnector {
    public HashMap<String, String> loginToParse= new HashMap<>();
    public String tableName;
    String url = "jdbc:derby:UIDSecrets;create=true";
    private Connection connection;
    public HashMap<Integer, String> items = new HashMap<>();

    public HashMap<String, String> connect(Boolean creating, Boolean UIDSecrets, Boolean creatingUser, String nullableName, String nullableSecret, Boolean defaultFalse, String nullableAmount, String nullableDescription, String nullablePrice) throws SQLException, NoSuchAlgorithmException {
        connection = DriverManager.getConnection(url);
        System.out.println("Database Created.");
        Statement s = connection.createStatement();
        if (!creating) {
        	 if (UIDSecrets) {
        		 ResultSet rs = s.executeQuery("SELECT * FROM UIDSecrets");
            	 while(rs.next()) {
            		 String username = rs.getString("UID");
            		 String pass = rs.getString("Secret");
            		 loginToParse.put(username, pass);
            		 System.out.println(pass);
            	 }
        	 } else {
        		 ResultSet rs = s.executeQuery("SELECT * FROM Inventory");
        		 int i = 0;
            	 while(rs.next()) {
            		 String itemID = rs.getString("ItemID");
            		 String amount = rs.getString("Amount");
            		 String description = rs.getString("Description");
            		 String price = rs.getString("Price");
            		 i++;
            		 items.put(i, new String("ID: " + itemID.toUpperCase() + ", Amount: " + amount + ", Description: " + description + ", Price: " + price));
            	 }
        	 }
        } else {
        	if (creatingUser) {
        		String name = nullableName;
        		String pass = nullableSecret;
        		System.out.println(pass);
        		Boolean isAdmin = defaultFalse;
        		String q = "INSERT INTO UIDSecrets(UID, Secret, IsAdmin) VALUES('" + name + "', '" + pass + "', '" + isAdmin + "')";
        		System.out.println(q);
        		System.out.println(pass.length());
        		s.executeUpdate(q);
        	} else {
        		String name = nullableName;
        		String desc = nullableDescription;
        		String amount = nullableAmount;
        		String price = nullablePrice;
        		String sql = "INSERT INTO Inventory(ItemID, Amount, Description, Price) VALUES('" + name.toUpperCase() + "', '" + amount + "', '" + desc + "', '" + price + "')";
        		s.executeUpdate(sql);
        	}
        }
		return loginToParse;
    }

    public void connect(String sqlMaker) throws SQLException {
    	 try {
			connection = DriverManager.getConnection(url);
			 Statement s = connection.createStatement();
			 s.executeUpdate(sqlMaker);
		} catch (NullPointerException e1) {
			JFrame frame2 = new JFrame("Delete Item");
			frame2.setPreferredSize(new Dimension(550, 100));
			WelcomePage.launchFrame(frame2, "Item with specified ID does not exist.");
		}
    }

    public Boolean getAdminStatus(String username) throws SQLException {
    	connection = DriverManager.getConnection(url);
    	Statement s = connection.createStatement();
    	ResultSet rs = s.executeQuery("SELECT * FROM UIDSecrets WHERE UID = '" + username + "'");
    	Boolean isAdmin = false;
    	while(rs.next()) {
    		isAdmin = rs.getBoolean("IsAdmin");
    	}
		return isAdmin;
    }



    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
