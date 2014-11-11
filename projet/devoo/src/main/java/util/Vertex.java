package util;

import java.util.ArrayList;

import model.data.Point;
import model.data.Troncon;

public class Vertex implements Comparable<Vertex> {

    private Point pointVertex;
    private double minTemps = Double.POSITIVE_INFINITY;
    private Vertex precedent;
    private ArrayList<Troncon> adjacencies = new ArrayList<Troncon>();
    
    public Vertex(Point pointVertex) 
    { 
    	this.pointVertex = pointVertex; 
    }
    
    public Vertex(Point pointVertex, ArrayList<Troncon> adjacencies)
    { 
    	this.pointVertex = pointVertex; 
    	this.adjacencies = adjacencies;
    }   
    
    public int compareTo(Vertex autre)
    {
        return Double.compare(minTemps, autre.minTemps);
    }
    
    public Point getPoint()
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
    	return adjacencies;
    }
    
    public void addTronconSortant(Troncon tronconSortant)
    {
    	adjacencies.add(tronconSortant);
    }
}
