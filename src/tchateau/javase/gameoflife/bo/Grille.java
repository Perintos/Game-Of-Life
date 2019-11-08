package tchateau.javase.gameoflife.bo;

import tchateau.javase.gameoflife.ressources.RessourcesListener;

public class Grille {
	private static boolean[][] grille;		//tableau de la grille qui indique quelle cellule est vivante et lesquelle ne le sont pas.
	private int posX=0;
	private int posY=0;
	
	public Grille() {
		RessourcesListener rl = new RessourcesListener();
		grille = new boolean[rl.readgrilleside()][rl.readgrilleside()];		//initialise le tableau de boolean à false;
		for(int x=0 ; x<grille.length ; ++x) {
			for(int y=0 ; y<grille.length ; ++y) {
				grille[x][y]=false;
			}
		}
	
									//les structures ocilantes (de haut en bas : clignotant, balise, crapeau)
		
//		grille[5][5] = true;				
//		grille[6][5] = true;
//		grille[7][5] = true;			
//		grille[10][10] = true;				
//		grille[11][10] = true;
//		grille[10][11] = true;	
//		grille[11][11] = true;				
//		grille[8][12] = true;				
//		grille[9][12] = true;				
//		grille[8][13] = true;				
//		grille[9][13] = true;				
//		grille[20][20] = true;
//		grille[19][21] = true;	
//		grille[19][22] = true;	
//		grille[22][21] = true;	
//		grille[22][22] = true;	
//		grille[21][23] = true;	
		
									//planer
		
//		grille[10][10] = true;				
//		grille[10][11] = true;
//		grille[10][12] = true;	
//		grille[9][12] = true;	
//		grille[8][11] = true;		
		
									//evolution vers la ruche
		
//		grille[12][10] = true;				
//		grille[13][10] = true;
//		grille[14][10] = true;	
//		grille[15][10] = true;	
		
									//clignotant x 4
		
//		grille[12][10] = true;				
//		grille[13][10] = true;
//		grille[14][10] = true;	
//		grille[15][10] = true;	
//		grille[16][10] = true;	
		
									//structure mourante
		
//		grille[10][10] = true;				
//		grille[10][11] = true;
//		grille[11][11] = true;
//		grille[11][10] = true;
//		grille[10][9] = true;
		
									//evlution vers 4 ruches
	
//		grille[10][10] = true;
//		grille[10][11] = true;
//		grille[11][12] = true;
//		grille[12][11] = true;
//		grille[12][10] = true;
//		grille[12][9] = true;
//		grille[11][9] = true;
		
									//evoultion longue vers 4 blocs
		
//		grille[20][20] = true;
//		grille[21][21] = true;
//		grille[22][22] = true;
//		grille[21][20] = true;
//		grille[22][21] = true;
//		grille[23][22] = true;

									//Macro Structure avec 5 cellule vivante au départ
		
//		grille[30][20] = true;
//		grille[21][21] = true;
//		grille[22][22] = true;
//		grille[21][20] = true;
//		grille[22][21] = true;
//		grille[23][22] = true;
		
									//pulsar (ocilateur de periode 3)
		
//		grille[12][10] = true;				
//		grille[13][10] = true;
//		grille[14][10] = true;	
//		grille[15][10] = true;	
//		grille[16][10] = true;
//		grille[12][10+4] = true;				
//		grille[13][10+4] = true;
//		grille[14][10+4] = true;	
//		grille[15][10+4] = true;	
//		grille[16][10+4] = true;
//		grille[12][12] = true;
//		grille[16][12] = true;		

									//Pentadecathlon (ocilateur de periode 15)
		
//		grille[12][10] = true;				
//		grille[13][10] = true;
//		grille[14][10] = true;	
//		grille[15][10] = true;	
//		grille[16][10] = true;		
//		grille[17][10] = true;
//		grille[18][10] = true;
//		grille[19][10] = true;
//		grille[20][10] = true;
//		grille[21][10] = true;			
	}

	public int getCote() {						//getteur et setteur
		return grille.length;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
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

	public void randomiser() {
		for(int x=0 ; x<grille.length ; ++x) {
			for(int y=0 ; y<grille.length ; ++y) {
				
				if((int)(Math.random() * 2) == 0)
					grille[x][y]=false;
				else
					grille[x][y]=true;
			}
		}
	}
}
