package model.manager;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


/*
 * controller import
 */
import controller.Controller;

/*
 * model import
 */
import model.data.DemandeLivraisons;
import model.data.Itineraire;
import model.manager.PlanManager;
import model.data.Point;
import model.exceptions.LivraisonXMLException;
import model.exceptions.PlanXMLException;

/*
 * util import
 */
import util.XMLLoader;

/**
 * 
 */
public class LivraisonManager {
	
	private Controller mController;
	private PlanManager mPlanManager;
	private DemandeLivraisons mDemandeLivraisons;

    /**
     * 
     */
    public LivraisonManager() {
    }
    
    public LivraisonManager(PlanManager planManager, Controller controller) {
    	this.mPlanManager = planManager;
    	this.mController = controller;
    }

    

    /**
     * @return
     */
    public Itineraire calculItineraire() {
        // TODO implement here
        return null;
    }
    
    /*
     * 
     */
    public void loadDemandeLivraisonsXML(File fileXML) {
    	
    	if (fileXML != null) {
            try {
               // Creation d'un constructeur de documents a l'aide d'une fabrique
               DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
               // Lecture du contenu d'un fichier XML avec DOM
               Document document = constructeur.parse(fileXML);
               Element racine = document.getDocumentElement();
               
               // Get the livraison
               if (racine.getNodeName().equals("JourneeType")) {
               	try {
               		
               		HashMap<Integer, Point> planPoints = this.mPlanManager.getHashMapPlan();
               		
               		this.mDemandeLivraisons = XMLLoader.getLivraisonXML(fileXML, racine, planPoints);
               		
               		mController.afficherDemandeLivraisons();
               		
					} catch (LivraisonXMLException e) {
						// On affichera ca dans la vue
						mController.exceptionOpenFileXML(e.getMessage());
					}
               }
               else {
            	   mController.exceptionOpenFileXML("Structure de fichier inconnue");
               }
              
           } catch (ParserConfigurationException pce) {
        	   mController.exceptionOpenFileXML("Erreur de configuration du parseur DOM");
        	   mController.exceptionOpenFileXML("lors de l'appel a fabrique.newDocumentBuilder();");
           } catch (SAXException se) {
        	   mController.exceptionOpenFileXML("Erreur lors du parsing du document");
        	   mController.exceptionOpenFileXML("lors de l'appel a construteur.parse(xml)");
           } catch (IOException ioe) {
        	   mController.exceptionOpenFileXML("Erreur d'entree/sortie");
        	   mController.exceptionOpenFileXML("lors de l'appel a construteur.parse(xml)");
           }
       }     	
    }
}