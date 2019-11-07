package tchateau.javase.gameoflife.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import tchateau.javase.gameoflife.bll.InterfaceManager;
import tchateau.javase.gameoflife.bll.InterfaceManagerFact;
import tchateau.javase.gameoflife.bll.IterationManager;
import tchateau.javase.gameoflife.bll.IterationManagerFact;
import tchateau.javase.gameoflife.bo.Cell;
import tchateau.javase.gameoflife.bo.Grille;
import tchateau.javase.gameoflife.ressources.RessourcesListener;


public class PanelGraphics extends JPanel implements ActionListener, MouseListener {
	private Grille grille;
	IterationManager iterationManager;
	InterfaceManager interfaManager;
	
	public PanelGraphics() {
		
		grille = new Grille();
		RessourcesListener rl = new RessourcesListener();
		iterationManager = IterationManagerFact.getInstance();		
		interfaManager = InterfaceManagerFact.getInstance();
		iterationManager.addTimer(new Timer(rl.readTimer(), this));
		
		this.addMouseListener(this);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0,0,this.getWidth(),this.getWidth());
		
		g.setColor(Color.BLACK);

		
		for(int i=0 ; i<grille.getCote() ; i++) {
			for(int j=0 ; j<grille.getCote() ; j++) {
				if(grille.get(i,j))
					g.fillRect(i*Cell.getCote(), j*Cell.getCote(), Cell.getCote(), Cell.getCote());
				else
					g.drawRect(i*Cell.getCote(), j*Cell.getCote(), Cell.getCote(), Cell.getCote());
			}
		}
		
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(String.valueOf(this.iterationManager.getNbrIteration()), this.getWidth()-this.getWidth()/10, 50);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Grille.setGrille(iterationManager.jouer(Grille.getGrille()));
		repaint();
	}

	public void changeStatusTimerIteration() {
		iterationManager.changeStatusTimerIteration();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		RessourcesListener rl = new RessourcesListener();
		System.out.println("x : "+ e.getX()/rl.readcellside() + " / y : " + e.getY()/rl.readcellside());
		int x ;
		int y ;

		
		if(rl.readcellside()>0) {
			x = e.getX()/rl.readcellside();
			y = e.getY()/rl.readcellside();
			
			try {
				Grille.changeStatusCell(x, y);
				repaint();
			}catch(Exception exception) {
				
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	
}
