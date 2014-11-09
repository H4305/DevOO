package util;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.exceptions.LivraisonXMLException;
import model.exceptions.PlanXMLException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

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
						XMLVerification.getPlanXML(xml, racine);
					} catch (PlanXMLException e) {
						System.out.println("----");
						//e.printStackTrace();
						
						// On affichera ca dans la vue
						System.out.println(e.getMessage());
					}
                }
                else if (racine.getNodeName().equals("JourneeType")) {
                	try {
						XMLVerification.getLivraisonXML(xml, racine);
					} catch (LivraisonXMLException e) {
						System.out.println("----");
						//e.printStackTrace();
						
						// On affichera ca dans la vue
						System.out.println(e.getMessage());
					}
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
		//lireDepuisFichierXML();
		
		//Source xmlFile = new StreamSource(new File("src/test/resources/xml/plan20x20.xml"));
		Source xmlFile = new StreamSource(new File("src/main/resources/xml/livraison20x20-2.xml"));
		
		//Source xsdPlan = new StreamSource(new File("src/main/resources/plan.xsd"));
		Source xsdLivraison = new StreamSource(new File("src/main/resources/livraison.xsd"));
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsdLivraison);
		Validator validator = schema.newValidator();
		
		try {
		  validator.validate(xmlFile);
		  System.out.println("The " + xmlFile.getSystemId() + " is valid - XML complies with XSD");
		} catch (SAXException e) {
		  System.out.println("The " + xmlFile.getSystemId() + " is NOT valid");
		  System.out.println("Reason: " + e.getLocalizedMessage());
		}
	}		
}