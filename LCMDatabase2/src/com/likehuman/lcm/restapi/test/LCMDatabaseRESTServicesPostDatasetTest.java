package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if datasets can be successfully posted through an HTTP request.
 */

import static org.junit.Assert.assertEquals;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.junit.Test;


public class LCMDatabaseRESTServicesPostDatasetTest 
{
	
	//a random datasetid is created for the dataset to be posted
	public static Random rand = new Random();
	public static String datasetid = "" + rand.nextInt(1000000);
		
	@Test
	public void postDatasetTest()
	{
		try
		{	
			//a new JSON string is created for the dataset profile
			String datasetJSONString = "{'metadata' : [{ 'title': 'Horses', 'lastaccessed' : '010693' }], 'datafields' : [{'horse1' : 'Ardennes','horse2' : 'Mississipi Fox Trotter'}]}";
			
				
			//creation of HTTP connection to find the dataset we desire to post
			URL url = URLComposer.composeURL("/api/lcm/dataset/" + datasetid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
				
			//specification of request type
			con.setRequestMethod("POST");
				
			//the JSON is written to the dataset location
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(datasetJSONString);
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
