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
   //MongoLCMDatabasePostGetDatasetTest.class,
   //MongoLCMDatabasePutDatasetTest.class,
   //MongoLCMDatabaseDeleteDatasetTest.class
})


public class MongoLCMDatabaseTestSuite 
{
}
