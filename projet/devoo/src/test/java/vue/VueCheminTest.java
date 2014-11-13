package vue;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import model.data.Chemin;
import model.data.Itineraire;
import model.data.Noeud;
import model.data.Troncon;

import org.junit.Test;

import vue.widget.PlanPanel;
import vue.widget.PlanPanel.PointClickedListener;

public class VueCheminTest {
	
	Noeud p1 = new Noeud(1, -200, 400);
	Noeud p2 = new Noeud(2, -200, 600);
	Noeud p3 = new Noeud(3, -200, 800);
	Noeud p4 = new Noeud(4, 400, 800);
	Noeud p5 = new Noeud(5, 400, 400);
	Noeud p6 = new Noeud(5, 400, 600);

	@Test
	public void testDraw() throws Exception {
		ArrayList<Troncon> troncons = new ArrayList<Troncon>();

		troncons.addAll(Arrays.asList(new Troncon("rue", 50.0f, 50, p1, p2)));

		ArrayList<Troncon> itineraire = new ArrayList<Troncon>();

		itineraire.addAll(Arrays.asList(
				new Troncon("rue", 50.0f, 50, p1, p2),
				new Troncon("rue", 50.0f, 50, p2, p1),
				new Troncon("rue", 50.0f, 50, p1, p2),
				new Troncon("rue", 50.0f, 50, p2, p1),
				new Troncon("rue", 50.0f, 50, p1, p2),
				new Troncon("rue", 50.0f, 50, p2, p1),
				new Troncon("rue", 50.0f, 50, p1, p2)
				));

		JFrame jFrame = new JFrame();
		PlanPanel vuePlan = new PlanPanel(troncons);

		jFrame.add(vuePlan);
		jFrame.pack();
		jFrame.setVisible(true);
		Chemin chemin = new Chemin(p1, p2, itineraire);
		Itineraire itineraire2 = new Itineraire(Arrays.asList(chemin));
		vuePlan.setItineraire(itineraire2);
		vuePlan.afficherItineraire();
		try {
			Thread.sleep(500000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
