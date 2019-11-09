package tchateau.javase.gameoflife.bll;


import javax.swing.Timer;

public class IterationManagerImpl implements IterationManager {
	Timer iterationTimer;		//sert compter le temps par it�ration
	int nbrIteration = 0;		//contient le nombre d'it�ration jou�e

	

	public int getNbrIteration() {
		return this.nbrIteration;
	}
	
	@Override
	public void startIterationTimer() {				//d�marre le timer
		iterationTimer.start();				
		
	}

	@Override
	public void stopIterationTimer() {				//stoppe le tiemer
		iterationTimer.stop();		
	}

	@Override
	public boolean[][] jouer(boolean[][] grilleEntree) {									//joue une it�ration 
		boolean[][] grilleRetour = new boolean[grilleEntree.length][grilleEntree.length];	//la grille qui repr�sente l'�tat des cellule de g�n�ration suivante
		boolean[][] instantane;																//contient un instan� de la cellule courante
		
		for(int x=0 ; x<grilleEntree.length ; x++) {					//copie le tableau en parametre avec le tableau de retour
			for(int y=0 ; y<grilleEntree.length ; y++) {				//le tableau en entr� repr�sente le g�n�ration pr�c�dente 
				grilleRetour[x][y] = grilleEntree[x][y];				//le tableau en sortie repr�sente la g�n�ration suivnate
			}
		}
		

		for(int x=0 ; x<grilleEntree.length ; x++) {					//parcours chaque cellule de la g�n�ration pr�c�dente 
			for(int y=0 ; y<grilleEntree.length ; y++) {
				
				instantane = genererInstantane(grilleEntree, x, y);		//demande un tableau qui contient la cellule analys� au centre et les cellule adjacente (tableau de 3x3)
				
				if(instantane[1][1]) {									
					if(!doitSurvivre(instantane))						//Si la cellule analys� est vivante on se demande si elle doit survivre
						grilleRetour[x][y] = false;				
				}
				else {
					if(doitNaitre(instantane))							//Si la cellule est morte on se demande si elle doit naitre
						grilleRetour[x][y] = true ;
				}
			}
		}
		
		nbrIteration++;													//on peut passer � la g�n�ration suivante donc on incr�mente le nombre d'it�ration de 1
		return grilleRetour;											//la grille repr�sentant la g�n�ration est renvoy� pour aller remplacer la grille de la g�n�ration pr�c�dente. 
	}

	private boolean[][] genererInstantane(boolean[][] grilleEntree, int x, int y) {
		boolean[][] instantane = new boolean[3][3];						//on initilaise une grille de 3x3 cellules qui repr�sente les cellules autour de la cellule analys�es
		
		
		try {
			instantane[0][0] = grilleEntree[x-1][y-1];					//on initialise les celllules de l'instantan� en fonction de la grille qui repr�sente la g�n�ration pr�c�dente.
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
		
		instantane[1][1] = grilleEntree[x][y];							//Il s'agit de la cellule analys�e, elle existe forc�ment donc pas besoin de try catch			
		
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

	private boolean doitNaitre(boolean[][] instantane) {	//r�pond � la question : Cette cellule est morte, doit elle naitre dans la prochaine g�n�ration ?
		int nbrVivant = 0;
		boolean bRetour = false;
		for(int x=0 ; x<instantane.length ; x++) {			//parcours la grille de l'instantan� pour trouv� les cellule vivante	
			for(int y=0 ; y<instantane.length ; y++) {					
				if(instantane[x][y])						//si la cellule est vivante alors on incr�mente le nombre de cellule vivante trouv�es
					nbrVivant++;
			}
		}
				
		if(nbrVivant == 3)									//si il y a exactement trois cellule vivante dans l'instantan� alors la cellule naitra � la prochaine g�n�ration
			bRetour=true;
		
		return bRetour;
	}

	private boolean doitSurvivre(boolean[][] instantane) {	//r�pond � la question : Cette cellule n'existe pas, doit elle survivre dans la prochaine g�n�ration ?
		int nbrVivant = -1;									//sans compter la cellule analyz� qui est forc�ment vivante � l'appel de cette m�thode
		boolean bRetour = false;
		for(int x=0 ; x<instantane.length ; x++) {			//parcours la grille de l'instantan� pour trouv� les cellule vivante	
			for(int y=0 ; y<instantane.length ; y++) {		
				if(instantane[x][y])
					nbrVivant++;
			}
		}
				
		if(nbrVivant >=2 && nbrVivant <= 3)					//si 2 ou 3 cellule vivante entour une cellule vide alors elle pourra naitre � la prochaine g�n�ration
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
