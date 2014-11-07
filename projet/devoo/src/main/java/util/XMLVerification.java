package util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLVerification {
	
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
    	            
        NodeList listNodes = racine.getElementsByTagName("Noeud");

        for (int i = 0; i < listNodes.getLength(); i++) {
        	if(listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
            	final Element noeud = (Element) listNodes.item(i); 
            
            	// Check the attributes number
                if(noeud.getAttributes().getLength() != 3) {
        			System.out.println("Something wrong with the number of attributes of the node " + noeud.getNodeName());
            		return false;
            	}
            	// Check the attributes name
            	else if (!noeud.hasAttribute("id") && !noeud.hasAttribute("x") && !noeud.hasAttribute("y")) {
        			System.out.println("Something wrong with the attributes of the node " + noeud.getNodeName());
            		return false;
            	}
                // Check if the node contains at least one child
            	else if (noeud.getElementsByTagName("LeTronconSortant").getLength() < 1){
            		return false;
            	}
            	
                // Get the value of the attributes
                String id = noeud.getAttribute("id");
                String x = noeud.getAttribute("x");
                String y = noeud.getAttribute("y");
                
                // Check if the attributes are numerics and greater than zero
            	if (!isNumericAndGreaterThanZero(id) && !isNumericAndGreaterThanZero(x) && !isNumericAndGreaterThanZero(y)){
        			System.out.println("Something wrong with the attributes of the node " + noeud.getNodeName());
            		return false;
            	}
            	// Just print them
                System.out.print(noeud.getNodeName() + "(id : " + noeud.getAttribute("id"));
                System.out.print( ", x : " + noeud.getAttribute("x"));
                System.out.println(", y : " + noeud.getAttribute("y") +");");
            	
        	}
        }
                
        listNodes = racine.getElementsByTagName("LeTronconSortant");       
        
        for (int i = 0; i < listNodes.getLength(); i++) {
        	if(listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
            	final Element noeud = (Element) listNodes.item(i); 
            	if(noeud.getAttributes().getLength() != 4) {
        			System.out.println("Something wrong with the number of attributes of the node " + noeud.getNodeName());
            		return false;
            	}
            	// Check the attributes name
            	else if (!noeud.hasAttribute("nomRue") && !noeud.hasAttribute("vitesse") && !noeud.hasAttribute("longueur") && !noeud.hasAttribute("idNoeudDestination")) {
        			System.out.println("Something wrong with the attributes of the node " + noeud.getNodeName());
            		return false;
            	}
            	
                // Get the value of the attributes
                String nomRue = noeud.getAttribute("nomRue");
                String vitesse = noeud.getAttribute("vitesse");
                String longueur = noeud.getAttribute("longueur");
                String idNoeudDestination = noeud.getAttribute("idNoeudDestination");
                
                // Check if the attributes are numerics and greater than zero
            	if (nomRue.length() < 1 && !isNumericAndGreaterThanZero(idNoeudDestination) && !isFloatWithSixDecimalPlaces(vitesse) && !isFloatWithSixDecimalPlaces(longueur)){
        			System.out.println("Something wrong with the attributes of the node " + noeud.getNodeName());
            		return false;
            	}
            	
            	// Just print them
                System.out.print(noeud.getNodeName() + "(nomRue : " + noeud.getAttribute("nomRue"));
                System.out.print( ", vitesse : " + noeud.getAttribute("vitesse"));
                System.out.print( ", longueur : " + noeud.getAttribute("longueur"));
                System.out.println(", idNoeudDestination : " + noeud.getAttribute("idNoeudDestination") +");");
        	}
        }
		return true;
	}
		
	// -----------------------------------------------------------------------------------------
		
	public static Boolean getLivraisonXML(Element racine){

		 NodeList listNodes = racine.getElementsByTagName("Plage");

	        for (int i = 0; i < listNodes.getLength(); i++) {
	        	if(listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
	            	final Element noeud = (Element) listNodes.item(i); 
	            
	            	// Check the attributes number
	                if(noeud.getAttributes().getLength() != 2) {
	        			System.out.println("Something wrong with the number of attributes of the node " + noeud.getNodeName());
	            		return false;
	            	}
	            	// Check the attributes name
	            	else if (!noeud.hasAttribute("heureDebut") && !noeud.hasAttribute("heureFin")) {
	        			System.out.println("Something wrong with the attributes of the node " + noeud.getNodeName());
	            		return false;
	            	}
	                // Check if the node contains at least one child
	            	else if (noeud.getElementsByTagName("Livraisons").getLength() < 1){
	            		return false;
	            	}
	            	
	                // Get the value of the attributes
	                String heureDebut = noeud.getAttribute("heureDebut");
	                String heureFin = noeud.getAttribute("heureFin");
	                

	                // Check if the attributes are numerics and greater than zero
	            	if (!isHour(heureDebut) && !isHour(heureFin)){
	        			System.out.println("Something wrong with the attributes of the node " + noeud.getNodeName());
	            		return false;
	            	}
	                
	       		
	            	// Just print them
	                System.out.println(" ---- " + noeud.getNodeName());
	                System.out.print(noeud.getNodeName() + "(heureDebut : " + noeud.getAttribute("heureDebut"));
	                System.out.println(", heureFin : " + noeud.getAttribute("heureFin") +");");
	            	
	        	}
	        }
	                
	        listNodes = racine.getElementsByTagName("Livraison");       
	        
	        for (int i = 0; i < listNodes.getLength(); i++) {
	        	if(listNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
	            	final Element noeud = (Element) listNodes.item(i); 
	            	// Check the attributes number
	                if(noeud.getAttributes().getLength() != 3) {
	        			System.out.println("Something wrong with the number of attributes of the node " + noeud.getNodeName());
	            		return false;
	            	}
	            	// Check the attributes name
	            	else if (!noeud.hasAttribute("id") && !noeud.hasAttribute("client") && !noeud.hasAttribute("adresse")) {
	        			System.out.println("Something wrong with the attributes of the node " + noeud.getNodeName());
	            		return false;
	            	}

	                // Get the value of the attributes
	                String id = noeud.getAttribute("id");
	                String client = noeud.getAttribute("client");
	                String adresse = noeud.getAttribute("adresse");
	                
	                // Check if the attributes are numerics and greater than zero
	            	if (!isNumericAndGreaterThanZero(id) && !isNumericAndGreaterThanZero(client) && !isNumericAndGreaterThanZero(adresse)){
	        			System.out.println("Something wrong with the attributes of the node " + noeud.getNodeName());
	            		return false;
	            	}

	            	// Just print them
	                System.out.print(noeud.getNodeName() + "(id : " + noeud.getAttribute("id"));
	                System.out.print(", client : " + noeud.getAttribute("client"));
	                System.out.println(", adresse : " + noeud.getAttribute("adresse") +");");
	        	}
	        }
			return true;
	}
	

}
