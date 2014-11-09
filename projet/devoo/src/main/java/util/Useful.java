package util;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
                 // creation d'un constructeur de documents a l'aide d'une fabrique
                DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
                // lecture du contenu d'un fichier XML avec DOM
                Document document = constructeur.parse(xml);
                Element racine = document.getDocumentElement();
                
                // Get the plan
                //
                if (racine.getNodeName().equals("Reseau")) {
                	XMLVerification.getPlanXML(racine);
                }
                else if (racine.getNodeName().equals("JourneeType")) {
                	XMLVerification.getLivraisonXML(racine);
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
		
		Source xmlFile = new StreamSource(new File("C:\\Users\\Anthony\\Documents\\try JAVA\\XML_ERROR\\plan20x20.xml"));
		Source xmlFileXSD = new StreamSource(new File("C:\\Users\\Anthony\\Documents\\try JAVA\\plan.xsd"));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xmlFileXSD);
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
