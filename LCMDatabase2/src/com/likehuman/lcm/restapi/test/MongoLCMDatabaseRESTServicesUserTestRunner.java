package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test runner runs MongoLCMDatabaseRESTServicesUserTestSuite and displays if the tests
 * 			were successful.
 */


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class MongoLCMDatabaseRESTServicesUserTestRunner 
{
	//this main method receives the result of the test suite and prints it
	public static void main(String[] args)
	{
		Result result = JUnitCore.runClasses(MongoLCMDatabaseRESTServicesUserTestSuite.class);
		
		for(Failure failure : result.getFailures())
		{
			System.out.println(failure.toString());
		}
		
		System.out.println(result.wasSuccessful());
	}
}
