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
	private Noeud arrivee;
	private Noeud depart;
	private ArrayList<Troncon> troncon;
	private float tempsParcours;

	public Chemin(Noeud arrivee, Noeud depart, ArrayList<Troncon> argTroncons) {
		super();
		this.arrivee = arrivee;
		this.depart = depart;
		this.troncon = argTroncons;
	}
	
	
	public Chemin(Noeud arrivee, Noeud depart) {
		super();
		this.arrivee = arrivee;
		this.depart = depart;
		this.troncon = new ArrayList<Troncon>();
	}

	/**
     * 
     */
	public Noeud getDepart() {
		return depart;
	}

	/**
     * 
     */
	public Noeud getArrivee() {
		return arrivee;
	}

	/**
     * 
     */
	public void setArrivee(Noeud pointArrivee) {
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
		s = "Chemin : depart de " + this.depart.toString() + ", arrivee � " + this.arrivee.toString() + ", en passant par les rues ("+ troncon.size() +") : "; 
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