/**
 * Copyright (c) 2016 Betsey McCarthy, Jen Rhodes, Dawn Winsor 
 */

package edu.elon.contact;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * UI for Contact Application, sets up GUI
 */
public class ContactView  {
	JMenuItem add;
	JMenuItem clearDB;
	JMenuItem connect;
	JMenuItem exit;
	JMenuItem remove;
	JMenuItem update;

	JTextField userName;
	JTextField password;
	JTextField ipAddress;
	JTextField dataBaseName;
	JTextField tableName;

	JTextField firstName;
	JTextField middleName;
	JTextField lastName;
	JTextField email;
	JTextField major;

	JButton okDBConnect;
	JButton nextButton;
	JButton prevButton;
	JButton okAddButton;
	JButton cancelButton;

	JFrame frame;
	JFrame frame2;
	JFrame addUpdateRemoveFrame;

	JMenuBar bar;
	JMenu file;
	JMenu edit;

	/*
	 * Class constructor specifying JMenuItems, TextFields, and mnemonic key events to create.
	 * Default values are created for database connection.
	 */
	public ContactView() {
		add = new JMenuItem("Add");
		clearDB = new JMenuItem("Clear DB");
		connect = new JMenuItem("Connect");
		exit = new JMenuItem("Exit");
		remove = new JMenuItem("Remove");
		update = new JMenuItem("Update");
		okDBConnect = new JButton("OK");
		prevButton = new JButton("Previous");
		okAddButton = new JButton("OK");
		nextButton = new JButton("Next");
		cancelButton = new JButton("Cancel");

		prevButton.setEnabled(false);
		nextButton.setEnabled(false);
		add.setEnabled(false);
		update.setEnabled(false);
		remove.setEnabled(false);
		clearDB.setEnabled(false);
		
		bar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");

		file.setMnemonic(KeyEvent.VK_F);
		edit.setMnemonic(KeyEvent.VK_E);
		remove.setMnemonic(KeyEvent.VK_R);
		update.setMnemonic(KeyEvent.VK_U);
		add.setMnemonic(KeyEvent.VK_A);
		clearDB.setMnemonic(KeyEvent.VK_C);
		connect.setMnemonic(KeyEvent.VK_T);

		userName = new JTextField(20);
		password = new JTextField(20);
		ipAddress = new JTextField(20);
		dataBaseName = new JTextField(20);
		tableName = new JTextField(20);

		// Default database connection values
		userName.setText("root");
		password.setText("mysqluser");
		ipAddress.setText("jdbc:mysql://localhost:3306/students");
		dataBaseName.setText("students");
		tableName.setText("Contacts");
	}

