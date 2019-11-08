package tchateau.javase.gameoflife.bll;

import javax.swing.Timer;

public interface IterationManager {
	public void addTimer(Timer timer);						//configurer le timer qui donne le temps d'une itération
	public void startIterationTimer();						//démarre le timer
	public void stopIterationTimer();						//stop le timer
	public boolean[][] jouer(boolean[][] grilleEntree) ;	//joue une itération de la simulation 
	public int getNbrIteration();							//Renvois le nombre d'itération jouée
	public void changeStatusTimerIteration();				//change l'état du timer (On/Off)
}
