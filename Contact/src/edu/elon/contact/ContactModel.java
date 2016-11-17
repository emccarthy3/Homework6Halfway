/**
 *  Copyright (c) 2016 Betsey McCarthy, Jen Rhodes, Dawn Winsor 
 *  
 */

package edu.elon.contact;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * The ContactModel class contains the methods that run features on the GUI:
 * connecting to the database, adding, updating, & removing Contacts from the table, 
 * and clearing the database. It keeps a dynamic list of the Contacts in the table
 * that reflects the changes made by the user. 
 * 
 * @author Betsey, Dawn, & Jen
 *
 */
public class ContactModel {
	java.sql.Connection connection;

	/*
	 * Method that connects to MySQL database with automatic driver loading.
	 * 
	 * @return boolean, that indicates whether or not a successfull database
	 * connection was made
	 */
	public boolean getConnection(String userName, String password, String ipAddress, String dataBaseName,
			String tableName) {
		try {
			connection = DriverManager.getConnection(ipAddress, userName, password);
			System.out.println("Connection established");

		} catch (SQLException e) {
			for (Throwable t : e)
				t.printStackTrace();
			JOptionPane.showMessageDialog(null, "You did not correctly specify db parameters", "DB Settings",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/*
	 * Method that adds a contact to the connected database
	 */
	public Contact add(String firstName, String middleName, String lastName, String email, String major) {
		java.sql.PreparedStatement prepareStmt;
		try {
			prepareStmt = connection.prepareStatement(
					"INSERT INTO Contacts (FirstName, MiddleName, LastName, Email, Major) VALUES ('" + firstName
							+ "', '" + middleName + "', '" + lastName + "', '" + email + "', '" + major + "')");
			prepareStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Contact contact = new Contact(firstName, middleName, lastName, email, major);
		return contact;
	}

	/*
	 * Method that clears a contact from the connected database
	 */
	public void clear(String dbName, String tableName) {
		java.sql.PreparedStatement prepareStmt2;
		java.sql.PreparedStatement prepareStmt3;
		try {
			prepareStmt2 = connection.prepareStatement("use " + dbName + ";");
			prepareStmt3 = connection.prepareStatement("TRUNCATE TABLE " + tableName);

			prepareStmt2.executeUpdate();
			prepareStmt3.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Method that updates a contact in the connected database.
	 * 
	 * @return contact, a POJO 'Contact'
	 */
	public Contact update(int id, String firstName, String middleName, String lastName, String email, String major) {
		java.sql.PreparedStatement prepareStmt;
		try {
			prepareStmt = connection.prepareStatement("UPDATE Contacts SET firstName = '" + firstName
					+ "', middleName = '" + middleName + "', lastName = '" + lastName + "', email = '" + email
					+ "', major = '" + major + "' WHERE id = " + id);
			prepareStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Contact contact = new Contact(firstName, middleName, lastName, email, major);
		contact.setId(id);
		return contact;
	}

	/*
	 * Method that removes a contact from the connected database.
	 */
	public void remove(int id) {
		java.sql.PreparedStatement prepareStmt;
		try {
			prepareStmt = connection.prepareStatement("DELETE FROM Contacts WHERE id = " + id);
			prepareStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Method that populates the Contact list based on the data stored in the
	 * connected database and table the user is connected to.
	 * 
	 * @returns ArrayList<Contact> contacts - an array list of Contact
	 * information for each row (contact) in the table.
	 */
	public ArrayList<Contact> autoDbFill(String tableName) {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try {

			java.sql.PreparedStatement prepareStmt = connection
					.prepareStatement("SELECT id, firstName, middleName, lastName, email, major FROM " + tableName);
			ResultSet rs = prepareStmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String middleName = rs.getString(3);
				String lastName = rs.getString(4);
				String email = rs.getString(5);
				String major = rs.getString(6);
				Contact newContact = new Contact(firstName, middleName, lastName, email, major);
				newContact.setId(id);
				contacts.add(newContact);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacts;
	}

}
