package util;

import org.junit.Test;

public class Useful {

	@Test(expected = ArithmeticException.class)  
	public void testDivisionWithException() {  
	  int i = 1/0;
	}  

}
