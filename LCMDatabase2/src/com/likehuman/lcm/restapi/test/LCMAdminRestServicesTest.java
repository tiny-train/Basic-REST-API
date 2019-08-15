package com.likehuman.lcm.restapi.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LCMAdminRestServicesTest {

	private String hostname;
	private int port;
	private String path;

	@Before
	public void setup()
	{
		
		hostname = System.getProperty("hostname", "localhost");
		port = Integer.parseInt(System.getProperty("port", "8080"));
		path = System.getProperty("path", "/LCMDatabase/api/lcm/admin/ping");
		
	}

	@Test
	public void test() {
		
		URL url;
		try {
			
			// create url for api test
			url = new URL("http://"+hostname+":"+port+""+path);
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
