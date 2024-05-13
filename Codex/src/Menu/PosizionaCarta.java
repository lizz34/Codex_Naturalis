package Menu;

import Giocatori.*;
import java.util.Scanner;
import Ecccezioni.*;

public class PosizionaCarta implements MenuOption {

	Scanner sc = new Scanner(System.in);
	String buffer;
	TavoloDaGioco tavolo;
	
	/***
	 * istanza un oggetto per posizionare una nuova carta
	 * @param tavolo: il tavolo da gioco su cui si sta svolgendo la partita
	 */
	public PosizionaCarta(TavoloDaGioco tavolo) {
		this.tavolo = tavolo;
	}
	
	@Override
	/***
	 * funzione per posizionare una carta sul proprio campo da gioco da quelle che si hanno in mano
	 * @param g: il giocatore che ha scelto l'opzione
	 * @throws CardPlacementException
	 */
	public void execute(Giocatore g) throws CardPlacementException {
		//raccolta dati
		int numCarta=0;
		do {
			System.out.println("Inserisci il numero della carta che vuoi posizionare del tuo mazzo (da 1 a 3): ");
			buffer = sc.nextLine();
			try {
				numCarta = Integer.parseInt(buffer);
			}
			catch(NumberFormatException e) {
				System.out.println("Errore: inserisci un numero intero");
			}
		}
		while(numCarta < 1 || numCarta > 3);
		
		//numero della riga su cui si vuole posizionare la carta
		int nRiga = 0;
		do {
			System.out.println("inserisci il numero della riga della carta su cui vuoi posizionare la nuova carta: ");
			buffer = sc.nextLine();
			try {
				nRiga = Integer.parseInt(buffer);
			}
			catch(NumberFormatException e) {
				System.out.println("Errore: inserisci un numero intero");
			}
		}
		while(nRiga < 0 || nRiga > 49);
		
		//numero della colonna su cui si vuole posizionare la carta
		int nColonna = 0;
		do {
			System.out.println("inserisci il numero della colonna della carta su cui vuoi posizionare la nuova carta: ");
			buffer = sc.nextLine();
			try {
				nColonna = Integer.parseInt(buffer);
			}
			catch(NumberFormatException e) {
				System.out.println("Errore: inserisci un numero intero");
			}
		}
		while(nColonna < 0 || nColonna > 49);
		
		//numero dell'angolo su cui si vuole posizionare la carta
		int nAngolo = 0;
		boolean fronte = true;
		if(g.getCampoPersonale().trovaCarta(nRiga, nColonna).getFronte() != true)
			fronte=false;
			
		do {
			System.out.println("inserisci il numero dell'angolo della carta su cui vuoi posizionare la nuova carta: ");
			buffer = sc.nextLine();
			try {
				nAngolo = Integer.parseInt(buffer);
			}
			catch(NumberFormatException e) {
				System.out.println("Errore: inserisci un numero intero");
			}
		}
		while((fronte==false || (nAngolo < 1 || nAngolo > 4)) && (fronte==true || (nAngolo < 5 || nAngolo > 8)));

		if(!g.posizionaCarta(numCarta-1, nRiga, nColonna, nAngolo-1)) {
			//se la funzione restituisce false c'é stato un errore nel posizionamento della carta
			System.out.println("Errore nel posizionamento della carta"); //aggiungere la specifica dell'errore? tipo c'è già una carta oppure per i criteri dei punti?
			throw new CardPlacementException();
		}
		else {
			//il posizionamento della carta è avvenuto con successo, l'utente deve pescare una nuova carta da quelle presenti sul tavolo da gioco

			//scelta del tipo di carta che l'utente vuole pescare
			String tipoCartaPescata;
			do {
				System.out.println("Inserisci il tipo di carta che vuoi pescare (oro/risorsa): ");
				tipoCartaPescata = sc.nextLine();
				tipoCartaPescata.toLowerCase();
				tipoCartaPescata.trim();
			}while(!(tipoCartaPescata.equals("oro") || tipoCartaPescata.equals("risorsa")));
			
			//scelta del numero della carta che l'utente vuole pescare
			//(indipendentemente dal tipo, sia oro che risorsa hanno due carte sul tavolo da gioco)
			numCarta=0;
			do {
				System.out.println("Inserisci il numero di carta che vuoi pescare (1/2): ");
				buffer = sc.nextLine();
				try {
					numCarta = Integer.parseInt(buffer);
				}
				catch(NumberFormatException e) {
					System.out.println("Errore: inserisci un numero intero");
				}
			}
			while(numCarta < 1 || numCarta > 2);
			
			//richiamo classe per pescare una nuova carta dal tavolo
			new PescaCarta().pesca(g, tipoCartaPescata, numCarta, tavolo);

		}
	}
	
	/***
	 * @return il nome esteso dell'opzione del menu
	 */
	public String toString() {
		return "posiziona una nuova carta";
	}
}
