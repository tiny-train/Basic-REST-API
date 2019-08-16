package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if datasets can be successfully deleted through an HTTP request.
 */

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;


public class LCMDatabaseRESTServicesDeleteDatasetTest 
{
	
	@Test
	public void deleteDatasetTest() 
	{
		try
		{
			//the datasetid created by the post dataset test is acquired
			String datasetid = LCMDatabaseRESTServicesPostDatasetTest.datasetid;
			
			//creation of HTTP connection to find the dataset we want to delete
			URL url = URLComposer.composeURL("/api/lcm/dataset/" + datasetid);
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
