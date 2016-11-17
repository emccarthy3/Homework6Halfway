/**
 * FunctionServer.java 1.0 November 17, 2016
 *
 * Copyright (c) 2016 David J. Powell, Dawn Winsor, Betsey McCarthy, Jen Rhodes
 * Elon, North Carolina, 27244 U.S.A.
 * All Rights Reserved
 */
package edu.elon.math;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This class implements remote. It acts as the server in the proxy pattern.
 * @author emccarthy3, dwinsor, jrhodes
 *
 */
public class FunctionServer implements Remote {
	// private static ArrayList<JTextField> textFields;
	// public static void main(String[] args) {
	// Function samsFunction = new SamsClub();
	// Function dellFunction = new Dell();
	// Function minAbsSumFunction = new MinimumAbsoluteSum();
	// String inputText = "";
	/*
	 * FunctionGuiApplication samsClient = new
	 * FunctionGuiApplication(samsFunction); FunctionGuiApplication dellClient =
	 * new FunctionGuiApplication(dellFunction); FunctionGuiApplication minClient
	 * = new FunctionGuiApplication(minAbsSumFunction);
	 */
	/*
	 * dellFunction.setTextFields(textFields);
	 * samsFunction.setTextFields(textFields);
	 * minAbsSumFunction.setTextFields(textFields);
	 */

	public static void main(String args[]) {

		System.setProperty("java.security.policy", "client.policy");
		System.setSecurityManager(new SecurityManager());

		Context namingContext;
		try {
			Function f1 = new Dell();
			Function f2 = new SamsClub();
			Function f3 = new MinimumAbsoluteSum();
			namingContext = new InitialContext();
			namingContext.bind("rmi:dell", f1);
			namingContext.bind("rmi:samsClub", f2);
			namingContext.bind("rmi:minAbsSum", f3);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("Constructing server implementations...");
			System.out.println("Binding server implementations to registry...");

			System.out.println("Waiting for invocations from clients...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
