package Carte;

public class CartaOro extends Carta{
	private int punti;
	private Disegno disegnoRichieste[];
	private Disegno criterioPunti;
	
	public CartaOro(int punti, Disegno disegnoRichieste[], Disegno criterioPunti,Angolo ang[], Colore col, Disegno dis[]) {
		super (ang, col, dis);
		this.punti = punti;
		
		for(int i = 0; i < 5; i++) {
			this.disegnoRichieste[i] = disegnoRichieste[i];
		}
		
		this.criterioPunti = criterioPunti
	}
}
