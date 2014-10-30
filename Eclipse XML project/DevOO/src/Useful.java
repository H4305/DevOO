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
	
	// Need to make a generic method
	//
	public static void getPlanXML(Element racine){
    	                            
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
                        System.out.print(noeud.getNodeName() + "(id : " + noeud.getAttribute("id"));
                        System.out.print( ", x : " + noeud.getAttribute("x"));
                        System.out.println(", y : " + noeud.getAttribute("y") +");");
                        
                        // Get children
                        //
                        final NodeList racineNoeudsEnfants = noeud.getChildNodes();
                        final int nbRacineNoeudsEnfants = racineNoeudsEnfants.getLength();
                        for (int ii = 0; ii<nbRacineNoeudsEnfants; ii++) {
                            if(racineNoeudsEnfants.item(ii).getNodeType() == Node.ELEMENT_NODE) {
                                final Element noeudEnfants = (Element) racineNoeudsEnfants.item(ii);
                                if (noeudEnfants.getNodeName().equals("LeTronconSortant")) {
                                	
                                	// Check the attributs number
                                	//
                                	if(noeudEnfants.getAttributes().getLength() == 4) {
                                		// Just print attributes
                                		//
                                        System.out.print(noeudEnfants.getNodeName() + "(nomRue : " + noeudEnfants.getAttribute("nomRue"));
                                        System.out.print( ", vitesse : " + noeudEnfants.getAttribute("vitesse"));
                                        System.out.print( ", longueur : " + noeudEnfants.getAttribute("longueur"));
                                        System.out.println(", idNoeudDestination : " + noeudEnfants.getAttribute("idNoeudDestination") +");");
                                	}
                                	else {
                                		System.out.println("Il faut refuser le troncon");
                                	}
                                }
                            }
                        }
                	}
                	else {
                		System.out.println("Il faut refuser le Noeud");
                	}
                }
            }				
        }
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
