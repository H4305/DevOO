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

import controller.Controller;
import util.XMLLoader;
import model.data.Chemin;
import model.data.Point;
import model.data.Troncon;
import model.exceptions.LivraisonXMLException;
import model.exceptions.PlanXMLException;

/**
 * 
 */
public class PlanManager {
	
	private Controller mController;
	Set<Troncon> troncons = new HashSet<Troncon>();
	Set<Point> points = new HashSet<Point>();

    /**
     * 
     */
    public PlanManager(Controller controller) {
    	this.mController = controller;
    }



    /**
     * @param PointA 
     * @param PointB 
     * @return
     */
    public Chemin plusCourtChemin(Point PointA, Point PointB) {
        // TODO implement here
        return null;
    }

    /**
     * @param Points 
     * @return
     */
    public Chemin getChemin(Set<Point> Points) {
        // TODO implement here
        return null;
    }

    /**
     * @param listTroncon 
     * @param circuit
     */
    public void getChemin(Set<Troncon> listTroncon, List<Troncon> circuit) {
        // TODO implement here
    }
    
    /**
     * Ajoute un point au plan
     * @param troncon
     */
    public void addTroncon(Troncon troncon) {
    	troncons.add(troncon);
    }
    
    public Collection<Troncon> getAllTroncons() {
    	return troncons;
    }
    
    public class TronconNotFoundExcepetion extends Exception {
    	
 
		private static final long serialVersionUID = 3754278865880411751L;

		@Override
    	public String getMessage() {
    		return "Le troncon demand� n'existe pas";
    	}
    }

	public HashMap<Integer, Point> getHashMapPlan() {
		
		HashMap<Integer, Point> planPoints = new HashMap<Integer, Point>();
		
		for(Point point: points)
		{
			planPoints.put(point.getId(), point);
		}
		
		return planPoints;
	}
	
	public Set<Troncon> getPlan() {
		return troncons;
	}
	
	public void setPlan(Set<Troncon> plan) {
		troncons = plan;
	}

	/**
     * 
     */
    public void loadPlanXML(File fileXML) throws NullPointerException {
    	
    	if (fileXML != null) {
            try {
               // Creation d'un constructeur de documents a l'aide d'une fabrique
               DocumentBuilder constructeur = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
               // Lecture du contenu d'un fichier XML avec DOM
               Document document = constructeur.parse(fileXML);
               Element racine = document.getDocumentElement();
               
               // Get the plan
               if (racine.getNodeName().equals("Reseau")) {
               	try {
						//XMLVerification.checkPlanXML(xml, racine);
						setPlan(XMLLoader.getPlanXML(fileXML, racine));
						
						mController.afficherPlan();
						
					} catch (PlanXMLException e) {						
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
       } else {
    	   throw new NullPointerException();
       }
    }

}
