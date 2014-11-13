package vue;

import java.awt.Graphics;
import java.util.List;

import model.data.Chemin;
import model.data.Itineraire;
import vue.util.CoordinateConverter;
import vue.util.Vue;

public class VueItineraire extends Vue {

	Itineraire mItineraire;
	
	public VueItineraire(Itineraire mItineraire) {
		super();
		this.mItineraire = mItineraire;
	}

	@Override
	public void draw(Graphics g, CoordinateConverter converter) {
		
		if(mItineraire.getChemins() != null) {
			for(Chemin chemin : mItineraire.getChemins()) {
				new VueChemin(chemin).draw(g, converter);
			}
		}
	}
	
	
}
