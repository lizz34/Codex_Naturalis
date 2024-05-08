package Carte;

public class CartaOro extends Carta{
	
	private final int punti;
	private final Disegno disegnoRichieste[];
	private final Disegno criterioPunti;
	
	/***
	 * Costruttore classe CartaOro
	 * @param ang: vettore con gli 8 angoli della carta
	 * @param col: colore della carta
	 * @param dis: vettore con i 3 disegni sul retro della carta
	 * @param punti: punteggio che la carta fa guadagnare una volta giocata
	 * @param disegnoRichieste: vettore con i 5 disegni richiesti sul campo di gioco per poter posizionare la carta 
	 * @param criterioPunti: criterio di assegnazione dei punti
	 */
	public CartaOro(Angolo ang[], Colore col, Disegno dis[], int punti, Disegno disegnoRichieste[], Disegno criterioPunti) {
		
		super (ang, col, dis);
		
		this.punti = punti;
		
		this.disegnoRichieste = new Disegno[5];
		for(int i = 0; i < 5; i++) {
			this.disegnoRichieste[i] = disegnoRichieste[i];
		}
		
		this.criterioPunti = criterioPunti;
	}
	
	/***
	 * getter punti
	 * @return ritorna il numero di punti che la carta fa guadagnare
	 */
	public int getPunti() {
		return punti;
	}

	/***
	 * getter disegni richiesti sul campo
	 * @return ritorna un vettore con i 5 disegni richiesti sul campo di gioco per poter giocare la carta
	 */
	public Disegno[] getDisegnoRichieste() {
		return disegnoRichieste;
	}

	/***
	 * getter criterio punti
	 * @return ritorna il criterio di assegnazione dei punti della carta
	 */
	public Disegno getCriterioPunti() {
		return criterioPunti;
	}
	
	/***
	 * @override del metodo toString (con richiamo alla superclasse Carta)
	 */
	public String toString(){
		super.toString();
		System.out.print("Criterio del punteggio: ");
		if(criterioPunti!=null)
			System.out.println(criterioPunti);
		else
			System.out.println("nessuno");
		System.out.print("Richieste per posizionare la carta: ");
		for(int i = 0; i < 5; i++) {
			System.out.print(disegnoRichieste[i]+" ");
		}
		return "\nPunti: " + punti;
	}
}