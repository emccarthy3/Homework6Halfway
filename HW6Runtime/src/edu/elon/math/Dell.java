/**
 * Dell.java 1.0 August 20, 2016
 *
 * Copyright (c) 2016 Elon University, Dave Powell (good job, Dave!)
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Sum of square error for two design variables predicting the cost of a
 * computer purchase of a certain quantity from Dell.
 * 
 * @author dpowell2
 * @version 1.0
 * 
 */
public class Dell extends Function implements Serializable {
	private ArrayList<Observer> observers;

	/**
	 * Default constructor to set initial input point to (0, 0)
	 * 
	 * @throws RemoteException
	 */
	public Dell() throws RemoteException {
		this(new double[] { 0, 0 });
		observers = new ArrayList<Observer>();
	}

	/**
	 * Constructor initializes initial input point to ArrayList <Double> passed in
	 * as a parameter
	 * 
	 * @param inputValues
	 *          ArrayList<Double> representing values for initial design point.
	 */
	public Dell(ArrayList<Double> inputValues) throws RemoteException {
		this(inputValues, createDefaultInputNames());
	}

	/**
	 * Initializes the names of each input along with its initial value from the
	 * parameters.
	 * 
	 * @param inputValues
	 *          ArrayList<Double> representing values of initial design point.
	 * @param inputNames
	 *          ArrayList<String> representing names of each input parameter
	 */
	public Dell(ArrayList<Double> inputValues, ArrayList<String> inputNames) throws RemoteException {
		this.setInputValues(inputValues);
		this.setInputNames(inputNames);
		this.setMinimize(true);
		this.setTitle("Dell");
	}

	/**
	 * Constructor that initializes starting design point from values passed in as
	 * an array of double.
	 * 
	 * @param inputs
	 *          double[] array of values to set initial design point.
	 */
	public Dell(double[] inputs) throws RemoteException {
		ArrayList<Double> values = new ArrayList<Double>();
		for (double d : inputs) {
			values.add(new Double(d));
		}
		this.setInputValues(values);
		this.setInputNames(createDefaultInputNames());
		this.setMinimize(true);
		this.setTitle("Dell");
	}

	/**
	 * Provides a default set of names for input parameters and is used if user
	 * does not supply any.
	 * 
	 * @return ArrayList<String> representing input parameter names.
	 */
	public static ArrayList<String> createDefaultInputNames() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("x1");
		names.add("x2");
		return names;
	}

	/**
	 * Evaluates the function from the current set of input values. It notifies
	 * the observers of the changes made.
	 * 
	 * @return Double instance of function value
	 */
	@Override
	public Double evaluate() {
		double x1 = getInputValues().get(0).doubleValue();
		double y1 = getInputValues().get(1).doubleValue();
		double[] cost = { 7.9, 25, 13.1, 17.4, 19.5, 13, 17.8, 8.0, 9.2, 6.3, 42.0, 6.6 };
		double[] quantity = { 19, 2, 9, 4, 5, 6, 3, 11, 14, 17, 1, 20 };
		double lsq = 0.0;
		for (int i = 0; i < cost.length; i++) {
			lsq = lsq + Math.pow(cost[i] - x1 * Math.pow(quantity[i], y1), 2);
		}

		this.setOutput(new Double(lsq));
		notifyObservers();
		return this.getOutput();
	}

	// @Override
	// public void registerObserver(Observer o) {
	// observers.add(o);
	// }
	//
	// @Override
	// public void removeObserver(Observer o) {
	// int i = observers.indexOf(o);
	// if (i >= 0){
	// observers.remove(i);
	// }
	// }
	//
	// @Override
	// public void notifyObservers() {
	// for(Observer observer: observers){
	// observer.update(getInputValues());
	// }
	//
	// }
	public void measurementsChanged() {
		notifyObservers();
	}

	@Override
	public String getEnvironmentalVariables() throws RemoteException {

		return System.getenv("optimizers");
	}
}
