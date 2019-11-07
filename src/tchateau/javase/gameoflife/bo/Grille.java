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
		
//		grille[10][10] = true;
//		grille[11][10] = true;
//		grille[12][10] = true;
//		grille[13][10] = true;
//		grille[14][10] = true;	
		
		
//		grille[10][10] = true;
//		grille[10][11] = true;
//		grille[11][11] = true;
//		grille[11][10] = true;
//		grille[10][9] = true;
		
		grille[10][10] = true;
		grille[10][11] = true;
		grille[11][12] = true;
		grille[12][11] = true;
		grille[12][10] = true;
		grille[12][9] = true;
		grille[11][9] = true;

		
	}

	public int getCote() {
		// TODO Auto-generated method stub
		return grille.length;
	}

	public boolean get(int x, int y) {
		// TODO Auto-generated method stub
		return grille[x][y];
	}
	
	public static void changeStatusCell(int x, int y) {
		// TODO Auto-generated method stub
		if(grille[x][y])
			grille[x][y] = false;
		else
			grille[x][y] = true;
	}

	public static boolean[][] getGrille() {
		return grille;
	}
	public static void setGrille(boolean[][] grille) {
		Grille.grille = grille;
		
//		for(int x=0 ; x<grille.length ; x++) {
//			for(int y=0 ; y<grille.length ; y++) {
//				Grille.grille[x][y] = grille[x][y];
//			}
//		}
	}
}
