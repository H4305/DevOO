package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.data.DemandeLivraisons;
import model.data.Livraison;
import model.data.PlageHoraire;
import model.data.Point;
import model.data.Troncon;
import model.exceptions.LivraisonXMLException;
import model.exceptions.PlanXMLException;

public class XMLLoader {

	/**
	 * This method allows to get the Root from a xml file
	 * @param file : The XML file we want to get the root
	 * @return The Element root
	 */
	private static Element getRootFromXMLFile(File file){

        if (file != null) {
             try {
                DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
                Document document = constructeur.parse(file);
                return document.getDocumentElement();
                
            } catch (ParserConfigurationException pce) {
                System.out.println("Erreur de configuration du parseur DOM");
                System.out.println("lors de l'appel a fabrique.newDocumentBuilder();");
            } catch (SAXException se) {
                System.out.println("Erreur lors du parsing du document");
                System.out.println("lors de l'appel a construteur.parse(xml)");
            } catch (IOException ioe) {
                System.out.println("Erreur d'entree/sortie");
                System.out.println("lors de l'appel a construteur.parse(xml)");
            }
        }
		return null;  
	}
	
	/**
	 * This method allows to get the plan after check the match with XSD file
	 * @param file : The XML file we want to load
	 * @return A Pair containing a set of troncons as first element and an Array list of vertex as second
	 * @throws PlanXMLException
	 */
	public static PairKey<Set<Troncon>, ArrayList<Vertex>> getPlanXML(File file) throws PlanXMLException {
		
		if (!XMLVerification.checkPlanXML(file)) {
			throw new PlanXMLException("The " + file.getAbsolutePath() + " is NOT valid");
		}
		
		Element racine = getRootFromXMLFile(file);
		
		HashMap<Integer, Point> noeuds = new HashMap<Integer, Point>();
		ArrayList<Vertex> vertexs = new ArrayList<Vertex>();
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
		                float vitesse = Float.parseFloat(noeudFils.getAttribute("vitesse").replace(',', '.'));
		                float longueur = Float.parseFloat(noeudFils.getAttribute("longueur").replace(',', '.'));
		                String idNoeudDestination = noeudFils.getAttribute("idNoeudDestination");
		                
		                Point pointArrivee = noeuds.get(Integer.parseInt(idNoeudDestination));
		                listTroncon.add(new Troncon(nomRue, vitesse, longueur, pointDepart, pointArrivee));
		        	}
		        }
		        
		        pointDepart.addTronconSortants(listTroncon);

				vertexs.add( new Vertex(pointDepart, listTroncon) );
				
		        for (Troncon troncon : listTroncon){
		        	nodeListTronconSortant.add(troncon);
		        }
        	}
        }
		return new PairKey<Set<Troncon>, ArrayList<Vertex>>(nodeListTronconSortant, vertexs);
	}

	/**
	 * This method allows to get the livraison after check the match with XSD file
	 * @param file : The XML file we want to load
	 * @param plan : The previously loaded plan, to check if the adresse of the livraison already exists
	 * @return A DemandeLivraisons object
	 * @throws LivraisonXMLException
	 */
	public static DemandeLivraisons getLivraisonXML(File file, HashMap<Integer, Point> plan) throws LivraisonXMLException {
		
		if (!XMLVerification.checkLivraisonXML(file)) {
			throw new LivraisonXMLException("The " + file.getAbsolutePath() + " is NOT valid");
		}
		
		Element racine = getRootFromXMLFile(file);
		Point entrepot = new Point();
		NodeList listEntrepot = racine.getElementsByTagName("Entrepot");
		
		for (int i = 0; i < listEntrepot.getLength(); i++) {
			if (listEntrepot.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Element noeud = (Element) listEntrepot.item(i);
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
				NodeList listNodesFils = noeud.getElementsByTagName("Livraison");

				for (int j = 0; j < listNodesFils.getLength(); j++) {
					if (listNodesFils.item(j).getNodeType() == Node.ELEMENT_NODE) {
						final Element noeudFils = (Element) listNodesFils.item(j);

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
