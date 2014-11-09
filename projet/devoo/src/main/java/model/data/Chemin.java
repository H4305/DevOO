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
    private Point depart;

	private List<Troncon> troncon;

	public Chemin(Point arrivee, Point depart, List<Troncon> troncon) {
		super();
		this.arrivee = arrivee;
		this.depart = depart;
		this.troncon = troncon;
	}
	
	
	public List<Troncon> getTroncons() { return troncon; };

  

 

}