	/**
	 * Method creates Swing GUI for database connection. Default values are automatically populated 
	 * in the GUI text fields, however the user is able to alter these to connect to a different
	 * database.
	 */
	public void createLogin() {
		if (frame2 != null) {
			frame2.dispose();
		}
		
		// create menu bar
		bar.add(file);
		bar.add(edit);
		file.add(clearDB);
		file.add(connect);
		file.add(new JSeparator());
		file.add(exit);
		edit.add(add);
		edit.add(remove);
		edit.add(update);

		frame = new JFrame("Contact View Display");
		frame.setJMenuBar(bar);
		frame.setSize(360, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		frame2.setResizable(false);		

		JLabel userNameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		JLabel ipLabel = new JLabel("IP Address");
		JLabel databaseLabel = new JLabel("Database Name");
		JLabel tableNameLabel = new JLabel("Table Name");

		userNameLabel.setLabelFor(userName);
		passwordLabel.setLabelFor(password);
		ipLabel.setLabelFor(ipAddress);
		databaseLabel.setLabelFor(dataBaseName);
		tableNameLabel.setLabelFor(tableName);

		frame.getContentPane().add(userNameLabel);
		frame.getContentPane().add(userName);

		frame.getContentPane().add(passwordLabel);
		frame.getContentPane().add(password);

		frame.getContentPane().add(ipLabel);
		frame.getContentPane().add(ipAddress);

		frame.getContentPane().add(databaseLabel);
		frame.getContentPane().add(dataBaseName);

		frame.getContentPane().add(tableNameLabel);
		frame.getContentPane().add(tableName);

		frame.add(okDBConnect);
		frame.setVisible(true);

	}

	/**
	 * Method creates Swing GUI for the user to view, add, update, and delete 
	 * Contacts from the database he/she is connected to. 
	 */
	public void createInterface(JButton button1, JButton button2) {
		if (frame != null) {
			frame.dispose();
		}
		
		JMenuBar bar = new JMenuBar();		
		bar.add(file);
		bar.add(edit);
		file.add(clearDB);
		file.add(connect);
		file.add(new JSeparator());
		file.add(exit);
		edit.add(add);
		edit.add(remove);
		edit.add(update);

		frame2 = new JFrame("Contact View Display");
		frame2.setJMenuBar(bar);
		frame2.setSize(300, 300);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		frame2.setResizable(false);

		JLabel firstNameLabel = new JLabel("First Name");
		JLabel middleNameLabel = new JLabel("Middle Name");
		JLabel lastNameLabel = new JLabel("Last Name");
		JLabel emailLabel = new JLabel("Email");
		JLabel majorLabel = new JLabel("Major");

		firstName = new JTextField(20);
	
		middleName = new JTextField(20);
	
		lastName = new JTextField(20);
	
		email = new JTextField(20);

		major = new JTextField(20);

		firstNameLabel.setLabelFor(firstName);
		middleNameLabel.setLabelFor(middleName);
		lastNameLabel.setLabelFor(lastName);
		emailLabel.setLabelFor(email);
		majorLabel.setLabelFor(major);

		frame2.getContentPane().add(firstNameLabel);
		frame2.getContentPane().add(firstName);

		frame2.getContentPane().add(middleNameLabel);
		frame2.getContentPane().add(middleName);

		frame2.getContentPane().add(lastNameLabel);
		frame2.getContentPane().add(lastName);

		frame2.getContentPane().add(emailLabel);
		frame2.getContentPane().add(email);

		frame2.getContentPane().add(majorLabel);
		frame2.getContentPane().add(major);

		frame2.add(button1);
		frame2.add(button2);

		frame2.setVisible(true);

	}

	/**
	 * Action listener for the 'Add' menu item feature
	 * purposed to add a Contact to the database the
	 * user is connected to.
	 * @param a
	 */
	public void addAddActionListener(ActionListener a) {
		add.addActionListener(a);
	}

	/**
	 * Action listener for the 'Clear' menu item feature
	 * purposed to clear the database the user is connected to
	 * @param a
	 */	
	public void addClearActionListener(ActionListener a) {
		clearDB.addActionListener(a);
	}

	/**
	 * Action listener for the 'Connect' menu item feature 
	 * purposed to connect to a database
	 * @param a
	 */
	public void addConnectActionListener(ActionListener a) {
		connect.addActionListener(a);
	}

	/**
	 * Action listener for the 'Exit' menu item feature
	 * purposed to close out of the program.
	 * @param a
	 */
	public void addExitActionListener(ActionListener a) {
		exit.addActionListener(a);
	}

	/**
	 * Action listener for the 'Remove' menu item feature
	 * purposed to remove a Contact from the database 
	 * the user is connected to
	 * @param a
	 */
	public void addRemoveActionListener(ActionListener a) {
		remove.addActionListener(a);
	}

	/**
	 * Action listener for the 'Update' menu item feature
	 * purposed to update a Contact in the database the user 
	 * is connected to
	 * @param a
	 */
	public void addUpdateActionListener(ActionListener a) {
		update.addActionListener(a);
	}

	/**
	 * Action listener for the 'OK' button displayed on the
	 * database connection GUI.
	 * @param a
	 */
	public void addOkDBConnectActionListener(ActionListener a) {
		cancelButton.addActionListener(a);
		okDBConnect.addActionListener(a);
	}

	/**
	 * Action listener for the 'Previous' button purposed to 
	 * navigate between Contacts in the connected database
	 * @param a
	 */
	public void addPrevButtonActionListener(ActionListener a) {
		prevButton.addActionListener(a);
	}

	/**
	 * Action listener for the 'Next' button purposed to 
	 * navigate between Contacts in the connected database
	 * @param a
	 */
	public void addNextButtonActionListener(ActionListener a) {
		nextButton.addActionListener(a);
	}

	/**
	 * Action listener for the 'OK' button displayed on the
	 * 'Add' GUI feature, purposed to execute the add command to the database.
	 * @param a
	 */
	public void okAddButtonActionListener(ActionListener a) {
		okAddButton.addActionListener(a);
	}

	/**
	 * Action listener for the 'Cancel' button displayed on the
	 * 'Add' GUI feature, purposed to return back to the database
	 * without executing any changes made.
	 * @param a
	 */
	public void addCancelButtonActionListener(ActionListener a) {
		cancelButton.addActionListener(a);
	}

}
