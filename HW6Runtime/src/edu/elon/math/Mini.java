/**
 * Mini.java 1.0 November 13, 2016
 *
 * Copyright (c) 2016 Dawn Winsor, Betsey McCarthy, Jen Rhodes
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import flanagan.math.Minimisation;
import flanagan.math.MinimisationFunction;

/**
 * Mini class uses MinimisationFunction in order to set
 * the proper values (minimisation function, start point, step value, and
 * conversion tolerance). It then calls evaluate on the function and returns the
 * result of the evaluation.
 * 
 * @author dwinsor, emcarthy, jrhodes
 *
 */
public class Mini implements MinimisationFunction {
	private Function function;
	private NelderMead nelderMead;
	
	/**
	 * Constructor that gets passed a function.
	 * 
	 * @param function - the function to be evaluated
	 */
	public Mini(Function function) {
		this.function = function;
	}

	/**
	 * Sets/updates the input values of the function. It negates the evaluation of
	 * the function if the function is a maximization problem, or otherwise leaves
	 * it alone if it's a minimization problem.
	 * 
	 * @return result - the result of the function's evaluation
	 */
	@Override
	public double function(double[] x) {
		nelderMead = new NelderMead();
		function.setInputValues(nelderMead.convertDoubleArrayToArrayList(x));
		double result;

		if (!function.isMinimize()) {
			result = function.evaluate() * -1;
		} else {
			result = function.evaluate();
		}
		return result;
	}
	
}
