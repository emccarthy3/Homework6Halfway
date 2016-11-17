/**
 * Observer.java 1.0 November 17, 2016
 *
 * Copyright (c) 2016 Dawn Winsor, Betsey McCarthy, Jen Rhodes
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.util.ArrayList;

import javax.swing.JTextField;

/**
 * This is the observer interface for our created Observer class
 * 
 * @author emccarthy3, dwinsor, jrhodes
 *
 */
public interface Observer {

	/**
	 * This updates the array list of input values in the Gui
	 * 
	 * @param inputValues
	 */
	public void update(ArrayList<Double> inputValues);

}
