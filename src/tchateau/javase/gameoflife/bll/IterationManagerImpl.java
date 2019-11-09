package tchateau.javase.gameoflife.bll;


import javax.swing.Timer;

public class IterationManagerImpl implements IterationManager {
	Timer iterationTimer;		//sert compter le temps par itération
	int nbrIteration = 0;		//contient le nombre d'itération jouée

	

	public int getNbrIteration() {
		return this.nbrIteration;
	}
	
	@Override
	public void startIterationTimer() {				//démarre le timer
		iterationTimer.start();				
		
	}

	@Override
	public void stopIterationTimer() {				//stoppe le tiemer
		iterationTimer.stop();		
	}

	@Override
	public boolean[][] jouer(boolean[][] grilleEntree) {									//joue une itération 
		boolean[][] grilleRetour = new boolean[grilleEntree.length][grilleEntree.length];	//la grille qui représente l'état des cellule de génération suivante
		boolean[][] instantane;																//contient un instané de la cellule courante
		
		for(int x=0 ; x<grilleEntree.length ; x++) {					//copie le tableau en parametre avec le tableau de retour
			for(int y=0 ; y<grilleEntree.length ; y++) {				//le tableau en entré représente le génération précédente 
				grilleRetour[x][y] = grilleEntree[x][y];				//le tableau en sortie représente la génération suivnate
			}
		}
		

		for(int x=0 ; x<grilleEntree.length ; x++) {					//parcours chaque cellule de la génération précédente 
			for(int y=0 ; y<grilleEntree.length ; y++) {
				
				instantane = genererInstantane(grilleEntree, x, y);		//demande un tableau qui contient la cellule analysé au centre et les cellule adjacente (tableau de 3x3)
				
				if(instantane[1][1]) {									
					if(!doitSurvivre(instantane))						//Si la cellule analysé est vivante on se demande si elle doit survivre
						grilleRetour[x][y] = false;				
				}
				else {
					if(doitNaitre(instantane))							//Si la cellule est morte on se demande si elle doit naitre
						grilleRetour[x][y] = true ;
				}
			}
		}
		
		nbrIteration++;													//on peut passer à la génération suivante donc on incrémente le nombre d'itération de 1
		return grilleRetour;											//la grille représentant la génération est renvoyé pour aller remplacer la grille de la génération précédente. 
	}

	private boolean[][] genererInstantane(boolean[][] grilleEntree, int x, int y) {
		boolean[][] instantane = new boolean[3][3];						//on initilaise une grille de 3x3 cellules qui représente les cellules autour de la cellule analysées
		
		
		try {
			instantane[0][0] = grilleEntree[x-1][y-1];					//on initialise les celllules de l'instantané en fonction de la grille qui représente la génération précédente.
		}catch(Exception e){											//si on est sur une cellule hors de la grille alors on dit que cette cellules est morte
			instantane[0][0] = false;									
		}
		
		try {
			instantane[0][1] = grilleEntree[x-1][y];
		} catch (Exception e) {
			instantane[0][1] = false;
		}
		
		try {
			instantane[0][2] = grilleEntree[x-1][y+1];
		} catch (Exception e) {
			instantane[0][2] = false;
		}
		
		try {
			instantane[1][0] = grilleEntree[x][y-1];
		} catch (Exception e) {
			instantane[1][0] = false;
		}
		
		instantane[1][1] = grilleEntree[x][y];							//Il s'agit de la cellule analysée, elle existe forcément donc pas besoin de try catch			
		
		try {
			instantane[1][2] = grilleEntree[x][y+1];					
		} catch (Exception e) {
			instantane[1][2] = false;
		}
		
		try {
			instantane[2][0] = grilleEntree[x+1][y-1];
		} catch (Exception e) {
			instantane[2][0] = false;
		}
		
		try {
			instantane[2][1] = grilleEntree[x+1][y];
		} catch (Exception e) {
			instantane[2][1] = false;
		}
		
		try {
			instantane[2][2] = grilleEntree[x+1][y+1];
		} catch (Exception e) {
			instantane[2][2] = false;
		}
		
		return instantane;
	}

	private boolean doitNaitre(boolean[][] instantane) {	//répond à la question : Cette cellule est morte, doit elle naitre dans la prochaine génération ?
		int nbrVivant = 0;
		boolean bRetour = false;
		for(int x=0 ; x<instantane.length ; x++) {			//parcours la grille de l'instantané pour trouvé les cellule vivante	
			for(int y=0 ; y<instantane.length ; y++) {					
				if(instantane[x][y])						//si la cellule est vivante alors on incrémente le nombre de cellule vivante trouvées
					nbrVivant++;
			}
		}
				
		if(nbrVivant == 3)									//si il y a exactement trois cellule vivante dans l'instantané alors la cellule naitra à la prochaine génération
			bRetour=true;
		
		return bRetour;
	}

	private boolean doitSurvivre(boolean[][] instantane) {	//répond à la question : Cette cellule n'existe pas, doit elle survivre dans la prochaine génération ?
		int nbrVivant = -1;									//sans compter la cellule analyzé qui est forcément vivante à l'appel de cette méthode
		boolean bRetour = false;
		for(int x=0 ; x<instantane.length ; x++) {			//parcours la grille de l'instantané pour trouvé les cellule vivante	
			for(int y=0 ; y<instantane.length ; y++) {		
				if(instantane[x][y])
					nbrVivant++;
			}
		}
				
		if(nbrVivant >=2 && nbrVivant <= 3)					//si 2 ou 3 cellule vivante entour une cellule vide alors elle pourra naitre à la prochaine génération
			bRetour=true;
		
		return bRetour;
	}


	@Override
	public void changeStatusTimerIteration() {				//methode on off du timer 
		if(this.iterationTimer.isRunning())
			this.iterationTimer.stop();
		else
			this.iterationTimer.start();
		
	}

	@Override
	public void addTimer(Timer timer) {						//instancie le timer avec le timer en parametre.
		this.iterationTimer = timer;
	}	
}
