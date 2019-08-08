package com.likehuman.lcm.restapi.test;

import static org.junit.Assert.assertEquals;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.junit.Test;


public class MongoLCMDatabaseRESTServicesPostUserTest 
{
	
	public static Random rand = new Random();
	public static String userid = "" + rand.nextInt(1000000);
	
	@Test
	public void postUserTest()
	{
		try
		{	
			String userJSONString = "{'name': 'Johnny Joestar', 'email': 'tuskact5@gmail.com'}";
			
			URL url = new URL("http://localhost:8080/LCMDatabase2/api/lcm/user/" + userid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("POST");
			
			
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(userJSONString);
			wr.flush();
			wr.close();
		
			int responseCode = con.getResponseCode();
		
			assertEquals(200, responseCode);
		}
		catch(Exception e)
		{
			System.out.println("Post request could not be made.");
		}
	}
	
//	public static void main(String[] args)
//	{
//		MongoLCMDatabaseRESTServicesPostUserTest test = new MongoLCMDatabaseRESTServicesPostUserTest();
//		
//		test.postUserTest();
//	}
}
