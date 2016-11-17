/**
 * Strategy.java 1.0 October 10, 2016
 *
 * Copyright (c) 2016 Dawn Winsor, Betsey McCarthy, Jen Rhodes
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

/**
 * The interface which the optimization techniques implement. Uses Strategy
 * pattern.
 * 
 * @author dwinsor, emccarthy, jrhodes
 *
 */
public interface Strategy {

	/**
	 * Calculates the optimization values from the function passed through as a
	 * parameter.
	 * 
	 * @param function represents the function passed
	 * @return double with the result of the optimization
	 */
	public abstract Double calculateOptimizationValues(Function function);

}
