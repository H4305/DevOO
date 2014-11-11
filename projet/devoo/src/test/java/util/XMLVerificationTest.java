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
		file = new File("src/test/resources/xml/plan20x20-attribut-noeud-id.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));
 
		// Bad XML file - Problem Attribut Noeud x
		file = new File("src/test/resources/xml/plan20x20-attribut-noeud-x.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut Noeud y
		file = new File("src/test/resources/xml/plan20x20-attribut-noeud-y.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant nomRue
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-nomRue.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant vitesse
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-vitesse.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant vitesse 2
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-vitesse-1.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant vitesse 3
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-vitesse-2.xml"); 
        assertFalse(XMLVerification.checkPlanXML(file));
	
		// Bad XML file - Problem Attribut LeTronconSortant longueur
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-longueur.xml");  
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant longueur 2
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-longueur-1.xml");   
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem Attribut LeTronconSortant idNoeudDestination
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-idNoeudDestination.xml");    
        assertFalse(XMLVerification.checkPlanXML(file));
        
		// ------------------------------------------------------- / BAD Attributes values
        
        
		// ------------------------------------------------------- XML's structure malformed
        
        // TODO Other tests...
        
		// Bad XML file - Problem malformed Reseau <opening tag>
		file = new File("src/test/resources/xml/plan20x20-malformed-Reseau-1.xml");    
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem malformed Reseau <closing tag>
		file = new File("src/test/resources/xml/plan20x20-malformed-Reseau-2.xml");    
        assertFalse(XMLVerification.checkPlanXML(file));
      
		// Bad XML file - Problem malformed Noeud <opening tag>
		file = new File("src/test/resources/xml/plan20x20-malformed-Noeud-1.xml");    
        assertFalse(XMLVerification.checkPlanXML(file));

		// Bad XML file - Problem malformed Noeud <closing tag>
		file = new File("src/test/resources/xml/plan20x20-malformed-Noeud-2.xml");    
        assertFalse(XMLVerification.checkPlanXML(file));
        
		// Bad XML file - Problem malformed LeTronconSortant
		file = new File("src/test/resources/xml/plan20x20-malformed-LeTronconSortant.xml");    
        assertFalse(XMLVerification.checkPlanXML(file));
        
        
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
		file = new File("src/test/resources/xml/livraison10x10-attribut-Entrepot-adresse.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));

		// Bad XML file - Problem Attribut Plage heureDebut
		file = new File("src/test/resources/xml/livraison10x10-attribut-Plage-heureDebut.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
	  
		// Bad XML file - Problem Attribut Plage heureFin
		file = new File("src/test/resources/xml/livraison10x10-attribut-Plage-heureFin.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
	  
  		// Bad XML file - Problem Attribut Livraison id
		file = new File("src/test/resources/xml/livraison20x20-attribut-Livraison-id.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
	  
  		// Bad XML file - Problem Attribut Livraison client
		file = new File("src/test/resources/xml/livraison20x20-attribut-Livraison-client.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
	 
  		// Bad XML file - Problem Attribut Livraison adresse
		file = new File("src/test/resources/xml/livraison20x20-attribut-Livraison-adresse.xml"); 
        assertFalse(XMLVerification.checkLivraisonXML(file));
        
		// ------------------------------------------------------- / BAD Attributes values
        
        
		// ------------------------------------------------------- XML's structure malformed
        
		// Bad XML file - Problem malformed JourneeType <opening tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-JourneeType-1.xml");    
        assertFalse(XMLVerification.checkLivraisonXML(file));

		// Bad XML file - Problem malformed JourneeType <closing tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-JourneeType-2.xml");    
        assertFalse(XMLVerification.checkLivraisonXML(file));
        
		// Bad XML file - Problem malformed Livraisons <opening tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-Livraisons-1.xml");    
        assertFalse(XMLVerification.checkLivraisonXML(file));

		// Bad XML file - Problem malformed Livraisons <closing tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-Livraisons-2.xml");    
        assertFalse(XMLVerification.checkLivraisonXML(file));
        
		// Bad XML file - Problem malformed Plage <opening tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-Plage-1.xml");    
        assertFalse(XMLVerification.checkLivraisonXML(file));

		// Bad XML file - Problem malformed Plage <closing tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-Plage-2.xml");    
        assertFalse(XMLVerification.checkLivraisonXML(file));
        
		// Bad XML file - Problem malformed PlageHoraires <opening tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-PlageHoraires-1.xml");    
        assertFalse(XMLVerification.checkLivraisonXML(file));

		// Bad XML file - Problem malformed PlageHoraires <closing tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-PlageHoraires-2.xml");    
        assertFalse(XMLVerification.checkLivraisonXML(file));
        
		// Bad XML file - Problem malformed Entrepot
		file = new File("src/test/resources/xml/livraison20x20-malformed-Entrepot.xml");    
        assertFalse(XMLVerification.checkLivraisonXML(file));
        
		// Bad XML file - Problem malformed Livraison
		file = new File("src/test/resources/xml/livraison20x20-malformed-Livraison.xml");    
        assertFalse(XMLVerification.checkLivraisonXML(file));
        
		// ------------------------------------------------------- / XML's structure malformed
	}  
}
