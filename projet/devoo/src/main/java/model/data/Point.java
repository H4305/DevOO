package model.data;

import java.util.*;

/**
 * 
 */
public class Point {

    /**
     * 
     */
    public Point() {
    }
    
    /**
     * 
     * @param id
     * @param x
     * @param y
     */
    public Point (int id, int x, int y){
    	this.id = id;
    	this.x = x;
    	this.y = y;
    }

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    public int x;

    /**
     * 
     */
    public int y;

    /**
     * 
     * @return
     */
	public int getId() {
		return id;
	}


}