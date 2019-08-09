package com.likehuman.lcm.mongodb.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if users can be successfully deleted with the method defined in MongoLCMDatabase
 */


import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertNull;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.likehuman.lcm.mongodb.LCMDatabaseException;
import com.likehuman.lcm.mongodb.LCMDatabaseFactory;
import com.likehuman.lcm.mongodb.MongoLCMDatabase;

public class MongoLCMDatabaseDeleteUserTest 
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
	public void userDeleteTest() throws LCMDatabaseException, IOException
	{
		//creation of userid for the user we are going to post
		Random rand = new Random();
		String userid = "" + rand.nextInt(1000000);
		
		//creation of a JSON string for the user we are going to post
		String userJSONString = "{'name': 'Jean Pierre Polnareff', 'email': 'silverchariot@gmail.com'}";
		
		//post of user
		db.postUser(userid, userJSONString);
		
		//deletion of user
		db.deleteUser(userid);
		
		//attempt to find the user we have deleted
		Document deletedUser = db.getUser(userid);
		
		
		//assertion that the user does not exist anymore
		assertNull(deletedUser);
	}
	
	
	
	//this teardown deletes the test database we created after the test is finished
		@After
		public void teardown()
		{
			db.dropDatabase();
		}
	
}
