package com.likehuman.lcm.mongodb.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if datasets can be successfully posted and then found
 * 			with the method defined in MongoLCMDatabase
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


public class MongoLCMDatabasePostGetDatasetTest 
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
	public void datasetPostGetTest() 
	{
		try
		{
			//creation of a random id for the dataset we are going to post
			Random rand = new Random();
			String datasetid = "" + rand.nextInt(1000000);
				
			//creation of a JSON string for the dataset we are going to post
			String datasetJSONString = "{'metadata' : [{ 'title': 'Horses', 'lastaccessed' : '010693' }], 'datafields' : [{'horse1' : 'Ardennes','horse2' : 'Mississipi Fox Trotter'}]}";
			
			//posting dataset
			db.postDataset(datasetid, datasetJSONString);
			
			datasetJSONString = "{'_id': '" + datasetid + "', 'metadata' : [{ 'title': 'Horses', 'lastaccessed' : '010693' }], 'datafields' : [{'horse1' : 'Ardennes','horse2' : 'Mississipi Fox Trotter'}]}";
			
			
			Document  unpostedDataset = Document.parse(datasetJSONString);
			
			//finding the posted dataset
			Document postedDataset = db.getDataset(datasetid);
		
			
			//comparison of the hardcoded string we parsed as JSON, and the dataset found in database
			assertEquals(unpostedDataset, postedDataset);
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
