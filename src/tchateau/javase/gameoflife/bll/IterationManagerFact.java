package tchateau.javase.gameoflife.bll;

import tchateau.javase.gameoflife.ihm.PanelGraphics;

public class IterationManagerFact {
	public static IterationManager getInstance(PanelGraphics pan) {
		return new IterationManagerImpl(pan);
	}
}
