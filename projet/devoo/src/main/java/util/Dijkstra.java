package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import model.data.Troncon;

public class Dijkstra {
	
	/**
	 * 
	 * @param source (Vertex)
	 */
	public void computePaths(Vertex source)
    {
        source.setMinTemps(0.);
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      	vertexQueue.add(source);
      	
		while (!vertexQueue.isEmpty()) {
			
		    Vertex u = vertexQueue.poll();	
		    
		    for (Troncon t : u.getAdjacencies())
	        {
	        	
	        	Vertex v = t.getTarget();
	            float temps = t.getDuree();
	            float tempsThroughU = (float)(u.getMinTemps() + temps);
	            
	            if (tempsThroughU < v.getMinTemps()) {
	            	
				    vertexQueue.remove(v);
				    v.setMinTemps(tempsThroughU);
				    v.setPrecedent(u);		
				    vertexQueue.add(v);
				    
				}
	        }
	    }
		
    }
	
	/**
	 * 
	 * @param target (Vertex)
	 * @return list of vertex -> represents the shortest path to target
	 */
    public ArrayList<Vertex> getShortestPathTo(Vertex target)
    {
        ArrayList<Vertex> path = new ArrayList<Vertex>();
        
        for (Vertex vertex = target; vertex != null; vertex = vertex.getPrecedent())
        {
        	path.add(vertex);
        }
        
        Collections.reverse(path);
        return path;
    }

}
