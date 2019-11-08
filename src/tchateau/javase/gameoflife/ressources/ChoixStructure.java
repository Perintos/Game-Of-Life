package tchateau.javase.gameoflife.ressources;

import javax.swing.JComboBox;

public class ChoixStructure extends JComboBox<String> {
	public ChoixStructure(){
		RessourcesListener rl = new RessourcesListener();
		String s = rl.readStructure();
		String[] ss = s.split(",");
		for(String chaine : ss) {
			this.addItem(chaine);
		}
	}
}
