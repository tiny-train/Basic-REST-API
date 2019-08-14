package com.likehuman.lcm.mongodb.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test runner runs MongoLCMDatabaseUserTestSuite and displays if the tests
 * 			were successful.
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class MongoLCMDatabaseTestRunner 
{
	public static String mongodbhost = "localhost";
	public static int mongodbport = 27017;
	
	//this main method receives the result of the test suite and prints it
	public static void main(String[] args)
	{
		mongodbhost = System.getProperty("mongodbhost", "localhost");
		mongodbport = Integer.parseInt(System.getProperty("mongodbport", "27017"));
		
		Result result = JUnitCore.runClasses(MongoLCMDatabaseTestSuite.class);
		
		for(Failure failure : result.getFailures())
		{
			System.out.println(failure.toString());
		}
		
		System.out.println(result.wasSuccessful());
	}
}
