package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if groups can be successfully posted through an HTTP request.
 */


import static org.junit.Assert.assertEquals;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.junit.Test;


public class LCMDatabaseRESTServicesPostGroupTest 
{
	//a random groupid is created for the group to be posted
	public static Random rand = new Random();
	public static String groupid = "" + rand.nextInt(1000000);
	
	@Test
	public void postGroupTest()
	{
		try
		{	
			//a new JSON string is created for the group profile
			String groupJSONString = "{'rolename': 'Machine learning designer', 'companyname': 'Grand Cisco Co.', 'description': 'Allowed to modify, train, and improve neural nets', "
					+ "'actions' : [{'action1': 'Run', 'action2': 'Alter', 'action3': 'ModifyDataset', 'action4': 'NewDataset'}]}";
			
			//creation of HTTP connection to find the group we desire to post
			URL url = URLComposer.composeURL("/api/lcm/group/" + groupid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			//specification of request type
			con.setRequestMethod("POST");
			
			//the JSON is written to the group location
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(groupJSONString);
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
