package JUNIT_Tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import JUNIT_Tests.TestJUnit;

public class testRunner 
{
	public static void main(String[] args)
	{
		Result result = JUnitCore.runClasses(TestJUnit.class);
		
		for(Failure failure : result.getFailures())
		{
			System.out.println(failure.toString());
		}
		System.out.println("Result == " + result.wasSuccessful());
	}
}
