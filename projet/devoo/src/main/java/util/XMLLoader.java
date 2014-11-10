package util;

import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
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
			
		HashMap<Integer, Point> noeuds = new HashMap<Integer, Point>();
    	Set<Troncon> nodeListTronconSortant = new HashSet<Troncon>();
        NodeList listNodes = racine.getElementsByTagName("Noeud");

        for (int i = 0; i < listNodes.getLength(); i++) {
        	if(listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
        		final Element noeud = (Element) listNodes.item(i); 
            	
				int id = Integer.parseInt(noeud.getAttribute("id"));
				int x = Integer.parseInt(noeud.getAttribute("x"));
				int y = Integer.parseInt(noeud.getAttribute("y"));

				noeuds.put(id, new Point(id, x, y));	
        	}
        }
        
        ArrayList<Troncon> listTroncon = new ArrayList<Troncon>();
        
        for (int i = 0; i < listNodes.getLength(); i++) {
        	if(listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
        		final Element noeud = (Element) listNodes.item(i); 
            	
				int id = Integer.parseInt(noeud.getAttribute("id"));
				Point pointDepart = noeuds.get(id);
				NodeList listNodesFils = noeud.getElementsByTagName("LeTronconSortant");       
		        
		        for (int j = 0; j < listNodesFils.getLength(); j++) {
		        	if(listNodesFils.item(j).getNodeType() == Node.ELEMENT_NODE) {
		            	final Element noeudFils = (Element) listNodesFils.item(j); 
		            	
		                // Get the value of the attributes
		                String nomRue = noeudFils.getAttribute("nomRue");
		                
		                // Si .replace(',', '.') ne nous plait pas on peut utiliser ca ..
		                /*
		                NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		                Number number = null;
						try {
							number = format.parse("1,234");
						} catch (ParseException e) {
							e.printStackTrace();
						}
		                double d = number.floatValue();
		                */
		                float vitesse = Float.parseFloat(noeudFils.getAttribute("vitesse").replace(',', '.'));
		                float longueur = Float.parseFloat(noeudFils.getAttribute("longueur").replace(',', '.'));
		                String idNoeudDestination = noeudFils.getAttribute("idNoeudDestination");
		                
		                Point pointArrivee = noeuds.get(Integer.parseInt(idNoeudDestination));
		                listTroncon.add(new Troncon(nomRue, vitesse, longueur, pointDepart, pointArrivee));
 
		        	}
		        } 
		        pointDepart.addTronconSortants(listTroncon);
		        for (Troncon troncon : listTroncon){
		        	nodeListTronconSortant.add(troncon);
		        }
        	}
        }
		return nodeListTronconSortant;
	}

	// -----------------------------------------------------------------------------------------

	public static DemandeLivraisons getLivraisonXML(File file, Element racine, HashMap<Integer, Point> plan) throws LivraisonXMLException {
		
		if (!XMLVerification.checkLivraisonXML(file)) {
			throw new LivraisonXMLException("The " + file.getAbsolutePath() + " is NOT valid");
		}
		
		Point entrepot = new Point();
		
		NodeList listEntrepot = racine.getElementsByTagName("Entrepot");
		
		for (int i = 0; i < listEntrepot.getLength(); i++) {
			if (listEntrepot.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Element noeud = (Element) listEntrepot.item(i);
				
				// Get the entrepot id
				int id_entrepot = Integer.parseInt(noeud.getAttribute("adresse"));
						
				if (!plan.containsKey(id_entrepot)){
					throw new LivraisonXMLException("LivraisonXMLException - The adress of this warehouse doesn't exists");
				}
				
				entrepot = plan.get(id_entrepot);			
				
			}
		}
		
		List<PlageHoraire> plagesHoraires = new ArrayList<PlageHoraire>();
		NodeList listNodes = racine.getElementsByTagName("Plage");

		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Element noeud = (Element) listNodes.item(i);

				// Get the value of the attributes
				String heureDebut = noeud.getAttribute("heureDebut");
				String heureFin = noeud.getAttribute("heureFin");
				
				List<Livraison> listLivraisons = new ArrayList<Livraison>();
				listNodes = noeud.getElementsByTagName("Livraison");

				for (int j = 0; j < listNodes.getLength(); j++) {
					if (listNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
						final Element noeudFils = (Element) listNodes.item(j);

						// Get the value of the attributes
						int id = Integer.parseInt(noeudFils.getAttribute("id"));
						int client = Integer.parseInt(noeudFils.getAttribute("client"));
						int id_adresse = Integer.parseInt(noeudFils.getAttribute("adresse"));
						
						if (!plan.containsKey(id_adresse)){
							throw new LivraisonXMLException("LivraisonXMLException - The adress needed is not available");
						}
						
						Point adresse  = plan.get(id_adresse);
						
						if(adresse.equals(entrepot)){
							throw new LivraisonXMLException("LivraisonXMLException - Cannot deliver the warehouse");
						}
						
						listLivraisons.add(new Livraison(id, client, adresse));
					}
				}
				plagesHoraires.add(new PlageHoraire(heureDebut, heureFin, listLivraisons));
			}
		}
		return new DemandeLivraisons(entrepot, plagesHoraires);
	}
}
