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
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainPanel extends JPanel {

	VueGestionLivraison mGestionLivraison;
	private JPanel planWrapper;
	private JButton btnChargerPlan;
	private JButton btnLoadLivraison;
	private JPanel panelError;
	private JLabel lblErreur;
	private JLabel lblErreurMessage;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton btnAnnuler;
	private JButton btnRetablir;
	private JButton btnExporter;

	/**
	 * Create the panel.
	 */
	public MainPanel(VueGestionLivraison gestionLivraison) {
		mGestionLivraison = gestionLivraison;
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		btnLoadLivraison = new JButton("ChargerLivraison");
		panel_1.add(btnLoadLivraison);
		btnLoadLivraison.setEnabled(false);
		btnLoadLivraison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGestionLivraison.chargerLivraison();
			}
		});
		
		planWrapper = new JPanel();
		panel.add(planWrapper, BorderLayout.CENTER);
		
		panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.SOUTH);
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
		panel_2.add(panel_3);
		
		btnChargerPlan = new JButton("Charger Plan");
		panel_3.add(btnChargerPlan);
		btnChargerPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGestionLivraison.chargerPlan();
			}
		});
		
		panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
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
		planWrapper.removeAll();
		planWrapper.add(plan);
		if(plan.hasPlan()) {
			btnLoadLivraison.setEnabled(true);
		}
	}
	
	public void setErrorMessage(String message) {
		lblErreur.setVisible(true);
		lblErreurMessage.setText(message);
	}
}
