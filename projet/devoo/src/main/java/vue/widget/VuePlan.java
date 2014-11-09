package vue.widget;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JPanel;

import util.TwoKeyMap;
import vue.util.AppColors;
import vue.util.ComplexDrawing;
import model.data.Point;
import model.data.Troncon;

public class VuePlan extends JPanel {
	
	private static final int POINT_RADIUS = 5;
	private static final int LINE_WIDTH = 2;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 500;
	
	class TronconCounter{
		Troncon troncon;
		int number = 0;
	}
	
	Collection<Troncon> mTronconsPlan;
	List<Point> mPoints = new ArrayList<Point>();
	List<Troncon> mTronconsItineraire;
	TwoKeyMap<Point, Point, TronconCounter> displayedTroncon = 
			new TwoKeyMap<Point, Point, VuePlan.TronconCounter>();
	int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
	int	minX= Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
	
	
	
	public VuePlan(Collection<Troncon> troncons) {
		mTronconsPlan = troncons;
		
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		setBackground(Color.GREEN);
		
		
		// Find max and min for scale
		for(Troncon troncon : mTronconsPlan) {
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
			
//			
//			if(displayedTroncon.containsKeys(depart, arrivee ||
//					displayedTroncon.containsKeys(arrivee, key2)
		}
		
//		for(Troncon troncon : mTronconsItineraire) {
//			
//		}
		
		setBackground(Color.GREEN);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(AppColors.primary0);
		g.fillRect(0, 0, getWidth(), getHeight());
		for(Troncon troncon : mTronconsPlan) {
			drawTroncon(troncon, g);
			drawDirectedTroncon(troncon, g);
			
		}
	}
	
	private java.awt.Point convertCoordinate(int x, int y) {
		int convertedX, convertedY;
		convertedX =  new Float((x - minX) *  (float) getWidth()/(float) (maxX - minX)).intValue();
		convertedY =  new Float((y - minY) *  (float) getHeight()/(float) (maxY - minY)).intValue();
		return new java.awt.Point(convertedX, convertedY);
	}
	
	private void drawPoint(java.awt.Point point, Graphics g) {
		g.setColor(AppColors.fourth0);
		g.fillOval(
				point.x - POINT_RADIUS/2,
				point.y - POINT_RADIUS/2, 
				POINT_RADIUS, 
				POINT_RADIUS);
	}
	
	private void drawLine(java.awt.Point a, java.awt.Point b, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(LINE_WIDTH));
		g2.setColor(AppColors.third0);
		g2.drawLine(a.x,a.y, b.x, b.y);
	}
	
	private void drawTroncon(Troncon troncon, Graphics g) {
		java.awt.Point pointA, pointB;
		pointA = convertCoordinate(troncon.getDepart().x, troncon.getDepart().y);
		pointB = convertCoordinate(troncon.getArrivee().x, troncon.getArrivee().y);
		drawLine(pointA, pointB, g);
		drawPoint(pointA, g);
		drawPoint(pointB, g);
	}
	
	private void drawDirectedTroncon(Troncon troncon, Graphics g) {
		java.awt.Point pointA, pointB;
		pointA = convertCoordinate(troncon.getDepart().x, troncon.getDepart().y);
		pointB = convertCoordinate(troncon.getArrivee().x, troncon.getArrivee().y);
		g.setColor(Color.red);
		
		ComplexDrawing.drawArrow(g, pointA.x, pointA.y, pointB.x, pointB.y, LINE_WIDTH, AppColors.third2);
		
	}
	
}
