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

import model.data.DemandeLivraisons;
import model.data.Itineraire;
import model.data.Livraison;
import model.data.Noeud;
import model.data.PlageHoraire;
import model.data.Troncon;
import vue.VueItineraire;
import vue.VuePoint;
import vue.VuePoint.Shape;
import vue.VueTroncon;
import vue.util.AppColors;
import vue.util.CoordinateConverter;
import java.awt.SystemColor;

public class PlanPanel extends JPanel {
;

	private static final long serialVersionUID = 5098859015968622126L;
	
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 500;
	private static final int MARGIN = 20;

	private final static Logger LOGGER = Logger.getLogger(PlanPanel.class
			.getName());


	Collection<Troncon> mTronconsPlan;
	DemandeLivraisons demandeLivraisons;
	VueItineraire mItineraire;
	int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
	int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
	
	Set<VuePoint> vuesPoints = new HashSet<VuePoint>();
	Set<VueTroncon> vuesTroncon = new HashSet<VueTroncon>();
	PointClickedListener pointClickedListener;
	
	VuePoint lastSelectedPoint;

	boolean displayItineraire = false;

	/**
	 * Constructor 
	 * @param troncons : The collection of the troncon
	 */
	public PlanPanel(Collection<Troncon> troncons) {
		mTronconsPlan = troncons;

		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		setBackground(SystemColor.inactiveCaption);
		addMouseListener(new MouseActionListener());

		setPlan(new HashSet<Troncon>(troncons));
	}
	
	/**
	 * This method allows to set the Plan with a set of troncons 
	 * @param troncons : The set of Troncons
	 */
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
			Noeud arrivee = troncon.getArrivee();
			Noeud depart = troncon.getDepart();

			maxX = Math.max(maxX, arrivee.getX());
			maxX = Math.max(maxX, depart.getX());
			maxY = Math.max(maxY, arrivee.getY());
			maxY = Math.max(maxY, depart.getY());

			minX = Math.min(minX, arrivee.getX());
			minX = Math.min(minX, depart.getX());
			minY = Math.min(minY, arrivee.getY());
			minY = Math.min(minY, depart.getY());

			vuesPoints.add(new VuePoint(depart));
			vuesPoints.add(new VuePoint(arrivee));
			vuesTroncon.add(new VueTroncon(troncon));
		}
	}

	/**
	 * This method allows to set the Itineraire
	 * @param itineraire : The itineraire we want to set
	 */
	public void setItineraire(Itineraire itineraire) {
		if (itineraire == null) return;
		mItineraire = new VueItineraire(itineraire);
	}
	
	/**
	 * This methods allwos to set the demande of the Livraisons
	 * @param demandeLivraisons : The demandeLivraison object
	 */
	public void setDemandeLivraisons(DemandeLivraisons demandeLivraisons) {
		
		for (VuePoint vuePoint : vuesPoints) {
			vuePoint.setColor(AppColors.normalPoint);
			vuePoint.setShape(Shape.CIRCLE);
		}
		
		this.demandeLivraisons = demandeLivraisons;
		int colorIndex = 0;
		for (PlageHoraire plage : demandeLivraisons.getPlagesHoraire()) {
			Color color = AppColors.plageHoraire[colorIndex++];
			for(Livraison livraison : plage.getLivraisons()) {
				for(VuePoint vuePoint : vuesPoints) {
					if(vuePoint.getPoint().equals(livraison.getAdresse())) {
						vuePoint.setColor(color);
					} else if(vuePoint.getPoint().equals(demandeLivraisons.getEntrepot())) {
						vuePoint.setColor(AppColors.entrepot);
						vuePoint.setShape(Shape.SQUARE);
						vuePoint.setIsSelectable(false);
					}
					
				}
			}
		}
	}
	
	/**
	 * This method allows to set the point clicked listenr
	 * @param pointClickedListener
	 */
	public void setPointClickedListener(
			PointClickedListener pointClickedListener) {
		this.pointClickedListener = pointClickedListener;
	}
	
	/**
	 * This method allows to display the Itineraire
	 */
	public void afficherItineraire() {
		if(mItineraire == null) {
			LOGGER.log(Level.WARNING, "Aucun itineraire n'a �t� ajout� � ce plan. "
					+ "Utilsez addItineraire pour en ajouter un.");
			return;
		}
		displayItineraire = true;
		repaint();
	}
	
	/**
	 * This method allows to display the asking Livraison
	 */
	public void afficherDemandeLivraison() {
		repaint();
	}
	
	/**
	 * This method allows to hide the Itineraire
	 */
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
			mItineraire.draw(g, new Converter());
		}
	}

	/**
	 * This method allows to draw a Plan
	 * @param g : The Graphics object
	 */
	private void drawPlan(Graphics g) {
		// Affiche le plan
		for (VueTroncon troncon : vuesTroncon) {
			troncon.draw(g, new Converter());
		}
		for(VuePoint point : vuesPoints) {
			point.draw(g, new Converter());
		}
	}
	/**
	 * This method allows to know if the panel has a plan
	 * @return The boolean
	 */
	public boolean hasPlan() {
		return !mTronconsPlan.isEmpty();
	}
	
	/**
	 * The method allows to know if the panel has an Itineraire
	 * @return The boolean
	 */
	public boolean hasItienraire() {
		return displayItineraire;
	}
	
	/**
	 * Internal class
	 */
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
	
	/**
	 * Interface
	 */
	public interface PointClickedListener {
		public void pointClicked(Noeud point);
	}
	
	/**
	 * Internal class
	 */
	private class MouseActionListener implements MouseListener {
		
		boolean selectedPoint = false;
		

		@Override
		public void mouseClicked(MouseEvent arg0){			
			
			LOGGER.log(Level.INFO, "Plan clicked");
			if(pointClickedListener != null) {
				selectedPoint = false;
				for(VuePoint vuePoint : vuesPoints) {

					if(!selectedPoint 
							&& vuePoint.isSelectable()
							&& vuePoint.isClicked(new java.awt.Point(arg0.getX(), arg0.getY()))) {
						vuePoint.mouseDown(arg0);
						vuePoint.setSelected(true);
						selectedPoint = true;
						lastSelectedPoint = vuePoint;
					} else {
						vuePoint.setSelected(false);
					}
				}
				
				repaint();
				if(!selectedPoint && lastSelectedPoint != null) {
					lastSelectedPoint.setSelected(true);
				} else if (selectedPoint){
					pointClickedListener.pointClicked(lastSelectedPoint.getPoint());
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
			
		}
		
	}
	
}
