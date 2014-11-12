package vue;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import model.data.Livraison;
import model.data.PlageHoraire;
import java.awt.Component;
import java.awt.Dimension;

public class VueLivraison extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5815570766714741539L;
	private JLabel lblLivraisonContent;
	private JLabel lblHoraireContent;
	private JLabel lblPlageHoraireContent;
	private JLabel lblClientContent;
	private JLabel lblAdresseContent;
	private Livraison mLivraison;

	/**
	 * Create the panel.
	 */
	public VueLivraison(PlageHoraire plageHoraire, Livraison livraison) {
		this();
		setLivraison(plageHoraire, livraison);
	}
	
	public VueLivraison() {
		setPreferredSize(new Dimension(384, 206));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JLabel lblAdresse = new JLabel("Adresse : ");
		panel.add(lblAdresse);
		
		lblAdresseContent = new JLabel();
		panel.add(lblAdresseContent);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JLabel lblLivraisonId = new JLabel("Livraison : ");
		panel_1.add(lblLivraisonId);
		
		lblLivraisonContent = new JLabel();
		panel_1.add(lblLivraisonContent);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JLabel lblPlagehoraire = new JLabel("PlageHoraire : ");
		panel_2.add(lblPlagehoraire);
		
		lblPlageHoraireContent = new JLabel();
		panel_2.add(lblPlageHoraireContent);
		
		JPanel panel_4 = new JPanel();
		add(panel_4);
		FlowLayout fl_panel_4 = new FlowLayout(FlowLayout.LEFT, 0, 0);
		panel_4.setLayout(fl_panel_4);
		
		JLabel lblHoraireLivraison = new JLabel("Horaire Livraison pr\u00E9vu : ");
		panel_4.add(lblHoraireLivraison);
		
		lblHoraireContent = new JLabel();
		lblHoraireContent.setPreferredSize(new Dimension(200, 16));
		lblHoraireContent.setMinimumSize(new Dimension(200, 0));
		panel_4.add(lblHoraireContent);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JLabel lblClient = new JLabel("Client : ");
		panel_3.add(lblClient);
		
		lblClientContent = new JLabel();
		panel_3.add(lblClientContent);
	}
	
	public void setLivraison(PlageHoraire plageHoraire, Livraison livraison) {
		mLivraison = livraison;
		lblClientContent.setText(String.valueOf(livraison.getIdClient()));
		lblHoraireContent.setText(livraison.getHeureLivraison());
		lblPlageHoraireContent.setText(plageHoraire.getDateDebut() +  " - "  + plageHoraire.getDateFin());
		lblLivraisonContent.setText(String.valueOf(livraison.getId()));
		lblAdresseContent.setText(String.valueOf(livraison.getAdresse().getId()));
	}
	
	public void resetInfoLivraison() {
		mLivraison = null;
		lblClientContent.setText("");
		lblHoraireContent.setText("");
		lblPlageHoraireContent.setText("");
		lblLivraisonContent.setText("");
		lblAdresseContent.setText("");
	}

	public JLabel getLblLivraisonContent() {
		return lblLivraisonContent;
	}
	public JLabel getLblHoraireContent() {
		return lblHoraireContent;
	}
	public JLabel getLblPlageHoraireContent() {
		return lblPlageHoraireContent;
	}
	public JLabel getLblClientContent() {
		return lblClientContent;
	}
	public JLabel getLblAdresseContent() {
		return lblAdresseContent;
	}
	
	public Livraison getLivraison() {
		return mLivraison;
	}
}
