package com.likehuman.lcm.mongodb;

/**
 * @author: Milo Davis
 * Purpose: This class connects a database instance to a specified database when passed a web xml config file, 
 * 			and returns it for use in other classes.
 * 
 * 			The methods defined here allow for a database to be connected to under different circumstances, like if one 
 * 			wants to pass the host and port rather than letting the servlet define it. This is mainly for our own test purposes.
 */


import javax.servlet.ServletContext;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;


public class LCMDatabaseFactory 
{
	//definition of db to be set by one of the following methods
	private static MongoLCMDatabase db = null;
	
	
	
	//connects to database solely from servlet context
	public static MongoLCMDatabase getMongoLCMDatabase(ServletContext context) throws LCMDatabaseException
	{	
		//establishment of connection variables based on web.xml
		String mongohost 		= 	context.getInitParameter("MONGODB_HOST");
		String mongoporttemp 	= 	context.getInitParameter("MONGODB_PORT");
		int mongoport 			= 	Integer.parseInt(mongoporttemp);
			
		try
		{
			//creation of a client and retrieval of specified database
			MongoClient mongoClient = new MongoClient(mongohost, mongoport);
			
			db = new MongoLCMDatabase(mongoClient.getDatabase("LCMDatabase"));
		
		}
		catch(MongoException ex)
		{
			throw new LCMDatabaseException(ex);
		}
		
		return db;
		
	}
	
	
	
	//connects to database from the servletcontext as well as a specified database name
	public static MongoLCMDatabase getMongoLCMDatabase(ServletContext context, String dbname) throws LCMDatabaseException
	{	
		//establishment of connection variables based on web.xml
		String mongohost 		= 	context.getInitParameter("MONGODB_HOST");
		String mongoporttemp 	= 	context.getInitParameter("MONGODB_PORT");
		int mongoport 			= 	Integer.parseInt(mongoporttemp);
			
		
		MongoClient mongoClient = new MongoClient(mongohost, mongoport);
			
		//connects to specified database, creates one if it doesn't exist
		db = new MongoLCMDatabase(mongoClient.getDatabase(dbname));
			
		if(db == null)
		{
			throw new LCMDatabaseException("Cannot connect to specified database.");
		}
		return db;
		
	}
	
	
	
	//connects to database by passing MongoClient parameters like host and port
	public static MongoLCMDatabase getMongoLCMDatabase(String mongohost, int mongoport) throws LCMDatabaseException
	{	
		//connects to LCMDatabase with specified host and port
		MongoClient mongoClient = new MongoClient(mongohost, mongoport);
			
		db = new MongoLCMDatabase(mongoClient.getDatabase("LCMDatabase"));
		
		//throws exception if db is null
		if(db == null)
		{
			throw new LCMDatabaseException("Cannot connect to specified database.");
		}
		
		return db;
		
	}
	

	
	//connects to database of a specified name by passing MongoClient parameters like host and port
	public static MongoLCMDatabase getMongoLCMDatabase(String mongohost, int mongoport, String dbname) throws LCMDatabaseException
	{	
		//connects to database with specified host and port
		MongoClient mongoClient = new MongoClient(mongohost, mongoport);
			
		
		//connects to specified database, creates one if it doesn't exist
		db = new MongoLCMDatabase(mongoClient.getDatabase(dbname));
		
		//throws exception if db is null
		if(db == null)
		{
			throw new LCMDatabaseException("Cannot connect to specified database.");
		}
		
	
		
		return db;
		
	}
	
	
}
