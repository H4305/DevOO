package vue.widget;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import vue.VueGestionLivraison;
import vue.VueLivraison;

import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.BoxLayout;

import model.data.Livraison;
import model.data.PlageHoraire;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.SystemColor;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

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
	private JPanel panelLivraisonAdd_1;
	private JButton btnSupprimerLivraison;
	private JButton btnAjouter;
	
	private VueLivraison vueLivraison;
	private JButton btnCalculerTournee;
	private JLabel lblInfoMessage;
	private JPanel panel;
	private JPanel panel_9;
	private JPanel panel_4;
	private JPanel panel_6;
	private JLabel informations;
	private JPanel panel_7;
	private JPanel panel_8;
	
	/**
	 * Create the panel.
	 */
	public MainPanel(VueGestionLivraison gestionLivraison) {
		
		mGestionLivraison = gestionLivraison;
		setLayout(new BorderLayout(0, 0));
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(0, 0, 0)));
		add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panelPrincipal.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel, BorderLayout.WEST);
		
		btnExporter = new JButton("Exporter");
		panel.add(btnExporter);
		btnExporter.setHorizontalAlignment(SwingConstants.RIGHT);
		btnExporter.setEnabled(false);
		btnExporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGestionLivraison.exporterFeuilleDeRoute();
			}
		});
		
		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.inactiveCaption);
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_4, BorderLayout.EAST);
		
		btnAnnuler = new JButton("Annuler");
		panel_4.add(btnAnnuler);
		btnAnnuler.setEnabled(false);
		
		btnRetablir = new JButton("R\u00E9tablir");
		panel_4.add(btnRetablir);
		btnRetablir.setEnabled(false);
		
		panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.inactiveCaption);
		panelPrincipal.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelLeft = new JPanel();
		add(panelLeft, BorderLayout.WEST);
		
		panel_2 = new JPanel();
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.X_AXIS));
		panelLeft.add(panel_2);
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_9 = new JPanel();
		panel_3.add(panel_9, BorderLayout.SOUTH);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		lblInfoMessage = new JLabel("");
		panel_9.add(lblInfoMessage);
	
		panel_8 = new JPanel();
		panel_3.add(panel_8, BorderLayout.NORTH);
		
		btnChargerPlan = new JButton("Charger Plan");
		panel_8.add(btnChargerPlan);
		
		btnLoadLivraison = new JButton("Charger Livraison");
		panel_8.add(btnLoadLivraison);
		btnLoadLivraison.setEnabled(false);
		
		btnCalculerTournee = new JButton("Calculer Tourn\u00E9e");
		panel_8.add(btnCalculerTournee);
		btnCalculerTournee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mGestionLivraison.chargerTournee();
			}
		});
		btnCalculerTournee.setEnabled(false);
		btnCalculerTournee.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLoadLivraison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGestionLivraison.chargerLivraison();
			}
		});
		btnChargerPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGestionLivraison.chargerPlan();
			}
		});
		
		panelLivraisons = new JPanel();
		panel_2.add(panelLivraisons);
		panelLivraisons.setLayout(new BoxLayout(panelLivraisons, BoxLayout.Y_AXIS));
		
		panelLivraisonSelected = new JPanel();
		panelLivraisons.add(panelLivraisonSelected);
		panelLivraisonSelected.setLayout(new BorderLayout(0, 0));

		vueLivraison = new VueLivraison();
		panelLivraisonSelected.add(vueLivraison, BorderLayout.CENTER);
		
		panel_7 = new JPanel();
		panelLivraisons.add(panel_7);
		
				
				panelLivraisonAdd = new JPanel();
				panel_7.add(panelLivraisonAdd);
				panelLivraisonAdd.setLayout(new BorderLayout(0, 0));
				
				btnSupprimerLivraison = new JButton("Supprimer");
				panelLivraisonAdd.add(btnSupprimerLivraison, BorderLayout.NORTH);
				btnSupprimerLivraison.setEnabled(false);
				panelLivraisonAdd_1 = new JPanel();
				panel_7.add(panelLivraisonAdd_1);
				panelLivraisonAdd_1.setLayout(new BorderLayout(0, 0));
				
				btnAjouter = new JButton("Ajouter");
				btnAjouter.setEnabled(false);
				panelLivraisonAdd_1.add(btnAjouter, BorderLayout.SOUTH);
				btnSupprimerLivraison.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						mGestionLivraison.removeSelectedLivraison();
					}
				});
		
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
		
		panel_6 = new JPanel();
		panelError.add(panel_6, BorderLayout.SOUTH);
		
		String txtDate=new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date());
		
		informations = new JLabel(txtDate + " - Réalisation de feuilles de route");
		panel_6.add(informations);
	}
	
	/**
	 * 
	 * @param plan
	 */
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
	
	/**
	 * 
	 * @param message
	 */
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
		return panelLivraisonAdd_1;
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
	
	public JButton getBtnAjouter() {
		return btnAjouter;
	}
	
	public VueLivraison getVueLivraison() {
		return vueLivraison;
	}

	public void setCalculItineraire(boolean b) {
		btnCalculerTournee.setEnabled(b);
	}
	
	public void setInformationMessage(String message) {
		lblInfoMessage.setText(message);
	}
	
	/**
	 * This method allows to 
	 */
	public void disablebtnAjouter() {
		for(ActionListener listener : btnAjouter.getActionListeners()) {
			btnAjouter.removeActionListener(listener);
		}
		btnAjouter.setEnabled(false);
	}
}
