/*
 * Copyright (c) 2016 Betsey McCarthy, Jen Rhodes, Dawn Winsor 
 */

/**
 * JUnit test for each of the CRUD database operations needed to support manipulation of the contacts.
 * Tests are performed on add contact, update contact, remove contact, and delete all contacts 
 * to ensure they are properly being put into the MySQL database. 
 */

package edu.elon.contact;

import static org.junit.Assert.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test class for CRUD methods in the model
 */
public class ContactModelTest {	
	String actual;
	String expected;
	ContactModel model;
	java.sql.PreparedStatement prepare;
	ResultSet rs;
	String username = "root";
	String password = "mysqluser";
	String ipaddress = "jdbc:mysql://localhost:3306/students";
	String databasename = "students";
	String tablename = "contacts";
	java.sql.Connection connection;
	
	/**
	 * Establishes connection to the database for use in the other test methods
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		connection = DriverManager.getConnection(ipaddress, username, password);
		model = new ContactModel();
		model.getConnection(username, password, ipaddress, databasename, tablename);
	}

	/**
	 * Tests to see if the model adds the specified value at the given location in the database.
	 */
	@Test
	public void addTest() {
		model.add("Dawn", "Elizabeth", "Winsor", "dwinsor@elon.edu", "Computer Science");
		try {
			prepare = model.connection.prepareStatement("SELECT * FROM Contacts WHERE email = 'dwinsor@elon.edu'");
			ResultSet found = prepare.executeQuery();
			actual = found.getString(0);
			expected = "Dawn";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(actual,expected);	
	}
	
	/**
	 * Tests to see if the database updates the requested item to the user specified value
	 */
	@Test
	public void updateTest() {
		try {				
			model.update(1, "This", "is", "the", "updated", "info");
			prepare = model.connection.prepareStatement("SELECT * FROM Contacts WHERE id = '1'");
			ResultSet rs2 = prepare.executeQuery();
			actual = rs2.getString(0);
			expected = "This";		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(expected,actual);
	}
	
	/**
	 * Tests if one element can be removed from the database through the model by attempting to select
	 * the deleted object and verifying it is null.
	 */
	@Test
	public void removeTest() {
		try {
			model.remove(1);
			prepare = model.connection.prepareStatement("SELECT * FROM Contacts WHERE id = '1'");
			ResultSet rs = prepare.executeQuery();
			actual = rs.getString(0);
			expected = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(expected, actual);
	}
	
	/**
	 * Tests if model can clear the database by selecting a couple of values from the database and verifying they are null after
	 * clearing
	 */
	@Test
	public void clearTest() {
		String actual2 = null;
		try {
			model.clear(databasename, tablename);
			prepare = model.connection.prepareStatement("SELECT * FROM Contacts WHERE id = '1'");
			ResultSet rs = prepare.executeQuery();
			actual = rs.getString(0);
			prepare = model.connection.prepareStatement("SELECT * FROM Contacts WHERE id = '2'");
			rs = prepare.executeQuery();
			actual2 = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(expected, actual);
		assertEquals(expected, actual2);
	}
	


	

	
}
