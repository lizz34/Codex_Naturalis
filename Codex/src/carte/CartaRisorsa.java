package Carte;

public class CartaRisorsa extends Carta{
	private int punti;
	
	public CartaRisorsa(Angolo ang[], Colore col, Disegno dis[], int punti) {
		super (ang, col, dis);
		this.punti=punti;
		
	}

	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}
}

