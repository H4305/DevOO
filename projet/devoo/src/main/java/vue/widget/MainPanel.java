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

public class MainPanel extends JPanel {

	VueGestionLivraison mGestionLivraison;
	private JPanel planWrapper;
	private JButton btnChargerPlan;
	private JButton btnLoadLivraison;
	private JPanel panelError;
	private JLabel lblErreur;
	private JLabel lblErreurMessage;

	/**
	 * Create the panel.
	 */
	public MainPanel(VueGestionLivraison gestionLivraison) {
		mGestionLivraison = gestionLivraison;
		setLayout(new BorderLayout(0, 0));
		
		planWrapper = new JPanel();
		add(planWrapper, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.WEST);
		
		btnLoadLivraison = new JButton("ChargerLivraison");
		btnLoadLivraison.setEnabled(false);
		btnLoadLivraison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGestionLivraison.chargerLivraison();
			}
		});
		
		btnChargerPlan = new JButton("Charger Plan");
		btnChargerPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mGestionLivraison.chargerPlan();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnChargerPlan, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
						.addComponent(btnLoadLivraison, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnChargerPlan)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLoadLivraison)
					.addContainerGap(230, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
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
