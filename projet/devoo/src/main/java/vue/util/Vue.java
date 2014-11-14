package vue.util;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class  Vue {
	
	public enum Statut {
		NONE,
		CLICKED,
		SELECTIONED
	};
	
	private Statut statut = Statut.NONE;
	
	/**
	 * Draw on this {@link Vue} {@link Graphics} given in param.
	 * @param g The {@link Graphics} to draw on.
	 * @param converter a {@link CoordinateConverter} used to convert value from the model
	 * 		to the view.
	 * 
	 * @see CoordinateConverter
	 */
	public abstract void draw(Graphics g, CoordinateConverter converter);
	
	/**
	 * Method for the mouse down 
	 * @param event MouseEvent
	 */
	public void mouseDown(MouseEvent event) {
		statut = Statut.CLICKED;
	}
	
	/**
	 * Method for the mouse up 
	 * @param event MouseEvent
	 */
	public void mouseUp(MouseEvent event) {
		statut = Statut.NONE;
	}
	
	/**
	 * This method allows to get the status of the View
	 * @return The Status
	 */
	public Statut getStatut() {
		return statut;
	}

}
