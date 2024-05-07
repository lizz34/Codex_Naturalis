package Main;

import Carte.*;
import Giocatori.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//TODO FUNZIONE PER PULIRE LA CONSOLE?? chatgpt non ha aiutato :(
//devi farlo tu a mano, é il quarto bottone partendo da sinistra della console
public class Main {
	
	private static Giocatore[] giocatori;

	public static void main(String[] args) {
		TavoloDaGioco tavolo = new TavoloDaGioco();
		Scanner sc = new Scanner(System.in);
		String buffer;
		int nGiocatori=0;
		Giocatore[] giocatori;
		
		//Inserimento numero giocatori
		do {
			System.out.println("Digitare il numero di giocatori (da 2 a 4) che parteciperanno alla partita: ");
			buffer = sc.nextLine();
			try {
				nGiocatori = Integer.parseInt(buffer);
			}
			catch(NumberFormatException e) {
				System.out.println("Errore: l'input non è un numero intero");
			}
		}while(nGiocatori<2 || nGiocatori>4);
		
		
		//Implementazione dei giocatori con tutte le carte iniziali
		giocatori = new Giocatore[nGiocatori];
		for(int i=0; i<nGiocatori; i++) {
			ArrayList<Carta> manoIniziale = new ArrayList<Carta>();
			manoIniziale.add(tavolo.pescaCartaRisorsa());
			manoIniziale.add(tavolo.pescaCartaRisorsa());
			manoIniziale.add(tavolo.pescaCartaOro());
			giocatori[i] = new Giocatore(tavolo.pescaCartaObiettivo(), tavolo.pescaCartaStarter(), manoIniziale);
		}
		
		//System.out.println(giocatori[0].campoPersonale.posizionaCarta(giocatori[0].getCartaMano(2), 24, 24, 1));
		
		int turnoGiocatore=0;
		int scelta=0;
		
		//INIZIO DELLA PARTITA
		/*La rotazione dei turni è gestita dalla var turnoGiocatore che parte da 0 e si incrementa fino al numero massimo dei giocatori,
		 * per poi riazzerarsi. In questo modo il vettore giocatori[] scorre dal giocatore 0 al giocare nGiocatori e tutti giocano un turno
		 */
		while(tavolo.condEndGame1()==false) { //+altra condizione dei 20 punti
			System.out.println("\nTurno del giocatore " + (turnoGiocatore+1));
			
			//variabile booleana per capire se il turno puo' passare al giocatore successivo
			//diventa true solo nel caso in cui la carta scelta dal giocatore sia stata correttamente posizionata nella sua matrice
			boolean continua = false;
			//menu operazioni che un giocatore può fare
			do {
				
				do{
					System.out.println("1- Posiziona una carta sull'area di gioco");
					System.out.println("2- Guarda le carte che puoi pescare");
					System.out.println("3- Guarda la tua area di gioco");
					System.out.println("4- Guarda la tua mano");
					buffer = sc.nextLine();
					try {
						scelta = Integer.parseInt(buffer);
					}
					catch(InputMismatchException e) {
						System.out.println("Errore: l'input non è un numero intero");
					}
				}while(scelta<1 || scelta>4);
				
				
				switch(scelta) {
				case 1:
					//posiziona carta dalla mano alla matrice
					//paramentri necessari: carta da posizionare, nRiga, nColonna e nAngolo della carta da sovrapporre
					
					//numero carta da posizionare
					int numCarta=0;
					do {
						System.out.println("Inserisci il numero della carta che vuoi posizionare del tuo mazzo (da 1 a 3): ");
						buffer = sc.nextLine();
						try {
							numCarta = Integer.parseInt(buffer);
						}
						catch(InputMismatchException e) {
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
						catch(InputMismatchException e) {
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
						catch(InputMismatchException e) {
							System.out.println("Errore: inserisci un numero intero");
						}
					}
					while(nColonna < 0 || nColonna > 49);
					
					//numero dell'angolo su cui si vuole posizionare la carta
					int nAngolo = 0;
					do {
						System.out.println("inserisci il numero dell'angolo della carta su cui vuoi posizionare la nuova carta: ");
						buffer = sc.nextLine();
						try {
							nAngolo = Integer.parseInt(buffer);
						}
						catch(InputMismatchException e) {
							System.out.println("Errore: inserisci un numero intero");
						}
					}
					while(nAngolo < 0 || nAngolo > 7);
					
					//raccolti correttamente i parametri chiamata alla funzione del giocatore per posizionare la carta
					if(!giocatori[turnoGiocatore].posizionaCarta(numCarta-1, nRiga, nColonna, nAngolo)) {
						//se la funzione restituisce false c'é stato un errore nel posizionamento della carta
						System.out.print("errore: riprova a posizionare la carta sul tuo tavolo di gioco di gioco.");
						continua = false;
					}
					else {
						//TODO: prima di farlo continuar bisogna fargli pescare una carta da quelle presenti sul tavolo da gioco
						continua = true;
					}
					break;
				case 2:
					//stampa delle carte presenti sul tavolo da gioco che il giocatore puo' pescare
					System.out.println("Carte risorsa che puoi pescare:");
					for(CartaRisorsa c: tavolo.getCarteRisorsaBanco()) {
						System.out.println(c.toString() + "\n");
					}
					System.out.println("Carte oro che puoi pescare:");
					for(CartaOro c: tavolo.getCarteOroBanco()) {
						System.out.println(c.toString() + "\n");
					}
					continua = false;
					break;
				case 3:
					//stampa matrice
					giocatori[turnoGiocatore].getCampoPersonale().stampaCampoDaGioco();
					
					continua = false;
					break;
				case 4:
					//stampa della carte che il giocatore ha nella mano
					System.out.println("Ecco le carte che hai in mano:");
					for(Carta c: giocatori[turnoGiocatore].getMano()) {
						System.out.println(c.toString() + "\n");
					}
					
					continua = false;
					break;
				default:
					System.out.println("Errore menu di scelta delle operazioni");
					
					continua = false;
					break;
				}
				
				
			}while(continua == false);
			
			if(turnoGiocatore<(nGiocatori-1))
				turnoGiocatore++;
			else
				turnoGiocatore=0;
		}
		
		
		
	}
	
	//qui sotto no. da reimplementare TODO
	/***
	 * funzione per sapere quando la partita conclude
	 * @return true se la partita è finita, false se la partita va avanti
	 */
	private static boolean condizioneFineGioco() {
		if (esisteGiocatoreConAlmenoVentiPunti() && tuttiIGiocatoriHannoLoStessoNumeroDiTurni())
			return true;
		else
			return false;
	}

	/***
	 * funzione per verificare che i giocatori abbiano lo stesso numero di turni quando è finita la partita
	 * per assegnare eventuali turni bonus ai giocatori con un turno in meno
	 * @return true se tutti i giocatori hanno lo stesso numero di turni, false il contrario
	 */
	private static boolean tuttiIGiocatoriHannoLoStessoNumeroDiTurni() {
		for (int i = 0; i < giocatori.length; i++) {
			if (giocatori[i].getTurniGiocati() != giocatori[i - 1].getTurniGiocati())
				return false;
		}
		return true;
	}

	/***
	 * funzione per controllare se esiste un giocatore con almeno 20 punti
	 * @return true se esiste il giocatore, false se nessun giocatore è arrivato a 20 punti
	 */
	private static boolean esisteGiocatoreConAlmenoVentiPunti() {
		for (int i = 0; i < giocatori.length; i++) {
			if (giocatori[i].getPunteggio() >= 20)
				return true;
		}

		return false;
	}
	
}
