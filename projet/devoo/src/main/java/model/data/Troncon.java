package model.data;

import util.Vertex;

/**
 * 
 * Troncon is the link between two points, a street
 * It has a name, a velocity, a length and an orientation 
 * 
 * @author      Vadim Caen
 * @author      Maria Etegan
 * @author      Anthony Faraut
 * @author      Ludmila Danilescu
 * @author      Marco Montalto
 * @author      Bernardo Rittmeyer
 * 
 */
public class Troncon {

    private Vertex target;

    private String nomRue;

    private float vitesse;

    private float longueur;

    private Noeud depart;

    private Noeud arrivee;

   
    public Troncon() {
    }
    /**
     * Classe constructor specifying its attributes 
     * 
     * @param nomRue is the name of the street.
     * @param vitesse is the velocity  
     * @param longueur is the distance between the points 
     * @param pointDepart is the start point of the street   
     */
    public Troncon(String nomRue, float vitesse, float longueur, Noeud pointDepart) {
    	this.nomRue = nomRue;
    	this.vitesse = vitesse;
    	this.longueur = longueur;
    	this.depart = pointDepart;
    }
    /**
     * Classe constructor specifying its attributes 
     * 
     * @param nomRue is the name of the street.
     * @param vitesse is the velocity  
     * @param longueur is the distance between the points 
     * @param pointDepart is the start point of the street  
     * @param pointArrivee is the end point of the street  
     */
    public Troncon(String nomRue, float vitesse, float longueur, Noeud pointDepart, Noeud pointArrivee) {
    	this.nomRue = nomRue;
    	this.vitesse = vitesse;
    	this.longueur = longueur;
    	this.depart = pointDepart;
    	this.arrivee = pointArrivee;
    }
   

    public String getNomRue() {
		return nomRue;
	}

	public float getVitesse() {
		return vitesse;
	}

	public float getLongueur() {
		return longueur;
	}
	/**
	 * 
	 * @return the time 
	 */
	public float getDuree(){
		return (float)longueur/vitesse;
	}
	
    public Noeud getDepart() {
        return depart;
    }

    public Noeud getArrivee() {
       return arrivee;
    }
    
    public String toString()
	{
		String s;
		s = this.nomRue;
		return s;
	}

	public Vertex getTarget() {
		return this.target;
	}
    
	public void setTarget(Vertex target) {
		this.target = target;
	}    
	
}