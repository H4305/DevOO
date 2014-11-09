package model.manager;

import java.util.*;

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
    public Set<Troncon> plusCourtChemin(Point PointA, Point PointB) {
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

}