package Giocatori;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import Carte.CartaObiettivo;
import Carte.*;

public class Giocatore {
		   
	private int punteggio;
	private Vector<Carta> carte = new Vector<>();
	final private CarteObiettivo cartaObiettivo;
	final private Carta cartaStarter;
	//variabili della matrice
	private CampoDaGioco campoPersonale;
	
	/***
	 * costruttore della classe giocatore
	 * @param carte le 3 carte che il giocatore ha all'inizio della partita
	 * @param obiettivo la carta obiettivo che gli viene assegnata all'inizio della partita
	 * @param cartaStarter la carta starter con cui inizia a giocare
	 */
	public Giocatore(Carta carte[], CartaObiettivo obiettivo, Carta cartaStarter) {
		//TODO
		//al momento dell'avvio della partita al giocatore vengono assegnate tramite il main 
		//la carta obiettivo (che non cambierà mai)
		//le 3 carte di partenza da usare
		//la carta starter
		this.punteggio = 0;
		this.cartaObiettivo = obiettivo;
		this.cartaStarter = cartaStarter;
		
		for(int i = 0; i < 3; i++) {
			this.carte.add(manoIniziale[i]);
		}
		
		campoPersonale = new CampoDaGioco(this.cartaStarter);
	}
	
	/***
	 * permette al giocatore di scegliere la carta da giocare tra le 3 nel suo mazzo
	 * @return false se il posizionamento non e' avvenuto, true se e' avvenuto
	 */
	public boolean scegliCarta() {
		Carta cartaScelta = null;
		int scelta = 0;
		Scanner sc =  new Scanner();
		
		//cicli per chiedere all'utente di scegliere la prossima carta che vuole giocare
		do {
			for(int i = 0; i < 3; i++) {
				System.out.println(i++ + ": " + carte[i].toString() + " \n");
			}
			
			System.out.println();
			System.out.println("Inserisci il numero della carta che vuoi posizionare sul tuo tavolo di gioco: ");
			
			try {
				scelta = sc.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("Input non valido. Inserisci un numero da 1 a 3.");
				sc.nextLine();
			}
			
			if(scelta < 1 || scelta > 3) {
				System.out.println("Inserisci un numero da tra 1 e 3 per scegliere la tua carta.");
			}
			else {
				cartaScelta = carte[scelta];
			}
		}
		while(cartaScelta== null);
		
		//stampa la matrice per permettere al giocatore di scegliere dove posizionare la carta
		campoPersonale.stampaCampoDaGioco();
		
		//riga su cui l'utente vuole posizionare la carta
		int nRiga = 0;
		System.out.println("inserisci il numero della riga della carta su cui vuoi posizionare la nuova carta: ");
		try {
			nRiga = sc.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("Input non valido. Inserisci un numero intero da 0 a 49.");
			nRiga = sc.nextLine();
		}
		
		//colonna su cui l'utente vuole posizionare la carta
		int nColonna = 0;
		System.out.println("inserisci il numero della colonna della carta su cui vuoi posizionare la nuova carta: ");
		try {
			nColonna = sc.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("Input non valido. Inserisci un numero intero da 0 a 49.");
			nColonna = sc.nextLine();
		}
		
		//angolo su cui l'utente vuole posizionare la carta
		int nAngolo = 0;
		System.out.println("inserisci il numero dell'angolo della carta su cui vuoi posizionare la nuova carta: ");
		try {
			nAngolo = sc.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("Input non valido. Inserisci un numero intero da 0 a 7.");
			nAngolo = sc.nextLine();
		}
				
		
		//funzione della campo da gioco per posizionare la carta nella matrice
		if(campoPersonale.posizionaCarta(cartaScelta, nRiga, nColonna, nAngolo) == false) {
			System.out.println("errore!! non puoi posizionare questa carta");
			return false;
			
		}
		else {
			//la carta e' stata posizionata correttamente
			//rimozione della carta scelta dal vettore di carte del giocatore
			carte.remove(scelta);
			return true;
		}
		
		//TODO: chiamata alla funzione del main che permette di:
		//vedere le 4 carte presenti sul tavolo di gioco
		//sceglierne una ed aggiungerla al proprio tavolo
		sc.close();
	}
	

	//getter e setter
	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	public Carta[] getCarte() {
		return carte;
	}

	public void setCarte(Carta[] carte) {
		this.carte = carte;
	}
	
	public CarteObiettivo getCartaObiettivo() {
		return cartaObiettivo;
	}

	public Carta getCartaStarter() {
		return cartaStarter;
	
}
}