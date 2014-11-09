package util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import model.exceptions.LivraisonXMLException;
import model.exceptions.PlanXMLException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLVerification {

	private static final Source XSD_PLAN = new StreamSource(new File("src/main/resources/plan.xsd"));
	private static final Source XSD_LIVRAISON = new StreamSource(new File("src/main/resources/livraison.xsd"));
	private static final Logger LOGGER = Logger.getLogger(XMLVerification.class.getName());
	
	public static Boolean checkPlanXML(File file, Element racine) throws PlanXMLException {
		
		Source xmlFile = new StreamSource(file);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(XSD_PLAN);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			LOGGER.log(Level.FINE, "The " + xmlFile.getSystemId() + " is valid - XML complies with XSD");
		} catch (SAXException | IOException e1) {
			LOGGER.log(Level.SEVERE, "The " + xmlFile.getSystemId() + " is NOT valid");
			LOGGER.log(Level.SEVERE, "Reason: " + e1.getLocalizedMessage());
			
			throw new PlanXMLException("The " + xmlFile.getSystemId() + " is NOT valid", e1);
		}
		return true;
	}

	// -----------------------------------------------------------------------------------------

	// Return Boolean 
	public static Boolean checkLivraisonXML(File file, Element racine) throws LivraisonXMLException {

		Source xmlFile = new StreamSource(file);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(XSD_LIVRAISON);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			LOGGER.log(Level.FINE, "The " + xmlFile.getSystemId() + " is valid - XML complies with XSD");
		} catch (SAXException | IOException e1) {
			LOGGER.log(Level.SEVERE, "The " + xmlFile.getSystemId() + " is NOT valid");
			LOGGER.log(Level.SEVERE, "Reason: " + e1.getLocalizedMessage());
			
			throw new LivraisonXMLException("The " + xmlFile.getSystemId() + " is NOT valid", e1);
		}
		return true;
	}
}
