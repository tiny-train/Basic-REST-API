package com.likehuman.lcm.mongodb;

import java.io.IOException;

import javax.servlet.ServletContext;

/*
 * Author: Milo Davis
 * Purpose: This class defines CRUD/REST operations for user, data set, and group interactions with
 * mongodb.
 */


import org.bson.Document;




public interface LCMDatabase 
{
	
	void setContext(ServletContext context);
	//---------------User Functions---------------//
	void 		postUser(String userid, String userJSONObject) 		throws LCMDatabaseException, IOException;
	Document 	getUser(String userid) 								throws LCMDatabaseException;
	void 		deleteUser(String userid) 							throws LCMDatabaseException;
	void 		putUser(String userid, String newuserJSONObject)	throws LCMDatabaseException, IOException;
	
	
	//---------------Data Set Functions------------//
	
	
	//---------------Group Functions---------------//
	
	
	//---------------Database Functions------------//
	void dropDatabase();
}
