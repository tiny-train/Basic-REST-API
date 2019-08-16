package com.likehuman.lcm.restapi.test;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if datasets can be successfully found through an HTTP request.
 */

public class LCMDatabaseRESTServicesGetDatasetTest 
{

	@Test
	public void getUserTest() 
	{
		try
		{
			//the datasetid created by the post user test is acquired
			String datasetid = LCMDatabaseRESTServicesPostDatasetTest.datasetid;
		
			//creation of HTTP connection to find the dataset we want to find
			URL url =	URLComposer.composeURL("/api/lcm/dataset/" + datasetid);
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
