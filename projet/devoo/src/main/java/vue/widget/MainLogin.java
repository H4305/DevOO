package vue.widget;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class MainLogin extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public MainLogin() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		panel_4.add(passwordField);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Connexion");
		panel_4.add(btnNewButton);
		
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("src/main/ressources/fond.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setText("dfffhfhdfh");
		panel_4.add(picLabel);

	}

}
