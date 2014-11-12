package vue;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import model.data.Chemin;
import model.data.Point;
import model.data.Troncon;

import org.junit.Test;

import vue.widget.PlanPanel;
import vue.widget.PlanPanel.PointClickedListener;

public class VueCheminTest {
	
	Point p1 = new Point(1, -200, 400);
	Point p2 = new Point(2, -200, 600);
	Point p3 = new Point(3, -200, 800);
	Point p4 = new Point(4, 400, 800);
	Point p5 = new Point(5, 400, 400);
	Point p6 = new Point(5, 400, 600);

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
		vuePlan.setChemin(chemin);
		vuePlan.afficherItineraire();
		try {
			Thread.sleep(500000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
