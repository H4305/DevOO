package util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class XMLVerificationTest {

	// Example of other test I have to do 
	
    // Example :
   /* @Test(expected = ArithmeticException.class)  
	public void testdivisionWithException() {  
	  int i = 1/0;
	} */
	
	@Test 
	public void testcheckPlanXML() {
		File file = new File("src/main/resources/xml/plan20x20.xml");             
        assertTrue(XMLVerification.checkPlanXML(file));
        
		// Bad XML file
		file = new File("src/main/resources/xml/livraison20x20-2.xml");      
		assertFalse(XMLVerification.checkPlanXML(file));
		
		// ------------------------------------------------------- BAD Attributes values
		
		// Bad XML file - Problem Attribut Noeud id
		file = new File("src/test/resources/xml/plan20x20-1.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));
 
		// Bad XML file - Problem Attribut Noeud x
		file = new File("src/test/resources/xml/plan20x20-2.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut Noeud y
		file = new File("src/test/resources/xml/plan20x20-3.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant nomRue
		file = new File("src/test/resources/xml/plan20x20-4.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant vitesse
		file = new File("src/test/resources/xml/plan20x20-5.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant vitesse 2
		file = new File("src/test/resources/xml/plan20x20-6.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant vitesse 3
		file = new File("src/test/resources/xml/plan20x20-7.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));
	
		// Bad XML file - Problem Attribut LeTronconSortant longueur
		file = new File("src/test/resources/xml/plan20x20-8.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant longueur 2
		file = new File("src/test/resources/xml/plan20x20-9.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant idNoeudDestination
		file = new File("src/test/resources/xml/plan20x20-10.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));
        
		// ------------------------------------------------------- / BAD Attributes values
        
        
		// ------------------------------------------------------- XML's structure malformed
        
        // TODO Other tests...
        

		// ------------------------------------------------------- / XML's structure malformed
	}  
	
	@Test 
	public void testcheckLivraisonXML() {
		File file = new File("src/main/resources/xml/livraison20x20-2.xml");             
        assertTrue(XMLVerification.checkLivraisonXML(file));
        
		// Bad XML file
		file = new File("src/main/resources/xml/plan20x20.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
	  
		// ------------------------------------------------------- BAD Attributes values
        
		// Bad XML file - Problem Attribut Entrepot adresse
		file = new File("src/test/resources/xml/livraison10x10-1.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));

		// Bad XML file - Problem Attribut Plage heureDebut
		file = new File("src/test/resources/xml/livraison10x10-2.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
	  
		// Bad XML file - Problem Attribut Plage heureFin
		file = new File("src/test/resources/xml/livraison10x10-3.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
	  
  		// Bad XML file - Problem Attribut Livraison id
		file = new File("src/test/resources/xml/livraison20x20-1.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
	  
  		// Bad XML file - Problem Attribut Livraison client
		file = new File("src/test/resources/xml/livraison20x20-2.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
	 
  		// Bad XML file - Problem Attribut Livraison adresse
		file = new File("src/test/resources/xml/livraison20x20-3.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
        
		// ------------------------------------------------------- / BAD Attributes values
	}  
}
