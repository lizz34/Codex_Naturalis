package Giocatori;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import Carte.*;
import utility.Input;

public class Giocatore {

	private int punteggio;
	private int turniGiocati;
	private Vector<Carta> carte = new Vector<>();
	private CartaObiettivo cartaObiettivo;
	private Carta cartaStarter;
	// variabili della matrice
	private CampoDaGioco campoPersonale;
	// private CartaOro cartaOro;

	/***
	 * costruttore della classe giocatore
	 * 
	 * @param carte        le 3 carte che il giocatore ha all'inizio della partita
	 * @param obiettivo    la carta obiettivo che gli viene assegnata all'inizio
	 *                     della partita
	 * @param cartaStarter la carta starter con cui inizia a giocare
	 */
	public Giocatore(CartaObiettivo obiettivo, Carta cartaStarter, Vector<Carta> carte) {
		// TODO
		// al momento dell'avvio della partita al giocatore vengono assegnate tramite il
		// main
		// la carta obiettivo (che non cambierà mai)
		// le 3 carte di partenza da usare
		// la carta starter
		this.punteggio = 0;
		this.cartaObiettivo = obiettivo;
		this.cartaStarter = cartaStarter;
		this.carte = carte;
		/*
		 * for(int i = 0; i < 3; i++) { this.carte.add(manoIniziale[i]); }
		 */

		campoPersonale = new CampoDaGioco(this.cartaStarter);
	}
	/*
	 * cercando di fare ciclo su giocatori: chiedere conefrema.... public
	 * Giocatore(String string) { this.cartaStarter = null; // TODO Auto-generated
	 * constructor stub }
	 */

	/***
	 * permette al giocatore di scegliere la carta da giocare tra le 3 nel suo mazzo
	 * 
	 * @return false se il posizionamento non e' avvenuto, true se e' avvenuto
	 */
	public boolean scegliCarta() {
		Carta cartaScelta = null;
		int scelta = 0;

		// cicli per chiedere all'utente di scegliere la prossima carta che vuole
		// giocare
		do {
			for (int i = 0; i < 3; i++) {
				// System.out.println(i++ + ": " + carte[i].toString() + " \n");
				// stampo a video le carte del giocatore

			}

			System.out.println();
			System.out.println("Inserisci il numero della carta che vuoi posizionare sul tuo tavolo di gioco: ");

			try {
				scelta = Input.readInt();
			} catch (Exception e) {
				System.out.println("Input non valido. Inserisci un numero da 1 a 3.");
				scelta = Input.readInt();
			}

			if (scelta < 1 || scelta > 3) {
				System.out.println("Inserisci un numero da tra 1 e 3 per scegliere la tua carta.");
			} else {
				cartaScelta = carte.get(scelta);
			}
		} while (cartaScelta == null);

		// stampa la matrice per permettere al giocatore di scegliere dove posizionare
		// la carta
		campoPersonale.stampaCampoDaGioco();

		// riga su cui l'utente vuole posizionare la carta
		int nRiga = 0;
		System.out.println("inserisci il numero della riga della carta su cui vuoi posizionare la nuova carta: ");
		try {
			nRiga = Input.readInt();
		} catch (InputMismatchException e) {
			System.out.println("Input non valido. Inserisci un numero intero da 0 a 49.");
			nRiga = Input.readInt();
		}

		// colonna su cui l'utente vuole posizionare la carta
		int nColonna = 0;
		System.out.println("inserisci il numero della colonna della carta su cui vuoi posizionare la nuova carta: ");
		try {
			nColonna = Input.readInt();
		} catch (InputMismatchException e) {
			System.out.println("Input non valido. Inserisci un numero intero da 0 a 49.");
			nColonna = Input.readInt();
		}

		// angolo su cui l'utente vuole posizionare la carta
		int nAngolo = 0;
		System.out.println("inserisci il numero dell'angolo della carta su cui vuoi posizionare la nuova carta: ");
		try {
			nAngolo = Input.readInt();
		} catch (InputMismatchException e) {
			System.out.println("Input non valido. Inserisci un numero intero da 0 a 7.");
			nAngolo = Input.readInt();
		}

		// funzione della campo da gioco per posizionare la carta nella matrice
		if (campoPersonale.posizionaCarta(cartaScelta, nRiga, nColonna, nAngolo) == false) {
			System.out.println("errore!! non puoi posizionare questa carta");
			return false;

		} else {
			// la carta e' stata posizionata correttamente
			// rimozione della carta scelta dal vettore di carte del giocatore
			carte.remove(scelta);
			return true;
		}

		// TODO: chiamata alla funzione del main che permette di:
		// vedere le 4 carte presenti sul tavolo di gioco
		// sceglierne una ed aggiungerla al proprio tavolo
	}

	// getter e setter
	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	public Vector<Carta> getCarte() {
		return carte;
	}

	public void setCarte(Vector<Carta> carte) {
		this.carte = carte;
	}

	public CartaObiettivo getCartaObiettivo() {
		return cartaObiettivo;
	}

	public Carta getCartaStarter() {
		return cartaStarter;

	}

	public void mostraMano() {
		// TODO Auto-generated method stub

	}

	public int getTurniGiocati() {
		return this.turniGiocati;
	}

	public void incrementaTurniGiocati() {
		this.turniGiocati += 1;
	}

}