package model.manager;

import java.util.*;

import util.Dijkstra;
import util.TwoKeyMap;
import util.Vertex;

import java.io.File;
import java.io.IOException;

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
	private Set<Troncon> troncons = new HashSet<Troncon>();
	private Set<Point> points = new HashSet<Point>();

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
    public Chemin plusCourtChemin(Point source, Point cible) {
        
    	Dijkstra dijkstra = new Dijkstra();
    	Vertex vSource = new Vertex (source);
    	Vertex vCible = new Vertex (cible);
    	dijkstra.computePaths(vSource);
    	
    	List<Vertex> vertexCourtChemin = dijkstra.getShortestPathTo(vCible);
    	ArrayList<Point> pointsDuCourtChemin = new ArrayList<Point>();
    	
    	for(Vertex v : vertexCourtChemin)
    	{
    		pointsDuCourtChemin.add(v.point); 
    	}
    	// TODO recuperer la liste des troncons correspondate	
        return null;
    }

    /**
     * @param Points 
     * @return
     */
    public Chemin getChemin(List<Set<Point>> plages) {
    	TwoKeyMap<Point, Point, Chemin> courtsChemins = new TwoKeyMap<Point, Point, Chemin>();
        Set<Point> plagePrecedente = null;
    	//Pour chaque plage
    	for(Set<Point> plage : plages) {
    		//On calcule le meilleur chemin entre tous les points de cette plage
    		for(Point orig : plage) {
    			for(Point dest : plage) {
            		if(!orig.equals(dest)) {
            			courtsChemins.put(orig, dest, plusCourtChemin(orig, dest));
            		}
            	}
    		}
    		//On calcule le meilleur chemin de tous les points de la plage précedente a la plage actuelle
    		if(plagePrecedente != null) {
    			for(Point orig : plagePrecedente) {
    				for(Point dest : plage) {
    					courtsChemins.put(orig, dest, plusCourtChemin(orig, dest));
    				}
    			}
    		}
    		plagePrecedente = plage;
    	}
        
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
		//Lire troncon et mettre la chose des trucs mmmm
		HashMap<Integer, Point> planPoints = new HashMap<Integer, Point>();
		
		for(Troncon troncon: troncons)
		{	
			Point point = troncon.getDepart();
			int id = point.getId();
			
			if( !planPoints.containsKey(id) ) {
				planPoints.put(id, point);
			}
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
