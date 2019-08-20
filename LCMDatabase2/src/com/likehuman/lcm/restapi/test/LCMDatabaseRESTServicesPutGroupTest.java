package com.likehuman.lcm.restapi.test;

/**
 * @author Milo Davis
 * Purpose: This JUNIT test determines if groups can be successfully updated through an HTTP request.
 */


import static org.junit.Assert.assertEquals;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;


public class LCMDatabaseRESTServicesPutGroupTest 
{
	@Test
	public void putGroupTest()
	{
		try
		{
			//the groupid created by the post group test is acquired
			String groupid = LCMDatabaseRESTServicesPostGroupTest.groupid;
		
			//a new JSON string is created for the group profile
			String groupJSONString = "{'rolename': 'Machine learning designer', 'companyname': 'Grand Cisco Co.', 'description': 'Allowed to modify, train, and improve neural nets', "
					+ "'actions' : [{'action1': 'Run', 'action2': 'Alter', 'action3': 'ModifyDataset', 'action4': 'NewDataset'}]}";
			
			//creation of HTTP connection to find the group we desire to update
			URL url = URLComposer.composeURL("/api/lcm/group/" + groupid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			//specification of request type
			con.setRequestMethod("PUT");
			
			//the new JSON is written to the group location
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(groupJSONString);
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
