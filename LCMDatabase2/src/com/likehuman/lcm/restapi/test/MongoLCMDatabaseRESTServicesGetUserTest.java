package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if users can be successfully found through an HTTP request.
 */


import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;


public class MongoLCMDatabaseRESTServicesGetUserTest 
{
	
	@Test
	public void getUserTest() 
	{
		try
		{
			//the userid created by the post user test is acquired
			String userid = MongoLCMDatabaseRESTServicesPostUserTest.userid;
		
			//creation of HTTP connection to find the user we want to find
			URL url = new URL("http://localhost:8080/LCMDatabase2/api/lcm/user/" + userid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			//specification of request type
			con.setRequestMethod("GET");
		
			//assertion that the response code of the HTTP Request is 200
			int responseCode = con.getResponseCode();
		
			assertEquals(200, responseCode);
		}
		catch(Exception e)
		{
			System.out.println("Get request could not be made.");
			e.printStackTrace(System.err);
		}
	}

}



