package obiettivi;

public class CartaObiettivo{
	private static int COUNTER = 0;
	
	private final String obiettivo;
	private final int punti;
	private final int index;
	
	/***
	 * costruttore classe CartaObiettivo
	 * assegna automaticamente un indice incrementale alla carta tramite l'attributo statico COUNTER.
	 * La prima carta obiettivo avrà quindi indice 0.
	 * @param obiettivo: la stringa contenente l'obiettivo della carta
	 */
	public CartaObiettivo(String obiettivo, int punti) {
		this.obiettivo = obiettivo;
		this.punti = punti;
		index = COUNTER++;
	}
	
	public String toString() {
		return this.index + " - " + this.obiettivo;
	}

	/***
	 * getter dell'obiettivo della carta
	 * @return l'obiettivo della carta in formato di stringa 
	 */
	public String getObiettivo() {
		return obiettivo;
	}

	/***
	 * getter dell'indice della carta
	 * @return l'indice numerico della carta obiettivo
	 */
	public int getIndex() {
		return index;
	}

	public int getPunti() {
		return punti;
	}
}
