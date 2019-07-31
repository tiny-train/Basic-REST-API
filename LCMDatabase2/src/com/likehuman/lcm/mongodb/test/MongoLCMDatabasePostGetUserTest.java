package com.likehuman.lcm.mongodb.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
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
	private static MongoLCMDatabase db;
	@Before
	public static void setup() throws LCMDatabaseException
	{
		Random rand = new Random();
		
		String dbid = "" + rand.nextInt(1000000);
		db = LCMDatabaseFactory.getMongoLCMDatabase("localhost", 27017, "testLCMDatabase" + dbid);
	}
	
	
	
	@Test 
	public static void userPostGetTest(String userid, String filePath) throws LCMDatabaseException, IOException
	{
		//posting user
		db.postUser(userid, filePath);
		
		//making json document comparable
		String unpostedUserString = db.readFile(filePath);
		Document  unpostedUser = Document.parse(unpostedUserString);
		
		
		//finding the posted user
		Document postedUser = db.getUser(userid);
		
		
		//comparison
		assertEquals(unpostedUser, postedUser);
		
	}
	
	
	@After
	public void teardown()
	{
		db.dropDatabase();
	}
	

}
