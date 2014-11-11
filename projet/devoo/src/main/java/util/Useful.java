package util;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.data.DemandeLivraisons;
import model.data.Point;
import model.data.Troncon;
import model.exceptions.LivraisonXMLException;
import model.exceptions.PlanXMLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Useful {

	private static JFileChooser jFileChooserXML;
	
	public static File ouvrirFichier(char mode){
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
	
	public static Set<Troncon> lirePlanDepuisFichierXML(){
		File xml = ouvrirFichier('o');
		if (xml != null) {
			try {
				// Get the plan
				return XMLLoader.getPlanXML(xml);
			} catch (PlanXMLException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	public static DemandeLivraisons lireLivraisonDepuisFichierXML(){
		File xml = ouvrirFichier('o');
		if (xml != null) {
			try {
				// Get the livraison
				return XMLLoader.getLivraisonXML(xml, new HashMap<Integer, Point>());
			} catch (LivraisonXMLException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	public static void lireDepuisFichierXML(){
        File xml = ouvrirFichier('o');
        if (xml != null) {
             try {
                // Creation d'un constructeur de documents a l'aide d'une fabrique
                DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
                // Lecture du contenu d'un fichier XML avec DOM
                Document document = constructeur.parse(xml);
                Element racine = document.getDocumentElement();
                
                // Get the plan
                if (racine.getNodeName().equals("Reseau")) {
                	try {
						XMLLoader.getPlanXML(xml);
					} catch (PlanXMLException e) {						
						// On affichera ca dans la vue
						System.out.println(e.getMessage());
					}
                }
                // Get the livraison
                else if (racine.getNodeName().equals("JourneeType")) {
                	try {
                		XMLLoader.getLivraisonXML(xml, new HashMap<Integer, Point>());
					} catch (LivraisonXMLException e) {
						// On affichera ca dans la vue
						System.out.println(e.getMessage());
					}
                }
                else {
                	System.out.println("Structure de fichier inconnue");
                }
               
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
	public static void main(String[] args) throws IOException, SAXException {
		lireDepuisFichierXML();
	}		
}