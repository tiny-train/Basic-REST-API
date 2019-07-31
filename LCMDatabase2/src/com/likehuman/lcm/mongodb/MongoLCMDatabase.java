package com.likehuman.lcm.mongodb;

import java.io.IOException;

/**
 * @author Milo Davis
 * Purpose: This class implements CRUD/REST operations for user, data set, and group interactions with
 * 			mongodb.
 */





import java.nio.file.Files;
import java.nio.file.Paths;


import javax.servlet.ServletContext;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;



public class MongoLCMDatabase implements LCMDatabase
{
	private MongoDatabase db;
	private ServletContext context;
	
	public void setContext(ServletContext context)
	{
		this.context = context;
	}
	
	
	
	public MongoLCMDatabase(MongoDatabase db)
	{
		this.db = db;
	}
	
	
	//---------------User Functions---------------//
	
	public void 	postUser(String userid, String userJSONObject) throws LCMDatabaseException, IOException
	{	
		try
		{
			MongoCollection<Document> Users = db.getCollection("Users");
			
			Document  newUser = Document.parse(userJSONObject);
			newUser.put("_id", userid);
			
			
			Users.insertOne(newUser);
			
		}
		catch(MongoException e)
		{
			throw new LCMDatabaseException(e);
		}
		
	}
	
	
	
	public Document	getUser(String userid) throws LCMDatabaseException
	{
		Document foundUser;
		
		try
		{	
			MongoCollection<Document> Users = db.getCollection("Users");
			
			foundUser = (Document) Users.find(eq("_id", userid));
		}
		catch(MongoException e)
		{
			throw new LCMDatabaseException(e);
		}
		
		return foundUser;
		
	}
	
	
	
	
	public void 	deleteUser(String userid) throws LCMDatabaseException
	{	
		try
		{
			MongoCollection<Document> Users = db.getCollection("Users");
			
			
			Document foundUser = (Document) Users.find(eq("_id", userid)).first();
			
			Users.deleteOne(foundUser);
		}
		catch(MongoException e)
		{
			throw new LCMDatabaseException(e);
		}
		
	}
	
	
	
	
	public void 	putUser(String userid, String userUpdateFilePath) throws LCMDatabaseException, IOException
	{	
		try
		{
			MongoCollection<Document> Users = db.getCollection("Users");
			
			String updateUserFile = readFile(userUpdateFilePath);
			
			Document  update = Document.parse(updateUserFile);
			update.put("_id", userid);
			
			
			Document foundUser = (Document) Users.find(eq("_id", userid));
			
			Users.updateOne(foundUser, update);
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
		dropDatabase();
	}
	
	
	
	
	public static void main(String[] args) throws IOException
	{
		try
		{
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(args[0], Integer.parseInt(args[1]), args[2]);
		
			db.deleteUser("02");
		}
		catch(LCMDatabaseException e)
		{
			System.out.println(e);
		}
	}
	
	
}
