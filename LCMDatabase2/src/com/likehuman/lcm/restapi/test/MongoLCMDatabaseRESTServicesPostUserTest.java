package com.likehuman.lcm.restapi.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.*;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.likehuman.lcm.mongodb.LCMDatabaseException;
import com.likehuman.lcm.mongodb.LCMDatabaseFactory;
import com.likehuman.lcm.mongodb.MongoLCMDatabase;

public class MongoLCMDatabaseRESTServicesPostUserTest 
{

	URL url;
	HttpURLConnection con;
	@Before
	public void setup()
	{
		Random rand = new Random();
		
		String userid = "" + rand.nextInt(1000000);
	
		try
		{
			url = new URL("http://localhost:8080/LCMDatabase2/api/lcm/user" + userid);
			con = (HttpURLConnection) url.openConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void postUserTest()
	{
		try
		{
			con.setRequestMethod("GET");
		
			int responseCode = con.getResponseCode();
		
			assertEquals(200, responseCode);
		}
		catch(Exception e)
		{
			System.out.println("Get request could not be made.");
		}
	}
}
