package Carte;

public class CartaOro extends Carta{
	
	private final int punti;
	//le 5 richieste sul basso della carta necessarie per posizionarla
	private final Disegno disegnoRichieste[];
	//il criterio in alto necessario per il conteggio dei punti
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
	public static CartaOro parseString(String data) {
		String divisorio[] = data.split("-"); // divido l'intera riga per "-"
		// System.out.println(divisorio.length);

		// ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore
		// col, Disegno dis[]) )
		Colore col;
		Disegno criterioPunti;
		Disegno disRichieste[] = new Disegno[5];
		Angolo angoli[] = new Angolo[8];
		Disegno dis[] = new Disegno[3];

		switch (divisorio[1]) {
		case "rosso":
			col = Colore.rosso;
			break;
		case "blu":
			col = Colore.blu;
			break;
		case "verde":
			col = Colore.verde;
			break;
		case "viola":
			col = Colore.viola;
			break;
		default:
			System.out.println("Errore lettura colore");
			col = Colore.giallo; // giallo se errore (impossibile perchè risorsa hanno i 4 colori elencati
									// sopra)
			break;
		}

		switch (divisorio[2]) {
		case "boccetta":
			criterioPunti = Disegno.boccetta;
			break;
		case "piuma":
			criterioPunti = Disegno.piuma;
			break;
		case "pergamena":
			criterioPunti = Disegno.pergamena;
			break;
		case "angoloSovrapposto":
			criterioPunti = Disegno.angoloSovrapposto;
			break;
		case "null":
			criterioPunti = null;
			break;
		default:
			System.out.println("Errore lettura criterio assegnazione punti");
			criterioPunti = null;
			break;
		}

		String[] infoRichieste = divisorio[3].split(",");
		for (int j = 0; j < 5; j++) {
			switch (infoRichieste[j]) {
			case "foglia":
				disRichieste[j] = Disegno.foglia;
				break;
			case "farfalla":
				disRichieste[j] = Disegno.farfalla;
				break;
			case "lupo":
				disRichieste[j] = Disegno.lupo;
				break;
			case "fungo":
				disRichieste[j] = Disegno.fungo;
				break;
			case "null":
				disRichieste[j] = null;
				break;
			default:
				System.out.println("Errore lettura richieste per posizionare carta");
				disRichieste[j] = null;
				break;
			}
		}

		int contaAngoli = 0;
		for (int j = 4; j < divisorio.length - 1; j++) { // scorro la riga, ora divisa in 9 elementi (8angoli,
															// 1per disegni dietro)
			String[] infoAngolo = divisorio[j].split(","); // divido ogni elemento in due sottoelementi
			if (infoAngolo[0].equals("true")) { // se true devo creare l'angolo (ricordiamo Angolo(boolean
												// fronte, Disegno disegno) )
				Disegno disegno = null;
				boolean fronte;

				if (j < divisorio.length / 2) // la metà del vettore segna la divisione tra angoli fronte 0-3 e
												// angoli retro 4-7
					fronte = true;
				else
					fronte = false; // FIXME !!!!

				switch (infoAngolo[1]) { // assegno angolo
				case "foglia":
					disegno = Disegno.foglia;
					break;
				case "farfalla":
					disegno = Disegno.farfalla;
					break;
				case "lupo":
					disegno = Disegno.lupo;
					break;
				case "fungo":
					disegno = Disegno.fungo;
					break;
				case "pergamena":
					disegno = Disegno.pergamena;
					break;
				case "piuma":
					disegno = Disegno.piuma;
					break;
				case "boccetta":
					disegno = Disegno.boccetta;
					break;
				case "null":
					disegno = null;
					break;
				default: // se si toglie il case: null --> va tolto anche il default
					System.out.println("Errore lettura disegno 1 - oro");
					disegno = null;
					break;
				}

				angoli[contaAngoli] = new Angolo(fronte, disegno);

			} else
				angoli[contaAngoli] = null;

			contaAngoli++;
		}

		String[] infoDisegni = divisorio[divisorio.length - 1].split(","); // divido i tre disegni nell'ultimo
																			// segmento
		for (int j = 0; j < dis.length; j++) {
			switch (infoDisegni[j]) {
			case "lupo":
				dis[j] = Disegno.lupo;
				break;
			case "farfalla":
				dis[j] = Disegno.farfalla;
				break;
			case "fungo":
				dis[j] = Disegno.fungo;
				break;
			case "foglia":
				dis[j] = Disegno.foglia;
				break;
			case "null":
				dis[j] = null;
				break;
			default:
				System.out.println("Errore lettura disegno 2 - oro");
				dis[j] = null;
				break;
			}
		}
		return new CartaOro(angoli, col, dis, (Integer.parseInt(divisorio[0])), disRichieste, criterioPunti);
	}
}