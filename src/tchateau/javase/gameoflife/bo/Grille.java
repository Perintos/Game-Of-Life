package tchateau.javase.gameoflife.bo;

import tchateau.javase.gameoflife.ressources.RessourcesListener;

public class Grille {
	private static boolean[][] grille;		//tableau de la grille qui indique quelle cellule est vivante et lesquelle ne le sont pas. 
	
	public Grille() {
		RessourcesListener rl = new RessourcesListener();
		grille = new boolean[rl.readgrilleside()][rl.readgrilleside()];		//initialise le tableau de boolean à false;
		for(int x=0 ; x<grille.length ; ++x) {
			for(int y=0 ; y<grille.length ; ++y) {
				grille[x][y]=false;
			}
		}
		
//		grille[10][10] = true;				//état initiale que l'on souhaite obtenir
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

	public int getCote() {						//getteur et setteur
		return grille.length;
	}

	public boolean get(int x, int y) {
		return grille[x][y];
	}

	public static boolean[][] getGrille() {				
		return grille;
	}
	
	public static void setGrille(boolean[][] grille) {
		Grille.grille = grille;
	}
	
	public static void changeStatusCell(int x, int y) {		//change le status d'une cellule vers vivant ou mort en fonction de l'état courant
		// TODO Auto-generated method stub
		if(grille[x][y])
			grille[x][y] = false;
		else
			grille[x][y] = true;
	}
}
