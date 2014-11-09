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

public class MainPanel extends JPanel {

	VueGestionLivraison mGestionLivraison;
	private JPanel planWrapper;

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
		
		JButton btnLoadLivraison = new JButton("ChargerLivraison");
		btnLoadLivraison.setEnabled(false);
		btnLoadLivraison.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnChargerPlan = new JButton("Charger Plan");
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

	}
	
	public void setPlan(PlanPanel plan) {
		planWrapper.add(plan);
	}

}
