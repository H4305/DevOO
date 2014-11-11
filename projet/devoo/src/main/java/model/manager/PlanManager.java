package model.manager;

import java.util.*;

import util.Dijkstra;
import util.PairKey;
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
	private ArrayList<Vertex> vertexs = new ArrayList<Vertex>();

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
    public Chemin calculerPlusCourtChemin(Point source, Point cible, Vertex vSource, Vertex vCible) {
        
    	Dijkstra dijkstra = new Dijkstra();
    	ArrayList<Point> pointsDuCourtChemin = new ArrayList<Point>();
    	ArrayList<Vertex> vertexCourtChemin = new ArrayList<Vertex>();
    	
    	dijkstra.computePaths(vSource);
    	vertexCourtChemin = dijkstra.getShortestPathTo(vCible);  //on recupere la liste des vertex du plus court chemin
    	
    	for(Vertex v : vertexCourtChemin)    //on recupere la liste des points correspondants aux vertex
    	{
    		pointsDuCourtChemin.add(v.getPoint()); 
    	}    	
    	
        Chemin chemin = new Chemin(cible, source);  
        //TODO faire set dureeTrajet tempsParcours
    	
        for(int i = 0; i < pointsDuCourtChemin.size()-1; i++)   //on parcourt la liste des points du plus court chemin
        {
        	for(Troncon t : pointsDuCourtChemin.get(i).getTronconsSortants())  //on parcourt la liste de troncons sortants des points du plus court chemin
        	{
        		if(t.getArrivee().equals(pointsDuCourtChemin.get(i+1)))  //on verifie si on est sur le troncon partant du point i et arrivant au point i+1
        		{
        			chemin.addTronconChemin(t);   //on ajoute le troncon au chemin
        			break;
        		}
        	}
        }      
    		
        return chemin;
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
	
	public void setPlan(Set<Troncon> troncons) {
		this.troncons = troncons;
	}
	
	public void setVertexs(ArrayList<Vertex> vertexs) {
		this.vertexs = vertexs;
	}

	/**
     * 
     */
    public void loadPlanXML(File fileXML) throws NullPointerException {
    	if (fileXML != null) {
			// Get the plan
			try {
				
				PairKey<Set<Troncon>, ArrayList<Vertex>> tronconsVertexs = XMLLoader.getPlanXML(fileXML);
				setPlan(tronconsVertexs.troncons);
				setVertexs(tronconsVertexs.vertexs);	
				mController.afficherPlan();
				
			} catch (PlanXMLException e) {
				// On affichera ca dans la vue
				mController.exceptionOpenFileXML(e.getMessage());
			}
       } else {
    	   throw new NullPointerException();
       }
    }
}
