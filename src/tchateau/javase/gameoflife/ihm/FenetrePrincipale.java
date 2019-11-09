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
	    
	    
	    btnStart.addActionListener(this);							//surveiller les action sur btnStart
	    combobox.addActionListener(this);							//surveiller les action sur combobox
	    btnReset.addActionListener(this);							//surveiller les action sur btnReset
	    
	    panMenu.add(btnStart);										//ajout panMenu a l'interface
	    panMenu.add(btnReset);										//ajout btnReset a l'interface										
	    panMenu.add(combobox);									 	//ajout combobox a l'interface									
	    
		this.getContentPane().add(panGraphic, BorderLayout.CENTER);	//ajout du panneau contenant la grille 
		this.getContentPane().add(panMenu, BorderLayout.SOUTH);		//ajout du bouton de demarrage

		this.setVisible(true);										//L'application est visible à l'ecran
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnStart) {								//si le bouton de demarrage de la simulation est actionne
			this.panGraphic.changeStatusTimerIteration();				//le timer change de status (on/off)
			this.panGraphic.setEditable(false);							//on ne peut plus cliquer sur les cellules de la grille

			if(this.btnStart.getText().equalsIgnoreCase("start")) {		//si le bouton contient le texte paused
				this.btnStart.setText("PAUSED");						//Le text du bouton passe a PAUSED
			}
			else {
				this.btnStart.setText("START");							//Le text du bouton passe a START
			}
		}
		
		if(e.getSource()==this.btnReset) {								//si le bouton de demarrage de la simulation est actionne
			this.panGraphic.reset();
			this.btnStart.setText("START");
			this.combobox.setSelectedIndex(0);
		}
		
		else if(e.getSource()==this.combobox) {							//mode aleatoire si on selectionne aleatoire dans combobox
			if(combobox.getSelectedItem().equals("Aleatoire") ) {
				this.panGraphic.randomiserGrille();
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	}
}
