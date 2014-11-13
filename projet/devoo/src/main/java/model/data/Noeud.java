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
    private List<Troncon> tronconsSortants;
    
    /**
     * 
     */
    private int x;

    /**
     * 
     */
    private int y;

    
    /**
     * 
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
	
<<<<<<< HEAD
	public List<Troncon> getTronconsSortants(){
		return tronconsSortants;
=======
	public ArrayList<Troncon> getTronconsSortants(){
		return this.tronconsSortants;
>>>>>>> 57675473e37e9ff9b2d1e8015daf9c8b922cbd3f
	}
	
	public void addTronconSortant(Troncon troncon) {
		this.tronconsSortants.add(troncon);
	}
	
<<<<<<< HEAD
	public void addTronconSortants(List<Troncon> troncons) {
		tronconsSortants = troncons;
=======
	public void addTronconSortants(ArrayList<Troncon> troncons) {
		this.tronconsSortants = troncons;
>>>>>>> 57675473e37e9ff9b2d1e8015daf9c8b922cbd3f
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

	public void setIsLivraison(boolean bool) {
		isLivraison = bool;
	}
	
	public boolean isLivraison(){
		return isLivraison;
	}
	
	public Livraison getLivraison() {
		return livraison;
	}
	
	public void setLivraison (Livraison livraison) {
		this.livraison = livraison;
	}
}