package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import model.data.Troncon;

public class Dijkstra {
	
	public Dijkstra() {
    }
	
	public void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      	vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
		    Vertex u = vertexQueue.poll();
	
	    	ArrayList<Troncon> adjacencies = u.point.getTronconsSortants();
	        // Visit each edge exiting u
	        for (Troncon t : adjacencies)
	        {
	            Vertex v = new Vertex (t.getArrivee());
	            float temps = t.getDuree();
	            float distanceThroughU = (float)(u.minDistance + temps);
				if (distanceThroughU < v.minDistance) {
				    vertexQueue.remove(v);
				    v.minDistance = distanceThroughU ;
				    v.precedent = u;
				    vertexQueue.add(v);
				}
	        }
	    }
    }

    public List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.precedent)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }

}
