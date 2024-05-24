package obiettivi;

import Giocatori.CampoDaGioco;

public class CartaObiettivo implements ControlloObiettivo{
	private static int COUNTER = 0;
	
	protected final String obiettivo;
	protected final int punti;
	protected final int index;
	
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

	/***
	 * getter dei punti della carta
	 * @return i punti della carta obiettivo
	 */
	public int getPunti() {
		return punti;
	}

	@Override
	public int calcoloObiettivo(CampoDaGioco campoPersonale) {
		// TODO Auto-generated method stub
		return 0;
	}

}
