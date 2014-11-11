package util;

import java.util.ArrayList;

import model.data.Troncon;

public class Vertex implements Comparable<Vertex> {

    private Point point;
    private ArrayList<Troncon> adjacencies;
    private double minTemps = Double.POSITIVE_INFINITY;
    private Vertex precedent;
    
    public Vertex(Point argPoint) 
    { 
    	this.point = argPoint;
    	this.adjacencies = this.point.getTronconsSortants();
    }
    
    public int compareTo(Vertex autre)
    {
        return Double.compare(getMinTemps(), autre.getMinTemps());
    }

	public Point getPoint() {
		return point;
	}

	public Vertex getPrecedent() {
		return this.precedent;
	}

	public void setPrecedent(Vertex precedent) {
		this.precedent = precedent;
	}

	public ArrayList<Troncon> getAdjacencies() {
		return adjacencies;
	}

	public double getMinTemps() {
		return minTemps;
	}

	public void setMinTemps(double minTemps) {
		this.minTemps = minTemps;
	}


}
