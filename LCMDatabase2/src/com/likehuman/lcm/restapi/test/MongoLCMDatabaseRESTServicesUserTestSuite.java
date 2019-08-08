package com.likehuman.lcm.restapi.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses
({
	MongoLCMDatabaseRESTServicesPostUserTest.class,
	MongoLCMDatabaseRESTServicesGetUserTest.class,
	MongoLCMDatabaseRESTServicesPutUserTest.class,
	MongoLCMDatabaseRESTServicesDeleteUserTest.class
	
})

public class MongoLCMDatabaseRESTServicesUserTestSuite 
{
}
