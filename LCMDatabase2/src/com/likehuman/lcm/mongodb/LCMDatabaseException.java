package com.likehuman.lcm.mongodb;

/**
 * @author Milo Davis
 * Purpose: This class implements CRUD/REST operations for user, data set, and group interactions with
 * 			mongodb.
 */


@SuppressWarnings("serial")
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
