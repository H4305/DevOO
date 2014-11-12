package model.data;

import util.Vertex;

/**
 * 
 */
public class Troncon {

	/**
     * 
     */
    private Vertex target;

	/**
     * 
     */
    private String nomRue;

    /**
     * 
     */
    private float vitesse;

    /**
     * 
     */
    private float longueur;

    
    /**
     * 
     */
    private Noeud depart;

    /**
     * 
     */
    private Noeud arrivee;

	/**
     * 
     */
    public Troncon() {
    }

    public Troncon(String nomRue, float vitesse, float longueur, Noeud pointDepart) {
    	this.nomRue = nomRue;
    	this.vitesse = vitesse;
    	this.longueur = longueur;
    	this.depart = pointDepart;
    }
    
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

	public float getDuree(){
		return (float)longueur/vitesse;
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
    
	
 /*   public void setArrivee(Point pointArrivee) {
    	this.arrivee = pointArrivee;
    }*/

}