/**
 * StrategyFactory.java 1.0 October 24, 2016
 *
 * Copyright (c) 2016 Dawn Winsor, Betsey McCarthy, Jen Rhodes
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */

package edu.elon.math;

/**
 * The Strategy Factory class uses the singleton pattern to create a unique
 * instance of itself and then creates optimizer strategies.
 * 
 * @author dwinsor, jrhodes, emccarthy
 *
 */
public class StrategyFactory {
	private static StrategyFactory uniqueInstance;

	/**
	 * This is the singleton private no-argument construction to ensure that only
	 * one instance of StrategyFactory is created.
	 */
	private StrategyFactory() {
	}

	/**
	 * Creates unique instance of StrategyFactory if it doesn't already exist.
	 * 
	 * @return uniqueInstance - the unique Strategy Factory
	 */
	public static StrategyFactory getInstance() {
		if(uniqueInstance == null){
			uniqueInstance = new StrategyFactory();
		}
		return uniqueInstance;
	}

	/**
	 * This method creates the Strategy based on the optimizer that's pulled from
	 * the environmental variable called "optimizers".
	 * 
	 * @param type - the name of the strategy
	 * @return strategy - the strategy created
	 */
	public Strategy createStrategy(String type) {
		Strategy strategy = null;
		try {
			strategy = (Strategy) Class.forName(type).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return strategy;
	}
}
