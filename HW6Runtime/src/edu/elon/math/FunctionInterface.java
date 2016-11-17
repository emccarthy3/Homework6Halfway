/**
 * FunctionInterface.java 1.0 November 17, 2016
 *
 * Copyright (c) 2016 Dawn Winsor, Betsey McCarthy, Jen Rhodes
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */

package edu.elon.math;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This class extends Remote. It acts as the remote interface for the proxy
 * pattern.
 * 
 * @author emccarthy3, dwinsor, jrhodes
 *
 */
public interface FunctionInterface extends Remote {

	public String getEnvironmentalVariables() throws RemoteException;

	/**
	 * Gets the strategy from the name passed to the method and creates and
	 * instance of strategy
	 * 
	 * @param type
	 *          - the name of the strategy that gets passed and later created
	 * @return strategy - the strategy created from the Factory
	 */
	public Strategy callStrategy(String type) throws RemoteException;

	/**
	 * Evaluates the current set of input values to calculate the function value.
	 * We currently consider one output value for a function. If the function has
	 * multiple output values then the function must have these combined into a
	 * single value.
	 * 
	 * @return Double of function result from evaluation at current point.
	 */
	public abstract Double evaluate() throws RemoteException;

	/**
	 * Returns an ArrayList of String for the names of each input parameter. This
	 * allows the class creator to make the names meaningful to a user instead of
	 * X1, X2, ...
	 * 
	 * @return ArrayList<String> of names for each input parameter
	 */
	public ArrayList<String> getInputNames() throws RemoteException;

	/**
	 * Returns the current value of each input for the function. All function
	 * inputs are treated as Double
	 * 
	 * @return ArrayList<Double> of values representing current point.
	 */
	public ArrayList<Double> getInputValues() throws RemoteException;

	/**
	 * Gets the full package qualified classname of the currently set optimization
	 * technique
	 * 
	 * @return String representing package qualified classname of optimization
	 *         technique
	 */
	public String getOptimizationTechnique() throws RemoteException;

	/**
	 * Gets the function output value resulting from the evaluation of the current
	 * input set.
	 * 
	 * @return Double representing function result
	 */
	public Double getOutput() throws RemoteException;

	/**
	 * Gets the name of the function
	 * 
	 * @return String representing the user friendly name of the function.
	 */
	public String getTitle() throws RemoteException;

	/**
	 * Gets the direction of the optimization problem. If true then we have a
	 * minimization problem otherwise a maximization problem
	 * 
	 * @return boolean value of true if minimization
	 */
	public boolean isMinimize() throws RemoteException;

	public String getOptimizersString() throws RemoteException;

	/**
	 * Optimizes uses the Strategy interface to calculate the optimal value by
	 * passing a function to calculateOptimizationValues().
	 * 
	 * @return Double representing best achieved function value.
	 */
	public Double optimize() throws RemoteException;

	/**
	 * Sets the optimization technique (which implements the strategy interface).
	 * 
	 * @param s
	 *          Represents the optimization technique to be passed to the method
	 */
	public void setStrategy(Strategy s) throws RemoteException;

	/**
	 * Set the current set of input names for the input parameters to the
	 * inputNames passed as a parameter.
	 * 
	 * @param inputNames
	 *          ArrayList<String> of names for set of input parameters for the
	 *          function.
	 */
	public void setInputNames(ArrayList<String> inputNames) throws RemoteException;

	/**
	 * Sets the current value of the input set for the function.
	 * 
	 * @param inputValues
	 *          ArrayList<Double> representing the value of each input parameter.
	 *          The input set is assumed to be all Doubles.
	 */
	public void setInputValues(ArrayList<Double> inputValues) throws RemoteException;

	/**
	 * Sets function to be a minimization or a maximization
	 * 
	 * @param minimize
	 *          boolean of true if minimization
	 */
	public void setMinimize(boolean minimize) throws RemoteException;

	/**
	 * Sets internal field to the value of the passed parameter which represents
	 * the package qualified class name of the optimization technique to use.
	 * 
	 * @param optimizationTechnique
	 *          String representing package and class name of the optimizer to use
	 *          for the problem.
	 */
	public void setOptimizationTechnique(String optimizationTechnique) throws RemoteException;

	/**
	 * Sets the value of the function result.
	 * 
	 * @param output
	 *          Double instance of function result
	 */
	public void setOutput(Double output) throws RemoteException;

	/**
	 * Sets the user friendly name of the function
	 * 
	 * @param title
	 *          String representing function name
	 */
	public void setTitle(String title) throws RemoteException;

	/**
	 * Registers the observers to the function
	 * 
	 * @param o
	 * @throws RemoteException
	 */
	public abstract void registerObserver(Observer o) throws RemoteException;

	/**
	 * Removes an observer from the function
	 * 
	 * @param o
	 * @throws RemoteException
	 */
	public abstract void removeObserver(Observer o) throws RemoteException;

	/**
	 * Notifies the observers when changes in the function values have occurred
	 * 
	 * @throws RemoteException
	 */
	public abstract void notifyObservers() throws RemoteException;

	/**
	 * Get the environmental variables that will be used to create individual
	 * functions
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public String getEnvironmentVariables() throws RemoteException;

}
