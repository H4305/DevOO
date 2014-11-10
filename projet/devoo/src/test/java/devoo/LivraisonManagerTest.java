package devoo;

import static org.junit.Assert.*;
import model.data.Livraison;
import model.data.Point;
import model.manager.LivraisonManager;
import model.manager.PlanManager;

import org.junit.Test;

import controller.Controller;

public class LivraisonManagerTest {
	
	

	
	@Test
	public void testGetPlagesHoraires() {
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		//livraisonManager.loadDemandeLivraisonsXML();
		System.out.println(livraisonManager.getPlagesHoraire());
		
	}
	@Test
	public void testGetLivraisons() {
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		//livraisonManager.loadDemandeLivraisonsXML();
		System.out.println(livraisonManager.getLivraisons());
		
	}@Test
	public void testRemovePanel() {
		Point point = new Point(20,20,20);
		Livraison livraison = new Livraison(1,2,point);	
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		livraisonManager.remove(livraison);
		
	}
	@Test
	public void testAddPanel() {
		//Livraison livraison = new Livraison(1,"20",2);
		Point point = new Point(20,20,20);
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		livraisonManager.add(point);
		
	}

}
