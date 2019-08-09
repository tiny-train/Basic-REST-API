package com.likehuman.lcm.mongodb.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if users can be successfully posted and then found
 * with the method defined in MongoLCMDatabase
 */

import java.util.Random;

import static org.junit.Assert.assertEquals;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.likehuman.lcm.mongodb.LCMDatabaseException;
import com.likehuman.lcm.mongodb.LCMDatabaseFactory;
import com.likehuman.lcm.mongodb.MongoLCMDatabase;

public class MongoLCMDatabasePostGetUserTest 
{
	//this setup creates an entirely new database for the tests we are going to conduct 
		private static MongoLCMDatabase db;
		@Before
		public void setup() throws LCMDatabaseException
		{
			Random rand = new Random();
			
			String dbid = "" + rand.nextInt(1000000);
			db = LCMDatabaseFactory.getMongoLCMDatabase("localhost", 27017, "testLCMDatabase" + dbid);
		}
	
	
	
	@Test 
	public void userPostGetTest() 
	{
		try
		{
			//creation of a random userid for the user we are going to post
			Random rand = new Random();
			String userid = "" + rand.nextInt(1000000);
			
			//creation of a JSON string for the user we are going to post
			String userJSONString = "{'name': 'Jean Pierre Polnareff', 'email': 'silverchariot@gmail.com'}";
			
			//posting user
			db.postUser(userid, userJSONString);
		
			userJSONString = "{'_id': '"+ userid +"', 'name': 'Jean Pierre Polnareff', 'email': 'silverchariot@gmail.com'}";
			Document  unpostedUser = Document.parse(userJSONString);
		
			//finding the posted user
			Document postedUser = db.getUser(userid);
		
		
			//comparison of the hardcoded string we parsed as JSON, and the user found in database
			assertEquals(unpostedUser, postedUser);
		}
		catch(LCMDatabaseException e)
		{
			System.out.println("Test failed due to database exception.");
		}
		
	}
	
	
	//this teardown deletes the test database we created after the test is finished
		@After
		public void teardown()
		{
			db.dropDatabase();
		}
	
}
