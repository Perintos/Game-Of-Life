package tchateau.javase.gameoflife.ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import tchateau.javase.gameoflife.ressources.ChoixStructure;
import tchateau.javase.gameoflife.ressources.RessourcesListener;

public class FenetrePrincipale extends JFrame implements ActionListener, ItemListener{
	
	private PanelGraphics panGraphic = new PanelGraphics();
	private JButton btnStart = new JButton("START");
	private JButton btnReset = new JButton("STOP");
	private JSpinner spin = new JSpinner();
	private ChoixStructure combobox = new ChoixStructure();
	
	public FenetrePrincipale() {
		RessourcesListener rl = new RessourcesListener();
		this.setTitle("Game of life");							//titrer de la fenetre
		this.setSize(1080,720);									//dimensionner la fenetre
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//le bouton de fermeture de la fenetre ferme l'application et son processus
		//this.setResizable(false);
	    JPanel panMenu = new JPanel();
	    this.getContentPane().setLayout(new BorderLayout());	//le layout est definit sur le model BorderLayout
	    panMenu.setLayout(new FlowLayout());
	    
	    
	    btnStart.addActionListener(this);							//ajoute un listener d'action
	    combobox.addActionListener(this);
	    btnReset.addActionListener(this);
	    
	    panMenu.add(btnStart);
	    panMenu.add(btnReset);
	    panMenu.add(combobox);
	    
		this.getContentPane().add(panGraphic, BorderLayout.CENTER);	//ajout du panneau contenant la grille 
		this.getContentPane().add(panMenu, BorderLayout.SOUTH);	//ajout du bouton de démarrage

		this.setVisible(true);									//L'application est visible à l'écran
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnStart) {								//si le bouton de démarrage de la simulation est actionné
			this.panGraphic.changeStatusTimerIteration();					//le timer change de status (on/off)
			this.panGraphic.setEditable(false);							//on ne peut plus cliquer sur les cellules de la grille

			if(this.btnStart.getText().equalsIgnoreCase("start")) {	//si le bouton contient le texte paused
				this.btnStart.setText("PAUSED");						//Le text du bouton passe a PAUSED
			}
			else {
				this.btnStart.setText("START");						//Le text du bouton passe a START
			}
		}
		
		if(e.getSource()==this.btnReset) {								//si le bouton de démarrage de la simulation est actionné
			this.panGraphic.reset();
			this.btnStart.setText("START");
		}
		
		else if(e.getSource()==this.combobox) {
			if(combobox.getSelectedItem().equals("Aléatoire") ) {
				this.panGraphic.randomiserGrille();
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	}
}
