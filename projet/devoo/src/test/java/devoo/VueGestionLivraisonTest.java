package devoo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import model.data.Point;
import model.data.Troncon;
import model.manager.LivraisonManager;
import model.manager.PlanManager;

import org.junit.Test;

import controller.Controller;
import vue.VueGestionLivraison;

public class VueGestionLivraisonTest {

	@Test
	public void testVueGestionLivraison() {
		fail("Not yet implemented");
	}

	@Test
	public void testAfficherFenetrePrincipale() {
		fail("Not yet implemented");
	}

	@Test
	public void testAfficherItineraire() {
		fail("Not yet implemented");
	}

	@Test
	public void testAfficherPlan() {
		ArrayList<Troncon> troncons = new ArrayList<Troncon>();
		Point p1 = new Point(1, 2, 4);
		Point p2 = new Point(2, 2, 6);
		Point p3 = new Point(3, 2, 8);
		Point p4 = new Point(4, 4, 4);
		Point p5 = new Point(5, 4, 8);
		
		troncons.addAll(Arrays.asList(
				new Troncon("rue", 50.0f, 50, p1, p2),
		        new Troncon("rue", 50.0f, 50, p2, p3),
		        new Troncon("rue", 50.0f, 50, p3, p4),
		        new Troncon("rue", 50.0f, 50, p4, p5),
		        new Troncon("rue", 50.0f, 50, p5, p1)));
		Controller controller = new Controller();
		VueGestionLivraison gestionLivraison = new VueGestionLivraison(new PlanManager(controller), new LivraisonManager(), controller);
		gestionLivraison.afficherFenetrePrincipale();
		gestionLivraison.afficherPlan(troncons);
		for(long i=0; i<999999999; i++ );
		
	}

}
