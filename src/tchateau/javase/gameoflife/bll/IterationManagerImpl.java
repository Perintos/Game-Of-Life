package tchateau.javase.gameoflife.bll;


import javax.swing.Timer;

public class IterationManagerImpl implements IterationManager {
	Timer iterationTimer;
	int nbrIteration = 0;

	@Override
	public void startIterationTimer() {
		iterationTimer.start();
		
	}

	@Override
	public void stopIterationTimer() {
		iterationTimer.stop();		
	}

	@Override
	public boolean[][] jouer(boolean[][] grilleEntree) {
		boolean[][] grilleRetour = new boolean[grilleEntree.length][grilleEntree.length];
		boolean[][] instantané;
		
		for(int x=0 ; x<grilleEntree.length ; x++) {
			for(int y=0 ; y<grilleEntree.length ; y++) {
				grilleRetour[x][y] = grilleEntree[x][y];
			}
		}
		

		for(int x=0 ; x<grilleEntree.length ; x++) {
			for(int y=0 ; y<grilleEntree.length ; y++) {
				
				instantané = genererInstantane(grilleEntree, x, y);
				
				if(instantané[1][1]) {
					if(!doitSurvivre(instantané))
						grilleRetour[x][y] = false;
				}
				else {
					if(doitNaitre(instantané))
						grilleRetour[x][y] = true ;
				}
			}
		}
		
		nbrIteration++;
		return grilleRetour;
	}

	private boolean[][] genererInstantane(boolean[][] grilleEntree, int x, int y) {
		boolean[][] instantane = new boolean[3][3];
		
		
		try {
			instantane[0][0] = grilleEntree[x-1][y-1];
		}catch(Exception e){
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
		
		instantane[1][1] = grilleEntree[x][y];
		
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

	private boolean doitNaitre(boolean[][] instantane) {
		int nbrVivant = 0;
		boolean bRetour = false;
		for(int x=0 ; x<instantane.length ; x++) {
			for(int y=0 ; y<instantane.length ; y++) {
				if(instantane[x][y])
					nbrVivant++;
			}
		}
				
		if(nbrVivant == 3)
			bRetour=true;
		
		return bRetour;
	}

	private boolean doitSurvivre(boolean[][] instantane) {
		int nbrVivant = -1;
		boolean bRetour = false;
		for(int x=0 ; x<instantane.length ; x++) {
			for(int y=0 ; y<instantane.length ; y++) {
				if(instantane[x][y])
					nbrVivant++;
			}
		}
				
		if(nbrVivant >=2 && nbrVivant <= 3)
			bRetour=true;
		
		return bRetour;
	}

	public int getNbrIteration() {
		return this.nbrIteration;
	}

	@Override
	public void changeStatusTimerIteration() {
		if(this.iterationTimer.isRunning())
			this.iterationTimer.stop();
		else
			this.iterationTimer.start();
		
	}

	@Override
	public void addTimer(Timer timer) {
		this.iterationTimer = timer;
	}
	
	
}
