package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if users can be successfully updated through an HTTP request.
 */


import static org.junit.Assert.assertEquals;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;


public class LCMDatabaseRESTServicesPutUserTest
{
	@Test
	public void putUserTest()
	{
		try
		{
			//the userid created by the post user test is acquired
			String userid = LCMDatabaseRESTServicesPostUserTest.userid;
		
			//a new JSON string is created for the user profile
			String userJSONString = "{'name': 'Johnny Kujo', 'email': 'globalists@gmail.com'}";
			
			//creation of HTTP connection to find the user we desire to update
			URL url = URLComposer.composeURL("/api/lcm/user/" + userid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			//specification of request type
			con.setRequestMethod("PUT");
			
			//the new JSON is written to the user location
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(userJSONString);
			wr.flush();
			wr.close();
		
			//assertion that the response code of the request is 200
			int responseCode = con.getResponseCode();
		
			assertEquals(200, responseCode);
		}
		catch(Exception e)
		{
			System.out.println("Put/Update request could not be made.");
			e.printStackTrace(System.err);
		}
	}
	
}
