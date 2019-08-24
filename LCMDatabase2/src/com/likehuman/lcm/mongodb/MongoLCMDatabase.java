package com.likehuman.lcm.mongodb;

/**
 * @author Milo Davis
 * Purpose: This class implements CRUD/REST operations for user, data set, and group interactions with
 * 			mongodb from the LCMDatabase class.
 */

import static com.mongodb.client.model.Filters.eq;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bson.Document;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoLCMDatabase implements LCMDatabase
{
	private MongoDatabase db;

	
	//this method wraps the MongoLCMDatabase data type as an extension of MongoDatabase 
	public MongoLCMDatabase(MongoDatabase db)
	{
		this.db = db;
	}
	
	
	
	
	
	
	//---------------User Functions---------------//
	
	
	//the following method posts a user in the database
	public void 	postUser(String userid, String userJSONObject) throws LCMDatabaseException
	{	
		try
		{
			//retrieval of appropriate collection
			MongoCollection<Document> Users = db.getCollection("Users");
			
			//parsing of JSON string into a db document
			Document newUser = Document.parse(userJSONObject);
	
			//mongodb will usually generate a random id for any document you insert
			//the following line allows us to define the document id for our own purposes
			newUser.put("_id", userid);
			
			//document is inserted to database
			Users.insertOne(newUser);
			
		}
		
		//catches a MongoException and throws an LCMDatabaseException
		catch(MongoException e)
		{
			//LCMDatabaseException defers to the exception type its given, so it will act like a MongoException here
			throw new LCMDatabaseException(e);
		}
		
	}
	
	
	
	//the following method find a user with a specified id in the database
	public Document	getUser(String userid) throws LCMDatabaseException
	{
		Document foundUser;
		
		try
		{	
			//retrieval of appropriate collection
			MongoCollection<Document> Users = db.getCollection("Users");
			
			//we find the user with the userid and assign it to a new document
			foundUser = (Document) Users.find(eq("_id", userid)).first();
		}
		
		//catches a MongoException and throws an LCMDatabaseException
		catch(MongoException e)
		{
			//LCMDatabaseException defers to the exception type its given, so it will act like a MongoException here
			throw new LCMDatabaseException(e);
		}
		
		//return the user we found
		return foundUser;
		
	}
	
	
	
	//the following method deletes a user with a specified id in the database
	public void 	deleteUser(String userid) throws LCMDatabaseException
	{	
		try
		{
			//retrieval of appropriate collection
			MongoCollection<Document> Users = db.getCollection("Users");
			
			//we find the user and assign it to a new document
			Document foundUser = (Document) Users.find(eq("_id", userid)).first();
			
			//we delete the document from the database
			Users.deleteOne(foundUser);
		}
		
		//catches a MongoException and throws an LCMDatabaseException
		catch(MongoException e)
		{
			//LCMDatabaseException defers to the exception type its given, so it will act like a MongoException here
			throw new LCMDatabaseException(e);
		}
		
	}
	
	
	
	//the following method updates a user with a specified id with a new JSON object
	public void 	putUser(String userid, String userUpdateJSONObject) throws LCMDatabaseException
	{	
		Document foundUser;
		try
		{
			//retrieval of appropriate collection
			MongoCollection<Document> Users = db.getCollection("Users");
			
			//parsing of JSON string into a db document
			Document  update = Document.parse(userUpdateJSONObject);
			
			//we find the user we want to update
			foundUser = Users.find(eq("_id", userid)).first();
			
			//the user in the database is replaced with the update document
			Users.replaceOne(foundUser, update);
		}
		
		//catches a MongoException and throws an LCMDatabaseException
		catch(MongoException e)
		{
			//LCMDatabaseException defers to the exception type its given, so it will act like a MongoException here
			throw new LCMDatabaseException(e);
		}
		
	}
	
	
	
	
	
	//-----------Data Set Functions-----------//
	
	
	//the following method posts a dataset in the database
	public void 	postDataset(String datasetid, String datasetJSONObject) throws LCMDatabaseException
	{	
		try
		{
			//retrieval of appropriate collection
			MongoCollection<Document> Datasets = db.getCollection("Data Sets");
			
			Document  newDataset = Document.parse(datasetJSONObject);
			newDataset.put("_id", datasetid);
			
			
			Datasets.insertOne(newDataset);
			
		}
		
		//catches a MongoException and throws an LCMDatabaseException
		catch(MongoException e)
		{
			//LCMDatabaseException defers to the exception type its given, so it will act like a MongoException here
			throw new LCMDatabaseException(e);
		}
	}
	
	
	
	//the following method find a dataset with a specified id in the database
	public Document	getDataset(String datasetid) throws LCMDatabaseException
	{
		Document foundDataset;
		
		try
		{	
			//retrieval of appropriate collection
			MongoCollection<Document> Datasets = db.getCollection("Data Sets");
			
			foundDataset = (Document) Datasets.find(eq("_id", datasetid)).first();
		}
		
		//catches a MongoException and throws an LCMDatabaseException
		catch(MongoException e)
		{
			//LCMDatabaseException defers to the exception type its given, so it will act like a MongoException here
			throw new LCMDatabaseException(e);
		}
		
		return foundDataset;
		
	}
	
	
	
	//the following method deletes a dataset with a specified id in the database
	public void 	deleteDataset(String datasetid) throws LCMDatabaseException
	{	
		try
		{
			//retrieval of appropriate collection
			MongoCollection<Document> Datasets = db.getCollection("Data Sets");
			
			//we find the dataset we want to delete and store it in a new document
			Document foundDataset = (Document) Datasets.find(eq("_id", datasetid)).first();
			
			//the dataset is deleted
			Datasets.deleteOne(foundDataset);
		}
		
		//catches a MongoException and throws an LCMDatabaseException
		catch(MongoException e)
		{
			//LCMDatabaseException defers to the exception type its given, so it will act like a MongoException here
			throw new LCMDatabaseException(e);
		}
		
	}
	
	
	
	//the following method updates a dataset with a specified id with a new JSON object
	public void 	putDataset(String datasetid, String datasetUpdateJSONObject) throws LCMDatabaseException
	{	
		Document foundUser;
		try
		{
			//retrieval of appropriate collection
			MongoCollection<Document> Datasets = db.getCollection("Data Sets");
			
			//parsing of JSON into a db document
			Document update = Document.parse(datasetUpdateJSONObject);
			
			
			foundUser = Datasets.find(eq("_id", datasetid)).first();
			
			Datasets.replaceOne(foundUser, update);
		}
		
		//catches a MongoException and throws an LCMDatabaseException
		catch(MongoException e)
		{
			//LCMDatabaseException defers to the exception type its given, so it will act like a MongoException here
			throw new LCMDatabaseException(e);
		}
		
	}
	
	
	
	
	
	//--------------Group Functions--------------//
	
	
	public void 	postGroup(String groupid, String groupJSONObject) throws LCMDatabaseException
	{	
		try
		{
			MongoCollection<Document> Groups = db.getCollection("Groups");
			
			Document  newGroup = Document.parse(groupJSONObject);
			newGroup.put("_id", groupid);
			
			
			Groups.insertOne(newGroup);
			
		}
		catch(MongoException e)
		{
			throw new LCMDatabaseException(e);
		}
		
	}
	
	
	
	public Document	getGroup(String groupid) throws LCMDatabaseException
	{
		Document foundGroup;
		
		try
		{	
			MongoCollection<Document> Groups = db.getCollection("Groups");
			
			foundGroup = (Document) Groups.find(eq("_id", groupid)).first();
		}
		catch(MongoException e)
		{
			throw new LCMDatabaseException(e);
		}
		
		return foundGroup;
		
	}
	
	
	
	public void 	deleteGroup(String groupid) throws LCMDatabaseException
	{	
		try
		{
			MongoCollection<Document> Groups = db.getCollection("Groups");
			
			
			Document foundGroup = (Document) Groups.find(eq("_id", groupid)).first();
			
			Groups.deleteOne(foundGroup);
		}
		catch(MongoException e)
		{
			throw new LCMDatabaseException(e);
		}
		
	}
	
	
	
	public void 	putGroup(String groupid, String groupUpdateJSONObject) throws LCMDatabaseException
	{	
		Document foundGroup;
		try
		{
			MongoCollection<Document> Groups = db.getCollection("Groups");
			
			Document update = Document.parse(groupUpdateJSONObject);
			
			
			foundGroup = Groups.find(eq("_id", groupid)).first();
			
			Groups.replaceOne(foundGroup, update);
		}
		catch(MongoException e)
		{
			throw new LCMDatabaseException(e);
		}
		
	}
	
	
	
	
	
	//---------------I/O Functions---------------//
	
	public String readFile(String filepath) throws IOException
	{
		String userJSONObject = new String(Files.readAllBytes(Paths.get(filepath)));
		return userJSONObject;
		
	}
	
	
	
	
	
	//---------------Database Functions-----------//
	
	public void dropDatabase()
	{
		 db.drop();
	}
	
	
}
