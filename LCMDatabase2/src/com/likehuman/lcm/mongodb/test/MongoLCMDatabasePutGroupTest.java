package com.likehuman.lcm.mongodb.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if groups can be successfully posted and then updated
 * 			with the method defined in MongoLCMDatabase
 */

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


public class MongoLCMDatabasePutGroupTest 
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
	public void groupPutTest() throws LCMDatabaseException, IOException
	{
		//creation of a random id for the group we are going to experiment on
		Random rand = new Random();
		String groupid = "" + rand.nextInt(1000000);
			
			
		//creation of a JSON String to post, and another JSON string to update with
		String groupJSONString = "{'rolename': 'Machine learning designer', 'companyname': 'Grand Cisco Co.', 'description': 'Allowed to modify, train, and improve neural nets', "
				+ "'actions' : [{'action1': 'Run', 'action2': 'Alter', 'action3': 'ModifyDataset', 'action4': 'NewDataset'}]}";
			
			
		//the update is appended with the groupid so it will match with the user that will be posted
		String groupJSONStringUpdate = "{'_id': '" + groupid + "', 'rolename': 'Machine learning designer', 'companyname': 'Speedwagon Foundation', 'description': 'Allowed to modify, train, and improve neural nets', "
				+ "'actions' : [{'action1': 'Run', 'action2': 'Alter', 'action3': 'ModifyDataset', 'action4': 'NewDataset'}]}";
			
			
		//the first string is posted as a group 
		db.postGroup(groupid, groupJSONString);
			
			
		//the group is then updated in the database
		db.putGroup(groupid, groupJSONStringUpdate);
			
			
		//parse to JSON
		Document  groupUpdateComparison = Document.parse(groupJSONStringUpdate);
		
			
		//find the group that has been updated in the database
		Document postedGroupUpdate = db.getGroup(groupid);
			
			
		//assertion that the hardcoded string we just parsed as JSON is equal to the group we found in the 
		//database
		assertEquals(groupUpdateComparison, postedGroupUpdate);
	}
		
		
		
	//this teardown deletes the test database we created after the test is finished
	@After
	public void teardown()
	{
		db.dropDatabase();
	}

}