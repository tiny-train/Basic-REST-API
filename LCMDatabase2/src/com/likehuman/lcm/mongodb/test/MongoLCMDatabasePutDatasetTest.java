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


public class MongoLCMDatabasePutDatasetTest 
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
	public void datasetPutTest() throws LCMDatabaseException, IOException
	{
		//creation of a random id for the user we are going to experiment on
		Random rand = new Random();
		String datasetid = "" + rand.nextInt(1000000);
		
		
		//creation of a JSON String to post, and another JSON string to update with
		String datasetJSONString = "{'metadata' : [{ 'title': 'Horses', 'lastaccessed' : '010693' }], 'datafields' : [{'horse1' : 'Ardennes','horse2' : 'Mississipi Fox Trotter'}]}";
		
		
		//the update is appended with the userid so it will match with the user that will be posted
		String datasetJSONStringUpdate = "{'_id': '" + datasetid + "', 'metadata' : [{ 'title': 'Horses', 'lastaccessed' : '010693' }], 'datafields' : [{'horse1' : 'Mustang','horse2' : 'Arabian'}]}";
		
		
		//the first string is posted as a user 
		db.postDataset(datasetid, datasetJSONString);
		
		
		//the user is then updated in the database
		db.putDataset(datasetid, datasetJSONStringUpdate);
		
		
		//parse to JSON
		Document  datasetUpdateComparison = Document.parse(datasetJSONStringUpdate);
		
		
		//find the user that has been updated in the database
		Document postedDatasetUpdate = db.getDataset(datasetid);
		
		
		//assertion that the hardcoded string we just parsed as JSON is equal to the user we found in the 
		//database
		assertEquals(datasetUpdateComparison, postedDatasetUpdate);
	}
	
	
	
	//this teardown deletes the test database we created after the test is finished
	@After
	public void teardown()
	{
		db.dropDatabase();
	}
}
