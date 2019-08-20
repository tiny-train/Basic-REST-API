package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if datasets can be successfully found through an HTTP request.
 */


import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;


public class LCMDatabaseRESTServicesGetGroupTest
{
	@Test
	public void getGroupTest() 
	{
		try
		{
			//the groupid created by the post group test is acquired
			String groupid = LCMDatabaseRESTServicesPostGroupTest.groupid;
		
			//creation of HTTP connection to find the group we want to find
			URL url = URLComposer.composeURL("/api/lcm/group/" + groupid);
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
