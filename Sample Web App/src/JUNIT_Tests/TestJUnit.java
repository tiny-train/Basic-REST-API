package JUNIT_Tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJUnit 
{
	@Test 
	public void testSetup()
	{
		String str = "Hello JUnit!";
		assertEquals("Hello JUnit!", str);
	}
}
