package vue;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.data.Livraison;
import model.data.PlageHoraire;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.Point;

public class VueLivraison extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5815570766714741539L;
	private Livraison mLivraison;
	private JLabel lblAdresse, lblLivraisonId, lblPlagehoraire, lblHoraireLivraison;

	/**
	 * Create the panel.
	 */
	public VueLivraison(PlageHoraire plageHoraire, Livraison livraison) {
		this();
		setLivraison(plageHoraire, livraison);
	}
	
	public VueLivraison() {
		
		Border loweredetched;
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder title;
		title = BorderFactory.createTitledBorder(loweredetched,
				"Information supplémentaire");
		title.setTitleJustification(TitledBorder.RIGHT);
		title.setBorder(loweredetched);
		title.setTitleFont(new Font(" Verdana", Font.PLAIN , 16));
		setBorder(title);
		setPreferredSize(new Dimension(280, 150));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		lblAdresse = new JLabel("Adresse : ");
		panel.add(lblAdresse);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		add(panel);

		JPanel panel_1 = new JPanel();		
		lblLivraisonId = new JLabel("Livraison : ");
		panel_1.add(lblLivraisonId);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		lblPlagehoraire = new JLabel("PlageHoraire : ");
		panel_2.add(lblPlagehoraire);
		add(panel_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		lblHoraireLivraison = new JLabel("Horaire Livraison pr\u00E9vu : ");
		panel_4.add(lblHoraireLivraison);
		add(panel_4);
	}
	
	public void setLivraison(PlageHoraire plageHoraire, Livraison livraison) {
		mLivraison = livraison;

		lblHoraireLivraison.setText("Horaire Livraison pr\u00E9vu : " + livraison.getHeureLivraison());
		lblPlagehoraire.setText("PlageHoraire : " + plageHoraire.getDateDebut() +  " - "  + plageHoraire.getDateFin());
		lblLivraisonId.setText("Livraison : " + String.valueOf(livraison.getId()));
		lblAdresse.setText("Adresse : " + String.valueOf(livraison.getAdresse().getId()));
	}
	
	public void resetInfoLivraison() {
		mLivraison = null;

		lblHoraireLivraison.setText("");
		lblPlagehoraire.setText("");
		lblLivraisonId.setText("");
		lblAdresse.setText("");
	}
/**
 * 
 * @return
 */
	public JLabel getLblLivraisonContent() {
		return lblLivraisonId;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getLblHoraireContent() {
		return lblHoraireLivraison;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getLblPlageHoraireContent() {
		return lblPlagehoraire;
	}
	/**

	 * 
	 * @return
	 */
	public JLabel getLblAdresseContent() {
		return lblAdresse;
	}
	/**
	 * 
	 * @return
	 */
	public Livraison getLivraison() {
		return mLivraison;
	}
}
