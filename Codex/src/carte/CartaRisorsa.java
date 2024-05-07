package Carte;

public class CartaRisorsa extends Carta{
	
	private final int punti;
	
	/***
	 * Costruttore classe CartaRisorsa
	 * @param ang: vettore con gli 8 angoli della carta
	 * @param col: colore della carta
	 * @param dis: vettore con i 3 disegni sul retro della carta
	 * @param punti: punteggio che la carta fa guadagnare una volta giocata
	 */
	public CartaRisorsa(Angolo ang[], Colore col, Disegno dis[], int punti) {
		super (ang, col, dis);
		this.punti=punti;
	}

	/***
	 * getter punteggio della carta
	 * @return ritorna il punteggio che la carta fa guadagnare una volta giocata
	 */
	public int getPunti() {
		return punti;
	}

	/***
	 * @Override del metodo toString con richiamo alla superclasse Carta
	 */
	public String toString() {
		System.out.println(super.toString());
		return "punti: " + punti;
	} //TODO DA SISTEMARE LA STAMPA PERCHE' STAMPA I PUNTI SOLO ALLA FINE
}

