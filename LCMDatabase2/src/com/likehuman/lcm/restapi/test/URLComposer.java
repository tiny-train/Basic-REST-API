package com.likehuman.lcm.restapi.test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The URL composer simplifies doing tests via HTTP calls by supporting defaults
 * (localhost, port 8080, LCMDatabase) that may also be set by java properties
 * (like -Dhostname=192.168.0.1 -Dport=8081 -Dservlet=LCM)
 * 
 * @author markdavis
 *
 */

public class URLComposer {
	
	// append the REST function url to the path. The path should begin with /
	public static URL composeURL(String path) throws MalformedURLException {
		
		String hostname = System.getProperty("hostname", "localhost");
		
		int port = Integer.parseInt(System.getProperty("port", "8080"));
		
		String servletName = System.getProperty("servlet", "LCMDatabase");
		
		return new URL("http://"+hostname+":"+port+"/"+servletName+path);
		
	}
	

}
