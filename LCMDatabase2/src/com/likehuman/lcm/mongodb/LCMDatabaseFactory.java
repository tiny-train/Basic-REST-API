package com.likehuman.lcm.mongodb;

/*
 * Author: Milo Davis
 * Purpose: This class connects a database instance to our database when passed a web xml config file, 
 * 			and returns it for other class use.
 */

import javax.servlet.ServletContext;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoException;


public class LCMDatabaseFactory 
{
	private static MongoLCMDatabase db = null;
	
	public static MongoLCMDatabase getMongoLCMDatabase(ServletContext context) throws LCMDatabaseException
	{	
		String mongohost 		= 	context.getInitParameter("MONGODB_HOST");
		String mongoporttemp 	= 	context.getInitParameter("MONGODB_PORT");
		int mongoport 			= 	Integer.parseInt(mongoporttemp);
			
		try
		{
			
			MongoClient mongoClient = new MongoClient(mongohost, mongoport);
			
			db = new MongoLCMDatabase(mongoClient.getDatabase("LCMDatabase"));
		
		}
		catch(MongoException ex)
		{
			throw new LCMDatabaseException(ex);
		}
		
		return db;
		
	}
	
	public static MongoLCMDatabase getMongoLCMDatabase(ServletContext context, String dbname) throws LCMDatabaseException
	{	
		String mongohost 		= 	context.getInitParameter("MONGODB_HOST");
		String mongoporttemp 	= 	context.getInitParameter("MONGODB_PORT");
		int mongoport 			= 	Integer.parseInt(mongoporttemp);
			
		MongoClient mongoClient = new MongoClient(mongohost, mongoport);
			
		db = new MongoLCMDatabase(mongoClient.getDatabase(dbname));
			
		if(db == null)
		{
			throw new LCMDatabaseException("Cannot connect to specified database.");
		}
		return db;
		
	}
	
	public static MongoLCMDatabase getMongoLCMDatabase(String mongohost, int mongoport) throws LCMDatabaseException
	{	
		
		MongoClient mongoClient = new MongoClient(mongohost, mongoport);
			
		db = new MongoLCMDatabase(mongoClient.getDatabase("LCMDatabase"));
		
		if(db == null)
		{
			throw new LCMDatabaseException("Cannot connect to specified database.");
		}
		
		return db;
		
	}
	

	public static MongoLCMDatabase getMongoLCMDatabase(String mongohost, int mongoport, String dbname) throws LCMDatabaseException
	{	
		
		MongoClient mongoClient = new MongoClient(mongohost, mongoport);
			
		db = new MongoLCMDatabase(mongoClient.getDatabase(dbname));
		
		if(db == null)
		{
			throw new LCMDatabaseException("Cannot connect to specified database.");
		}
		
	
		
		return db;
		
	}
	
	
}
