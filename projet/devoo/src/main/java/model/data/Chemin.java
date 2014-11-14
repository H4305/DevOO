package model.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 
 */
public class Chemin {

	private Noeud arrivee;
	private Noeud depart;
	private ArrayList<Troncon> troncon;
	private float tempsParcours;

	/**
	 * Constructor of the Chemin's class 
	 * @param arrivee : The final node for the end of the way
	 * @param depart : The start node for the start of the way
	 * @param argTroncons : A list of the "troncons" who are in the "Chemin"
	 */
	public Chemin(Noeud arrivee, Noeud depart, ArrayList<Troncon> argTroncons) {
		super();
		this.arrivee = arrivee;
		this.depart = depart;
		this.troncon = argTroncons;
	}
	
	/**
	 * Constructor of the Chemin's class 
	 * @param arrivee : The final node for the end of the way
	 * @param depart : The start node for the start of the way
	 */
	public Chemin(Noeud arrivee, Noeud depart) {
		super();
		this.arrivee = arrivee;
		this.depart = depart;
		this.troncon = new ArrayList<Troncon>();
	}

	/**
     * This method allows to get the start node of the way
     * @return The node (Object Noeud)
     */
	public Noeud getDepart() {
		return depart;
	}

	/**
     * This method allows to get the end node of the way
     * @return The node (Object Noeud)
     */
	public Noeud getArrivee() {
		return arrivee;
	}

	/**
     * This method allows to set the end node of the way
     * @param The node (Object Noeud)
     */
	public void setArrivee(Noeud pointArrivee) {
		this.arrivee = pointArrivee;
	}
	
	/**
     * This method allows to get all the "Troncons" who are in the "Chemin"
     * @return The list of the "Troncons"
     */
	public ArrayList<Troncon> getTroncons() {
		return troncon;
	}
	
	/**
     * This method allows to add a "Troncon" into the existing list of "Troncon"
     * @param troncon : The troncon who we want to add
     */
	public void addTronconChemin(Troncon troncon) {
		this.troncon.add(troncon);
	}
	
	/**
     * This method is the implementation of the toString method
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

	/**
	 * This method allows to get the time of the way    
	 * @return The float representing the time
	 */
	public float getTempsParcours() {
		return tempsParcours;
	}

	/**
	 * This method allows to set the time of the way
	 * @param tempsParcours : The time in float
	 */
	public void setTempsParcours(float tempsParcours) {
		this.tempsParcours = tempsParcours;
	}
}