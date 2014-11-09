package model.manager;
import java.util.*;

import util.Dijkstra;
import util.Vertex;
import model.data.Chemin;
import model.data.Point;
import model.data.Troncon;

/**
 * 
 */
public class PlanManager {
	
	Set<Troncon> troncons = new HashSet<Troncon>();
	Set<Point> points = new HashSet<Point>();

    /**
     * 
     */
    public PlanManager() {
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
    		points.add(v.point);
    	}
    	// TODO recuperer la liste des troncons correspondate	
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
    		return "Le troncon demandé n'existe pas";
    	}
    }

}