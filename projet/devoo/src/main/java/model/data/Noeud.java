package model.data;

import java.util.*;

/**
 * 
 */
public class Noeud {

    /**
     * 
     */
    public Noeud() {
    }
    
    /**
     * 
     * @param id
     * @param x
     * @param y
     */
    public Noeud (int id, int x, int y) {
    	this.id = id;
    	this.x = x;
    	this.y = y;
    	tronconsSortants = new ArrayList<Troncon>();
    }

    /**
     * 
     */
    private int id;
    
    /**
     * 
     */
    private ZoneGeographique zone;

    /**
     * 
     */
    private ArrayList<Troncon> tronconsSortants;
    
    /**
     * 
     */
    public int x;

    /**
     * 
     */
    public int y;

    /**
     * 
     * @return
     */    
	public int getId() {
		return id;
	}
	
	public ArrayList<Troncon> getTronconsSortants(){
		return tronconsSortants;
	}
	
	public void addTronconSortant(Troncon troncon) {
		tronconsSortants.add(troncon);
	}
	
	public void addTronconSortants(ArrayList<Troncon> troncons) {
		tronconsSortants = troncons;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(!(arg0 instanceof Noeud)) return false;
		Noeud p = (Noeud) arg0;
		if(p.x == x && p.y == y) return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + x;
		hash = 89 * hash + y;
		return hash;
	}
	@Override
	public String toString(){
		return "(" + this.x + "," + this.y + ")"; 
	}

}