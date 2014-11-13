package devoo;

import static org.junit.Assert.*;
import model.data.Livraison;
import model.data.Noeud;
import model.manager.LivraisonManager;
import model.manager.PlanManager;

import org.junit.Assert;
import org.junit.Test;

import controller.Controller;

public class LivraisonManagerTest {
	
	@Test
	public void testTransfHeureMinANDFirstBefSecond() {
		float testFloat = 7800.0f;
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		String testString = livraisonManager.transformeEnHeureMin(testFloat);
		//System.out.println(testString);
		Assert.assertEquals(testString, "02:10");	

		testFloat = 7860.0f;
		String testString2 = livraisonManager.transformeEnHeureMin(testFloat);
		//System.out.println(testString2);
		Assert.assertEquals(testString2, "02:11");	
		
		Boolean res = livraisonManager.firstBeforeSecond(testString2, testString);
		//System.out.println(res);
	}
	
	@Test
	public void testSommeHeures() {
		String heureA = "12:10";
		String heureB = "0:55";
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		String testSomme = livraisonManager.sommeHeures(heureA, heureB);
		
		System.out.println(testSomme);
		Assert.assertEquals(testSomme, "13:05");
		
		heureA = "14:25";
		heureB = "0:47";

		testSomme = livraisonManager.sommeHeures(heureA, heureB);
		
		System.out.println(testSomme);
		Assert.assertEquals(testSomme, "15:12");	
	}
	
	@Test
	public void testAddLivraison() {
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		
<<<<<<< HEAD
=======
	}@Test
	public void testRemovePanel() {
		Noeud point = new Noeud(20,20,20);
		Livraison livraison = new Livraison(1,2,point);	
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		//livraisonManager.remove(livraison);
		
	}
	
	@Test
	public void testAddPanel() {
		//Livraison livraison = new Livraison(1,"20",2);
		Noeud point = new Noeud(20,20,20);
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		//livraisonManager.add(point);
>>>>>>> branch 'master' of https://github.com/H4305/DevOO.git
		
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
