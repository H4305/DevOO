package model.data;

/**
 * 
 */
public class Troncon {

    
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
    private Point depart;

    /**
     * 
     */
    private Point arrivee;

	/**
     * 
     */
    public Troncon() {
    }

    public Troncon(String nomRue, float vitesse, float longueur, Point pointDepart) {
    	this.nomRue = nomRue;
    	this.vitesse = vitesse;
    	this.longueur = longueur;
    	this.depart = pointDepart;
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
    
    public void setArrivee(Point pointArrivee) {
    	this.arrivee = pointArrivee;
    }

}