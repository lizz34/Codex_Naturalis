package Carte;

public class CartaOro extends Carta{
	private final int punti;
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
	

	public int getPunti() {
		return punti;
	}

	public Disegno[] getDisegnoRichieste() {
		return disegnoRichieste;
	}

	public Disegno getCriterioPunti() {
		return criterioPunti;
	}
	
	public String toString(){
		super.toString();
		System.out.println("Criterio del punteggio: " + criterioPunti);
		System.out.print("Richieste per posizionare la carta: ");
		for(int i = 0; i < 5; i++) {
			System.out.print(disegnoRichieste[i]+" ");
		}
		return "\nPunti: " + punti;
	}
}