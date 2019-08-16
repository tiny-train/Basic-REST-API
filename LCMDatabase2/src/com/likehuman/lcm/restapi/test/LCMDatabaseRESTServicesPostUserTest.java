package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if users can be successfully posted through an HTTP request.
 */


import static org.junit.Assert.assertEquals;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.junit.Test;


public class LCMDatabaseRESTServicesPostUserTest 
{
	//a random userid is created for the user to be posted
	public static Random rand = new Random();
	public static String userid = "" + rand.nextInt(1000000);
	
	@Test
	public void postUserTest()
	{
		try
		{	
			//a new JSON string is created for the user profile
			String userJSONString = "{'name': 'Johnny Joestar', 'email': 'tuskact5@gmail.com'}";
			
			//creation of HTTP connection to find the user we desire to post
			URL url = URLComposer.composeURL("/api/lcm/user/" + userid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			//specification of request type
			con.setRequestMethod("POST");
			
			//the JSON is written to the user location
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(userJSONString);
			wr.flush();
			wr.close();
		
			//assertion that the response code of the HTTP Request is 200
			int responseCode = con.getResponseCode();
		
			assertEquals(200, responseCode);
		}
		catch(Exception e)
		{
			System.out.println("Post request could not be made.");
			e.printStackTrace(System.err);
		}
	}
	
}
