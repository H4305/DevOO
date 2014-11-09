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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import model.data.Point;
import model.data.Troncon;
import util.TwoKeyMap;
import vue.util.AppColors;
import vue.util.ComplexDrawing;

public class VuePlan extends JPanel {
	
	Color[] colorTroncon = {
			AppColors.itineraire1,
			AppColors.itineraire2,
			AppColors.itineraire3,
			AppColors.itineraire4
	};

	private static final long serialVersionUID = 5098859015968622126L;

	private static final int POINT_RADIUS = 5;
	private static final int LINE_WIDTH = 5;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 500;
	private static final int PARALLELE_LINE_DISTANCE = 5;
	private static final int MARGIN = 20;

	private final static Logger LOGGER = Logger.getLogger(VuePlan.class
			.getName());

	class TronconCounter {
		Troncon troncon;
		int number = 0;
		int displayed = 0;
	}

	Collection<Troncon> mTronconsPlan;
	List<Point> mPoints = new ArrayList<Point>();
	List<Troncon> mTronconsItineraire;
	TwoKeyMap<Point, Point, TronconCounter> displayedTroncon = new TwoKeyMap<Point, Point, VuePlan.TronconCounter>();
	int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
	int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;

	boolean displayItineraire = false;

	public VuePlan(Collection<Troncon> troncons) {
		mTronconsPlan = troncons;

		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		setBackground(Color.GREEN);

		// Find max and min for scale
		for (Troncon troncon : mTronconsPlan) {
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

	public void addItineraire(List<Troncon> itineraire) {
		if (itineraire == null) return;
		mTronconsItineraire = itineraire;
		for (Troncon troncon : mTronconsItineraire) {
			if (displayedTroncon.containsKeys(troncon.getDepart(),
					troncon.getDepart())) {
				displayedTroncon.get(troncon.getDepart(), troncon.getArrivee()).number++;
			} else if (displayedTroncon.containsKeys(troncon.getArrivee(),
					troncon.getArrivee())) {
				displayedTroncon.get(troncon.getArrivee(), troncon.getDepart()).number++;
			} else {
				TronconCounter tronconCounter = new TronconCounter();
				tronconCounter.troncon = troncon;
				displayedTroncon.put(troncon.getDepart(), troncon.getArrivee(),
						tronconCounter);
			}

		}
	}

	public void afficherItineraire() {
		if(mTronconsItineraire == null) {
			LOGGER.log(Level.WARNING, "Aucun itineraire n'a été ajouté à ce plan. "
					+ "Utilsez addItineraire pour en ajouter un.");
			return;
		}
		displayItineraire = true;
		repaint();
	}
	
	public void masquerItineraire() {
		displayItineraire = false;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(AppColors.planBackground);
		g.fillRect(0, 0, getWidth(), getHeight());

		drawPlan(g);
		if (displayItineraire) {
			drawItineraire(g);
		}
	}

	private void drawItineraire(Graphics g) {
		// Affiche l'itineraire
		if (mTronconsItineraire != null) {
			for (Troncon troncon : mTronconsItineraire) {
				TronconCounter counter = null;
				if (displayedTroncon.containsKeys(troncon.getDepart(),
						troncon.getArrivee())) {
					counter = displayedTroncon.get(troncon.getDepart(),
							troncon.getArrivee());
				} else if (displayedTroncon.containsKeys(troncon.getArrivee(),
						troncon.getArrivee())) {
					counter = displayedTroncon.get(troncon.getArrivee(),
							troncon.getDepart());
				}
				if (counter != null) {
					int offset = Math.round(
							(counter.number / 2f + 0.5f) * LINE_WIDTH 
							+ counter.displayed	* PARALLELE_LINE_DISTANCE);
					java.awt.Point point1 = convertCoordinate(
							troncon.getDepart().x, troncon.getDepart().y);
					java.awt.Point point2 = convertCoordinate(
							troncon.getArrivee().x, troncon.getArrivee().y);

					double L = Math.sqrt((point1.x - point2.x)
							* (point1.x - point2.x) + (point1.y - point2.y)
							* (point1.y - point2.y));
					int nx1 = new Double(point1.x + offset
							* (point2.y - point1.y) / L).intValue();
					int nx2 = new Double(point2.x + offset
							* (point2.y - point1.y) / L).intValue();
					int ny1 = new Double(point1.y + offset
							* (point1.x - point2.x) / L).intValue();
					int ny2 = new Double(point2.y + offset
							* (point1.x - point2.x) / L).intValue();
					
					
					g.setColor(colorTroncon[counter.displayed % colorTroncon.length]);
					drawDirectedTroncon(new java.awt.Point(nx1, ny1),
							new java.awt.Point(nx2, ny2), g);
					counter.displayed++;
				} else {
					LOGGER.log(Level.WARNING,
							"Troncon de l'tineraire introuvable dans le plan");
				}
			}
		}
	}

	private void drawPlan(Graphics g) {
		// Affiche le plan
		for (Troncon troncon : mTronconsPlan) {
			drawTroncon(troncon, g);
			drawTroncon(troncon, g);
		}
	}

	private java.awt.Point convertCoordinate(int x, int y) {
		int convertedX, convertedY;
		convertedX = new Float((x - minX + MARGIN)
				* (float) (getWidth() - MARGIN * 2) / (float) (maxX - minX))
				.intValue();
		convertedY = new Float((y - minY + MARGIN)
				* (float) (getHeight() - MARGIN * 2) / (float) (maxY - minY))
				.intValue();
		return new java.awt.Point(convertedX, convertedY);
	}

	private void drawPoint(java.awt.Point point, Graphics g) {
		g.setColor(AppColors.fourth0);
		g.fillOval(point.x - POINT_RADIUS / 2, point.y - POINT_RADIUS / 2,
				POINT_RADIUS, POINT_RADIUS);
	}

	private void drawLine(java.awt.Point a, java.awt.Point b, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(LINE_WIDTH));
		g2.setColor(AppColors.third0);
		g2.drawLine(a.x, a.y, b.x, b.y);
	}

	private void drawTroncon(Troncon troncon, Graphics g) {
		java.awt.Point pointA, pointB;
		pointA = convertCoordinate(troncon.getDepart().x, troncon.getDepart().y);
		pointB = convertCoordinate(troncon.getArrivee().x,
				troncon.getArrivee().y);
		drawLine(pointA, pointB, g);
		drawPoint(pointA, g);
		drawPoint(pointB, g);
	}

	private void drawDirectedTroncon(Troncon troncon, Graphics g) {
		java.awt.Point pointA, pointB;
		pointA = convertCoordinate(troncon.getDepart().x, troncon.getDepart().y);
		pointB = convertCoordinate(troncon.getArrivee().x,
				troncon.getArrivee().y);
		g.setColor(Color.red);

		ComplexDrawing.drawArrow(g, pointA.x, pointA.y, pointB.x, pointB.y,
				LINE_WIDTH / 3, AppColors.third2);

	}

	private void drawDirectedTroncon(java.awt.Point pointA,
			java.awt.Point pointB, Graphics g) {
		ComplexDrawing.drawArrow(g, pointA.x, pointA.y, pointB.x, pointB.y,
				LINE_WIDTH / 3, g.getColor());

	}

}
