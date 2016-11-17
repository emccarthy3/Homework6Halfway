/**
 *  Copyright (c) 2016 Betsey McCarthy, Jen Rhodes, Dawn Winsor 
 */

package edu.elon.contact;

/**
 * This class runs the entire program. It does so by running the go() method
 * from the ContactController class. Users are able to connect to a database
 * via Swing GUIs and from there can view, add, update, and remove Contacts
 * from the table. 
 * @author Betsey McCarthy, Jen Rhodes, and Dawn Winsor
 *
 */
public class ContactApplication {

	public static void main(String[] args) {
		ContactController contactController = new ContactController();
		contactController.go();
	}

}
