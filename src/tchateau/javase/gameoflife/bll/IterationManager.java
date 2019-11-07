package tchateau.javase.gameoflife.bll;

public interface IterationManager {
	public void startIterationTimer();
	public void stopIterationTimer();
	public boolean[][] jouer(boolean[][] grilleEntree) ;
	public int getNbrIteration();
}
