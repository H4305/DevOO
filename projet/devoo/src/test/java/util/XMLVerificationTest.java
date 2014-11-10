package util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class XMLVerificationTest {

	@Test 
	public void testcheckPlanXML_OK() {
		File file = new File("src/main/resources/xml/plan20x20.xml");             
        assertTrue(XMLVerification.checkPlanXML(file));
	}  
	
	@Test 
	public void testcheckLivraisonXML_OK() {
		File file = new File("src/main/resources/xml/livraison20x20-2.xml");             
        assertTrue(XMLVerification.checkLivraisonXML(file));
	}  
	
	@Test 
	public void testcheckPlanXML_NOK() {
		// Bad XML file
		File file = new File("src/main/resources/xml/livraison20x20-2.xml");      
		assertFalse(XMLVerification.checkPlanXML(file));
	}  
	
	@Test 
	public void testcheckLivraisonXML_NOK() {
		// Bad XML file
		File file = new File("src/main/resources/xml/plan20x20.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
	}  
}
