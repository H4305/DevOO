package util;
import model.data.Point;
import model.data.Troncon;
import java.util.ArrayList;

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
