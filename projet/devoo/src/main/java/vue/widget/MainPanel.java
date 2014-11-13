package vue.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.data.Livraison;
import model.data.PlageHoraire;
import vue.VueGestionLivraison;
import vue.VueLivraison;

public class MainPanel extends JPanel {

	VueGestionLivraison mGestionLivraison;
	private JButton btnChargerPlan;
	private JButton btnLoadLivraison;
	private JPanel panelError;
	private JLabel lblErreur;
	private JLabel lblErreurMessage;
	private JPanel panelPrincipal;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panelLivraisons;
	private JPanel panel_5;
	private JButton btnAnnuler;
	private JButton btnRetablir;
	private JButton btnExporter;
	private JPanel panelLivraisonSelected;
	private JPanel panelLivraisonAdd;
	private JButton btnSupprimerLivraison;
	private JButton btnAjouter;
	
	private VueLivraison vueLivraison;
	private JButton btnCalculerTournee;

	/**
	 * Create the panel.
	 */
	public MainPanel(VueGestionLivraison gestionLivraison) {
		mGestionLivraison = gestionLivraison;
		setLayout(new BorderLayout(0, 0));
		
		panelPrincipal = new JPanel();
		add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panelPrincipal.add(panel_1, BorderLayout.NORTH);
		
		btnLoadLivraison = new JButton("ChargerLivraison");
		panel_1.add(btnLoadLivraison);
		btnLoadLivraison.setEnabled(false);
		btnLoadLivraison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGestionLivraison.chargerLivraison();
			}
		});
		
		panel_5 = new JPanel();
		panelPrincipal.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnExporter = new JButton("<html><center>Exporter <br/>feuille de route</center></html>");
		btnExporter.setEnabled(false);
		btnExporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_5.add(btnExporter);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setEnabled(false);
		panel_5.add(btnAnnuler);
		
		btnRetablir = new JButton("R\u00E9tablir");
		btnRetablir.setEnabled(false);
		panel_5.add(btnRetablir);
		
		JPanel panelLeft = new JPanel();
		add(panelLeft, BorderLayout.WEST);
		
		panel_2 = new JPanel();
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.X_AXIS));
		panelLeft.add(panel_2);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		btnChargerPlan = new JButton("Charger Plan");
		btnChargerPlan.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(btnChargerPlan);
		btnChargerPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGestionLivraison.chargerPlan();
			}
		});
		
		btnCalculerTournee = new JButton("Calculer Tourn\u00E9e");
		btnCalculerTournee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mGestionLivraison.chargerTournee();
			}
		});
		btnCalculerTournee.setEnabled(false);
		btnCalculerTournee.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(btnCalculerTournee);
		
		panelLivraisons = new JPanel();
		panel_2.add(panelLivraisons);
		panelLivraisons.setLayout(new BoxLayout(panelLivraisons, BoxLayout.Y_AXIS));
		
		panelLivraisonSelected = new JPanel();
		panelLivraisons.add(panelLivraisonSelected);
		panelLivraisonSelected.setLayout(new BorderLayout(0, 0));
		
		vueLivraison = new VueLivraison();
		panelLivraisonSelected.add(vueLivraison, BorderLayout.CENTER);
		
		btnSupprimerLivraison = new JButton("Supprimer");
		btnSupprimerLivraison.setEnabled(false);
		btnSupprimerLivraison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGestionLivraison.removeSelectedLivraison();
			}
		});
		panelLivraisonSelected.add(btnSupprimerLivraison, BorderLayout.SOUTH);
		
		panelLivraisonAdd = new JPanel();
		panelLivraisons.add(panelLivraisonAdd);
		panelLivraisonAdd.setLayout(new BorderLayout(0, 0));
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setEnabled(false);
		panelLivraisonAdd.add(btnAjouter, BorderLayout.SOUTH);
		
		panelError = new JPanel();
		panelError.setOpaque(false);
		add(panelError, BorderLayout.SOUTH);
		panelError.setLayout(new BorderLayout(0, 0));
		
		lblErreur = new JLabel("Erreur : ");
		lblErreur.setForeground(Color.RED);
		lblErreur.setVisible(false);
		panelError.add(lblErreur, BorderLayout.WEST);
		
		lblErreurMessage = new JLabel();
		lblErreurMessage.setForeground(Color.RED);
		panelError.add(lblErreurMessage, BorderLayout.CENTER);

	}
	
	public void setPlan(PlanPanel plan) {
		Component component = ((BorderLayout)panelPrincipal.getLayout()).getLayoutComponent(BorderLayout.CENTER);
		if(component != null) {
			panelPrincipal.remove(component);
		}
		panelPrincipal.add(plan, BorderLayout.CENTER);
		if(plan.hasPlan()) {
			btnLoadLivraison.setEnabled(true);
		}
	}
	
	public void setErrorMessage(String message) {
		lblErreur.setVisible(true);
		lblErreurMessage.setText(message);
	}
	
	public void setLivraisonSelected(PlageHoraire plageHoraire, Livraison livraison) {
		vueLivraison.setLivraison(plageHoraire, livraison);
		btnSupprimerLivraison.setEnabled(true);
		//validate();
	}
	
	public void resetInfoLivraison() {
		vueLivraison.resetInfoLivraison();
		btnSupprimerLivraison.setEnabled(false);
		//validate();
	}
	
	public JPanel getPanelLivraisonAdd() {
		return panelLivraisonAdd;
	}
	public JButton getBtnExporter() {
		return btnExporter;
	}
	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}
	public JButton getBtnRetablir() {
		return btnRetablir;
	}
	
	public VueLivraison getVueLivraison() {
		return vueLivraison;
	}

	public void setCalculItineraire(boolean b) {
		btnCalculerTournee.setEnabled(b);
	}
}
