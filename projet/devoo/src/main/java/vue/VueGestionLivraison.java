package vue;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.data.Troncon;

/**
 * 
 */
public class VueGestionLivraison {

    /**
     * 
     */
    public VueGestionLivraison() {
    }
    
    /**
     * Affiche la fenetre principale du programme
     */
    public void afficherFenetrePrincipale() {
    	JFrame mainFrame = new JFrame();
    	mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	JPanel mainPanel = new JPanel();
    	JLabel helloWorldLabel = new JLabel();
    	helloWorldLabel.setText("Atho é gay");
    	mainPanel.add(helloWorldLabel);
    	mainFrame.add(mainPanel);
    	mainFrame.pack();
    	mainFrame.setVisible(true);
    }
    


    /**
     * @param circuit
     */
    public void afficherItineraire(List<Troncon> circuit) {
        // TODO implement here
    }

}