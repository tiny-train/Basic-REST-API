package com.likehuman.lcm.restapi.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LCMAdminRestServicesTest {

	//public static Tomcat tomcat;

	@Before
	public void setup()
	{
		System.err.println("Setup!");
/*
		tomcat = new Tomcat();

		try {
			
			tomcat.addWebapp("/", "/");
			tomcat.start();
			
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (LifecycleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/		
	}

	@Test
	public void test() {
		assert (true);
	}

	@After
	public void teardown()
	{
		/*
		try {
			tomcat.stop();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}*/
	}
	
}
