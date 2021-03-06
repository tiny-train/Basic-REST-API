package com.likehuman.lcm.mongodb.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test suite allows for all JUNIT tests in the package com.likehuman.lcm.mongodb.test to be ran
 * 			with a test runner.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses
({
   MongoLCMDatabasePostGetUserTest.class,		//user posted and found
   MongoLCMDatabasePutUserTest.class,			//user updated
   MongoLCMDatabaseDeleteUserTest.class,		//user deleted
   
   MongoLCMDatabasePostGetDatasetTest.class,	//dataset posted and found
   MongoLCMDatabasePutDatasetTest.class,		//dataset updated
   MongoLCMDatabaseDeleteDatasetTest.class,		//dataset deleted
   
   MongoLCMDatabasePostGetGroupTest.class,
   MongoLCMDatabasePutGroupTest.class,
   MongoLCMDatabaseDeleteGroupTest.class
})


public class MongoLCMDatabaseTestSuite 
{
}
