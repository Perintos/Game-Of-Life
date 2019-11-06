package tchateau.javase.gameoflife.ihm;

import java.awt.Graphics;

import javax.swing.JPanel;

import tchateau.javase.gameoflife.bo.Cell;
import tchateau.javase.gameoflife.bo.Grille;
import tchateau.javase.gameoflife.ressources.RessourcesListener;


public class PanelGraphics extends JPanel {
	private Grille grille;
	
	public PanelGraphics() {
		grille = new Grille();
	}
	
	public void paintComponent(Graphics g) {
		
		for(int i=0 ; i<grille.getCote() ; i++) {
			for(int j=0 ; j<grille.getCote() ; j++) {
				if(grille.get(i,j))
					g.fillRect(i*Cell.getCote(), j*Cell.getCote(), Cell.getCote(), Cell.getCote());
				else
					g.drawRect(i*Cell.getCote(), j*Cell.getCote(), Cell.getCote(), Cell.getCote());
			}
		}
			
		//g.fillRect(50, 50, 50, 50);
	}
}
