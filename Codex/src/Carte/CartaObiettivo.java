package Carte;

public class CartaObiettivo {
	private final String obiettivo;
	
	/***
	 * costruttore classe CartaObiettivo
	 * @param obiettivo: 
	 */
	public CartaObiettivo(String obiettivo) {
		this.obiettivo=obiettivo;
	}

	/***
	 * getter dell'obiettivo della carta
	 * @return l'obiettivo della carta in formato di stringa 
	 */
	public String getObiettivo() {
		return obiettivo;
	}
}
