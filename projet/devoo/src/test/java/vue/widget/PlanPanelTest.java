package vue.widget;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import model.data.Chemin;
import model.data.DemandeLivraisons;
import model.data.Itineraire;
import model.data.Livraison;
import model.data.PlageHoraire;
import model.data.Noeud;
import model.data.Troncon;

import org.junit.Test;

import vue.widget.PlanPanel.PointClickedListener;

public class PlanPanelTest {
	Noeud p1 = new Noeud(1, -200, 400);
	Noeud p2 = new Noeud(2, -200, 600);
	Noeud p3 = new Noeud(3, -200, 800);
	Noeud p4 = new Noeud(4, 400, 800);
	Noeud p5 = new Noeud(5, 400, 400);
	Noeud p6 = new Noeud(5, 400, 600);

	@Test
	public void testVuePlan() {

		ArrayList<Troncon> troncons = new ArrayList<Troncon>();

		troncons.addAll(Arrays.asList(new Troncon("rue", 50.0f, 50, p1, p2),
				new Troncon("rue", 50.0f, 50, p2, p3), new Troncon("rue",
						50.0f, 50, p3, p4), new Troncon("rue", 50.0f, 50, p4,
						p5), new Troncon("rue", 50.0f, 50, p5, p1)));

		JFrame jFrame = new JFrame();
		PlanPanel vuePlan = new PlanPanel(troncons);
		jFrame.add(vuePlan);
		jFrame.pack();
		jFrame.setVisible(true);
	}

	@Test
	public void testVuePlanItineraire() {

		ArrayList<Troncon> troncons = new ArrayList<Troncon>();

		troncons.addAll(Arrays.asList(
				new Troncon("rue", 50.0f, 50, p1, p2),
				new Troncon("rue", 50.0f, 50, p2, p3), 
				new Troncon("rue",50.0f, 50, p3, p4),
				new Troncon("rue", 50.0f, 50, p4,p5),
				new Troncon("rue", 50.0f, 50, p5, p1),
				new Troncon("rue", 50.0f, 50, p2, p6)));

		ArrayList<Troncon> itineraire = new ArrayList<Troncon>();

		itineraire.addAll(Arrays.asList(
				new Troncon("rue", 50.0f, 50, p1, p2),
				new Troncon("rue", 50.0f, 50, p2, p3),
				new Troncon("rue", 50.0f, 50, p2, p6), 
				new Troncon("rue", 50.0f, 50, p6, p2),
				new Troncon("rue", 50.0f, 50, p2, p6),
				new Troncon("rue", 50.0f, 50, p3, p2)));

		JFrame jFrame = new JFrame();
		PlanPanel vuePlan = new PlanPanel(troncons);
		vuePlan.setPointClickedListener(new PointClickedListener() {

			@Override
			public void pointClicked(Noeud point) {
				System.out.println("Point clicked");
			}
		});

		jFrame.add(vuePlan);
		jFrame.pack();
		jFrame.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Chemin chemin = new Chemin(p1, p6, itineraire);
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

	@Test
	public void testSetDemandeLivraisons() throws Exception {
		ArrayList<Troncon> troncons = new ArrayList<Troncon>();

		troncons.addAll(Arrays.asList(
				new Troncon("rue", 50.0f, 50, p1, p2),
				new Troncon("rue", 50.0f, 50, p2, p3), 
				new Troncon("rue",50.0f, 50, p3, p4)));
		
		PlanPanel panel = new PlanPanel(troncons);
		Livraison livraison1 = new Livraison(1,1,p1);
		Livraison livraison2 = new Livraison(1,1,p2);
		Livraison livraison3 = new Livraison(1,1,p3);
		Livraison livraison4 = new Livraison(1,1,p4);
		
		PlageHoraire plage1 = new PlageHoraire("8", "9", Arrays.asList(livraison1));
		PlageHoraire plage2 = new PlageHoraire("9", "10", Arrays.asList(livraison3, livraison4));
		PlageHoraire plage3 = new PlageHoraire("10", "11", Arrays.asList(livraison2));
		DemandeLivraisons demandeLivraisons = new DemandeLivraisons(p6, Arrays.asList(plage1, plage2, plage3));
		panel.setDemandeLivraisons(demandeLivraisons);
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		Thread.sleep(10000);
	}

}
