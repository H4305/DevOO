package vue.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import model.data.Chemin;
import model.data.Point;
import model.data.Troncon;
import vue.VueChemin;
import vue.VuePoint;
import vue.VueTroncon;
import vue.util.AppColors;
import vue.util.CoordinateConverter;

public class PlanPanel extends JPanel {
;

	private static final long serialVersionUID = 5098859015968622126L;
	
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 500;
	private static final int MARGIN = 20;

	private final static Logger LOGGER = Logger.getLogger(PlanPanel.class
			.getName());


	Collection<Troncon> mTronconsPlan;
	VueChemin mChemin;
	int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
	int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
	
	Set<VuePoint> vuesPoints = new HashSet<VuePoint>();
	Set<VueTroncon> vuesTroncon = new HashSet<VueTroncon>();
	PointClickedListener pointClickedListener;

	boolean displayItineraire = false;

	public PlanPanel(Collection<Troncon> troncons) {
		mTronconsPlan = troncons;

		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		setBackground(Color.GREEN);
		addMouseListener(new MouseActionListener());

		setPlan(new HashSet<Troncon>(troncons));

	}
	
	public void setPlan(Set<Troncon> troncons) {
		mTronconsPlan = troncons;
		vuesPoints.clear();
		vuesTroncon.clear();
		
		maxX = Integer.MIN_VALUE;
		maxY = Integer.MIN_VALUE;
		minX = Integer.MAX_VALUE;
		minY = Integer.MAX_VALUE;
		
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
	
	public void setPointClickedListener(
			PointClickedListener pointClickedListener) {
		this.pointClickedListener = pointClickedListener;
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
			convertedX = new Float((x - minX)
					* (float) (getWidth() - MARGIN * 2) / (float) (maxX - minX)) 
					.intValue() + MARGIN;
			convertedY = new Float((y - minY)
					* (float) (getHeight() - MARGIN * 2) / (float) (maxY - minY))
					.intValue() + + MARGIN;
			return new java.awt.Point(convertedX, convertedY);
		}
	}
	
	public interface PointClickedListener {
		public void pointClicked(Point point);
	}
	
	private class MouseActionListener implements MouseListener {
		
		VuePoint lastPoint;

		@Override
		public void mouseClicked(MouseEvent arg0){
			LOGGER.log(Level.INFO, "Plan clicked");
			if(pointClickedListener != null) {
				for(VuePoint vuePoint : vuesPoints) {
					lastPoint = vuePoint;
					if(vuePoint.isClicked(new java.awt.Point(arg0.getX(), arg0.getY()))) {
						vuePoint.mouseDown(arg0);
						pointClickedListener.pointClicked(vuePoint.getPoint());
					}
				}
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if(lastPoint != null) {
				lastPoint.mouseUp(arg0);
			}
			
		}
		
	}
	
}
