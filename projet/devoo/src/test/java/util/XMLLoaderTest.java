package util;

import model.data.DemandeLivraisons;
import model.data.Point;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLLoaderTest {

	@Test
	public void testGetPlanXML() throws Exception {
		throw new RuntimeException("not yet implemented");
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
		 DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = constructeur.parse(file);
        Element racine = document.getDocumentElement();
		DemandeLivraisons demandeLivraisons = XMLLoader.getLivraisonXML(file, racine, plan);
		Assert.assertEquals(3, demandeLivraisons.getPlagesHoraire().size());
		Assert.assertEquals(1, demandeLivraisons.getPlagesHoraire().get(0).getLivraisons().size());
		Assert.assertEquals(2, demandeLivraisons.getPlagesHoraire().get(1).getLivraisons().size());
	}

}
