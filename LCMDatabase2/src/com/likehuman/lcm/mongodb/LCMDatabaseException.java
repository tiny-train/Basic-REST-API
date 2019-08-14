package com.likehuman.lcm.mongodb;

/**
 * @author Milo Davis
 * Purpose: This class implements an exception wrapper for any exception/message passed to LCMDatabaseException.
 */


@SuppressWarnings("serial")
public class LCMDatabaseException extends Exception
{
	//takes an exception wraps it as a LCMDatabaseException
	public LCMDatabaseException(Exception e)
	{
		//this does whatever the passed exception would do
		super(e);
	}
	
	
	
	//takes a message and displays it
	public LCMDatabaseException(String message)
	{
		super(message);
	}
	
}
