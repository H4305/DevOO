package devoo;

import java.util.ArrayList;
import java.util.List;

import model.data.Chemin;
import model.data.Itineraire;
import model.data.Noeud;
import model.manager.LivraisonManager;
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
		
	/**
	 * 
	 */
	@Test
	public void testExporterFeuilleRoute() {

		Controller controller = new Controller();		
		PlanManager planManager = controller.getPlanManager();
		LivraisonManager livraisonManager = controller.getLivraisonManager();
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
		livraisonManager.setItineraire(itineraire);
						
		livraisonManager.exporterFeuilleRoute();
	}
		


}
