package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if groups can be successfully deleted through an HTTP request.
 */


import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;


public class LCMDatabaseRESTServicesDeleteGroupTest 
{
	@Test
	public void deleteGroupTest() 
	{
		try
		{
			//the userid created by the post group test is acquired
			String groupid = LCMDatabaseRESTServicesPostGroupTest.groupid;
			
			//creation of HTTP connection to find the group we want to delete
			URL url = URLComposer.composeURL("/api/lcm/group/" + groupid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			//specification of request type
			con.setRequestMethod("DELETE");
		
			//assertion that the response code of the HTTP Request is 200
			int responseCode = con.getResponseCode();
		
			assertEquals(200, responseCode);
		}
		
		catch(Exception e)
		{
			System.out.println("Delete request could not be made.");
			e.printStackTrace(System.err);
		}
	}	
	

}
