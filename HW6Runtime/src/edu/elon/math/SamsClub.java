/**
 * SamsClub.java 1.0 August 20, 2016
 *
 * Copyright (c) 2016 David J. Powell, Dawn Winsor, Betsey McCarthy, Jen Rhodes
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JTextField;

/**
 * Determines the profit value of building a new Sams Club at a particular
 * location. SamsClub extends Function.
 * 
 * @author dpowell2, dwinsor, emccarthy, jrhodes
 * @version 1.0
 * 
 */
public class SamsClub extends Function implements Serializable{
	private ArrayList<Observer> observers;
	/**
	 * Default constructor to set initial input point to (-5, 0)
	 * @throws RemoteException 
	 * 
	 */
	public SamsClub() throws RemoteException {
		this(new double[] { -5, 0 });
		observers = new ArrayList<Observer>();
	}

	/**
	 * Constructor initializes initial input point to ArrayList <Double> passed in
	 * as a parameter
	 * 
	 * @param inputs ArrayList<Double> representing values for initial design
	 * point.
	 */
	public SamsClub(ArrayList<Double> inputs) throws RemoteException {
		this(inputs, createDefaultInputNames());
	}

	/**
	 * Initializes the names of each input along with its initial value from the
	 * parameters.
	 * 
	 * @param values ArrayList<Double> representing values of initial design
	 * point.
	 * @param names ArrayList<String> representing names of each input parameter
	 */
	public SamsClub(ArrayList<Double> values, ArrayList<String> names) throws RemoteException {
		this.setInputValues(values);
		this.setInputNames(names);
		this.setMinimize(false);
		this.setTitle("Sams Club");
	}

	/**
	 * Constructor that initializes starting design point from values passed in as
	 * an array of double.
	 * 
	 * @param inputs double[] array of values to set initial design point.
	 */
	public SamsClub(double[] inputs) throws RemoteException {
		ArrayList<Double> values = new ArrayList<Double>();
		for (double d : inputs) {
			values.add(new Double(d));
		}
		this.setInputValues(values);
		this.setInputNames(createDefaultInputNames());
		// maximize profit for Sams Club
		this.setMinimize(false);
		this.setTitle("Sams Club");
	}

	/**
	 * Provides a default set of names for input parameters and is used if user
	 * does not supply any.
	 * 
	 * @return ArrayList<String> representing input parameter names.
	 */
	private static ArrayList<String> createDefaultInputNames() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("X");
		names.add("Y");
		return names;
	}

	/**
	 * Determines if two SamsClub instances are the same based on having the same
	 * values and names for each input
	 * 
	 * @return boolean true if input names and values are equal
	 */
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o instanceof SamsClub) {
			SamsClub sc = (SamsClub) o;
			result = this.getInputNames().equals(sc.getInputNames()) && getInputValues().equals(sc.getInputValues());
		}
		return result;
	}

	/**
	 * Evaluates the function from the current set of input values. It notifies
	 * the observers of the changes made.
	 * 
	 * @return Double instance of function value
	 */
	@Override
	public Double evaluate() {
		double x = getInputValues().get(0).doubleValue();
		double y = getInputValues().get(1).doubleValue();
		double cost = 60.0 / (1 + Math.pow(x + 1, 2) + Math.pow(y - 3, 2))
				+ 20.0 / (1 + Math.pow(x - 1, 2) + Math.pow(y - 3, 2)) + 30.0 / (1 + Math.pow(x, 2) + Math.pow(y + 4, 2));

		this.setOutput(new Double(cost));
		notifyObservers();
		return this.getOutput();

	}
//	@Override
//	public void registerObserver(Observer o) {
//		observers.add(o);	
//	}
//
//	@Override
//	public void removeObserver(Observer o) {
//		int i = observers.indexOf(o);
//		if (i >= 0){
//			observers.remove(i);
//		}		
//	}
//
//	@Override
//	public void notifyObservers() {
//		for(Observer observer: observers){
//			observer.update(getInputValues());
//		}
//		
//	}
	public void measurementsChanged(){
		notifyObservers();
	}

	@Override
	public String getEnvironmentalVariables() throws RemoteException {
		return System.getenv("optimizers");
	}
	

}
