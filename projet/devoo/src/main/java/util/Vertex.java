package util;

import java.util.ArrayList;

import model.data.Troncon;

public class Vertex implements Comparable<Vertex> {

    private Integer id;
    private double minTemps = Double.POSITIVE_INFINITY;
    private Vertex precedent;
    private ArrayList<Troncon> adjacencies;
    
    public Vertex(Integer id, ArrayList<Troncon> adjacencies) 
    { 
    	this.id = id; 
    	this.adjacencies = adjacencies;
    }
    
    
    public int compareTo(Vertex autre)
    {
        return Double.compare(minTemps, autre.minTemps);
    }
    
    public Integer getId()
    {
    	return this.id;
    }
    
    public double getMinTemps()
    {
    	return minTemps;
    }
    
    public void setMinTemps(double minTemps)
    {
    	this.minTemps = minTemps;
    }
    
    public Vertex getPrecedent()
    {
    	return precedent;
    }
    
    public void setPrecedent(Vertex precedent)
    {
    	this.precedent = precedent;
    }
    
    public void setAdjacencies(ArrayList<Troncon> adjacencies)
    {
    	this.adjacencies = adjacencies;
    }
    
    public ArrayList<Troncon> getAdjacencies()
    {
    	return adjacencies;
    }
}
