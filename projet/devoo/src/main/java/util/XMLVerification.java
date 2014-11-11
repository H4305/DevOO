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

import org.xml.sax.SAXException;

public class XMLVerification {

	/**
	 * XSD File to check the plan XML files
	 */
	private static final Source XSD_PLAN = new StreamSource(new File("src/main/resources/plan.xsd"));
	
	/**
	 * XSD File to check the livraison XML files
	 */
	private static final Source XSD_LIVRAISON = new StreamSource(new File("src/main/resources/livraison.xsd"));
	
	/**
	 * LOGGER to write with level, all the issues we want
	 */
	private static final Logger LOGGER = Logger.getLogger(XMLVerification.class.getName());
	
	/**
	 * This method allows to check if the Plan XML corresponds to the XSD file
	 * @param file : The XML file we want to check
	 * @return Boolean
	 */
	public static Boolean checkPlanXML(File file) {
		
		Source xmlFile = new StreamSource(file);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(XSD_PLAN);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			LOGGER.log(Level.FINE, "The " + xmlFile.getSystemId() + " is valid - XML complies with XSD");
			return true;
		} catch (SAXException | IOException e1) {
			LOGGER.log(Level.SEVERE, "The " + xmlFile.getSystemId() + " is NOT valid");
			LOGGER.log(Level.SEVERE, "Reason: " + e1.getLocalizedMessage());
			return false;
		}
	}


	/**
	 * This method allows to check if the Livraison XML corresponds to the XSD file
	 * @param file : The XML file we want to check
	 * @return Boolean
	 */
	public static Boolean checkLivraisonXML(File file){

		Source xmlFile = new StreamSource(file);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		try {
			schema = schemaFactory.newSchema(XSD_LIVRAISON);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			LOGGER.log(Level.FINE, "The " + xmlFile.getSystemId() + " is valid - XML complies with XSD");
			return true;
		} catch (SAXException | IOException e1) {
			LOGGER.log(Level.SEVERE, "The " + xmlFile.getSystemId() + " is NOT valid");
			LOGGER.log(Level.SEVERE, "Reason: " + e1.getLocalizedMessage());
			return false;
		}
	}
}
