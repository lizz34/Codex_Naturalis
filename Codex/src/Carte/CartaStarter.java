/**
 * 
 */
package Carte;

/**
 * 
 */
public class CartaStarter extends Carta{

	public CartaStarter(Angolo[] ang, Colore col, Disegno[] dis) {
		super(ang, col, dis);
	}

	/*
	 * istanzia una carta partendo da una stringa scritta in questo formato:
	 * true,fungo-true,foglia-true,lupo-true,farfalla-true,null-true,foglia-true,
	 * null-true,farfalla-farfalla,null,null
	 */
	public static CartaStarter parseString(String data) {
		String divisorio[] = data.split("-"); // divido l'intera riga per "-"
		// System.out.println(divisorio.length);

		// ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore
		// col, Disegno dis[]) )
		Angolo angoli[] = new Angolo[8];
		Disegno dis[] = new Disegno[3];

		for (int j = 0; j < (divisorio.length - 1); j++) { // scorro la riga, ora divisa in 9 elementi (8angoli, // 1per
															// disegni dietro)
			String[] infoAngolo = divisorio[j].split(","); // divido ogni elemento in due sottoelementi
			if (infoAngolo[0].equals("true")) { // se true devo creare l'angolo (ricordiamo Angolo(boolean fronte,
												// Disegno disegno) )
				Disegno disegno = null;
				boolean fronte;

				if (j < (divisorio.length / 2)) // la metà del vettore segna la divisione tra angoli fronte 0-3 e angoli
												// retro 4-7
					fronte = true;
				else
					fronte = false;

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
				case "null":
					disegno = null;
					break;
				default: // se si toglie il case: null --> va tolto anche il default
					System.out.println("Errore lettura disegno 1 - Starter");
					disegno = null;
					break;
				}
				angoli[j] = new Angolo(fronte, disegno);

			} else
				angoli[j] = null;
		}

		String[] infoDisegni = divisorio[divisorio.length - 1].split(","); // divido i tre disegni nell'ultimo segmento
		for (int j = 0; j < (dis.length); j++) {
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
				System.out.println("Errore lettura disegno 2 - starter");
				dis[j] = null;
				break;
			}
		}

		return new CartaStarter(angoli, Colore.giallo, dis); //colore giallo per la carta starter 
	}
}
