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
	
	// Il faudrait passer en parametre un tableau des attributs pour rendre la methode generic pour chaque enfants !
	// La genericité se prete mal pour vérifier les valeurs
	// Car on pourrait faire un map (attribut, contraintes) .. Mais après il faut se parser les contraintes etc (à voir) ..
	public static Boolean getTronconSortant (Element racine, String node, int nbAttributs) {
        final NodeList racineNoeuds = racine.getChildNodes();
        final int nbRacineNoeuds = racineNoeuds.getLength();
                
        for (int i = 0; i<nbRacineNoeuds; i++) {
            if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element noeud = (Element) racineNoeuds.item(i);
                if (noeud.getNodeName().equals(node)) {
                	                	
                	// Check the attributs number
                	//
                	if(noeud.getAttributes().getLength() == nbAttributs) {
                		
                		// Just print attributes
                		//
                        System.out.print(noeud.getNodeName() + "(nomRue : " + noeud.getAttribute("nomRue"));
                        System.out.print( ", vitesse : " + noeud.getAttribute("vitesse"));
                        System.out.print( ", longueur : " + noeud.getAttribute("longueur"));
                        System.out.println(", idNoeudDestination : " + noeud.getAttribute("idNoeudDestination") +");");
                	}
                	else {
            			System.out.println("Il faut refuser le Fichier");
                		return false;
                	}
                }
            }				
        }
        return true;
	}
	
	public static Boolean getPlanXML(Element racine){
    	                           	
        final NodeList racineNoeuds = racine.getChildNodes();
        final int nbRacineNoeuds = racineNoeuds.getLength();
                
        for (int i = 0; i<nbRacineNoeuds; i++) {
            if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element noeud = (Element) racineNoeuds.item(i);
                if (noeud.getNodeName().equals("Noeud")) {
                	                	
                	// Check the attributs number
                	//
                	if(noeud.getAttributes().getLength() == 3) {
                		
                		// Just print attributes
                		//
                		// Need to check all the attributs
                        System.out.print(noeud.getNodeName() + "(id : " + noeud.getAttribute("id")); // Need to check all the attributs
                        System.out.print( ", x : " + noeud.getAttribute("x"));
                        System.out.println(", y : " + noeud.getAttribute("y") +");");
                        
                        // Get children
                        //                        
                		if (getTronconSortant(noeud, "LeTronconSortant", 4) == false) {
                			System.out.println("Il faut refuser le Fichier");
                			return false;
                		}
                	}
                	else {
                		System.out.println("Il faut refuser le Fichier");
                		return false;
                	}
                }
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
                	System.out.println("Livraison not available for the moment");
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
