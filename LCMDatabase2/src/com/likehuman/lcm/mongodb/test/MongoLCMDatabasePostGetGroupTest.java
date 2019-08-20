package com.likehuman.lcm.mongodb.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if groups can be successfully posted and then found
 * 			with the method defined in MongoLCMDatabase
 */


import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.likehuman.lcm.mongodb.LCMDatabaseException;
import com.likehuman.lcm.mongodb.LCMDatabaseFactory;
import com.likehuman.lcm.mongodb.MongoLCMDatabase;


public class MongoLCMDatabasePostGetGroupTest 
{
	//this setup creates an entirely new database for the tests we are going to conduct 
	private static MongoLCMDatabase db;
	@Before
	public void setup() throws LCMDatabaseException
	{
		Random rand = new Random();
			
		String dbid = "" + rand.nextInt(1000000);
		db = LCMDatabaseFactory.getMongoLCMDatabase(MongoLCMDatabaseTestRunner.mongodbhost, MongoLCMDatabaseTestRunner.mongodbport, "testLCMDatabase" + dbid);
	}
	
	
	
	@Test 
	public void groupPostGetTest() 
	{
		try
		{
			//creation of a random groupid for the group we are going to post
			Random rand = new Random();
			String groupid = "" + rand.nextInt(1000000);
			
			//creation of a JSON string for the group we are going to post
			String groupJSONString = "{'rolename': 'Machine learning designer', 'companyname': 'Grand Cisco Co.', 'description': 'Allowed to modify, train, and improve neural nets', "
					+ "'actions' : [{ 'action1':'Run', 'action2':'Alter', 'action3':'ModifyDataset', 'action4':'NewDataset'}]}";
			
			//posting group
			db.postGroup(groupid, groupJSONString);
		
			groupJSONString = "{'_id': '" + groupid + "', 'rolename': 'Machine learning designer', 'companyname': 'Grand Cisco Co.', 'description': 'Allowed to modify, train, and improve neural nets', "
					+ "'actions' : [{ 'action1':'Run', 'action2':'Alter', 'action3':'ModifyDataset', 'action4':'NewDataset'}]}";
			Document  unpostedGroup = Document.parse(groupJSONString);
		
			//finding the posted user
			Document postedGroup = db.getGroup(groupid);
		
		
			//comparison of the hardcoded string we parsed as JSON, and the group found in database
			assertEquals(unpostedGroup, postedGroup);
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
