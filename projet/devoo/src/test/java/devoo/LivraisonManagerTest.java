package devoo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.data.Chemin;
import model.data.Itineraire;
import model.data.Livraison;
import model.data.Noeud;
import model.data.Troncon;
import model.manager.LivraisonManager;
import model.manager.PlanManager;

import org.junit.Assert;
import org.junit.Test;

import util.CalculesHoraires;
import controller.Controller;

public class LivraisonManagerTest {
	
	@Test
	public void testTransfHeureMinANDFirstBefSecond() {
		float testFloat = 7800.0f;
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		String testString = CalculesHoraires.transformeEnHeureMin(testFloat);
		//System.out.println(testString);
		Assert.assertEquals(testString, "02:10");	

		testFloat = 7860.0f;
		String testString2 = CalculesHoraires.transformeEnHeureMin(testFloat);
		//System.out.println(testString2);
		Assert.assertEquals(testString2, "02:11");	
		
		Boolean res = CalculesHoraires.firstBeforeSecond(testString2, testString);
		//System.out.println(res);
	}
	
	@Test
	public void testSommeHeures() {
		String heureA = "12:10";
		String heureB = "0:55";
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		String testSomme = CalculesHoraires.sommeHeures(heureA, heureB);
		
		System.out.println(testSomme);
		Assert.assertEquals(testSomme, "13:05");
		
		heureA = "14:25";
		heureB = "0:47";

		testSomme = CalculesHoraires.sommeHeures(heureA, heureB);
		
		System.out.println(testSomme);
		Assert.assertEquals(testSomme, "15:12");	
	}
	
	@Test
	public void testAddLivraison() {
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);

	}	
	@Test
	public void exporterFeuilleRoute() {
		
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		//livraisonManager.exporterFeuilleRoute();
		  System.out.println("Suivez les instructions suivantes: ");
		  System.out.print("D�part du d�pot : rue");
		  System.out.println("Lalal");
		
	}
	@Test
	public void testSuppresionLivraison() {
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		Noeud n1 = new Noeud(1,5,7);
		Noeud n2 = new Noeud(2,5,9);
		Noeud n3 = new Noeud(3,10,9);
		Troncon t12 = new Troncon("rue12", 10, 52, n1,n2);
		Troncon t23 = new Troncon("rue23", 5, 40, n2,n2);
		Troncon t31 = new Troncon("rue31", 7, 60, n3,n1);
		Livraison l1 = new Livraison(1,20,n1);
		Livraison l2 = new Livraison(2,20,n2);
		Livraison l3 = new Livraison(3,20,n3);
		n1.setLivraison(l1);
		n2.setLivraison(l2);
		n3.setLivraison(l3);
		n1.setIsLivraison(true);
		n2.setIsLivraison(true);
		n3.setIsLivraison(true);
		Chemin c12 = new Chemin(n1,n2,t12); 
		Chemin c23 = new Chemin(n2,n3,t23); 
		Chemin c31 = new Chemin(n3,n1,t31); 
		List<Chemin> mesChemins = new ArrayList<Chemin>();
		Itineraire it = new Itineraire(mesChemins);
		livraisonManager.setItineraire(it);
		
		
		livraisonManager.supprimerLivraison(n2);

	}	


}
