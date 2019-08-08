package com.likehuman.lcm.mongodb.test;

import static org.junit.Assert.assertEquals;


import java.util.Random;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.likehuman.lcm.mongodb.LCMDatabaseException;
import com.likehuman.lcm.mongodb.LCMDatabaseFactory;
import com.likehuman.lcm.mongodb.MongoLCMDatabase;

public class MongoLCMDatabasePostGetUserTest 
{
	private static MongoLCMDatabase db = null;
	private static String dbname;
	@Before
	public void setup() 
	{
		try
		{
			Random rand = new Random();
		
			String dbid = "" + rand.nextInt(1000000);
			
			dbname = "testLCMDatabase" + dbid;
			db = LCMDatabaseFactory.getMongoLCMDatabase("localhost", 27017, dbname);
		}
		catch(LCMDatabaseException e)
		{
			System.out.println("Could not connect to database.");
		}
	}
	
	
	
	@Test 
	public void userPostGetTest() 
	{
		try
		{
			Random rand = new Random();
			//String dbid = "" + rand.nextInt(1000000);
			//MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase("localhost", 27017, "testLCMDatabase" + dbid);
			
			String userid = "" + rand.nextInt(1000000);
			String userJSONString = "{'name': 'Jean Pierre Polnareff', 'email': 'silverchariot@gmail.com'}";
			
			//posting user
			db.postUser(userid, userJSONString);
		
			userJSONString = "{'_id': '"+ userid +"', 'name': 'Jean Pierre Polnareff', 'email': 'silverchariot@gmail.com'}";
			Document  unpostedUser = Document.parse(userJSONString);
		
			//finding the posted user
			Document postedUser = db.getUser(userid);
		
		
			//comparison
			assertEquals(unpostedUser, postedUser);
		}
		catch(LCMDatabaseException e)
		{
			System.out.println("Test failed due to database exception.");
		}
		
	}
	
	
	@After
	public void teardown()
	{
		db.dropDatabase();
	}
	
}
