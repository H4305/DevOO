package vue.widget;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JPanel;

import model.data.Point;
import model.data.Troncon;

public class VuePlan extends JPanel{
	
	Collection<Troncon> mTroncons;
	List<Point> mPoints = new ArrayList<Point>();
	int maxX, maxY, minX, minY;
	
	
	
	public VuePlan(Collection<Troncon> troncons) {
		mTroncons = troncons;
		
		// Find max and min for scale
		for(Troncon troncon : mTroncons) {
			Point arrivee = troncon.getArrivee();
			Point depart = troncon.getDepart();
			
			maxX = Math.max(maxX, arrivee.x);
			maxX = Math.max(maxX, depart.x);
			maxY = Math.max(maxY, arrivee.y);
			maxY = Math.max(maxY, depart.y);
			
			minX = Math.min(minX, arrivee.x);
			minX = Math.min(minX, depart.x);
			minY = Math.min(minY, arrivee.y);
			minY = Math.min(minY, depart.y);
			
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		for(Troncon troncon : mTroncons) {
			
		}
	}
	
}
