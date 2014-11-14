package model.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.data.Chemin;
import model.data.Itineraire;
import model.data.Noeud;
import model.data.PlageHoraire;
import model.data.Troncon;
import model.manager.LivraisonManagerTest;
import model.manager.PlanManager;

import org.junit.Assert;
import org.junit.Test;

import util.CalculesHoraires;
import util.Vertex;
import controller.Controller;

public class LivraisonManagerTest {
	
	/**
	 * 
	 */
	@Test
	public void testTransfHeureMinANDFirstBefSecond() {
		float testFloat = 7800.0f;
		String testString = CalculesHoraires.transformeEnHeureMin(testFloat);
		Assert.assertEquals(testString, "02:10");	

		testFloat = 7860.0f;
		String testString2 = CalculesHoraires.transformeEnHeureMin(testFloat);
		Assert.assertEquals(testString2, "02:11");	
		
		Boolean res = CalculesHoraires.firstBeforeSecond(testString2, testString);
		System.out.println(res);
	}
	
	 /**
	  * 
	  */
	@Test
	public void testSommeHeures() {
		String heureA = "12:10";
		String heureB = "0:55";
		String testSomme = CalculesHoraires.sommeHeures(heureA, heureB);
		
		System.out.println(testSomme);
		Assert.assertEquals(testSomme, "13:05");
		
		heureA = "14:25";
		heureB = "0:47";

		testSomme = CalculesHoraires.sommeHeures(heureA, heureB);
		
		System.out.println(testSomme);
		Assert.assertEquals(testSomme, "15:12");	
	}
		
	
	@Test
	public void testupdateLivraisonsTime(){
		Controller controller = new Controller();
		PlanManager planManager = new PlanManager(controller);
		LivraisonManager livraisonManager = new LivraisonManager(planManager, controller);
		
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
				
		List<Set<Noeud>> listePlages = new ArrayList<Set<Noeud>>();
		listePlages.add(Plage0);
		listePlages.add(Plage1);
		listePlages.add(Plage0);
		
		List<PlageHoraire> listPlageHoraire = new ArrayList<PlageHoraire>();
		
		//listPlageHoraire.add(arg0);
		List<Chemin> list = planManager.getChemins(listePlages);
		
		//System.out.println(planManager.getChemins(listePlages).toString());
		
		//livraisonManager.updateLivraisonsTime(listePlages, list);
	
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testExporterFeuilleRoute() {

		Controller controller = new Controller();		
		PlanManager planManager = controller.getPlanManager();
		LivraisonManagerTest livraisonManager = controller.getLivraisonManager();
		Chemin chemin1;
		Chemin chemin2;
		Chemin chemin3;
		List<Chemin> listChemins = new ArrayList<Chemin>();

		controller.loadPlanXML();		
		
		List<Vertex> v = planManager.getVertexes();		
		Noeud a = v.get(0).getPoint();
		Noeud b = v.get(2).getPoint();
		Noeud c = v.get(4).getPoint();		
				
		chemin1 = planManager.calculerPlusCourtChemin(a,b);   //entre le premier et le dernier noeuds de la liste
		
		chemin2 = planManager.calculerPlusCourtChemin(b,c);   //entre le premier et le dernier noeuds de la liste
		
		chemin3 = planManager.calculerPlusCourtChemin(c,a);   //entre le premier et le dernier noeuds de la liste
		
		listChemins.add(chemin1);
		listChemins.add(chemin2);
		listChemins.add(chemin3);
		
		Itineraire itineraire = new Itineraire(listChemins);
		/*livraisonManager.setItineraire(itineraire);	
		livraisonManager.exporterFeuilleRoute();*/
	}
}
