package util;

import model.data.DemandeLivraisons;
import model.data.Point;
import model.data.Troncon;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		

		List<Troncon> listTroncons = new ArrayList<Troncon>(troncons);

		Troncon troncon = listTroncons.get(0);
		Assert.assertEquals(point0, troncon.getDepart());
		Assert.assertEquals(point1, troncon.getArrivee());
		float vitesse = 350.200000f;
		float longueur = 4.400000f;
		float duree = (float)vitesse/longueur;
		Assert.assertEquals(new Float(duree), new Float(troncon.getDuree()));
		
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
