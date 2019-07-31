package com.likehuman.lcm.mongodb.test;

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
