package com.likehuman.lcm.mongodb.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if users can be successfully posted and then updated
 * with the method defined in MongoLCMDatabase
 */



import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertEquals;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.likehuman.lcm.mongodb.LCMDatabaseException;
import com.likehuman.lcm.mongodb.LCMDatabaseFactory;
import com.likehuman.lcm.mongodb.MongoLCMDatabase;

public class MongoLCMDatabasePutUserTest 
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
	public void userPutTest() throws LCMDatabaseException, IOException
	{
		//creation of a random id for the user we are going to experiment on
		Random rand = new Random();
		String userid = "" + rand.nextInt(1000000);
		
		//creation of a JSON String to post, and another JSON string to update with
		String userJSONString = "{'name': 'Jean Pierre Polnareff', 'email': 'silverchariot@gmail.com'}";
		
		//the update is appended with the userid so it will match with the user that will be posted
		String userJSONStringUpdate = "{'_id': '"+ userid +"','name': 'Jean Pierre Polnareff', 'email': 'silverchariotrequiem@gmail.com'}";
		
		//the first string is posted as a user 
		db.postUser(userid, userJSONString);
		
		//the user is then updated in the database
		db.putUser(userid, userJSONStringUpdate);
		
		//parse to JSON
		Document  userUpdateComparison = Document.parse(userJSONStringUpdate);
		
		//find the user that has been updated in the database
		Document postedUserUpdate = db.getUser(userid);
		
		//assertion that the hardcoded string we just parsed as JSON is equal to the user we found in the 
		//database
		assertEquals(userUpdateComparison, postedUserUpdate);
	}
	
	
	//this teardown deletes the test database we created after the test is finished
		@After
		public void teardown()
		{
			db.dropDatabase();
		}
}
