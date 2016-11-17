/**
 * MinimumAbsoluteSum.java 1.0 August 20, 2016
 *
 * Copyright (c) 2016 Dave Powell, Dawn Winsor, Betsey McCarthy, Jen Rhodes
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JTextField;

/**
 * 
 * Class extends Function. Takes 10 inputs between and including (-100 and 100).
 * MinimumAbsoluteSum calculates the sum of the absolute values of the input
 * parameters. The function is set up for minimization.
 * 
 * @author dpowell2, dwinsor, emccarthy, jrhodes
 * @version 1.0
 * 
 */
public class MinimumAbsoluteSum extends Function implements Serializable {
	private ArrayList<Observer> observers;
	/**
	 * Default constructor to set initial input values
	 * @throws RemoteException 
	 * 
	 */
	public MinimumAbsoluteSum() throws RemoteException {
		this(new double[] { -100, 100, -100, 100, -100, 100, -100, 100, -100, 100 });
		observers = new ArrayList<Observer>();
	}

	/**
	 * Constructor initializes initial input point to ArrayList <Double> passed in
	 * as a parameter
	 * 
	 * @param inputs ArrayList<Double> representing values for initial design
	 * point.
	 */
	public MinimumAbsoluteSum(ArrayList<Double> inputs) throws RemoteException{
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
	public MinimumAbsoluteSum(ArrayList<Double> values, ArrayList<String> names) throws RemoteException {
		this.setInputValues(values);
		this.setInputNames(names);
		this.setMinimize(false);
		this.setTitle("Minimum Absolute Sum");
	}

	/**
	 * Constructor that initializes starting design point from values passed in as
	 * an array of double.
	 * 
	 * @param inputs double[] array of values to set initial design point.
	 */
	public MinimumAbsoluteSum(double[] inputs) throws RemoteException{
		ArrayList<Double> values = new ArrayList<Double>();
		for (double d : inputs) {
			values.add(new Double(d));
		}
		this.setInputValues(values);
		this.setInputNames(createDefaultInputNames());

		this.setMinimize(true);
		this.setTitle("MinimumAbsoluteSum");
	}

	/**
	 * Provides a default set of names for input parameters and is used if user
	 * does not supply any.
	 * 
	 * @return ArrayList<String> representing input parameter names.
	 */
	private static ArrayList<String> createDefaultInputNames() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Value1");
		names.add("Value2");
		names.add("Value3");
		names.add("Value4");
		names.add("Value5");
		names.add("Value6");
		names.add("Value7");
		names.add("Value8");
		names.add("Value9");
		names.add("Value10");
		return names;
	}

	/**
	 * Determines if two MinimumabsoluteSum instances are the same based on having
	 * the same values and names for each input
	 * 
	 * @return boolean true if input names and values are equal
	 */
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o instanceof MinimumAbsoluteSum) {
			MinimumAbsoluteSum sc = (MinimumAbsoluteSum) o;
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
		double v1 = Math.abs(getInputValues().get(0).doubleValue());
		double v2 = Math.abs(getInputValues().get(1).doubleValue());
		double v3 = Math.abs(getInputValues().get(2).doubleValue());
		double v4 = Math.abs(getInputValues().get(3).doubleValue());
		double v5 = Math.abs(getInputValues().get(4).doubleValue());
		double v6 = Math.abs(getInputValues().get(5).doubleValue());
		double v7 = Math.abs(getInputValues().get(6).doubleValue());
		double v8 = Math.abs(getInputValues().get(7).doubleValue());
		double v9 = Math.abs(getInputValues().get(8).doubleValue());
		double v10 = Math.abs(getInputValues().get(9).doubleValue());
		double sum = v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8 + v9 + v10;
		this.setOutput(new Double(sum));
		notifyObservers();
		return this.getOutput();

	}
//	@Override
//	public void registerObserver(Observer o) {
//		observers.add(o);
//		
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
