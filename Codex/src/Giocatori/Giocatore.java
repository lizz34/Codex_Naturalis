package Giocatori;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Carte.*;

public class Giocatore {

	private int punteggio;
	private int turniGiocati;
	private ArrayList<Carta> mano;
	private CartaObiettivo cartaObiettivo;
	private Carta cartaStarter;
	private CampoDaGioco campoPersonale;

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

		//creazione del campo personale
		campoPersonale = new CampoDaGioco(this.cartaStarter);
	}

	/***
	 * posiziona una carta dalla mano del giocatore nella sua matrice, cancellandola poi dalla sua mano di carte
	 * @param nCarta l'indice della carta che vuole posizionare
	 * @param nRiga la riga della carta su cui vuole posizionare la sua
	 * @param nColonna la colonna della carta su cui vuole posizionare la sua
	 * @param nAngolo il numero dell'angolo che vuole sovrapporre con la sua carta
	 * @return true se il posizionamento é avvenuto, false se c'é stato un errore
	 */
	public boolean posizionaCarta(int nCarta, int nRiga, int nColonna, int nAngolo) {
		Carta cartaScelta = mano.get(nCarta);
		
		if (campoPersonale.posizionaCarta(cartaScelta, nRiga, nColonna, nAngolo) == false) {
			return false;
		}
		else {
			//se la carta é stata posizionata correttamente la si rimuove dalla lista delle carte che il giocatore ha in mano
			mano.remove(nCarta);
			return true;
		}
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
	 * getter di una singola carta dalla propria mano 
	 * @param l'indice della carta nell'arrayList
	 * @return la carta selezionata se esiste, null se la carta non esiste
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
	 * getter del campo di gioco personale del giocatore
	 * @return il campo da gioco
	 */
	public CampoDaGioco getCampoPersonale() {
		return campoPersonale;
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
		return " ";
	}



}