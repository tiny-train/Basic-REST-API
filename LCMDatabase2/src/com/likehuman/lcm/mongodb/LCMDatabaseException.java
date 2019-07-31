package com.likehuman.lcm.mongodb;

public class LCMDatabaseException extends Exception
{
	public LCMDatabaseException(Exception e)
	{
		super(e);
	}
	
	public LCMDatabaseException(String message)
	{
		super(message);
	}
}
