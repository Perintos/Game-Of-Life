package tchateau.javase.gameoflife.ihm;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import tchateau.javase.gameoflife.bll.IterationManager;
import tchateau.javase.gameoflife.bll.IterationManagerFact;
import tchateau.javase.gameoflife.bo.Cell;
import tchateau.javase.gameoflife.bo.Grille;


public class PanelGraphics extends JPanel implements ActionListener {
	private Grille grille;
	IterationManager iterationManager;
	
	public PanelGraphics() {
		grille = new Grille();
		iterationManager = IterationManagerFact.getInstance(this);
		iterationManager.startIterationTimer();
		
	}
	
	public void paintComponent(Graphics g) {
		System.out.println("hello");

		
		for(int i=0 ; i<grille.getCote() ; i++) {
			for(int j=0 ; j<grille.getCote() ; j++) {
				if(grille.get(i,j))
					g.fillRect(i*Cell.getCote(), j*Cell.getCote(), Cell.getCote(), Cell.getCote());
				else
					g.drawRect(i*Cell.getCote(), j*Cell.getCote(), Cell.getCote(), Cell.getCote());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		iterationManager.jouer(grille.getGrille());
		repaint();
	}
}
