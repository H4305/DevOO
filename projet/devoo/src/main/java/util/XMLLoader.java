package util;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.data.Livraison;
import model.data.PlageHoraire;
import model.data.Point;
import model.data.Troncon;

public class XMLLoader {

	public static Boolean getPlanXML(Element racine){
    	List<Point> nodeListNoeud = new ArrayList<Point>();
    	
        NodeList listNodes = racine.getElementsByTagName("Noeud");

        for (int i = 0; i < listNodes.getLength(); i++) {
        	if(listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
        		final Element noeud = (Element) listNodes.item(i); 
            	
				int id = Integer.parseInt(noeud.getAttribute("id"));
				int x = Integer.parseInt(noeud.getAttribute("x"));
				int y = Integer.parseInt(noeud.getAttribute("y"));
         	
				nodeListNoeud.add(new Point(id, x, y));	
        	}
        }
        
        // Return a Troncon' set
        
        List<Troncon> nodeListTronconSortant = new ArrayList<Troncon>();
        listNodes = racine.getElementsByTagName("LeTronconSortant");       
        
        for (int i = 0; i < listNodes.getLength(); i++) {
        	if(listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
            	final Element noeud = (Element) listNodes.item(i); 
            	
                // Get the value of the attributes
                String nomRue = noeud.getAttribute("nomRue");
                float vitesse = Float.parseFloat(noeud.getAttribute("vitesse"));
                float longueur = Float.parseFloat(noeud.getAttribute("longueur"));
                String idNoeudDestination = noeud.getAttribute("idNoeudDestination");
                
                for (Point p : nodeListNoeud) {
                	if (p.getId() == Integer.parseInt(idNoeudDestination)) {
                		nodeListTronconSortant.add(new Troncon(nomRue, vitesse, longueur, p));
                		break; // On sort de la boucle
                	}
                }
        	}
        }
		return true;
	}

	// -----------------------------------------------------------------------------------------

	public static Boolean getLivraisonXML(Element racine) {
		List<PlageHoraire> nodeListTronconSortant = new ArrayList<PlageHoraire>();
		NodeList listNodes = racine.getElementsByTagName("Plage");

		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Element noeud = (Element) listNodes.item(i);

				// Get the value of the attributes
				String heureDebut = noeud.getAttribute("heureDebut");
				String heureFin = noeud.getAttribute("heureFin");

				nodeListTronconSortant.add(new PlageHoraire(heureDebut, heureFin));
			}
		}
		
		List<Livraison> nodeListLivraison= new ArrayList<Livraison>();
		listNodes = racine.getElementsByTagName("Livraison");

		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Element noeud = (Element) listNodes.item(i);

				// Get the value of the attributes
				int id = Integer.parseInt(noeud.getAttribute("id"));
				int client = Integer.parseInt(noeud.getAttribute("client"));
				String adresse = noeud.getAttribute("adresse");
				
				nodeListLivraison.add(new Livraison(id, adresse, client));

			}
		}
		return true;
	}

}
