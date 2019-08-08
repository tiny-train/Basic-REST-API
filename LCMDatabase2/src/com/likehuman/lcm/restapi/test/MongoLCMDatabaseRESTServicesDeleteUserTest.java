package com.likehuman.lcm.restapi.test;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class MongoLCMDatabaseRESTServicesDeleteUserTest 
{
	@Test
	public void deleteUserTest() 
	{
		try
		{
			String userid = MongoLCMDatabaseRESTServicesPostUserTest.userid;
			//String userid = "04";
			
			URL url = new URL("http://localhost:8080/LCMDatabase2/api/lcm/user/" + userid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");
		
			int responseCode = con.getResponseCode();
		
			assertEquals(200, responseCode);
			
			System.out.println(responseCode);
		}
		catch(Exception e)
		{
			System.out.println("Delete request could not be made.");
		}
	}	
	
	
}
