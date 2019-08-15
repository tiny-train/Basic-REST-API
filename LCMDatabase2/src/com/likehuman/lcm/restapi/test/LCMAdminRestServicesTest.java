package com.likehuman.lcm.restapi.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LCMAdminRestServicesTest {
	
	@Before
	public void setup()
	{
		
	}

	@Test
	public void test() {
		
		URL url;
		try {
			
			url = URLComposer.composeURL("/api/lcm/admin/ping");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			//specification of request type
			con.setRequestMethod("GET");
			
			//assertion that the response code of the HTTP Request is 200
			int responseCode = con.getResponseCode();
			
			DataInputStream wr = new DataInputStream(con.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(wr));
			
			String inLine = br.readLine();
			while (inLine != null) {
				System.err.println(inLine);
				inLine = br.readLine();
			}
		
			assertEquals(200, responseCode);	
			
		} catch (IOException e) {
		
			e.printStackTrace();
			assert(false);
			
		}
	}

	@After
	public void teardown()
	{
		
	}
	
}
