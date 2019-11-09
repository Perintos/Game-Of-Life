package tchateau.javase.gameoflife.ihm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import tchateau.javase.gameoflife.bll.IterationManager;
import tchateau.javase.gameoflife.bll.IterationManagerFact;
import tchateau.javase.gameoflife.bo.Cell;
import tchateau.javase.gameoflife.bo.Grille;
import tchateau.javase.gameoflife.ressources.RessourcesListener;


public class PanelGraphics extends JPanel implements ActionListener, MouseListener, MouseWheelListener, MouseMotionListener {
	private Grille grille;					//L'objet grille modelise la grille de la simulation
	boolean isEditable = true;				//permet de savoir si on peut cliquer sur les cellule de la grille pour changer leur etat
	IterationManager iterationManager;		//contient l'instanciation des regles de la simulation
	int mousePressClickX;
	int mousePressClickY;

	
	public PanelGraphics() {				
		grille = new Grille();
		RessourcesListener rl = new RessourcesListener();				//un lecteur de fichier property	
		iterationManager = IterationManagerFact.getInstance();			//instanciation des regle de la simulation
		iterationManager.addTimer(new Timer(rl.readTimer(), this));		//ajout d'un timer au regle du jeu, le timer est connecte � cette class et � sa methodez actionPerformed
		
		this.addMouseListener(this);			//ajout d'un listener de souris
		this.addMouseWheelListener(this);		//ajout d'un listener de molette de souris
		this.addMouseMotionListener(this);		//ajout d'un listener deplacement de souris de souris
	}
	
	public boolean isEditable() {					//getteur et setteur
		return isEditable;
	}
	
	public void setEditable(boolean isEditable) {		
		this.isEditable = isEditable;					
	}
	
	public void paint(Graphics g) {							//methode surcharge de la classe JComponent elle dessine le JPANEL et son contenu par dessus sans effacer ce qu'il y a avant
		g.setColor(Color.WHITE);							//Dessine un carre blanc pour effacer le contenu precedent.
		g.fillRect(0,0,this.getWidth(),this.getWidth());	
		
		g.setColor(Color.BLACK);							//repassse en dessin trait noir
		
		for(int x=0 ; x<grille.getCote() ; x++) {			//Dessine cellule par cellule la grille
			for(int y=0 ; y<grille.getCote() ; y++) {
				if(grille.get(x,y))
					g.fillRect(x*Cell.getCote()+this.grille.getPosX(), y*Cell.getCote()+this.grille.getPosY(), Cell.getCote(), Cell.getCote());
				else
					g.drawRect(x*Cell.getCote()+this.grille.getPosX(), y*Cell.getCote()+this.grille.getPosY(), Cell.getCote(), Cell.getCote());
			}
		}
		
		g.setColor(Color.RED);								//Dessine le compteur d'iteration de la simulation
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(String.valueOf(this.iterationManager.getNbrIteration()), this.getWidth()-this.getWidth()/10, 50);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {						//execute a chaque fois que le timer de IterationManagerImpl se termine
		Grille.setGrille(iterationManager.jouer(Grille.getGrille()));	//Executer une iteration de la simulation
		repaint();														//redessine le JPanel (appel implicitement la methode paint())
	}

	public void changeStatusTimerIteration() {					//demande a iterationManagerImpl de changer le status de son timer (On/Off)
		iterationManager.changeStatusTimerIteration();	
	}

	@Override
	public void mouseClicked(MouseEvent e) {					//est appele lors d'un clic gauche de souris 
		int x ;
		int y ;
		
		if(Cell.getCote()>0 && isEditable) {					
			x = (e.getX()-this.grille.getPosX())/Cell.getCote();
			y = (e.getY()-this.grille.getPosY())/Cell.getCote();
//			System.out.println("x : " + x + " y : " + y);

			try {												//Essaye de changer le status de la cellule clique.
				Grille.changeStatusCell(x, y);
				repaint();
			}catch(Exception exception) {						//Si l'utilisateur clique en dehors de la grille ne fait rien
				
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {					//cette methode est appele quand un boutton de la souris est presse
		if(e.getButton() == MouseEvent.BUTTON3)					//si c'est le bouton clic droit
		{
			Cursor c = new Cursor(Cursor.MOVE_CURSOR);			// instanciation d'un curseur de deplacement. 
			this.setCursor(c);									// le cursor prend la forme d'un curseur de deplacement
			mousePressClickX = e.getX();						//On enregistre la position ou l'on a presse le bouton 
			mousePressClickY = e.getY();						
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {					// quand un bouton est relache
		
		if(e.getButton() == MouseEvent.BUTTON3)					//si c'est le bouton clic droit
		{
//		System.out.println("Deplacement - x : " +  (this.mousePressClickX - e.getX()) + " y : " +  (this.mousePressClickY - e.getY()));
//		System.out.println("operation / " + this.grille.getPosX() + " - (" + this.mousePressClickX + " - " + e.getX() + ") = " + (this.grille.getPosX() - (this.mousePressClickX - e.getX())) );
//		System.out.println("Nouvelle position de la grille - x : " + (this.grille.getPosX() - (this.mousePressClickX - e.getX())) + " y : " + (this.grille.getPosY() - (this.mousePressClickY - e.getY())));
//		System.out.println("-----------------------------------------------------------------------");
			this.setCursor(Cursor.getDefaultCursor());

			if(e.getButton() == MouseEvent.BUTTON3)
			{
				this.grille.setPosX(this.grille.getPosX() - (this.mousePressClickX - e.getX()));	// On soustrait � x la distance entre le x initiale (debut deplacement) et le x final (fin de deplacement). 
				this.grille.setPosY(this.grille.getPosY() - (this.mousePressClickY - e.getY()));	// On soustrait � y la distance entre le y initiale (debut deplacement) et le y final (fin de deplacement). 
				repaint();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {		//appele si la molette de la souris est tourne	
        int notches = e.getWheelRotation();					//on obtient -1 si la molette est tourne vers le haut et +1 si c'est vers le bas
        if(notches>0)										//en fonction de la valeur on augmente la taille du cote des cellules ou on la reduit
			Cell.setCote(Cell.getCote()-1);
        else 
			Cell.setCote(Cell.getCote()+1);

		repaint();											//Il faut alors redessiner le JPanel 
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//		System.out.println("X = " +  this.grille.getPosX() + " / Y = " +  this.grille.getPosY());
//		System.out.println(this.grille.getPosX() + " - " + e.getX() + " = " + (this.grille.getPosX() - e.getX()));
//		System.out.println(this.grille.getPosY() + " - " + e.getY() + " = " + (this.grille.getPosY() - e.getY()));
		
//		System.out.println("drag -  x : " + e.getX() + " y : " + e.getY());
//
//
//		this.grille.setPosX(this.grille.getPosX() - ( mousePressClickX - e.getX() ) );
//		this.grille.setPosY(this.grille.getPosY() - ( mousePressClickY - e.getY() ) );
//		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	public void randomiserGrille() {		//appeller la methode redomiser de grille et redessiner le JPANEL
		this.grille.randomiser();
		repaint();
		
	}

	public void reset() {					//remettre a zero la simulation. 
		RessourcesListener rl = new RessourcesListener();

		this.iterationManager.stopIterationTimer();
		this.iterationManager= IterationManagerFact.getInstance();
		this.grille = new Grille();
		this.isEditable = true;
		iterationManager.addTimer(new Timer(rl.readTimer(), this));		//ajout d'un timer au regle du jeu, le timer est connecte � cette class et � sa methodez actionPerformed
		repaint();
	}
}
