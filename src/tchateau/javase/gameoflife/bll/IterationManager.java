package tchateau.javase.gameoflife.bll;

import javax.swing.Timer;

public interface IterationManager {
	public void addTimer(Timer timer);
	public void startIterationTimer();
	public void stopIterationTimer();
	public boolean[][] jouer(boolean[][] grilleEntree) ;
	public int getNbrIteration();
	public void changeStatusTimerIteration();
}
