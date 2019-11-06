package tchateau.javase.gameoflife.bo;

import tchateau.javase.gameoflife.ressources.RessourcesListener;

public class Grille {
	private static boolean[][] grille;
	
	public Grille() {
		RessourcesListener rl = new RessourcesListener();
		grille = new boolean[rl.readgrilleside()][rl.readgrilleside()];
		for(int x=0 ; x<grille.length ; ++x) {
			for(int y=0 ; y<grille.length ; ++y) {
				grille[x][y]=false;
			}
		}
		
		grille[0][0] = true;
		grille[1][0] = true;
		grille[1][1] = true;
		grille[1][2] = true;
		
		System.out.println("ok");
		
	}

	public int getCote() {
		// TODO Auto-generated method stub
		return grille.length;
	}

	public boolean get(int x, int y) {
		// TODO Auto-generated method stub
		return grille[x][y];
	}

	public boolean[][] getGrille() {
		return grille;
	}
}
