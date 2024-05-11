/**
 * 
 */
package grafica;

/**
 * 
 */
import java.awt.Color;
import java.io.Serializable;

public class Cella implements Serializable {

	private Color colore;
	private String testo;

	public Cella(String testo, Color colore) {
		this.testo = testo;
		this.colore = colore;
	}

	public char[] getTesto() {
		return this.testo.toCharArray();
	}

	public Color getColore() {
		return colore;
	}

	public void setColore(Color colore) {
		this.colore = colore;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
}
