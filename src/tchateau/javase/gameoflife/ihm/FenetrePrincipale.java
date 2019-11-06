package tchateau.javase.gameoflife.ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetrePrincipale extends JFrame {
	public FenetrePrincipale() {
		this.setTitle("Game of life");
		this.setSize(1080,720);
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(false);
		this.setContentPane(new PanelGraphics());
		this.setVisible(true);
	}
}
