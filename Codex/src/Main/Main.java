package Main;

import Carte.*;
import Giocatori.*;

import java.util.ArrayList;
import java.util.Scanner;

//TODO FUNZIONE PER PULIRE LA CONSOLE?? chatgpt non ha aiutato :(
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
					catch(NumberFormatException e) {
						System.out.println("Errore: l'input non è un numero intero");
					}
				}while(scelta<1 || scelta>4);
				
				
				switch(scelta) {
				case 1:
					//posiziona carta dalla mano alla matrice
					
					break;
				case 2:
					System.out.println("Carte risorsa che puoi pescare:");
					System.out.println(tavolo.getCarteRisorsaBanco().toString() + "\n");
					System.out.println("Carte oro che puoi pescare:");
					System.out.println(tavolo.getCarteOroBanco().toString() + "\n");
					break;
				case 3:
					//stampa matrice
					
					break;
				case 4:
					System.out.println("Ecco le carte che hai in mano:");
					System.out.println(giocatori[turnoGiocatore].getMano().toString() + "\n");
					break;
				default:
					System.out.println("Errore menu di scelta delle operazioni");
					break;
				}
				
				
				
			}while(scelta!=1);
			
			if(turnoGiocatore<(nGiocatori-1))
				turnoGiocatore++;
			else
				turnoGiocatore=0;
		}
		
		
		
		
		/*
		tavolo.mazzoCarteOro.get(0).setFronte(false);
		for(int i=0; i<tavolo.mazzoCarteOro.size(); i++) {
			System.out.println(tavolo.mazzoCarteOro.get(i).toString());
		}
		System.out.println(tavolo.condEndGame1());
		*/
		/*
		while (!condizioneFineGioco()) {

			//TODO implemntare turni di gioco e incrementare numero turni giocatore

		}
		 */
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
