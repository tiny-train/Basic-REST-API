package com.likehuman.lcm.restapi.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import org.junit.Before;
import org.junit.Test;

import com.likehuman.lcm.mongodb.LCMDatabaseException;

public class MongoLCMDatabaseRESTServicesGetUserTest 
{
	
	@Test
	public void getUserTest(String userid) throws IOException
	{
		try
		{
			URL url = new URL("http://localhost:8080/LCMDatabase2/api/lcm/user/" + userid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
		
			int responseCode = con.getResponseCode();
		
			assertEquals(200, responseCode);
			
			System.out.println(responseCode);
		}
		catch(Exception e)
		{
			System.out.println("Get request could not be made.");
		}
	}
	
	
	public static void main(String[] args)
	{
		MongoLCMDatabaseRESTServicesGetUserTest test = new MongoLCMDatabaseRESTServicesGetUserTest();
		
		try {
			test.getUserTest("02");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



