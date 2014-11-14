package vue;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import model.data.Livraison;
import model.data.Noeud;
import model.data.Troncon;
import model.manager.PlanManager;

import org.junit.Test;

import controller.Controller;
import devoo.LivraisonManagerTest;
import vue.VueGestionLivraison;

public class VueGestionLivraisonTest {

	@Test
	public void testVueGestionLivraison() {
		fail("Not yet implemented");
	}

	@Test
	public void testAfficherFenetrePrincipale() {
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManagerTest  livraisonManager = new LivraisonManagerTest(planManager, controller);
		VueGestionLivraison gestionLivraison = new VueGestionLivraison(planManager, livraisonManager, controller);
		gestionLivraison.afficherFenetrePrincipale();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAfficherItineraire() {
		fail("Not yet implemented");
	}

	@Test
	public void testAfficherPlan() {
		ArrayList<Troncon> troncons = new ArrayList<Troncon>();
		Noeud p1 = new Noeud(1, 2, 4);
		Noeud p2 = new Noeud(2, 2, 6);
		Noeud p3 = new Noeud(3, 2, 8);
		Noeud p4 = new Noeud(4, 4, 4);
		Noeud p5 = new Noeud(5, 4, 8);
		
		troncons.addAll(Arrays.asList(
				new Troncon("rue", 50.0f, 50, p1, p2),
		        new Troncon("rue", 50.0f, 50, p2, p3),
		        new Troncon("rue", 50.0f, 50, p3, p4),
		        new Troncon("rue", 50.0f, 50, p4, p5),
		        new Troncon("rue", 50.0f, 50, p5, p1)));
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManagerTest  livraisonManager = new LivraisonManagerTest(planManager, controller);
		VueGestionLivraison gestionLivraison = new VueGestionLivraison(planManager, livraisonManager, controller);
		
		planManager.setPlan(new HashSet<Troncon>(troncons));
		gestionLivraison.afficherFenetrePrincipale();
		gestionLivraison.afficherPlan();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAfficherLivraisonImpossible() throws Exception {
		VueGestionLivraison vueGestionLivraison = new VueGestionLivraison(null, null, null);
		//vueGestionLivraison.afficherFenetrePrincipale();
		vueGestionLivraison.afficherLivraisonImpossible(Arrays.asList(new Livraison(1, 1, null),
				new Livraison(1, 1, null),
				new Livraison(1, 1, null),
				new Livraison(1, 1, null),
				new Livraison(1, 1, null)));
	}

}
