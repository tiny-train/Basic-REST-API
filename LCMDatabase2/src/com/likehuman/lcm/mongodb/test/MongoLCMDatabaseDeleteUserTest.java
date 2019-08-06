package com.likehuman.lcm.mongodb.test;


import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Random;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.likehuman.lcm.mongodb.LCMDatabaseException;
import com.likehuman.lcm.mongodb.LCMDatabaseFactory;
import com.likehuman.lcm.mongodb.MongoLCMDatabase;

public class MongoLCMDatabaseDeleteUserTest 
{
	private static MongoLCMDatabase db;
	@Before
	public static void setup() throws LCMDatabaseException
	{
		Random rand = new Random();
		
		String dbid = "" + rand.nextInt(1000000);
		db = LCMDatabaseFactory.getMongoLCMDatabase("localhost", 27017, "testLCMDatabase" + dbid);
	}
	
	
	
	@Test
	public void userDeleteTest(String userid, String filePath) throws LCMDatabaseException, IOException
	{
		db.postUser(userid, filePath);
		
		db.deleteUser(userid);
		
		Document deletedUser = db.getUser(userid);
		
		assertNull(deletedUser);
	}
	
	
	
	@After
	public void teardown()
	{
		db.dropDatabase();
	}
	
}
