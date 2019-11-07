package tchateau.javase.gameoflife.ressources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class RessourcesListener {
	
	private static Properties prop = null;
	
	public RessourcesListener() {
		prop = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(System.getProperty("user.dir")+"\\src\\tchateau\\javase\\gameoflife\\ressources\\config.properties");
			try {
				prop.load(in);
			} catch (IOException e) {
				System.out.println("loading of ressource's file");
				e.printStackTrace();
			} 
		} catch (FileNotFoundException e) {
			System.out.println("propertie's file is not find");
			e.printStackTrace();
		}
	}
	
	public int readcellside() {
		return Integer.parseInt(prop.getProperty("cellside"));
	}
	public void writecellside(int value) {
		prop.setProperty("cellside", Integer.toString(value));
	}
	public int readgrilleside() {
		return Integer.parseInt(prop.getProperty("grilleside"));
	}
	public void writegrilleside(int value) {
		prop.setProperty("grilleside", Integer.toString(value));
	}

	public int readTimer() {
		return Integer.parseInt(prop.getProperty("timer"));
	}
}
