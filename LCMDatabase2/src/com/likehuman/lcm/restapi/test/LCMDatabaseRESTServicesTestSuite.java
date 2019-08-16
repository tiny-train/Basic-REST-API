package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test suite allows for all tests in com.likehuman.lcm.restapi.test to be run in a 
 * 			particular order, such that a user is posted, found, updated, and finally deleted.
 */


import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

@Suite.SuiteClasses
({
	LCMDatabaseRESTServicesPostUserTest.class,		//user posted
	LCMDatabaseRESTServicesGetUserTest.class,		//user found
	LCMDatabaseRESTServicesPutUserTest.class,		//user updated
	LCMDatabaseRESTServicesDeleteUserTest.class,	//user deleted
	
	LCMDatabaseRESTServicesPostDatasetTest.class,	//dataset posted
	LCMDatabaseRESTServicesGetDatasetTest.class,	//dataset found
	LCMDatabaseRESTServicesPutDatasetTest.class,	//dataset updated
	LCMDatabaseRESTServicesDeleteDatasetTest.class	//dataset deleted
	
})

public class LCMDatabaseRESTServicesTestSuite 
{
}
