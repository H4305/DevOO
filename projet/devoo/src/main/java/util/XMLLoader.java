package util;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.data.Point;
import model.data.Troncon;

public class XMLLoader {

	public static boolean isNumericAndGreaterThanZero(String inputData) {
		return inputData.matches("[0-9]+");
	}

	public static boolean isFloatWithSixDecimalPlaces(String inputData) {
		return inputData.matches("[0-9]+\\,[0-9]{6}");
	}

	public static boolean isHour(String inputData) {
		return inputData.matches("[0-2]?[0-9]?:[0-5]?[0-9]?:[0-5]?[0-9]");
	}

	public static Boolean getPlanXML(Element racine){
    	List<Point> nodeListNoeud = new ArrayList<Point>();
    	
        NodeList listNodes = racine.getElementsByTagName("Noeud");

        for (int i = 0; i < listNodes.getLength(); i++) {
        	if(listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
        		final Element noeud = (Element) listNodes.item(i); 
            	
				Integer id = Integer.parseInt(noeud.getAttribute("id"));
				Integer x = Integer.parseInt(noeud.getAttribute("x"));
				Integer y = Integer.parseInt(noeud.getAttribute("y"));
         	
				nodeListNoeud.add(new Point(id, x, y));	
        	}
        }
        
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

		NodeList listNodes = racine.getElementsByTagName("Plage");

		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Element noeud = (Element) listNodes.item(i);

				// Check the attributes number
				if (noeud.getAttributes().getLength() != 2) {
					System.out
							.println("Something wrong with the number of attributes of the node "
									+ noeud.getNodeName());
					return false;
				}
				// Check the attributes name
				else if (!noeud.hasAttribute("heureDebut")
						&& !noeud.hasAttribute("heureFin")) {
					System.out
							.println("Something wrong with the attributes of the node "
									+ noeud.getNodeName());
					return false;
				}
				// Check if the node contains at least one child
				else if (noeud.getElementsByTagName("Livraisons").getLength() < 1) {
					return false;
				}

				// Get the value of the attributes
				String heureDebut = noeud.getAttribute("heureDebut");
				String heureFin = noeud.getAttribute("heureFin");

				// Check if the attributes are numerics and greater than zero
				if (!isHour(heureDebut) && !isHour(heureFin)) {
					System.out
							.println("Something wrong with the attributes of the node "
									+ noeud.getNodeName());
					return false;
				}

				// Just print them
				System.out.println(" ---- " + noeud.getNodeName());
				System.out.print(noeud.getNodeName() + "(heureDebut : "
						+ noeud.getAttribute("heureDebut"));
				System.out.println(", heureFin : "
						+ noeud.getAttribute("heureFin") + ");");

			}
		}

		listNodes = racine.getElementsByTagName("Livraison");

		for (int i = 0; i < listNodes.getLength(); i++) {
			if (listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				final Element noeud = (Element) listNodes.item(i);
				// Check the attributes number
				if (noeud.getAttributes().getLength() != 3) {
					System.out
							.println("Something wrong with the number of attributes of the node "
									+ noeud.getNodeName());
					return false;
				}
				// Check the attributes name
				else if (!noeud.hasAttribute("id")
						&& !noeud.hasAttribute("client")
						&& !noeud.hasAttribute("adresse")) {
					System.out
							.println("Something wrong with the attributes of the node "
									+ noeud.getNodeName());
					return false;
				}

				// Get the value of the attributes
				String id = noeud.getAttribute("id");
				String client = noeud.getAttribute("client");
				String adresse = noeud.getAttribute("adresse");

				// Check if the attributes are numerics and greater than zero
				if (!isNumericAndGreaterThanZero(id)
						&& !isNumericAndGreaterThanZero(client)
						&& !isNumericAndGreaterThanZero(adresse)) {
					System.out
							.println("Something wrong with the attributes of the node "
									+ noeud.getNodeName());
					return false;
				}

				// Just print them
				System.out.print(noeud.getNodeName() + "(id : "
						+ noeud.getAttribute("id"));
				System.out.print(", client : " + noeud.getAttribute("client"));
				System.out.println(", adresse : "
						+ noeud.getAttribute("adresse") + ");");
			}
		}
		return true;
	}

}
