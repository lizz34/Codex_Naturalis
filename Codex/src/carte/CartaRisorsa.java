package Carte;

public class CartaRisorsa extends Carta{
	private final int punti;
	
	public CartaRisorsa(Angolo ang[], Colore col, Disegno dis[], int punti) {
		super (ang, col, dis);
		
		this.punti=punti;
		
	}

	public int getPunti() {
		return punti;
	}

	public String toString() {
		System.out.println(super.toString());
		return "punti: " + punti;
	}
}

