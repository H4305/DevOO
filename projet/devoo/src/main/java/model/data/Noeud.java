package model.data;

import java.util.*;
/**
 * 
 * Noeud is a point of the city map (a street crossroad)
 * It contains the x, y  coordinates in meters 
 * 
 * @author      Vadim Caen
 * @author      Maria Etegan
 * @author      Anthony Faraut
 * @author      Ludmila Danilescu
 * @author      Marco Montalto
 * @author      Bernardo Rittmeyer
 * 
 */

public class Noeud {

    public Noeud() {
    }
    
    /**
     * 
     * @param id of the node
     * @param x the x coordinates of the point in meters 
     * @param y the y coordinates of the point in meters 
     */
    public Noeud (int id, int x, int y) {
    	this.id = id;
    	this.x = x;
    	this.y = y;
    	tronconsSortants = new ArrayList<Troncon>();
    }

    
    private int id;
    
    private ZoneGeographique zone;

    private List<Troncon> tronconsSortants;
    
    private int x;

    private int y;
   
    /**
     * if true the node is the address of a delivery
     */
    private boolean isLivraison;
    
    
    /**
     * Lien bi-directionnel
     */
    private Livraison livraison;
    
    /**
     * 
     * @return
     */    
	public int getId() {
		return id;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public List<Troncon> getTronconsSortants(){
		return tronconsSortants;
	}
	/**
	 * 
	 * @param troncon add a troncon that represents a link to another node 
	 */
	public void addTronconSortant(Troncon troncon) {
		this.tronconsSortants.add(troncon);
	}
	/**
	 * 
	 * @param troncon add a list of troncon that represents a list of links to nodes 
	 */
	public void addTronconSortants(List<Troncon> troncons) {
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
		return "(" + this.id + ")"; 
	}
	/**
	 * 
	 * @param bool is true if the node is the address of a delivery 
	 */
	public void setIsLivraison(boolean bool) {
		isLivraison = bool;
	}
	
	/**
	 * 
	 * @return true if the node is the address of a delivery 
	 */
	public boolean isLivraison(){
		return isLivraison;
	}
	
	/**
	 * 
	 * @return the delivery that has the address this node
	 */
	public Livraison getLivraison() {
		return livraison;
	}
	
	/**
	 * 
	 * @param livraison the delivery that has the address this node
	 */
	public void setLivraison (Livraison livraison) {
		this.livraison = livraison;
	}
}