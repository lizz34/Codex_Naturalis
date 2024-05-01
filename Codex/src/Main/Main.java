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
		 * cilco sull'array giocatori istanziando correttamente ogni giocatore con le
		 * proprie carte da gioco
		 */
		for (int i = 0; i < giocatori.length; i++) {
			giocatori[i] = new Giocatore(tavolo.pescaCartaObiettivo(), tavolo.pescaCartaStarter(),
					tavolo.pescaCarteIniziali());
			System.out.println(giocatori[i]);
		}
		System.out.println("creati " + giocatori.length + " giocatori");

		while (!condizioneFineGioco()) {

			//TODO implemntare turni di gioco e incrementare numero turni giocatore

		}

	}

	private static boolean condizioneFineGioco() {
		if (esisteGiocatoreConAlmenoVentiPunti() && tuttiIGiocatoriHannoLoStessoNumeroDiTurni())
			return true;
		else
			return false;
	}

	private static boolean tuttiIGiocatoriHannoLoStessoNumeroDiTurni() {
		for (int i = 1; i < giocatori.length; i++) {
			if (giocatori[i].getTurniGiocati() != giocatori[i - 1].getTurniGiocati())
				return false;
		}
		return true;
	}

	private static boolean esisteGiocatoreConAlmenoVentiPunti() {
		for (int i = 0; i < giocatori.length; i++) {
			if (giocatori[i].getPunteggio() >= 20)
				return true;
		}

		return false;
	}

}
