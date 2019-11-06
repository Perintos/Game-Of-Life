package tchateau.javase.gameoflife.bll;


import javax.swing.Timer;

import tchateau.javase.gameoflife.ihm.PanelGraphics;

public class IterationManagerImpl implements IterationManager {
	Timer iterationTimer;
	
	public IterationManagerImpl(PanelGraphics pan){
		iterationTimer = new Timer(1000, pan);
	}

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
		boolean[][] grilleRetour = null;
		
		int x=1;
		int y=1;
		
		boolean[][] instantané = genererInstantane(grilleEntree, x, y);

//		for(int x=0 ; x<grilleEntree.length ; x++) {
//			for(int y=0 ; y<grilleEntree.length ; y++) {
//				if(grilleEntree[x][y]) {
//					boolean[][] instantané = genererInstantane(grilleEntree, x, y);
//					grilleRetour[x][y] = doitSurvivre(instantané);
//				}
//				else {
//					grilleRetour[x][y] = doitVivre(grilleEntree);
//				}
//			}
//		}
		
		return grilleRetour;
	}

	private boolean[][] genererInstantane(boolean[][] grilleEntree, int x, int y) {
		boolean[][] instantane = new boolean[3][3];
		
		instantane[1][2] = grilleEntree[x][y];
		
		try {
			instantane[0][0] = grilleEntree[x-1][y+1];
		}catch(Exception e){
			instantane[0][0] = false;
		}
		
		try {
			instantane[1][0] = grilleEntree[x][y + 1];
		} catch (Exception e) {
			instantane[1][0] = false;
		}
		
		try {
			instantane[2][0] = grilleEntree[x+1][y+1];
		} catch (Exception e) {
			instantane[2][0] = false;
		}
		
		try {
			instantane[2][1] = grilleEntree[x+1][y];
		} catch (Exception e) {
			instantane[2][1] = false;
		}
		
		try {
			instantane[2][2] = grilleEntree[x+1][y-1];
		} catch (Exception e) {
			instantane[2][2] = false;
		}
		
		try {
			instantane[1][2] = grilleEntree[x+1][y];
		} catch (Exception e) {
			instantane[1][2] = false;
		}
		
		try {
			instantane[0][2] = grilleEntree[x+1][y-1];
		} catch (Exception e) {
			instantane[0][2] = false;
		}
		
		try {
			instantane[0][1] = grilleEntree[x-1][y];
		} catch (Exception e) {
			instantane[0][1] = false;
		}
		
		return instantane;
	}

	private boolean doitVivre(boolean[][] grilleEntree) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean doitSurvivre(boolean[][] grilleEntree) {
		
		return false;
	}
}
