package tchateau.javase.gameoflife.bll;

import javax.swing.Timer;

public interface IterationManager {
	public void addTimer(Timer timer);						//configurer le timer qui donne le temps d'une it�ration
	public void startIterationTimer();						//d�marre le timer
	public void stopIterationTimer();						//stop le timer
	public boolean[][] jouer(boolean[][] grilleEntree) ;	//joue une it�ration de la simulation 
	public int getNbrIteration();							//Renvois le nombre d'it�ration jou�e
	public void changeStatusTimerIteration();				//change l'�tat du timer (On/Off)
}
