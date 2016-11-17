/**
 * Copyright (c) 2016 Betsey McCarthy, Jen Rhodes, Dawn Winsor 
 */

package edu.elon.contact;

/**
 * POJO for storing ResultSet data from queries into Contact objects for easy
 * access in controller and display in UI
 */
public class Contact {
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String major;
	private int id;

	/**
	 * Default constructor for Contact Object
	 */
	public Contact() {
	}

	/**
	 * Constructor to create Contact object with parameters
	 * 
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param email
	 */
	public Contact(String firstName, String middleName, String lastName, String email, String major) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.major = major;
	}

	/**
	 * Gets ID of Contact
	 * 
	 * @return id of Contact
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets ID of Contact
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets first name of Contact
	 * 
	 * @return firstName of Contact
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name of Contact
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets middle name of Contact
	 * 
	 * @return middleName of Contact
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets middle name of Contact
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets last name of Contact
	 * 
	 * @return lastName of Contact
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets last name of Contact
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets email of Contact
	 * 
	 * @return email of Contact
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email of Contact
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets major of Contact
	 * 
	 * @return major of Contact
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * Sets major of contact
	 */
	public void setMajor(String major) {
		this.major = major;
	}

}
