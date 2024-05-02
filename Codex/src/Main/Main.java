package Main;

import Carte.*;
import Giocatori.*;
import utility.Input;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	private static Giocatore[] giocatori;

	public static void main(String[] args) {
		TavoloDaGioco tavolo = new TavoloDaGioco();
		int numeroGiocatori = 0;
		
		do {
			
			System.out.println("quanti sono i giocatori? (da 2 a 4)");
			numeroGiocatori = Input.readInt();
			
		} while (numeroGiocatori < 2 || numeroGiocatori > 4);
		
		giocatori = new Giocatore[numeroGiocatori];
		/*
		 * ciclo sull'array giocatori istanziando correttamente ogni giocatore con le
		 * proprie carte da gioco
		 */
		for (int i = 0; i < giocatori.length; i++) {
			//FIXME
			//giocatori[i] = new Giocatore(tavolo.pescaCartaObiettivo(), tavolo.pescaCartaStarter(), tavolo.pescaCarteIniziali());
			System.out.println(giocatori[i].toString());
		}
		System.out.println("creati " + giocatori.length + " giocatori");

		while (!condizioneFineGioco()) {

			//TODO implemntare turni di gioco e incrementare numero turni giocatore

		}

	}

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
