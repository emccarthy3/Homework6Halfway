/**
 * Function.java 1.0 November 17, 2016
 *
 * Copyright (c) 2016 David J. Powell, Dawn Winsor, Betsey McCarthy, Jen Rhodes
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JTextField;

/**
 * Standard interface for consistency in the Elon evaluation process. Each
 * concrete Elon Function must extend this class. Makes an instance of
 * StrategyFactory in order to instantiate strategies. This is the remote object
 * to be passed to the client.
 * 
 * @author dpowell2, dwinsor, jrhodes, emcarthy
 * @version 1.0
 */
public abstract class Function extends UnicastRemoteObject implements FunctionInterface {

	/**
	 * constant to represent new line
	 */
	public static final String EOL = "\n";

	/**
	 * constant to represent one blank space
	 */
	public static final String SPACE = " ";
	private ArrayList<String> inputNames;
	private Strategy strategy;
	private ArrayList<Double> inputValues;
	private ArrayList<Observer> observers;

	// true if minimization function and false if maximization
	private boolean minimize;
	private String optimizationTechnique;
	private Double output;
	private String title;
	private StrategyFactory factory = StrategyFactory.getInstance();
	private String optimizersString;

	/**
	 * Default constructor
	 */
	public Function() throws RemoteException {
	}

	/**
	 * Gets the strategy from the name passed to the method and creates and
	 * instance of strategy
	 * 
	 * @param type
	 *          - the name of the strategy that gets passed and later created
	 * @return strategy - the strategy created from the Factory
	 */
	public Strategy callStrategy(String type) {

		strategy = factory.createStrategy(type);

		return strategy;
	}

	/**
	 * Evaluates the current set of input values to calculate the function value.
	 * We currently consider one output value for a function. If the function has
	 * multiple output values then the function must have these combined into a
	 * single value.
	 * 
	 * @return Double of function result from evaluation at current point.
	 */
	public abstract Double evaluate();

	/**
	 * Returns an ArrayList of String for the names of each input parameter. This
	 * allows the class creator to make the names meaningful to a user instead of
	 * X1, X2, ...
	 * 
	 * @return ArrayList<String> of names for each input parameter
	 */
	public ArrayList<String> getInputNames() {
		return inputNames;
	}

	/**
	 * Returns the current value of each input for the function. All function
	 * inputs are treated as Double
	 * 
	 * @return ArrayList<Double> of values representing current point.
	 */
	public ArrayList<Double> getInputValues() {
		return inputValues;
	}

	/**
	 * Gets the full package qualified classname of the currently set optimization
	 * technique
	 * 
	 * @return String representing package qualified classname of optimization
	 *         technique
	 */
	public String getOptimizationTechnique() {
		return optimizationTechnique;
	}

	/**
	 * Gets the function output value resulting from the evaluation of the current
	 * input set.
	 * 
	 * @return Double representing function result
	 */
	public Double getOutput() {
		return output;
	}

	/**
	 * Gets the name of the function
	 * 
	 * @return String representing the user friendly name of the function.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the direction of the optimization problem. If true then we have a
	 * minimization problem otherwise a maximization problem
	 * 
	 * @return boolean value of true if minimization
	 */
	public boolean isMinimize() {
		return minimize;
	}

	public String getOptimizersString() {
		return optimizersString;
	}

	/**
	 * Optimizes uses the Strategy interface to calculate the optimal value by
	 * passing a function to calculateOptimizationValues().
	 * 
	 * @return Double representing best achieved function value.
	 */
	public Double optimize() {
		Double optimalValue = strategy.calculateOptimizationValues(this);
		return optimalValue;
	}

	/**
	 * Sets the optimization technique (which implements the strategy interface).
	 * 
	 * @param s
	 *          Represents the optimization technique to be passed to the method
	 */
	public void setStrategy(Strategy s) {
		strategy = s;
	}

	/**
	 * Set the current set of input names for the input parameters to the
	 * inputNames passed as a parameter.
	 * 
	 * @param inputNames
	 *          ArrayList<String> of names for set of input parameters for the
	 *          function.
	 */
	public void setInputNames(ArrayList<String> inputNames) {
		this.inputNames = inputNames;
	}

	/**
	 * Sets the current value of the input set for the function.
	 * 
	 * @param inputValues
	 *          ArrayList<Double> representing the value of each input parameter.
	 *          The input set is assumed to be all Doubles.
	 */
	public void setInputValues(ArrayList<Double> inputValues) {
		this.inputValues = inputValues;
	}

	/**
	 * Sets function to be a minimization or a maximization
	 * 
	 * @param minimize
	 *          boolean of true if minimization
	 */
	public void setMinimize(boolean minimize) {
		this.minimize = minimize;
	}

	/**
	 * Sets internal field to the value of the passed parameter which represents
	 * the package qualified class name of the optimization technique to use.
	 * 
	 * @param optimizationTechnique
	 *          String representing package and class name of the optimizer to use
	 *          for the problem.
	 */
	public void setOptimizationTechnique(String optimizationTechnique) {
		this.optimizationTechnique = optimizationTechnique;
	}

	/**
	 * Sets the value of the function result.
	 * 
	 * @param output
	 *          Double instance of function result
	 */
	public void setOutput(Double output) {
		this.output = output;
	}

	/**
	 * Sets the user friendly name of the function
	 * 
	 * @param title
	 *          String representing function name
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public void registerObserver(Observer o) {
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(getInputValues());
		}
	}

	public String getEnvironmentVariables() throws RemoteException {
		return System.getenv("optimizers");
	}

	/**
	 * User friendly representation of the function state and configuration. Shows
	 * the function name, input variable names and input variable values
	 * 
	 * @return String representing state of function.
	 */
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append("Function: " + this.getTitle() + EOL);
		for (int i = 0; i < getInputValues().size(); i++) {
			s.append(getInputNames().get(i) + SPACE + getInputValues().get(i) + EOL);
		}
		return s.toString();
	}

}