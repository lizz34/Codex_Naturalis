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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartaRisorsa)) return false;
        if (!super.equals(o)) return false;
        CartaRisorsa cr = (CartaRisorsa) o;
        return (this.punti == cr.punti);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Integer.hashCode(this.punti);
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
	}
	
	/***
	 * metodo per copiare una carta risorsa
	 * @return ritorna una nuova carta risorsa uguale a quella chiamata
	 */
	public CartaRisorsa deepCopy() {
		return new CartaRisorsa(super.getAngoli(), super.getColore(), super.getDisegni(), this.getPunti());
	}
	
}

