package MONGO_Tests;

import java.util.Date;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;


public class HelloDatabase 
{
	
	public static void main(String[] args)
	{
		
		//connecting to mongodb and creates a database
		MongoClient mongo = new MongoClient("localhost", 27017);
	
		DB database = mongo.getDB("testdb");
		
		
		//creates a collection, because one doesn't exist
		DBCollection table = database.getCollection("user");
		
		//create a document, fill with some information
		BasicDBObject document = new BasicDBObject();
		document.put("Name", "Milo Davis");
		document.put("Age", 20);
		document.put("Date of Creation", new Date());
		table.insert(document);
		
		
		//creates object to find in table
		BasicDBObject searchquery = new BasicDBObject();
		searchquery.put("Name", "Milo Davis");
		
		
		//creates a cursor to iterate through table to find desired information
		DBCursor cursor = table.find(searchquery);
		while(cursor.hasNext())
		{
			System.out.println(cursor.next());
		}
		
		System.out.println("Hello MongoDB!");
		
		
		
		
	}
		
}
