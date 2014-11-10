package devoo;

import static org.junit.Assert.*;
import model.data.Livraison;
import model.data.Point;
import model.manager.LivraisonManager;

import org.junit.Test;

public class LivraisonManagerTest {

	
	@Test
	public void testGetPlagesHoraires() {
		LivraisonManager livraisonManager = new LivraisonManager();
		//livraisonManager.loadDemandeLivraisonsXML();
		System.out.println(livraisonManager.getPlagesHoraire());
		
	}
	@Test
	public void testGetLivraisons() {
		LivraisonManager livraisonManager = new LivraisonManager();
		//livraisonManager.loadDemandeLivraisonsXML();
		System.out.println(livraisonManager.getLivraisons());
		
	}@Test
	public void testRemovePanel() {
		Point point = new Point(20,20,20);
		Livraison livraison = new Livraison(1,2,point);	
		LivraisonManager livraisonManager = new LivraisonManager();
		livraisonManager.remove(livraison);
		
	}
	@Test
	public void testAddPanel() {
		//Livraison livraison = new Livraison(1,"20",2);
		Point point = new Point(20,20,20);
		LivraisonManager livraisonManager = new LivraisonManager();
		livraisonManager.add(point);
		
	}

}
