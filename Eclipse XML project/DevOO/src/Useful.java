import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Useful {

	private static JFileChooser jFileChooserXML;
	
	private static File ouvrirFichier(char mode){
        jFileChooserXML = new JFileChooser();
        // Note: source for ExampleFileFilter can be found in FileChooserDemo,
        // under the demo/jfc directory in the JDK.
        ExampleFileFilter filter = new ExampleFileFilter();
        filter.addExtension("xml");
        filter.setDescription("Fichier XML");
        jFileChooserXML.setFileFilter(filter);
        jFileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal;
        if (mode == 'o')
        	returnVal = jFileChooserXML.showOpenDialog(null);
        else
        	returnVal = jFileChooserXML.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) 
                return new File(jFileChooserXML.getSelectedFile().getAbsolutePath());
        return null;
	}
		
	public static boolean isNumericAndGreaterThanZero(String inputData) {
		  return inputData.matches("[0-9]+");
	}
		
	public static boolean isFloatWithSixDecimalPlaces(String inputData) {
		  return inputData.matches("[0-9]+\\,[0-9]{6}");
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
	                
	                /// TODO 
	                /*
	                // Check if the attributes are numerics and greater than zero
	            	if (!isNumericAndGreaterThanZero(id) && !isNumericAndGreaterThanZero(x) && !isNumericAndGreaterThanZero(y)){
	        			System.out.println("Something wrong with the attributes of the node " + noeud.getNodeName());
	            		return false;
	            	}
	                */
	       		
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
	
	public static void lireDepuisFichierXML(){
        File xml = ouvrirFichier('o');
        if (xml != null) {
             try {
                 // creation d'un constructeur de documents a l'aide d'une fabrique
                DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
                // lecture du contenu d'un fichier XML avec DOM
                Document document = constructeur.parse(xml);
                Element racine = document.getDocumentElement();
                
                // Get the plan
                //
                if (racine.getNodeName().equals("Reseau")) {
                	getPlanXML(racine);
                }
                else if (racine.getNodeName().equals("JourneeType")) {
                	getLivraisonXML(racine);
                	//System.out.println("Livraison not available for the moment");
                }
                
            // todo : traiter les erreurs
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
	}
	public static void main(String[] args) {
		lireDepuisFichierXML();
	}		
}
