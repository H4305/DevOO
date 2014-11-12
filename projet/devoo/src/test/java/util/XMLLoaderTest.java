package util;

import static org.junit.Assert.assertFalse;
import model.data.DemandeLivraisons;
import model.data.Noeud;
import model.data.Troncon;
import model.exceptions.LivraisonXMLException;
import model.exceptions.PlanXMLException;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class XMLLoaderTest {

	
	@Test(expected = PlanXMLException.class)  
	public void testGetPlanXML_with_exception() throws PlanXMLException {
		
		// Bad XML file
		File file = new File("src/main/resources/xml/livraison20x20-2.xml");      
		XMLLoader.getPlanXML(file);
		
		// ------------------------------------------------------- BAD Attributes values
		
		// Bad XML file - Problem Attribut Noeud id
		file = new File("src/test/resources/xml/plan20x20-attribut-noeud-id.xml"); 
        XMLLoader.getPlanXML(file);
 
		// Bad XML file - Problem Attribut Noeud x
		file = new File("src/test/resources/xml/plan20x20-attribut-noeud-x.xml"); 
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem Attribut Noeud y
		file = new File("src/test/resources/xml/plan20x20-attribut-noeud-y.xml"); 
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem Attribut LeTronconSortant nomRue
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-nomRue.xml"); 
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem Attribut LeTronconSortant vitesse
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-vitesse.xml"); 
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem Attribut LeTronconSortant vitesse 2
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-vitesse-1.xml"); 
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem Attribut LeTronconSortant vitesse 3
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-vitesse-2.xml"); 
        XMLLoader.getPlanXML(file);
	
		// Bad XML file - Problem Attribut LeTronconSortant longueur
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-longueur.xml");  
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem Attribut LeTronconSortant longueur 2
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-longueur-1.xml");   
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem Attribut LeTronconSortant idNoeudDestination
		file = new File("src/test/resources/xml/plan20x20-attribut-LeTronconSortant-idNoeudDestination.xml");    
        XMLLoader.getPlanXML(file);
        
		// ------------------------------------------------------- / BAD Attributes values
        
        
		// ------------------------------------------------------- MISSING Attributes values
		
		// Bad XML file - Problem Attribut Noeud id
		file = new File("src/test/resources/xml/plan20x20-attribut-missing-noeud-id.xml"); 
        XMLLoader.getPlanXML(file);
 
		// Bad XML file - Problem Attribut Noeud x
		file = new File("src/test/resources/xml/plan20x20-attribut-missing-noeud-x.xml"); 
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem Attribut Noeud y
		file = new File("src/test/resources/xml/plan20x20-attribut-missing-noeud-y.xml"); 
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem Attribut LeTronconSortant nomRue
		file = new File("src/test/resources/xml/plan20x20-attribut-missing-LeTronconSortant-nomRue.xml"); 
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem Attribut LeTronconSortant vitesse
		file = new File("src/test/resources/xml/plan20x20-attribut-missing-LeTronconSortant-vitesse.xml"); 
        XMLLoader.getPlanXML(file);
	
		// Bad XML file - Problem Attribut LeTronconSortant longueur
		file = new File("src/test/resources/xml/plan20x20-attribut-missing-LeTronconSortant-longueur.xml");  
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem Attribut LeTronconSortant idNoeudDestination
		file = new File("src/test/resources/xml/plan20x20-attribut-missing-LeTronconSortant-idNoeudDestination.xml");    
        XMLLoader.getPlanXML(file);
        
		// ------------------------------------------------------- / MISSING Attributes values
        
		// ------------------------------------------------------- XML's structure malformed
        
		// Bad XML file - Problem malformed Reseau <opening tag>
		file = new File("src/test/resources/xml/plan20x20-malformed-Reseau-1.xml");    
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem malformed Reseau <closing tag>
		file = new File("src/test/resources/xml/plan20x20-malformed-Reseau-2.xml");    
        XMLLoader.getPlanXML(file);
      
		// Bad XML file - Problem malformed Noeud <opening tag>
		file = new File("src/test/resources/xml/plan20x20-malformed-Noeud-1.xml");    
        XMLLoader.getPlanXML(file);

		// Bad XML file - Problem malformed Noeud <closing tag>
		file = new File("src/test/resources/xml/plan20x20-malformed-Noeud-2.xml");    
        XMLLoader.getPlanXML(file);
        
		// Bad XML file - Problem malformed LeTronconSortant
		file = new File("src/test/resources/xml/plan20x20-malformed-LeTronconSortant.xml");    
        XMLLoader.getPlanXML(file);
        
		// ------------------------------------------------------- / XML's structure malformed
	}
	
	@Test
	public void testGetPlanXML() throws Exception {

		// 1150 troncons
		File file = new File("src/main/resources/xml/plan20x20.xml");
		Set<Troncon> troncons = XMLLoader.getPlanXML(file).troncons;
		Assert.assertEquals(1150, troncons.size());
		
		// 724 troncons
		file = new File("src/main/resources/xml/plan20x20_724Troncons.xml");
		troncons = XMLLoader.getPlanXML(file).troncons;
		Assert.assertEquals(724, troncons.size());
		
		// 200 troncons
		file = new File("src/main/resources/xml/plan20x20_200Troncons.xml");
		troncons = XMLLoader.getPlanXML(file).troncons;
		Assert.assertEquals(200, troncons.size());
		
		
		// 7 troncons
		file = new File("src/main/resources/xml/plan20x20_7Troncons.xml");
		troncons = XMLLoader.getPlanXML(file).troncons;
		Assert.assertEquals(7, troncons.size());
		
		Noeud point0 = new Noeud(0,51,37);
		Noeud point1 = new Noeud(1,26,65);
		Noeud point2 = new Noeud(2,23,116);
		
		// Because of the set, it is not ordered
		for (Troncon troncon : troncons) {
			
			if (troncon.getNomRue().equals("h1")){
				float longueur = 350.200000f;
				float vitesse = 4.400000f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point0, troncon.getDepart());
				Assert.assertEquals(point1, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h2")){
				float longueur = 116.400000f;
				float vitesse = 4.300000f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point0, troncon.getDepart());
				Assert.assertEquals(point2, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h3")){
				float longueur = 300.200000f;
				float vitesse = 4.500000f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point1, troncon.getDepart());
				Assert.assertEquals(point0, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h4")){
				float longueur = 408.700000f;
				float vitesse = 4.200000f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point1, troncon.getDepart());
				Assert.assertEquals(point2, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h5")){
				float longueur = 408.700000f;
				float vitesse = 4.700000f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point2, troncon.getDepart());
				Assert.assertEquals(point1, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h6")){
				float longueur = 291.200000f;
				float vitesse = 3.800000f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point2, troncon.getDepart());
				Assert.assertEquals(point0, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h7")){
				float longueur = 364.300000f;
				float vitesse = 4.000002f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point2, troncon.getDepart());
				Assert.assertEquals(point1, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
		}
	}
	
	@Test(expected = LivraisonXMLException.class)  
	public void testGetLivraisonXML_with_exception() throws LivraisonXMLException {

		/*

@Test
    public void canVote_throws_IllegalArgumentException_for_zero_age() {
        Student student = new Student();
        try {
            student.canVote(0);
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), containsString("age should be +ve"));
        }
        fail("expected IllegalArgumentException for non +ve age");
    }

		 * 
		 */
		
		
		
		// Bad XML file
		File file = new File("src/main/resources/xml/plan20x20.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
	  
		// ------------------------------------------------------- BAD Attributes values
        
		// Bad XML file - Problem Attribut Entrepot adresse
		file = new File("src/test/resources/xml/livraison10x10-attribut-Entrepot-adresse.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());

		// Bad XML file - Problem Attribut Plage heureDebut
		file = new File("src/test/resources/xml/livraison10x10-attribut-Plage-heureDebut.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
	  
		// Bad XML file - Problem Attribut Plage heureFin
		file = new File("src/test/resources/xml/livraison10x10-attribut-Plage-heureFin.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
	  
  		// Bad XML file - Problem Attribut Livraison id
		file = new File("src/test/resources/xml/livraison20x20-attribut-Livraison-id.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
	  
  		// Bad XML file - Problem Attribut Livraison client
		file = new File("src/test/resources/xml/livraison20x20-attribut-Livraison-client.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
	 
  		// Bad XML file - Problem Attribut Livraison adresse
		file = new File("src/test/resources/xml/livraison20x20-attribut-Livraison-adresse.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
        
		// ------------------------------------------------------- / BAD Attributes values
        
		// ------------------------------------------------------- MISSING Attributes values
        
		// Bad XML file - Problem Attribut Entrepot adresse
		file = new File("src/test/resources/xml/livraison10x10-attribut-missing-Entrepot-adresse.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());

		// Bad XML file - Problem Attribut Plage heureDebut
		file = new File("src/test/resources/xml/livraison10x10-attribut-missing-Plage-heureDebut.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
	  
		// Bad XML file - Problem Attribut Plage heureFin
		file = new File("src/test/resources/xml/livraison10x10-attribut-missing-Plage-heureFin.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
	  
  		// Bad XML file - Problem Attribut Livraison id
		file = new File("src/test/resources/xml/livraison20x20-attribut-missing-Livraison-id.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
	  
  		// Bad XML file - Problem Attribut Livraison client
		file = new File("src/test/resources/xml/livraison20x20-attribut-missing-Livraison-client.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
	 
  		// Bad XML file - Problem Attribut Livraison adresse
		file = new File("src/test/resources/xml/livraison20x20-attribut-missing-Livraison-adresse.xml"); 
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
        
		// ------------------------------------------------------- / MISSING Attributes values
        
		// ------------------------------------------------------- XML's structure malformed
        
		// Bad XML file - Problem malformed JourneeType <opening tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-JourneeType-1.xml");    
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());

		// Bad XML file - Problem malformed JourneeType <closing tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-JourneeType-2.xml");    
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
        
		// Bad XML file - Problem malformed Livraisons <opening tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-Livraisons-1.xml");    
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());

		// Bad XML file - Problem malformed Livraisons <closing tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-Livraisons-2.xml");    
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
        
		// Bad XML file - Problem malformed Plage <opening tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-Plage-1.xml");    
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());

		// Bad XML file - Problem malformed Plage <closing tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-Plage-2.xml");    
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
        
		// Bad XML file - Problem malformed PlageHoraires <opening tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-PlageHoraires-1.xml");    
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());

		// Bad XML file - Problem malformed PlageHoraires <closing tag>
		file = new File("src/test/resources/xml/livraison20x20-malformed-PlageHoraires-2.xml");    
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
        
		// Bad XML file - Problem malformed Entrepot
		file = new File("src/test/resources/xml/livraison20x20-malformed-Entrepot.xml");    
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
        
		// Bad XML file - Problem malformed Livraison
		file = new File("src/test/resources/xml/livraison20x20-malformed-Livraison.xml");    
        XMLLoader.getLivraisonXML(file, new HashMap<Integer, Noeud>());
        
		// ------------------------------------------------------- / XML's structure malformed
	}
	
	@Test
	public void testGetLivraisonXML() throws Exception {
		HashMap<Integer, Noeud> plan = new HashMap<Integer, Noeud>();
		plan.put((Integer) 1, new Noeud(1, 1, 1));
		plan.put((Integer) 2, new Noeud(2, 1, 2));
		plan.put((Integer) 3, new Noeud(3, 2, 1));
		plan.put((Integer) 4, new Noeud(4, 2, 2));
		plan.put((Integer) 0, new Noeud(0, 0, 0));
		File file = new File("src/test/resources/xml/livraisonTestOk.xml");
		DemandeLivraisons demandeLivraisons = XMLLoader.getLivraisonXML(file, plan);
		Assert.assertEquals(3, demandeLivraisons.getPlagesHoraire().size());
		Assert.assertEquals(1, demandeLivraisons.getPlagesHoraire().get(0).getLivraisons().size());
		Assert.assertEquals(2, demandeLivraisons.getPlagesHoraire().get(1).getLivraisons().size());
	}
}
