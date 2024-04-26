package Carte;

public class CartaOro extends Carta{
	private int punti;
	private final Disegno disegnoRichieste[];
	private final Disegno criterioPunti;
	
	public CartaOro(Angolo ang[], Colore col, Disegno dis[], int punti, Disegno disegnoRichieste[], Disegno criterioPunti) {
		
		super (ang, col, dis);
		
		this.punti = punti;
		
		this.disegnoRichieste = new Disegno[5];
		for(int i = 0; i < 5; i++) {
			this.disegnoRichieste[i] = disegnoRichieste[i];
		}
		
		this.criterioPunti = criterioPunti;
	}
	
	public String toString() {
		String carta = "Colore: " + this.colore + "\n" + 
				(angoli[0] != null)? angoli[0].disegno "-": "---" +
				this.punti + "-" + this.criterioPunti + "  " +
				(angoli[1] != null)? angoli[1].disegno "-": "--- \n" +
				(angoli[4] != null)? angoli[4].disegno "-": "---" +
				this.disegnoRichieste + "  " +
				(angoli[3] != null)? angoli[3].disegno "-": "---";
	}

	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}

	public Disegno[] getDisegnoRichieste() {
		return disegnoRichieste;
	}

	public Disegno getCriterioPunti() {
		return criterioPunti;
	}

}
