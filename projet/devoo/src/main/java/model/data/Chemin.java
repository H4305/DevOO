package model.data;

import java.util.ArrayList;
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
	private Point depart;
	private ArrayList<Troncon> troncon;
	private float tempsParcours;

	public Chemin(Point arrivee, Point depart, ArrayList<Troncon> argTroncons) {
		super();
		this.arrivee = arrivee;
		this.depart = depart;
		this.troncon = argTroncons;
	}
	
	public Chemin(Point arrivee, Point depart) {
		super();
		this.arrivee = arrivee;
		this.depart = depart;
		this.troncon = new ArrayList<Troncon>();
	}

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
	
	/**
     * 
     */
	public ArrayList<Troncon> getTroncons() {
		return troncon;
	}
	
	/**
     * 
     */
	public void addTronconChemin(Troncon troncon) {
		this.troncon.add(troncon);
	}
	
	/**
     * 
     */
	public String toString()
	{
		String s;
		s = "Chemin : depart de " + this.depart.toString() + ", arrivee à " + this.arrivee.toString() + ", en passant par les rues : "; 
		for(int i = 0; i< this.troncon.size(); i++)
		{
			s = s + this.troncon.get(i).toString() + ", " ;
		}
		return s;
	}

	public float getTempsParcours() {
		return tempsParcours;
	}

	public void setTempsParcours(float tempsParcours) {
		this.tempsParcours = tempsParcours;
	}
	

}