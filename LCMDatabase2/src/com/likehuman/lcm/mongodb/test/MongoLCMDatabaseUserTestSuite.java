package com.likehuman.lcm.mongodb.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses
({
   MongoLCMDatabasePostGetUserTest.class,
   MongoLCMDatabaseDeleteUserTest.class,
   MongoLCMDatabasePutUserTest.class
})


public class MongoLCMDatabaseUserTestSuite 
{
}
