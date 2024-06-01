package Carte;

public class Angolo {
	private final boolean fronte;
	private boolean occupato;
	private final Disegno disegno;

	/***
	 * costruttore della classe Angolo
	 * @param fronte: se l'angolo considerato si trova sul fronte(true) o sul retro(false) della carta
	 * @param disegno: disegno presente nell'angolo considerato
	 */
	public Angolo(boolean fronte, Disegno disegno) {
		this.fronte = fronte;
		this.disegno = disegno;
		this.occupato = false;
	}

	/***
	 * getter dell'attributo fronte
	 * @return ritorna true se l'angolo si trova sul fronte - false se si trova sul retro
	 */
	public boolean getFronte() {
		return fronte;
	}

	/***
	 * getter occupazione angolo 
	 * @return ritorna true se l'angolo è occupato - false se è libero
	 */
	public boolean getOccupato() {
		return occupato;
	}

	/***
	 * setter occupazione angolo
	 * @param occupato: true se l'angolo viene occupato - false se si libera
	 */
	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	/***
	 * getter disegno sull'angolo
	 * @return ritorna il disegno presente nell'angolo considerato
	 */
	public Disegno getDisegno() {
		return disegno;
	}
	
	/***
	 * @override del metodo toString
	 */
	public String toString() {
		if(occupato == true)
			return "occupato" + " " + disegno;
		else
			return "libero" + " " + disegno;
	}
	
}
