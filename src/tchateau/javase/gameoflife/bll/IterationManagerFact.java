package tchateau.javase.gameoflife.bll;

public class IterationManagerFact {
	public static IterationManager getInstance() {
		return new IterationManagerImpl();
	}
}
