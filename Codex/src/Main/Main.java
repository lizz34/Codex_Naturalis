package Main;

import Carte.*;
import Giocatori.*;
import utility.Input;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static Giocatore[] giocatori;

	public static void main(String[] args) {
		TavoloDaGioco tavolo = new TavoloDaGioco();
		Scanner sc = new Scanner(System.in);
		int nGiocatori;
		Giocatore[] giocatori;
		
		//Inserimento numero giocatori
		do {
			System.out.println("Digitare il numero di giocatori (da 2 a 4) che parteciperanno alla partita: ");
			nGiocatori = sc.nextInt();
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
		
		
		
		for(int i=0; i<nGiocatori; i++) {
			System.out.println(giocatori[i].toString());
		}
		
		while(tavolo.condEndGame1()==false) { //+altra condizione dei 20 punti
			
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
