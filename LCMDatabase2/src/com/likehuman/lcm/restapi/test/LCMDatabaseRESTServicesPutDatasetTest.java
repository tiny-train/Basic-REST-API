package com.likehuman.lcm.restapi.test;

import static org.junit.Assert.assertEquals;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if datasets can be successfully updated through an HTTP request.
 */


public class LCMDatabaseRESTServicesPutDatasetTest 
{
	
	@Test
	public void putUserTest()
	{
		try
		{
			//the datasetid created by the post dataset test is acquired
			String datasetid = LCMDatabaseRESTServicesPostDatasetTest.datasetid;
		
			//a new JSON string is created for the dataset profile
			String datasetJSONString = "{'metadata' : [{ 'title': 'Horses', 'lastaccessed' : '010693' }], 'datafields' : [{'horse1' : 'Ardennes','horse2' : 'Mississipi Fox Trotter'}]}";
			
			//creation of HTTP connection to find the dataset we desire to update
			URL url = URLComposer.composeURL("/api/lcm/dataset/" + datasetid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			//specification of request type
			con.setRequestMethod("PUT");
			
			//the new JSON is written to the dataset location
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(datasetJSONString);
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
