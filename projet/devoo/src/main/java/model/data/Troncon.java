package model.data;

/**
 * 
 */
public class Troncon {

    /**
     * 
     */
    public Troncon() {
    }

    public Troncon(String nomRue, float vitesse, float longueur, Point pointDestination) {
    	this.nomRue = nomRue;
    	this.vitesse = vitesse;
    	this.longueur = longueur;
    	this.pointDestination = pointDestination;
    }
    
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
    private Point pointDestination;
    
    /**
     * 
     */
    private Point[] depart;

    /**
     * 
     */
    private Point[] arrivee;

    /**
     * 
     */
    public void getDepart() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getArrivee() {
        // TODO implement here
    }

}