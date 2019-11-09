package tchateau.javase.gameoflife.bo;

import tchateau.javase.gameoflife.ressources.RessourcesListener;

public class Cell {
	private static int cote = 10; 		//Taille en pixel du cote d'une cellule 
	
	public Cell() {
		RessourcesListener rl = new RessourcesListener();	
		Cell.cote = rl.readcellside();						//lis le fichier property et renvois la taille par defaut du cote d'une cellule.
	}

	public static int getCote() {							//getteur et setteur
		return Cell.cote;
	}

	public static void setCote(int cote) {
		Cell.cote = cote;
	}	
}
