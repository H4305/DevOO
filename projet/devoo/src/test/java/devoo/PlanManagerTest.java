package devoo;

import model.manager.PlanManager;
import model.data.Point;
import model.data.Troncon;

import org.junit.Test;

import util.Vertex;
import controller.Controller;

public class PlanManagerTest {

	@Test
	public void testCalculerPlusCourtChemin() {
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		Point a = new Point(1, 5, 2);
		Point b = new Point(2, 5, 7);
		Point c = new Point(3, 4, 2);
		Point d = new Point(4, 1, 2);
		Point e = new Point(5, 9, 7);
		Point f = new Point(6, 3, 1);
		Vertex va = new Vertex (a);
		Vertex vb = new Vertex (b);
		Vertex vc = new Vertex (c);
		Vertex vd = new Vertex (d);
		Vertex ve = new Vertex (e);
		Vertex vf = new Vertex (f);
		Troncon t1 = new Troncon("rue 1", 10, 20, a, c);
		t1.setTarget(vc);
		Troncon t2 = new Troncon("rue 2", 10, 20, a, b);
		t2.setTarget(vb);
		Troncon t3 = new Troncon("rue 3", 10, 10, a, d);
		t3.setTarget(vd);
		Troncon t4 = new Troncon("rue 4", 10, 80, d, c);
		t4.setTarget(vc);
		Troncon t5 = new Troncon("rue 5", 10, 10, b, e);
		t5.setTarget(ve);
		Troncon t6 = new Troncon("rue 6", 10, 10, e, f);
		t6.setTarget(vf);
		Troncon t7 = new Troncon("rue 7", 10, 50, c, f);
		t7.setTarget(vf);
		
		a.addTronconSortant(t1);
		a.addTronconSortant(t2);
		a.addTronconSortant(t3);
		b.addTronconSortant(t5);
		c.addTronconSortant(t7);
		d.addTronconSortant(t4);
		e.addTronconSortant(t6);
		

		System.out.println(planManager.calculerPlusCourtChemin(a, f, va, vf).toString());
		
	}
	
}
