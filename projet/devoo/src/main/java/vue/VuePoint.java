package vue;

import java.awt.Color;
import java.awt.Graphics;

import model.data.Noeud;
import vue.util.AppColors;
import vue.util.CoordinateConverter;
import vue.util.Vue;

public class VuePoint extends Vue{
	
	
	private static final int POINT_RADIUS = 10;
	private static final int POINT_SELECTED_RADIUS = 15;
	
	/**
	 * 
	 * This enum represent the shapes that a {@link VuePoint}
	 * can have.
	 *
	 */
	public enum Shape {
		CIRCLE,
		SQUARE,
		CROSS
	};
	
	
	
	Noeud pointModel;
	java.awt.Point pointVue;
	
	boolean selected = false;

	Color color = AppColors.normalPoint;
	private Shape shape;
	private boolean selectable = true;
	public VuePoint(Noeud pointModel) {
		super();
		this.pointModel = pointModel;
	}
	
	
	@Override
	public void draw(Graphics g, CoordinateConverter converter) {
		this.pointVue = converter.convert(pointModel.x, pointModel.y);
		if(selected) {
			g.setColor(AppColors.selectedPointBorder);
			g.fillOval(pointVue.x - POINT_SELECTED_RADIUS / 2, pointVue.y - POINT_SELECTED_RADIUS / 2,
					POINT_SELECTED_RADIUS, POINT_SELECTED_RADIUS);
		}
			g.setColor(color);
			
		if(shape != null) {
			switch (shape) {
			case SQUARE:
				int size = Math.round(POINT_RADIUS * 1.25f);
				g.fillRect(pointVue.x - size / 2, pointVue.y - size / 2,
						size, size);
				break;

			default:
				g.fillOval(pointVue.x - POINT_RADIUS / 2, pointVue.y - POINT_RADIUS / 2 ,
						POINT_RADIUS, POINT_RADIUS);
				break;
			}
			
		} else {
			g.fillOval(pointVue.x - POINT_RADIUS / 2, pointVue.y - POINT_RADIUS / 2 ,
					POINT_RADIUS, POINT_RADIUS);
		}
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 == this) return true;
		if(!(arg0 instanceof VuePoint)) return false;
		VuePoint vuePoint = (VuePoint) arg0;
		return pointModel.equals(vuePoint.pointModel);
	}

	/**
	 * @return theCoordinate where the point is displayed.
	 */
	public java.awt.Point getGemometricPoint() { return pointVue; }
	
	/**
	 * @return the data point represented by this view
	 */
	public Noeud getPoint() { return pointModel;}
	
	@Override
	public int hashCode() {
		return pointModel.hashCode() * 89;
	}
	
	public boolean isClicked(java.awt.Point pointClicked) {
		return pointVue.distance(pointClicked) < POINT_RADIUS;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Set the {@link Shape} of the point.
	 * @param shape The desired shape.
	 * @see Shape.
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	/**
	 * Set this {@link VuePoint} as selected.
	 * @param selected <code>true</code> to select the {@link VuePoint}.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 *  Set whether this {@link VuePoint} is can be selected or not.
	 * @param b True if the {@link VuePoint} can be selected. This is the default value.
	 */
	public void setIsSelectable(boolean b) {
		selectable = false;
	}
	
	/**
	 * Check whether this {@link VuePoint} is can be selected or not.
	 * @return true if the {@link VuePoint} can be selected.
	 */
	public boolean isSelectable() {
		return selectable;
	}
	
	
}
