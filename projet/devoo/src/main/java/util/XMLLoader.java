package util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.data.DemandeLivraisons;
import model.data.Livraison;
import model.data.PlageHoraire;
import model.data.Point;
import model.data.Troncon;
import model.exceptions.LivraisonXMLException;
import model.exceptions.PlanXMLException;

public class XMLLoader {

	public static Set<Troncon> getPlanXML(File file, Element racine) throws PlanXMLException {
		
		
		if (!XMLVerification.checkPlanXML(file, racine)) {
			throw new PlanXMLException("The " + file.getAbsolutePath() + " is NOT valid");
		}
			
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
     
        Set<Troncon> nodeListTronconSortant = new HashSet<Troncon>();
        listNodes = racine.getElementsByTagName("LeTronconSortant");       
        
        for (int i = 0; i < listNodes.getLength(); i++) {
        	if(listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
            	final Element noeud = (Element) listNodes.item(i); 
            	
                // Get the value of the attributes
                String nomRue = noeud.getAttribute("nomRue");
                float vitesse = Float.parseFloat(noeud.getAttribute("vitesse"));
                float longueur = Float.parseFloat(noeud.getAttribute("longueur"));
                String idNoeudDestination = noeud.getAttribute("idNoeudDestination");
                
                // Rajouter le point de Depart
                
                for (Point p : nodeListNoeud) {
                	if (p.getId() == Integer.parseInt(idNoeudDestination)) {
                		nodeListTronconSortant.add(new Troncon(nomRue, vitesse, longueur, p));
                		break; // On sort de la boucle
                	}
                }
        	}
        }
		return nodeListTronconSortant;
	}

	// -----------------------------------------------------------------------------------------

	public static DemandeLivraisons getLivraisonXML(File file, Element racine, HashMap<Integer, Point> plan) throws LivraisonXMLException {
		
		if (!XMLVerification.checkLivraisonXML(file, racine)) {
			throw new LivraisonXMLException("The " + file.getAbsolutePath() + " is NOT valid");
		}
			
		List<PlageHoraire> nodeListTronconSortant = new ArrayList<PlageHoraire>();
		NodeList listNodes = racine.getElementsByTagName("Plage");

		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Element noeud = (Element) listNodes.item(i);

				// Get the value of the attributes
				String heureDebut = noeud.getAttribute("heureDebut");
				String heureFin = noeud.getAttribute("heureFin");
				
				List<Livraison> nodeListLivraison = new ArrayList<Livraison>();
				listNodes = noeud.getElementsByTagName("Livraison");

				for (int j = 0; j < listNodes.getLength(); j++) {
					if (listNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
						final Element noeudFils = (Element) listNodes.item(j);

						// Get the value of the attributes
						int id = Integer.parseInt(noeudFils.getAttribute("id"));
						int client = Integer.parseInt(noeudFils.getAttribute("client"));
						int id_point = Integer.parseInt(noeudFils.getAttribute("adresse"));
						
						if (!plan.containsKey(id_point)){
							throw new LivraisonXMLException("LivraisonXMLException - The adresse needed is not available");
						}
						Point p  = plan.get(id_point);
						nodeListLivraison.add(new Livraison(id, client, p));
					}
				}
				nodeListTronconSortant.add(new PlageHoraire(heureDebut, heureFin, nodeListLivraison));
			}
		}
		return new DemandeLivraisons(nodeListTronconSortant);
	}
}
