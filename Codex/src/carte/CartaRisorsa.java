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
	}
	
	public static CartaRisorsa parseString(String data) {
		String divisorio[] = data.split("-"); // divido l'intera riga per "-"
		// System.out.println(divisorio.length);

		// ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore col, Disegno dis[]) )
		Colore col;
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
			col = Colore.giallo; // giallo se errore (impossibile perchè risorsa hanno i 4 colori elencati sopra)
			break;
		}

		int contaAngoli = 0;
		for (int j = 2; j < divisorio.length - 1; j++) { // scorro la riga, ora divisa in 9 elementi (8angoli, 1per disegni dietro)
			String[] infoAngolo = divisorio[j].split(","); // divido ogni elemento in due sottoelementi
			if (infoAngolo[0].equals("true")) { // se true devo creare l'angolo (ricordiamo Angolo(boolean fronte, Disegno disegno) 
				Disegno disegno = null;
				boolean fronte;

				if (j < divisorio.length / 2) // la metà del vettore segna la divisione tra angoli fronte 0-3 e angoli retro 4-7
					fronte = true;
				else
					fronte = false;

				switch ((String) infoAngolo[1]) { // assegno angolo
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
					System.out.println("Errore lettura disegno 1 - risorsa");
					disegno = null;
					break;
				}

				angoli[contaAngoli] = new Angolo(fronte, disegno);
			} else
				angoli[contaAngoli] = null;

			contaAngoli++;
		}

		String[] infoDisegni = divisorio[divisorio.length - 1].split(","); // divido i tre disegni nell'ultimo segmento
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
				System.out.println("Errore lettura disegno 2 - risorsa");
				dis[j] = null;
				break;
			}
		}
		return new CartaRisorsa(angoli, col, dis, (Integer.parseInt(divisorio[0])));
	}
	
	
}

