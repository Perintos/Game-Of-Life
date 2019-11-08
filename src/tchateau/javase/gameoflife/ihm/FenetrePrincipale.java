package tchateau.javase.gameoflife.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetrePrincipale extends JFrame implements ActionListener{
	
	private PanelGraphics pan = new PanelGraphics();
	private JButton buton = new JButton("START");
	
	public FenetrePrincipale() {
		this.setTitle("Game of life");							//titrer de la fenetre
		this.setSize(1080,720);									//dimensionner la fenetre
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//le bouton de fermeture de la fenetre ferme l'application et son processus
		//this.setResizable(false);
	    this.getContentPane().setLayout(new BorderLayout());	//le layout est definit sur le model BorderLayout
	    
	    buton.addActionListener(this);							//ajoute un listener d'action 
	    
		this.getContentPane().add(pan, BorderLayout.CENTER);	//ajout du panneau contenant la grille 
		this.getContentPane().add(buton, BorderLayout.SOUTH);	//ajout du bouton de démarrage

		this.setVisible(true);									//L'application est visible à l'écran
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.buton) {								//si le bouton de démarrage de la simulation est actionné
			this.pan.changeStatusTimerIteration();					//le timer change de status (on/off)
			this.pan.setEditable(false);							//on ne peut plus cliquer sur les cellules de la grille

			if(this.buton.getText().equalsIgnoreCase("start")) {	//si le bouton contient le texte paused
				this.buton.setText("PAUSED");						//Le text du bouton passe a PAUSED
			}
			else {
				this.buton.setText("START");						//Le text du bouton passe a START
			}
		}
	}
}
