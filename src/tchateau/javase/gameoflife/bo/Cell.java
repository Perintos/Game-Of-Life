package tchateau.javase.gameoflife.bo;

import tchateau.javase.gameoflife.ressources.RessourcesListener;

public class Cell {
	private static int cote = 10;
	
	public Cell() {
		RessourcesListener rl = new RessourcesListener();
		Cell.cote = rl.readcellside();
	}

	public static int getCote() {
		return Cell.cote;
	}

	public static void setCote(int cote) {
		Cell.cote = cote;
	}	
}
