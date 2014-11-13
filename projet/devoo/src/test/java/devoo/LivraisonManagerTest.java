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
	


}
