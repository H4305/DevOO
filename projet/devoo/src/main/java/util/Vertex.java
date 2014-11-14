package util;

import java.util.ArrayList;
import java.util.List;

import model.data.Noeud;
import model.data.Troncon;

/**
 * 
 * @author Liuda
 * 
 *
 */
public class Vertex implements Comparable<Vertex> {

    private Noeud pointVertex;
    private double minTemps = Double.POSITIVE_INFINITY;
    private Vertex precedent;
    private List<Troncon> adjacencies = new ArrayList<Troncon>();
    
    /**
     * Constructor
     * 
     * @param pointVertex
     */
    public Vertex(Noeud pointVertex) 
    { 
    	this.pointVertex = pointVertex; 
    }
    
    /**
     * Constructor
     * 
     * @param pointVertex
     * @param adjacencies
     */
    public Vertex(Noeud pointVertex, List<Troncon> adjacencies)
    { 
    	this.pointVertex = pointVertex; 
    	this.adjacencies = adjacencies;
    }   
    
    /**
     * Method of interface Comparable
     * useful for implements the priority queue
     */
    public int compareTo(Vertex autre)
    {
        return Double.compare(minTemps, autre.getMinTemps());
    }
    
    /**
     * 
     * @return Noeud
     */
    public Noeud getPoint()
    {
    	return this.pointVertex;
    }
    
    /**
     * 
     * @return  minimum duration
     */
    public double getMinTemps()
    {
    	return minTemps;
    }
    
    /**
     * 
     * @param minimum duration
     */
    public void setMinTemps(double minTemps)
    {
    	this.minTemps = minTemps;
    }
    
    /**
     * 
     * @return vertex
     */
    public Vertex getPrecedent()
    {
    	return precedent;
    }
    
    /**
     * 
     * @param precedent vertex
     */
    public void setPrecedent(Vertex precedent)
    {
    	this.precedent = precedent;
    }
    
    /**
     * 
     * @param adjacencies 
     */
    public void setAdjacencies(ArrayList<Troncon> adjacencies)
    {
    	this.adjacencies = adjacencies;
    }
      
    /**
     * 
     * @return list of adjacencies of a vertex
     */
    public List<Troncon> getAdjacencies()
    {
    	return this.adjacencies;
    }
    
    /**
     * 
     * @param tronconSortant
     */
    public void addTronconSortant(Troncon tronconSortant)
    {
    	this.adjacencies.add(tronconSortant);
    }
}
