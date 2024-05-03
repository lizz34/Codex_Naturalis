package Giocatori;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import Carte.*;

public class Giocatore {

	private int punteggio;
	private int turniGiocati;
	private ArrayList<Carta> mano; //arraylist cambia l'ordine quando vuole? o solo se si toglie/aggiunge elemento e poi rimangono fissi?
	private CartaObiettivo cartaObiettivo;
	private Carta cartaStarter;
	public CampoDaGioco campoPersonale; //public per testing

	/***
	 * costruttore della classe Giocatore
	 * @param obiettivo: la carta obiettivo che gli viene assegnata all'inizio della partita
	 * @param cartaStarter: la carta starter con cui inizia a giocare
	 * @param manoIniziale: le 3 carte che il giocatore ha all'inizio della partita
	 */
	public Giocatore(CartaObiettivo obiettivo, Carta cartaStarter, ArrayList<Carta> manoIniziale) {
		this.punteggio = 0;
		this.turniGiocati=0;
		this.cartaObiettivo = obiettivo;
		this.cartaStarter = cartaStarter;
		this.mano = new ArrayList<Carta>();
		this.mano = manoIniziale;
		/*
		 * for(int i = 0; i < 3; i++) { this.carte.add(manoIniziale[i]); }
		 */

		campoPersonale = new CampoDaGioco(this.cartaStarter);
	}


	/***
	 * permette al giocatore di scegliere la carta da giocare tra le 3 nel suo mazzo
	 * @return false se il posizionamento non e' avvenuto, true se e' avvenuto
	 */
	public boolean scegliCarta() {
		Carta cartaScelta = null;
		int scelta = 0;

		// cicli per chiedere all'utente di scegliere la prossima carta che vuole giocare
		do {
			for (int i = 0; i < 3; i++) {
				System.out.println(i++ + ": " + mano.get(i).toString() + " \n");
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
				cartaScelta = mano.get(scelta);
			}
		} while (cartaScelta == null);

		// stampa la matrice per permettere al giocatore di scegliere dove posizionare la carta
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
			mano.remove(scelta);
			return true;
		}

		// TODO: chiamata alla funzione del main che permette di:
		// vedere le 4 carte presenti sul tavolo di gioco
		// sceglierne una ed aggiungerla al proprio tavolo
	}

	/***
	 * getter punteggio
	 * @return ritorna il punteggio del giocatore
	 */
	public int getPunteggio() {
		return punteggio;
	}

	/***
	 * setter punteggio
	 * @param punteggio: imposta il punteggio del giocatore
	 */
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	/***
	 * getter mano attuale del giocatore
	 * @return ritorna la mano attuale del giocatore
	 */
	public ArrayList<Carta> getMano(){
		return this.mano;
	}
	
	/***
	 * 
	 */
	public Carta getCartaMano(int index) {
		if(index>=0 && index<this.mano.size())
			return this.mano.get(index);
		
		return null;
	}
	
	/***
	 * setter mano giocatore: aggiunge una carta alla mano
	 * @param carta: carta che viene aggiunta alla mano del giocatore
	 */
	public void setMano(Carta carta) {
		this.mano.add(carta);
	}
	
	/***
	 * getter carta obiettivo
	 * @return ritorna la carta obiettivo del giocatore
	 */
	public CartaObiettivo getCartaObiettivo() {
		return cartaObiettivo;
	}

	/***
	 * getter carta starter
	 * @return ritorna la carta starter del giocatore
	 */
	public Carta getCartaStarter() {
		return cartaStarter;
	}

	/***
	 * getter turni giocati
	 * @return ritorna il numero di turni giocati dal giocatore
	 */
	public int getTurniGiocati() {
		return this.turniGiocati;
	}

	/***
	 * incrementa i turni giocati di +1
	 */
	public void incrementaTurniGiocati() {
		this.turniGiocati++;
	}
	
	/***
	 * @override del metodo toString
	 */
	public String toString() {
		System.out.println("Punteggio: " + this.punteggio);
		System.out.println("Carta Starter: ");
		System.out.println(this.cartaStarter.toString());
		System.out.println("CartaObiettivo: ");
		System.out.println(this.cartaObiettivo.toString());
		System.out.println("Mano attuale: ");
		System.out.println(this.mano.toString());
		return " "; //VA BENE COME TOSTRING?
	}

}