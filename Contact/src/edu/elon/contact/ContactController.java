/**
 * Copyright (c) 2016 Betsey McCarthy, Jen Rhodes, Dawn Winsor 
 */

package edu.elon.contact;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import com.mysql.jdbc.PreparedStatement;

/**
 * Class to communicate between the model and the view. Adds ActionListeners to
 * UI elements and calls CRUD methods in the model in the ActionListeners to
 * update contact information
 */
public class ContactController {
	ContactView contactView;
	ContactModel contactModel;
	ArrayList<Contact> contacts;
	private int currentArrayIndex;

	/*
	 * Creates an instance of ContactController, instantiates Model and View
	 * objects as well as an ArrayList of Contacts
	 */
	public ContactController() {
		contactView = new ContactView();
		contactModel = new ContactModel();
		contacts = new ArrayList<Contact>();
	}

	/*
	 * Method to be called by application to run program. Adds actionListeners
	 * to GUI objects and creates first GUI window to be displayed to the user
	 */
	public void go() {
		addActionListeners();
		contactView.createInterface(contactView.prevButton, contactView.nextButton);
	}

	/*
	 * Creates ActionListeners GUI elements (JMenuItem, JButton) to respond to
	 * user requests and calls methods in model based on criteria provided by
	 * the user.
	 */
	public void addActionListeners() {
		contactView.addAddActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contactView.createInterface(contactView.okAddButton, contactView.cancelButton);
			}
		});
		contactView.addClearActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dbName = contactView.dataBaseName.getText();
				String tableName = contactView.tableName.getText();
				contactModel.clear(dbName, tableName);
				contacts.clear();
				updateDBInfo();
			}
		});
		contactView.addConnectActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contactView.createLogin();
			}
		});
		contactView.addExitActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contactView.addUpdateActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contacts.remove(contacts.get(currentArrayIndex));
				Contact contact = contactModel.update(currentArrayIndex + 1, contactView.firstName.getText(),
						contactView.middleName.getText(), contactView.lastName.getText(), contactView.email.getText(),
						contactView.major.getText());
				contacts.add(currentArrayIndex, contact);

				updateDBInfo();
			}
		});
		contactView.addRemoveActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contactModel.remove(contacts.get(currentArrayIndex).getId());
				contacts.remove(currentArrayIndex);
				if (currentArrayIndex == contacts.size()) {
					currentArrayIndex -= 1;
				}
				updateDBInfo();
			}
		});
		contactView.addOkDBConnectActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contactView.createInterface(contactView.prevButton, contactView.nextButton);
				if (contactModel.getConnection(contactView.userName.getText(), contactView.password.getText(),
						contactView.ipAddress.getText(), contactView.dataBaseName.getText(),
						contactView.tableName.getText())) {
					contactView.clearDB.setEnabled(true);
					contactView.add.setEnabled(true);
					contactView.update.setEnabled(true);
					contactView.remove.setEnabled(true);
					contactView.prevButton.setEnabled(true);
					contactView.nextButton.setEnabled(true);
					contacts = contactModel.autoDbFill(contactView.tableName.getText());
					contactView.firstName.setText(contacts.get(currentArrayIndex).getFirstName());
					contactView.middleName.setText(contacts.get(currentArrayIndex).getMiddleName());
					contactView.lastName.setText(contacts.get(currentArrayIndex).getLastName());
					contactView.email.setText(contacts.get(currentArrayIndex).getEmail());
					contactView.major.setText(contacts.get(currentArrayIndex).getMajor());
				} else {
					contactView.createLogin();
				}
			}
		});
		contactView.addPrevButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentArrayIndex > 0) {
					currentArrayIndex--;
					updateDBInfo();
				}
			}
		});
		contactView.addNextButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentArrayIndex < contacts.size() - 1) {
					currentArrayIndex++;
					updateDBInfo();
				}
			}
		});
		contactView.okAddButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contact contact = contactModel.add(contactView.firstName.getText(), contactView.middleName.getText(),
						contactView.lastName.getText(), contactView.email.getText(), contactView.major.getText());
				contacts.add(contact);
				contactView.createInterface(contactView.prevButton, contactView.nextButton);
				updateDBInfo();

			}
		});
	}

	/*
	 * Method to be called to update UI Textfields to reflect changes in the
	 * database through use of an ArrayList of Contact objects
	 */
	public void updateDBInfo() {
		if (!contacts.isEmpty()) {
			contactView.firstName.setText(contacts.get(currentArrayIndex).getFirstName());
			contactView.middleName.setText(contacts.get(currentArrayIndex).getMiddleName());
			contactView.lastName.setText(contacts.get(currentArrayIndex).getLastName());
			contactView.email.setText(contacts.get(currentArrayIndex).getEmail());
			contactView.major.setText(contacts.get(currentArrayIndex).getMajor());
		} else {
			contactView.firstName.setText(null);
			contactView.middleName.setText(null);
			contactView.lastName.setText(null);
			contactView.email.setText(null);
			contactView.major.setText(null);
		}
	}
}
