package util;

import model.data.DemandeLivraisons;
import model.data.Point;
import model.data.Troncon;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class XMLLoaderTest {

	/*
	@Test(expected = ArithmeticException.class)  
	public void testDivisionWithException() {  
	  int i = 1/0;
	}  */
	
	@Test
	public void testGetPlanXML() throws Exception {

		// 1150 troncons
		File file = new File("src/main/resources/xml/plan20x20.xml");
		Set<Troncon> troncons = XMLLoader.getPlanXML(file).troncons;
		Assert.assertEquals(1150, troncons.size());
		
		// 724 troncons
		file = new File("src/main/resources/xml/plan20x20_724Troncons.xml");
		troncons = XMLLoader.getPlanXML(file).troncons;
		Assert.assertEquals(724, troncons.size());
		
		// 200 troncons
		file = new File("src/main/resources/xml/plan20x20_200Troncons.xml");
		troncons = XMLLoader.getPlanXML(file).troncons;
		Assert.assertEquals(200, troncons.size());
		
		
		// 7 troncons
		file = new File("src/main/resources/xml/plan20x20_7Troncons.xml");
		troncons = XMLLoader.getPlanXML(file).troncons;
		Assert.assertEquals(7, troncons.size());
		
		Point point0 = new Point(0,51,37);
		Point point1 = new Point(1,26,65);
		Point point2 = new Point(2,23,116);
		
		// Because of the set, it is not ordered
		for (Troncon troncon : troncons) {
			
			if (troncon.getNomRue().equals("h1")){
				float longueur = 350.200000f;
				float vitesse = 4.400000f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point0, troncon.getDepart());
				Assert.assertEquals(point1, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h2")){
				float longueur = 116.400000f;
				float vitesse = 4.300000f;
				float duree = (float)longueur/vitesse;
				System.out.println(troncon.getDepart());
				Assert.assertEquals(point0, troncon.getDepart());
				Assert.assertEquals(point2, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h3")){
				float longueur = 300.200000f;
				float vitesse = 4.500000f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point1, troncon.getDepart());
				Assert.assertEquals(point0, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h4")){
				float longueur = 408.700000f;
				float vitesse = 4.200000f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point1, troncon.getDepart());
				Assert.assertEquals(point2, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h5")){
				float longueur = 408.700000f;
				float vitesse = 4.700000f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point2, troncon.getDepart());
				Assert.assertEquals(point1, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h6")){
				float longueur = 291.200000f;
				float vitesse = 3.800000f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point2, troncon.getDepart());
				Assert.assertEquals(point0, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
			else if (troncon.getNomRue().equals("h7")){
				float longueur = 364.300000f;
				float vitesse = 4.000002f;
				float duree = (float)longueur/vitesse;
				Assert.assertEquals(point2, troncon.getDepart());
				Assert.assertEquals(point1, troncon.getArrivee());
				Assert.assertEquals(new Float(longueur), new Float(troncon.getLongueur()));
				Assert.assertEquals(new Float(vitesse), new Float(troncon.getVitesse()));
				Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
			}
		}
	}

	@Test
	public void testGetLivraisonXML() throws Exception {
		HashMap<Integer, Point> plan = new HashMap<Integer, Point>();
		plan.put((Integer) 1, new Point(1, 1, 1));
		plan.put((Integer) 2, new Point(2, 1, 2));
		plan.put((Integer) 3, new Point(3, 2, 1));
		plan.put((Integer) 4, new Point(4, 2, 2));
		plan.put((Integer) 0, new Point(0, 0, 0));
		File file = new File("src/test/resources/xml/livraisonTestOk.xml");
		DemandeLivraisons demandeLivraisons = XMLLoader.getLivraisonXML(file, plan);
		Assert.assertEquals(3, demandeLivraisons.getPlagesHoraire().size());
		Assert.assertEquals(1, demandeLivraisons.getPlagesHoraire().get(0).getLivraisons().size());
		Assert.assertEquals(2, demandeLivraisons.getPlagesHoraire().get(1).getLivraisons().size());
	}
}
