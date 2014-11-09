import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import model.data.Point;
import model.data.Troncon;

import org.junit.Test;

import vue.widget.VuePlan;


public class VuePlanTest {

	@Test
	public void testVuePlan() {
		
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
		
		JFrame jFrame = new JFrame();
		VuePlan vuePlan = new VuePlan(troncons);
		jFrame.add(vuePlan);
		jFrame.pack();
		jFrame.setVisible(true);
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
