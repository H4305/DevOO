package util;

import org.junit.Test;

public class UsefulTest {

	@Test(expected = ArithmeticException.class)  
	public void testDivisionWithException() {  
	  int i = 1/0;
	}  

}
