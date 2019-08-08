package com.likehuman.lcm.restapi.test;

import static org.junit.Assert.assertEquals;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class MongoLCMDatabaseRESTServicesPutUserTest
{
	@Test
	public void putUserTest()
	{
		try
		{
			String userid = MongoLCMDatabaseRESTServicesPostUserTest.userid;
		
			
			String userJSONString = "{'name': 'Johnny Kujo', 'email': 'globalists@gmail.com'}";
			
			URL url = new URL("http://localhost:8080/LCMDatabase2/api/lcm/user/" + userid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("PUT");
			
			
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(userJSONString);
			wr.flush();
			wr.close();
		
			int responseCode = con.getResponseCode();
		
			assertEquals(200, responseCode);
			
			System.out.println(responseCode);
		}
		catch(Exception e)
		{
			System.out.println("Put/Update request could not be made.");
		}
	}
	
}
