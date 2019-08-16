package com.likehuman.lcm.mongodb.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if datasets can be successfully deleted
 * 			with the method defined in MongoLCMDatabase
 */

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


public class MongoLCMDatabaseDeleteDatasetTest 
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
	public void datasetDeleteTest() throws LCMDatabaseException, IOException
	{
		//creation of a random id for the dataset we are going to post
		Random rand = new Random();
		String datasetid = "" + rand.nextInt(1000000);

		//creation of a JSON string for the dataset we are going to post
		String datasetJSONString = "{'metadata' : [{ 'title': 'Horses', 'lastaccessed' : '010693' }], 'datafields' : [{'horse1' : 'Ardennes','horse2' : 'Mississipi Fox Trotter'}]}";
		

		//post of dataset
		db.postDataset(datasetid, datasetJSONString);

		//deletion of dataset
		db.deleteDataset(datasetid);

		//attempt to find the dataset we have deleted
		Document deletedDataset = db.getDataset(datasetid);


		//assertion that the dataset does not exist anymore
		assertNull(deletedDataset);
	}



	//this teardown deletes the test database we created after the test is finished
	@After
	public void teardown()
	{
		db.dropDatabase();
	}

}
