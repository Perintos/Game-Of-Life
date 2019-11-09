package tchateau.javase.gameoflife.bll;

import javax.swing.Timer;

public interface IterationManager {
	public void addTimer(Timer timer);						//configurer le timer qui donne le temps d'une iteration
	public void startIterationTimer();						//demarre le timer
	public void stopIterationTimer();						//stop le timer
	public boolean[][] jouer(boolean[][] grilleEntree) ;	//joue une iteration de la simulation 
	public int getNbrIteration();							//Renvois le nombre d'iteration jouee
	public void changeStatusTimerIteration();				//change l'etat du timer (On/Off)
}
