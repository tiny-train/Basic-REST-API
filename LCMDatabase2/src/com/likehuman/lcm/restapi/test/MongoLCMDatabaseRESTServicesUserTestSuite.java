package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test suite allows for all tests in com.likehuman.lcm.restapi.test to be run in a 
 * particular order.
 */


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses
({
	MongoLCMDatabaseRESTServicesPostUserTest.class,		//user posted
	MongoLCMDatabaseRESTServicesGetUserTest.class,		//user found
	MongoLCMDatabaseRESTServicesPutUserTest.class,		//user updated
	MongoLCMDatabaseRESTServicesDeleteUserTest.class	//user deleted
	
})

public class MongoLCMDatabaseRESTServicesUserTestSuite 
{
}
