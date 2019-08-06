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

public class MongoLCMDatabasePutUserTest 
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
	public void userPutTest(String userid, String filePath, String updatedFilePath) throws LCMDatabaseException, IOException
	{
		db.postUser(userid, filePath);
		
		String unpostedUserUpdateString = db.readFile(updatedFilePath);
		Document  unpostedUserUpdate = Document.parse(unpostedUserUpdateString);
		
		db.putUser(userid, updatedFilePath);
		Document postedUserUpdate = db.getUser(userid);
		
		assertEquals(unpostedUserUpdate, postedUserUpdate);
	}
	
	
	
	@After
	public void teardown()
	{
		db.dropDatabase();
	}
}
