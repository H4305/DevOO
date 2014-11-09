package vue.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import model.data.Chemin;
import model.data.Point;
import model.data.Troncon;
import util.TwoKeyMap;
import vue.VueChemin;
import vue.VuePoint;
import vue.VueTroncon;
import vue.util.AppColors;
import vue.util.ComplexDrawing;
import vue.util.CoordinateConverter;

public class VuePlan extends JPanel {
;

	private static final long serialVersionUID = 5098859015968622126L;
	
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 500;
	private static final int MARGIN = 20;

	private final static Logger LOGGER = Logger.getLogger(VuePlan.class
			.getName());


	Collection<Troncon> mTronconsPlan;
	VueChemin mChemin;
	int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
	int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
	
	Set<VuePoint> vuesPoints = new HashSet<VuePoint>();
	Set<VueTroncon> vuesTroncon = new HashSet<VueTroncon>();

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

			vuesPoints.add(new VuePoint(depart));
			vuesPoints.add(new VuePoint(arrivee));
			vuesTroncon.add(new VueTroncon(troncon));
		}


	}

	public void setChemin(Chemin itineraire) {
		if (itineraire == null) return;
		mChemin = new VueChemin(itineraire);
	}

	public void afficherItineraire() {
		if(mChemin == null) {
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
			mChemin.draw(g, new Converter());
		}
	}

	private void drawPlan(Graphics g) {
		// Affiche le plan
		for (VueTroncon troncon : vuesTroncon) {
			troncon.draw(g, new Converter());
		}
		for(VuePoint point : vuesPoints) {
			point.draw(g, new Converter());
		}
	}
	
	private class Converter implements CoordinateConverter {

		@Override
		public java.awt.Point convert(int x, int y) {
			int convertedX, convertedY;
			convertedX = new Float((x - minX + MARGIN)
					* (float) (getWidth() - MARGIN * 2) / (float) (maxX - minX))
					.intValue();
			convertedY = new Float((y - minY + MARGIN)
					* (float) (getHeight() - MARGIN * 2) / (float) (maxY - minY))
					.intValue();
			return new java.awt.Point(convertedX, convertedY);
		}
		
	}
}
