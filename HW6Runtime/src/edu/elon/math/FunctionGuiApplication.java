/**
 * FunctionGuiApplication.java 1.0 November 17, 2016
 *
 * Copyright (c) 2016 David J. Powell, Dawn Winsor, Betsey McCarthy, Jen Rhodes
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Application which contains the main method that when run causes a GUI(s) to
 * display the starting input of a function(s) given by an environmental
 * variable called "optimizers". If multiple functions are provided in the
 * environmental variable, these are delimited by ','. This class utilizes the
 * Simple Factory Pattern to instantiate optimizer strategies. This class acts
 * as the client for the proxy pattern.
 * 
 * @author dpowell2, dwinsor, emccarthy, jrhodes
 * @version 1.0
 */
public class FunctionGuiApplication extends JFrame implements Observer, Serializable {

	private JButton solveButton;
	private JButton optimizeButton;

	private FunctionInterface function;
	private JComboBox<String> comboBox;
	private JScrollPane scroll;

	private JTextField resultTextField;
	private String[] optimizersArray;
	private ArrayList<String> labels;
	private ArrayList<JTextField> textFields;
	private String optimizersString;
	private ArrayList<Strategy> stratArray;
	private ArrayList<Double> textFieldValues;
	private ArrayList<Double> inputValues;
	private DefaultComboBoxModel<String> model;
	private ThreadClass threadClass;

