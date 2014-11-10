package model.data;

import java.util.List;
import java.util.Set;

/**
 * 
 */
public class Chemin {

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

	private Point depart;

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
	public void setArrivee(Point pointArrivee) {
		this.arrivee = pointArrivee;
	}
	
	public List<Troncon> getTroncons() {
		return troncon;
	}

}