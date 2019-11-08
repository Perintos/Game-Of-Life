package tchateau.javase.gameoflife.ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import tchateau.javase.gameoflife.bll.IterationManager;
import tchateau.javase.gameoflife.bll.IterationManagerFact;
import tchateau.javase.gameoflife.bo.Cell;
import tchateau.javase.gameoflife.bo.Grille;
import tchateau.javase.gameoflife.ressources.RessourcesListener;


public class PanelGraphics extends JPanel implements ActionListener, MouseListener, MouseWheelListener {
	private Grille grille;					//L'objet grille mod�lise la grille de la simulation
	boolean isEditable = true;				//permet de savoir si on peut cliquer sur les cellule de la grille pour changer leur �tat
	IterationManager iterationManager;		//contient l'instanciation des regles de la simulation
	
	public PanelGraphics() {				
		
		grille = new Grille();
		RessourcesListener rl = new RessourcesListener();				//un lecteur de fichier property	
		iterationManager = IterationManagerFact.getInstance();			//instanciation des regle de la simulation
		iterationManager.addTimer(new Timer(rl.readTimer(), this));		//ajout d'un timer au regle du jeu, le timer est connect� � cette class et � sa m�thodez actionPerformed
		
		this.addMouseListener(this);			//ajout d'un listener de souris
		this.addMouseWheelListener(this);		//ajout d'un listener de molette de souris
	}
	
	public boolean isEditable() {					//getteur et setteur
		return isEditable;
	}
	
	public void setEditable(boolean isEditable) {		
		this.isEditable = isEditable;					
	}
	
	public void paint(Graphics g) {							//methode surcharg� de la classe JComponent elle dessine le JPANEL et son contenu par dessus sans effacer ce qu'il y a avant
		g.setColor(Color.WHITE);							//Dessine un carr� blanc pour effacer le contenu pr�c�dent.
		g.fillRect(0,0,this.getWidth(),this.getWidth());	
		
		g.setColor(Color.BLACK);							//repassse en dessin trait noir
		
		for(int x=0 ; x<grille.getCote() ; x++) {			//Dessine cellule par cellule la grille
			for(int y=0 ; y<grille.getCote() ; y++) {
				if(grille.get(x,y))
					g.fillRect(x*Cell.getCote(), y*Cell.getCote(), Cell.getCote(), Cell.getCote());
				else
					g.drawRect(x*Cell.getCote(), y*Cell.getCote(), Cell.getCote(), Cell.getCote());
			}
		}
		
		g.setColor(Color.RED);								//Dessine le compteur d'it�ration de la simulation
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(String.valueOf(this.iterationManager.getNbrIteration()), this.getWidth()-this.getWidth()/10, 50);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {						//execut� a chaque fois que le timer de IterationManagerImpl se termine
		Grille.setGrille(iterationManager.jouer(Grille.getGrille()));	//Executer une it�ration de la simulation
		repaint();														//redessine le JPanel (appel implicitement la m�thode paint())
	}

	public void changeStatusTimerIteration() {					//demande a iterationManagerImpl de changer le status de son timer (On/Off)
		iterationManager.changeStatusTimerIteration();	
	}

	@Override
	public void mouseClicked(MouseEvent e) {					//est appel� lors d'un clic gauche de souris 
		int x ;
		int y ;

		
		if(Cell.getCote()>0 && isEditable) {					
			x = e.getX()/Cell.getCote();
			y = e.getY()/Cell.getCote();
			
			try {												//Essaye de changer le status de la cellule cliqu�.
				Grille.changeStatusCell(x, y);
				repaint();
			}catch(Exception exception) {						//Si l'utilisateur clique en dehors de la grille ne fait rien
				
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {		//appel� si la molette de la souris est tourn�	
        int notches = e.getWheelRotation();					//on obtient - si la molette est tourn� vers le haut et + si c'est vers le bas
        System.out.println(notches);
        if(notches>0)										//en fonction de la valeur on augmente la taille du cote des cellules on la reduit
			Cell.setCote(Cell.getCote()-1);
        else 
			Cell.setCote(Cell.getCote()+1);

		repaint();											//Il faut alors redessiner le JPanel
	}	
}
