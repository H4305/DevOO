package devoo;

import java.io.File;
import java.util.ArrayList;

import model.manager.LivraisonManagerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import model.manager.PlanManager;
import model.data.Chemin;
import model.data.Noeud;
import model.data.Troncon;

import org.junit.Test;

import util.Vertex;
import controller.Controller;

public class PlanManagerTest {

	/**
	 * Test the calculation of the shortest path between 2 points
	 */
	@Test
	public void testCalculerPlusCourtChemin() {
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		Noeud a = new Noeud(1, 5, 2);
		Noeud b = new Noeud(2, 5, 7);
		Noeud c = new Noeud(3, 4, 2);
		Noeud d = new Noeud(4, 1, 2);
		Noeud e = new Noeud(5, 9, 7);
		Noeud f = new Noeud(6, 3, 1);
		
		List<Vertex> argVertexs = new ArrayList<Vertex>();
		
		Vertex va = new Vertex (a);
		argVertexs.add(va);
		Vertex vb = new Vertex (b);
		argVertexs.add(vb);
		Vertex vc = new Vertex (c);
		argVertexs.add(vc);
		Vertex vd = new Vertex (d);
		argVertexs.add(vd);
		Vertex ve = new Vertex (e);
		argVertexs.add(ve);
		Vertex vf = new Vertex (f);
		argVertexs.add(vf);
		
		planManager.setVertexs(argVertexs);
		
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
		Troncon t8 = new Troncon("rue 8", 10, 30, f, d);
		t8.setTarget(vd);
		
		a.addTronconSortant(t1);
		a.addTronconSortant(t2);
		a.addTronconSortant(t3);
		b.addTronconSortant(t5);
		c.addTronconSortant(t7);
		d.addTronconSortant(t4);
		e.addTronconSortant(t6);
		f.addTronconSortant(t8);
		
		va.addTronconSortant(t1);
		va.addTronconSortant(t2);
		va.addTronconSortant(t3);
		vb.addTronconSortant(t5);
		vc.addTronconSortant(t7);
		vd.addTronconSortant(t4);
		ve.addTronconSortant(t6);
		vf.addTronconSortant(t8);

		System.out.println(planManager.calculerPlusCourtChemin(f, a).toString());
		
	}
	

	
	@Test
	public void testCalculerPlusCourtCheminXML() {
		Controller controller = new Controller();		
		PlanManager planManager = controller.getPlanManager();
		Chemin chemin;

		controller.loadPlanXML();		
		
		List<Vertex> v = planManager.getVertexes();		
		Noeud a = v.get(0).getPoint();
		Noeud b = v.get(30).getPoint();
				
		chemin = planManager.calculerPlusCourtChemin(a,b);   //entre le premier et le dernier noeuds de la liste
		System.out.println("Le plus court chemin est" + chemin.toString());
	}
	
	@Test
	public void testgetChemins() {
		
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		
		/*Noeud a = new Noeud(1, 5, 2);
		Noeud b = new Noeud(2, 5, 7);
		Noeud c = new Noeud(3, 4, 2);
		Noeud d = new Noeud(4, 1, 2);
		Noeud e = new Noeud(5, 9, 7);
		Noeud f = new Noeud(6, 3, 1);
		
		List<Vertex> argVertexs = new ArrayList<Vertex>();
		
		Vertex va = new Vertex (a);
		argVertexs.add(va);
		Vertex vb = new Vertex (b);
		argVertexs.add(vb);
		Vertex vc = new Vertex (c);
		argVertexs.add(vc);
		Vertex vd = new Vertex (d);
		argVertexs.add(vd);
		Vertex ve = new Vertex (e);
		argVertexs.add(ve);
		Vertex vf = new Vertex (f);
		argVertexs.add(vf);
		
		planManager.setVertexs(argVertexs);
		
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
		Troncon t8 = new Troncon("rue 7", 10, 30, f, d);
		t8.setTarget(vd);
		
		a.addTronconSortant(t1);
		a.addTronconSortant(t2);
		a.addTronconSortant(t3);
		b.addTronconSortant(t5);
		c.addTronconSortant(t7);
		d.addTronconSortant(t4);
		e.addTronconSortant(t6);
		f.addTronconSortant(t8);
		
		va.addTronconSortant(t1);
		va.addTronconSortant(t2);
		va.addTronconSortant(t3);
		vb.addTronconSortant(t5);
		vc.addTronconSortant(t7);
		vd.addTronconSortant(t4);
		ve.addTronconSortant(t6);
		vf.addTronconSortant(t8);*/
		Noeud a = new Noeud(0, 5, 2);
		Noeud b = new Noeud(1, 5, 7);
		Noeud c = new Noeud(2, 4, 2);
		Noeud d = new Noeud(3, 1, 2);
		Noeud e = new Noeud(4, 9, 7);

		List<Vertex> argVertexs = new ArrayList<Vertex>();
		
		Vertex va = new Vertex (a);
		argVertexs.add(va);
		Vertex vb = new Vertex (b);
		argVertexs.add(vb);
		Vertex vc = new Vertex (c);
		argVertexs.add(vc);
		Vertex vd = new Vertex (d);
		argVertexs.add(vd);
		Vertex ve = new Vertex (e);
		argVertexs.add(ve);
		
		planManager.setVertexs(argVertexs);
		
		Troncon t1 = new Troncon("rue 1", 10f, 10f, a, b);
		t1.setTarget(vb);
		Troncon t2 = new Troncon("rue 2", 10f, 20f, b, c);
		t2.setTarget(vc);
		Troncon t3 = new Troncon("rue 3", 10f, 20f, c, d);
		t3.setTarget(vd);
		Troncon t4 = new Troncon("rue 4", 10f, 30f, d, e);
		t4.setTarget(ve);
		Troncon t5 = new Troncon("rue 5", 10f, 10f, e, a);
		t5.setTarget(va);
		Troncon t6 = new Troncon("rue 6", 10f, 50f, b, d);
		t6.setTarget(vd);
		Troncon t7 = new Troncon("rue 7", 10f, 40f, e, c);
		t7.setTarget(vc);
		Troncon t8 = new Troncon("rue 8", 10f, 100f, c, a);
		t8.setTarget(va);
		
		a.addTronconSortant(t1);
		b.addTronconSortant(t2);
		b.addTronconSortant(t6);
		c.addTronconSortant(t3);
		c.addTronconSortant(t8);
		d.addTronconSortant(t4);
		e.addTronconSortant(t5);
		e.addTronconSortant(t7);
		
		va.addTronconSortant(t1);
		vb.addTronconSortant(t2);
		vb.addTronconSortant(t6);
		vc.addTronconSortant(t3);
		vc.addTronconSortant(t8);
		vd.addTronconSortant(t4);
		ve.addTronconSortant(t5);
		ve.addTronconSortant(t7);
		
		Set<Noeud> Plage0 = new HashSet<Noeud>();
		Plage0.add(a);
		
		Set<Noeud> Plage1 = new HashSet<Noeud>();
		Plage1.add(b);
		Plage1.add(c);
		Plage1.add(d);
		Plage1.add(e);
		
		//Set<Noeud> Plage2 = new HashSet<Noeud>();
		//Plage2.add(a);
		
		List<Set<Noeud>> listePlages = new ArrayList<Set<Noeud>>();
		listePlages.add(Plage0);
		listePlages.add(Plage1);
		//listePlages.add(Plage2);
		listePlages.add(Plage0);
		
		System.out.println(planManager.getChemins(listePlages).toString());

	}
			
	
}
