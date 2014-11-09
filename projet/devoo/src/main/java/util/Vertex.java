package util;
import model.data.Point;

public class Vertex implements Comparable<Vertex> {

    public Point point;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex precedent;
    public Vertex(Point argPoint) 
    { 
    	point = argPoint; 
    }
    public int compareTo(Vertex autre)
    {
        return Double.compare(minDistance, autre.minDistance);
    }

}
