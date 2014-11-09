package model.data;

import java.util.List;


/**
 * 
 */
public class Chemin {
	
	   /**
     * 
     */
    private Point arrivee;

    /**
     * 
     */
    private Point arrivee;

	private List<Troncon> troncon;

	public Chemin(Point arrivee, Point depart, List<Troncon> troncon) {
		super();
		this.arrivee = arrivee;
		this.depart = depart;
		this.troncon = troncon;
	}
	
	
	public List<Troncon> getTroncons() { return troncon; };

  

 
    private Point depart;
    
    /**
     * 
     */
    private Set<Troncon> troncons;
    
    
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
    public Set<Troncon> getTroncons() {
       return troncons;
    }
    
    /**
     * 
     */
    public void setArrivee(Point pointArrivee) {
    	this.arrivee = pointArrivee;
    }

}