	/**
	 * Application to optimize an Elon function using one of a variety of
	 * optimization techniques. Currently the only two working techniques are
	 * RandomWalk and Powell. The main method instantiates GUIs by passing
	 * function as a parameter.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		// Function samsFunction = null;
		// Function dellFunction = null;
		// Function minAbsSumFunction = null;
		// try {
		// samsFunction = new SamsClub();
		// System.out.println("SAMS ENV VARS " + samsFunction.getTitle());
		// dellFunction = new Dell();
		// minAbsSumFunction = new MinimumAbsoluteSum();
		// } catch (RemoteException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		System.setProperty("java.security.policy", "client.policy");
		System.setSecurityManager(new SecurityManager());
		String url = "rmi://localhost/";
		if (args.length == 1) {
			url = "rmi://" + args[0] + "/";
		}
		// change to "rmi://yourserver.com/"
		// when server runs on remote machine yourserver.com
		try {
			Context namingContext = new InitialContext();
			FunctionInterface f1 = (FunctionInterface) namingContext.lookup(url + "dell");
			// Dell f1 = (Dell) namingContext.lookup(url + "dell");

			FunctionInterface f2 = (FunctionInterface) namingContext.lookup(url + "samsClub");
			// SamsClub f2 = (SamsClub) namingContext.lookup(url + "samsClub");

			FunctionInterface f3 = (FunctionInterface) namingContext.lookup(url + "minAbsSum");
			// MinimumAbsoluteSum f3 = (MinimumAbsoluteSum)
			// namingContext.lookup(url + "minAbsSum");
			FunctionGuiApplication dellApplication = new FunctionGuiApplication(f1);
			FunctionGuiApplication samsApplication = new FunctionGuiApplication(f2);
			FunctionGuiApplication minApplication = new FunctionGuiApplication(f3);
			System.out.println("Environment Variables" + f3.getEnvironmentalVariables());
			System.out.println(f1.getTitle());
			System.out.println(f2.getTitle());
			System.out.println(f3.getTitle());

			FunctionServer server;
			String optimizersEnvironment;

			JFrame dell = new FunctionGuiApplication(f1);
			dell.setLocation(0, 0);

			JFrame samsClub = new FunctionGuiApplication(f2);
			samsClub.setLocation(0, 230);

			JFrame minAbsSum = new FunctionGuiApplication(f3);
			minAbsSum.setLocation(330, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * FunctionGuiApplication takes observable (in this case a function) as a
	 * parameter and adds itself as an observer of the function.
	 * 
	 * @param o
	 */
	public FunctionGuiApplication(FunctionInterface function) {
		this.function = function;
		// try {
		// function.registerObserver(this);
		// } catch (RemoteException e1) {
		// e1.printStackTrace();
		// }
		textFieldValues = new ArrayList<Double>();
		textFields = new ArrayList<JTextField>();
		try {
			labels = function.getInputNames();
			function.getInputValues();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		createGui();

	}

	/**
	 * Method sets up GUI display(s) based on input values and labels defined in
	 * the observer (function) itself.
	 */
	public void createGui() {
		try {
			this.setTitle(function.getTitle());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		resultTextField = new JTextField();
		inputValues = new ArrayList<Double>();
		Container container = this.getContentPane();

		JPanel holdGrid = new JPanel();
		JPanel leftGrid = new JPanel();
		JPanel rightGrid = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel resultPanel = new JPanel();
		JPanel techniquePanel = new JPanel();
		JPanel bottomGrid = new JPanel();
		// optimizersString = System.getenv("optimizers");
		try {
			optimizersString = function.getEnvironmentalVariables();

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("OP STRING!! " + optimizersString);
		optimizersArray = optimizersString.split(",");
		for (int i = 0; i < optimizersArray.length; ++i) {
			System.out.println("THE ARRAY " + optimizersArray[i]);
		}
		model = new DefaultComboBoxModel<String>();
		stratArray = new ArrayList<Strategy>();

		for (int i = 0; i < optimizersArray.length; ++i) {
			Strategy strat;
			try {
				strat = function.callStrategy(optimizersArray[i]);
				function.setStrategy(strat);
				stratArray.add(strat);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

			model.addElement(optimizersArray[i]);
		}

		comboBox = new JComboBox<String>(model);

		techniquePanel.add(new JLabel("Technique", SwingConstants.RIGHT));
		techniquePanel.add(comboBox);

		int gridHeight = labels.size();
		leftGrid.setLayout(new GridLayout(gridHeight, 3));
		rightGrid.setLayout(new GridLayout(gridHeight, 3));

		for (int i = 0; i < labels.size(); ++i) {
			JTextField jText;
			try {
				jText = new JTextField(function.getInputValues().get(i) + "", SwingConstants.RIGHT);
				jText.setText(function.getInputValues().get(i) + "");
				textFieldValues.add(function.getInputValues().get(i));
				textFields.add(jText);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

			leftGrid.add(new JLabel(labels.get(i), SwingConstants.RIGHT));
			rightGrid.add(textFields.get(i));
		}

		resultPanel.add(new JLabel("Result", SwingConstants.RIGHT));
		resultPanel.add(resultTextField = new JTextField("", 20));

		buttonPanel.add(solveButton = new JButton("Solve"));
		buttonPanel.add(optimizeButton = new JButton("Optimize"));

		holdGrid.setLayout(new BorderLayout(5, 0));
		holdGrid.add(leftGrid, BorderLayout.WEST);
		holdGrid.add(rightGrid, BorderLayout.CENTER);
		scroll = new JScrollPane(holdGrid);

		bottomGrid.setLayout(new BorderLayout(3, 0));
		bottomGrid.add(resultPanel, BorderLayout.NORTH);
		bottomGrid.add(buttonPanel, BorderLayout.SOUTH);

		container.add(techniquePanel, BorderLayout.NORTH);
		container.add(scroll, BorderLayout.CENTER);
		container.add(bottomGrid, BorderLayout.SOUTH);

		solveButton.addActionListener(event -> solve());
		optimizeButton.addActionListener(event -> {
			threadClass = new ThreadClass();
			threadClass.start();
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}

	/**
	 * Method takes values from text-fields and sets them as input parameters to
	 * the function and calls that function's evaluate method.
	 */
	public void solve() {
		ArrayList<Double> inputs = new ArrayList<Double>();

		for (JTextField textfield : textFields) {
			inputs.add(Double.parseDouble(textfield.getText()));
		}

		try {
			function.setInputValues(inputs);
			function.evaluate();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method gets called when the values of the function have been modified
	 * through setChange() and notifyObservers() methods. It resets the text-field
	 * values based on that change.
	 */
	@Override
	public void update(ArrayList<Double> inputValues) {
		for (int i = 0; i < textFields.size(); ++i) {
			textFields.get(i).setText(inputValues.get(i) + "");
		}
		try {
			resultTextField.setText(function.getOutput() + "");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ThreadClass class starts a thread in order to dynamically update the GUI
	 * when update() method is called.
	 * 
	 * @author dwinsor, emccarthy, jrhodes
	 *
	 */
	public class ThreadClass extends Thread {
		public void run() {

			ArrayList<Double> inputs = new ArrayList<Double>();

			for (JTextField textfield : textFields) {
				inputs.add(Double.parseDouble(textfield.getText()));
			}
			try {
				function.callStrategy(comboBox.getSelectedItem().toString());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < textFields.size(); ++i) {
				double value = textFieldValues.get(i);
				inputValues.add(value);
			}
			try {
				function.setInputValues(inputs);
				function.callStrategy(comboBox.getSelectedItem().toString());
				function.optimize();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}