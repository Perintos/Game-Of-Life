package tchateau.javase.gameoflife.bll;

public class InterfaceManagerFact {
	public static InterfaceManagerImpl getInstance() {
		return new InterfaceManagerImpl();
	}
}
