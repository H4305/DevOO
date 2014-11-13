package util;

import java.util.ArrayList;

import model.data.Noeud;
import model.data.Troncon;

public class Vertex implements Comparable<Vertex> {

    private Noeud pointVertex;
    private double minTemps = Double.POSITIVE_INFINITY;
    private Vertex precedent;
    private ArrayList<Troncon> adjacencies = new ArrayList<Troncon>();
    
    public Vertex(Noeud pointVertex) 
    { 
    	this.pointVertex = pointVertex; 
    }
    
    public Vertex(Noeud pointVertex, ArrayList<Troncon> adjacencies)
    { 
    	this.pointVertex = pointVertex; 
    	this.adjacencies = adjacencies;
    }   
    
    public int compareTo(Vertex autre)
    {
        return Double.compare(minTemps, autre.minTemps);
    }
    
    public Noeud getPoint()
    {
    	return this.pointVertex;
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
    	System.out.println("Je suis dans getAdjacencies !");
    	return this.adjacencies;
    }
    
    public void addTronconSortant(Troncon tronconSortant)
    {
    	this.adjacencies.add(tronconSortant);
    }
}
