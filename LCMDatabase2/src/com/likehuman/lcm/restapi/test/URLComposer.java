package com.likehuman.lcm.restapi.test;

import java.net.MalformedURLException;
import java.net.URL;

public class URLComposer {
	
	public static URL composeURL(String path) throws MalformedURLException {
		
		String hostname = System.getProperty("hostname", "localhost");
		int port = Integer.parseInt(System.getProperty("port", "8080"));
		String servletName = System.getProperty("servlet", "LCMDatabase");
		return new URL("http://"+hostname+":"+port+"/"+servletName+path);
		
	}
	

}
