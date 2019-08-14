package com.likehuman.lcm.mongodb;

/**
 * @author Milo Davis
 * Purpose: This interface class defines CRUD/REST functions for actions on the database..
 */


import org.bson.Document;


public interface LCMDatabase 
{
	//---------------User Functions---------------//
	void 		postUser(String userid, String userJSONObject) 					throws LCMDatabaseException;
	Document 	getUser(String userid) 											throws LCMDatabaseException;
	void 		deleteUser(String userid) 										throws LCMDatabaseException;
	void 		putUser(String userid, String userUpdateJSONObject)				throws LCMDatabaseException;
	
	
	//---------------Data Set Functions------------//
	void 		postDataset(String datasetid, String datasetJSONObject)			throws LCMDatabaseException;
	Document	getDataset(String datasetid)									throws LCMDatabaseException;
	void 		deleteDataset(String datasetid)									throws LCMDatabaseException;
	void 		putDataset(String datasetid, String datasetUpdateJSONObject)	throws LCMDatabaseException;
	
	
	//---------------Group Functions---------------//
	
	
	//---------------Database Functions------------//
	void 		dropDatabase();
}
