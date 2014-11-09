package model.data;

import java.util.*;

/**
 * 
 */
public class Chemin {

    /**
     * 
     */
    public Chemin() {
    }

    /**
     * 
     */
    private Point arrivee;


    /**
     * 
     */
    private Point depart;
    
    /**
     * 
     */
    private Troncon[] troncons;
    
    
    /**
     * 
     */
    public Point getDepart() {
        return depart;
    }

    /**
     * 
     */
    public Point getArrivee() {
       return arrivee;
    }
    
    /**
     * 
     */
    public Troncon[] getTroncons() {
       return troncons;
    }
    
    /**
     * 
     */
    public void setArrivee(Point pointArrivee) {
    	this.arrivee = pointArrivee;
    }

}