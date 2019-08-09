package com.likehuman.lcm.mongodb.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test runner runs MongoLCMDatabaseUserTestSuite and displays if the tests
 * were successful.
 */


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class MongoLCMDatabaseUserTestRunner 
{
	public static void main(String[] args)
	{
		Result result = JUnitCore.runClasses(MongoLCMDatabaseUserTestSuite.class);
		
		for(Failure failure : result.getFailures())
		{
			System.out.println(failure.toString());
		}
		
		System.out.println(result.wasSuccessful());
	}
}
