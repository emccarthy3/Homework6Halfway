/**
 * NelderMead.java 1.0 November 13, 2016
 *
 * Copyright (c) 2016 David J. Powell, Dawn Winsor, Betsey McCarthy, Jen Rhodes
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.util.ArrayList;
import java.util.Vector;
import flanagan.math.Minimisation;

/**
 * Nelder Mead also known as direct simplex method is a widely used nonlinear
 * unconstrained optimization technique. The goSimplex code is only partially
 * implemented and always returns a optimal value of 0.0. This class will be
 * replaced in a follow on homework assignment using the Adapter design pattern
 * and code from Michael Flanagan. Neldermead implements the Strategy pattern.
 * 
 * @author dpowell2, dwinsnor, emccarthy, jrhodes
 * @version 1.0
 */
public class NelderMead implements Strategy {

	private final double initialSimplexSize = .1;
	private int nDim;
	private double[] startPoint;
	private double[][] vertices;
	private final double FTOL = 1e-15;

	/**
	 * Default constructor to satisfy coding best practices
	 */
	public NelderMead() {

	}

	/**
	 * Arrays will start at 1 instead of 0 so we need to allocate 1 more space
	 * than anticipated.
	 * 
	 * @param nDim the number of elements in the input
	 */
	public void allocateArrays(int nDim) {
		startPoint = new double[nDim + 1];
		vertices = new double[nDim + 2][nDim + 1];
	}

	/**
	 * This calculates the step size. If the absolute value of a starting value is
	 * less than 1.0, the step size will be 1.0, otherwise it will be 0.5*the
	 * current value.
	 * 
	 * @param start
	 * @return step - the step size
	 */
	public double[] stepSize(double[] start) {
		double[] step = new double[start.length];
		for (int i = 0; i < start.length; ++i) {
			if (Math.abs(start[i]) < 1) {
				step[i] = 1;
			} else {
				step[i] = .5 * start[i];
			}
		}
		return step;
	}

	/**
	 * Leaves optimal design as the value of the parameter, function instance,
	 * field called inputValues;
	 * 
	 * @param function Function instance containing function starting point and
	 * evaluation logic
	 * @return Double value for optimal design.
	 */
	public Double goSimplex(Function function) {
		double objective = 0.0;
		nDim = function.getInputValues().size();
		allocateArrays(nDim);
		setStartingPoint(function.getInputValues());
		// similarity check
		getElonCopyrightVertices();
		createInitialSetOfPoints();
		return new Double(objective);
	}

	/**
	 * Will be implemented in a later assignment.
	 */
	@SuppressWarnings("unused")
	private void amoeba() {
		Vector<Double> pSum = new Vector<Double>();
		pSum.add(10.0); // first element is a dummy placeholder
	}

	/**
	 * Will be implemented in a later assignment.
	 */
	@SuppressWarnings("unused")
	private void amoeba(double[][] p, double[] y, int elondim, float ftol, Integer nFunk) {
		// not implemented
	}

	/**
	 * Methods creates the initial set of points to be used in the optimization
	 * calculation.
	 */
	private void createInitialSetOfPoints() {
		for (int i = 2; i < nDim + 2; i++) {
			for (int j = 1; j < startPoint.length; j++) {
				double value = initialSimplexSize * startPoint[j];
				if (Math.abs(startPoint[j]) <= .5) {
					value = 1.0;
				}
				if ((i - 1) == j) {
					vertices[i][j] = value;
				} else {
					vertices[i][j] = startPoint[j];
				}
			}
		}
	}

	/**
	 * Vertices to be used in the goSimplex() method.
	 * 
	 * @return vertices represents a double array of vertex points
	 */
	private double[][] getElonCopyrightVertices() {
		return vertices;
	}

	/**
	 * Will be implemented in a later assignment.
	 */
	@SuppressWarnings("unused")
	private int getNDim() {
		return nDim;
	}

	/**
	 * Will be implemented in a later assignment.
	 */
	@SuppressWarnings("unused")
	private double[] getStartPoint() {
		return startPoint;
	}

	/**
	 * Methods creates the starting point to be used in the optimization
	 * calculation.
	 */
	private void setStartingPoint(ArrayList<Double> values) {
		// 0 row index is the dummy position
		int col;
		int row;
		for (col = 0; col < vertices[0].length; col++) {
			vertices[0][col] = 0;
		}
		// 0 column is not used on each row
		for (row = 0; row < vertices.length; row++) {
			vertices[row][0] = 0.0;
		}
		// row 1 is the entry design point
		row = 1;
		for (col = 0; col < values.size(); col++) {
			startPoint[col + 1] = values.get(col).doubleValue();
			vertices[row][col + 1] = startPoint[col + 1];
		}
	}

	/**
	 * Calculates the optimization values of the function passed to the method. It
	 * then calls function method of the Mini class which implements
	 * MinimisationFunction, which finds the minimum (if minimization problem) or
	 * maximum (if maximization problem) value. That value is set as the result in
	 * the GUI.
	 * 
	 * @return minmax - the best minimum/maximum to be set as the optimal point
	 */
	@Override
	public Double calculateOptimizationValues(Function function) {
		Mini mini = new Mini(function);
		mini.function(convertArrayListToDouble(function.getInputValues()));
		Minimisation minimisation = new Minimisation();
		double[] startPoint = convertArrayListToDouble(function.getInputValues());
		minimisation.nelderMead(mini, startPoint, stepSize(startPoint), FTOL);
		double minmax = minimisation.getMinimum();
		double [] bestInputs = minimisation.getParamValues();
		function.setInputValues(convertDoubleArrayToArrayList(bestInputs));
		minmax = function.evaluate();
		return minmax;
	}

	/**
	 * Converts an arraylist of Double into an array of doubles
	 * 
	 * @param aStartingPoint arraylist of Double representing an input point for
	 * an optimization problem
	 * @return double array of input point represented as a 1D vector
	 */
	public double[] convertArrayListToDouble(ArrayList<Double> aStartingPoint) {
		int length = aStartingPoint.size();
		double[] inputArray = new double[length];
		for (int i = 0; i < length; i++) {
			inputArray[i] = aStartingPoint.get(i);
		}
		return inputArray;
	}

	/**
	 * Converts a input point represented as a one dimensional array of doubles
	 * into an arraylist of double
	 * 
	 * @param aInputArray input array of double values
	 * @return input arraylist of Double values
	 */
	public ArrayList<Double> convertDoubleArrayToArrayList(double[] aInputArray) {
		ArrayList<Double> bestInputPoint = new ArrayList<Double>();
		for (double d : aInputArray) {
			bestInputPoint.add(d);
		}
		return bestInputPoint;
	}

}
