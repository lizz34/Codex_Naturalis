package Main;

import java.util.Scanner;

import Giocatori.*;
import Menu.MenuManager;

//classe per gestire tutto il gioco
public class GameManager {

	private TavoloDaGioco tavolo;
	private int nGiocatori;
	private MenuManager mg;
	private Scanner sc;
	
	public GameManager() {
		this.nGiocatori=0;
	}
	
	/***
	 * salva il numero dei giocatori che vogliono giocare
	 * @param buffer: il numero di giocatori (stringa da castare)
	 * @return false se l'input non é un inero, true se va tutto bene
	 */
	public boolean insertNumGiocatori(String buffer) {
		boolean approvato=true;
		try {
			this.nGiocatori = Integer.parseInt(buffer);
			if(nGiocatori<2 || nGiocatori>4)
				approvato=false;
		}
		catch(NumberFormatException e) {
			approvato=false;
		}
		
		return approvato;
	}

	/***
	 * funzione per giocare una carta starter sul retro
	 * @param buffer: stringa che contiene la scelta dell'untente: si/no al giocare la carta starter a rovescio
	 * @return true se tutto viene eseguito correttamente
	 */
	public boolean rovesciaStarter(String buffer) {
		boolean[] starterRetro = new boolean [this.getnGiocatori()];
		int n=0;
		sc = new Scanner(System.in);
		
		for(int i=0; i<this.getnGiocatori(); i++) {
			starterRetro[i]=false;
		}
		
		while(buffer.equals("si")) {
			do {
				System.out.println("Digitare il numero del giocatore che desidera giocare la carta starter di retro (1 - " + (this.getnGiocatori()) + "): ");
				do {
					String buffer2;
					buffer2 = sc.nextLine();
					try {
						n = Integer.parseInt(buffer2);
					}
					catch(NumberFormatException e) {
						System.out.println("Errore, digitare un numero intero");
					}
				}while(n<1 || n>this.getnGiocatori());
				starterRetro[n-1] = true;
				
				System.out.println("Ci sono altri giocatori che vogliono giocare la carta starter di retro? (si/no)");
				buffer = sc.nextLine();
				buffer = buffer.toLowerCase();
				buffer.trim();
			}while(!(buffer.equals("si") || buffer.equals("no")));
		}
		
		for(int i=0; i<this.getnGiocatori(); i++) {
			if(starterRetro[i]==true) {
				this.getTavolo().getGiocatori()[i].getCampoPersonale().trovaCarta(24,24).setFronte(false);
			}
		}
		
		return true;
	}
	
	/***
	 * crea un nuovo tavolo di gioco sul quale si svolgerà la partita
	 * @param nGiocatori: il numero di giocatori che giocano (da 2 a 4)
	 */
	public void createTable(int nGiocatori) {
		//crea un nuovo tavolo da gioco
		this.tavolo = new TavoloDaGioco(nGiocatori);
		//crea un nuovo menu' manager per la scelta delle opzioni utente
		this.mg = new MenuManager(tavolo);
	}
	
	/***
	 * calcola a fine partita i punti da aggiungere ad ogni giocatore nel caso in cui abbiano completato la loro
	 * carta obiettivo personale e/o le due carte obiettivo comuni sul tavolo da gioco
	 */
	public void calcoloPuntiObiettivi() {
		for(Giocatore g: tavolo.getGiocatori()) {
			g.incrementaPunteggio(g.getCampoPersonale().controllaObiettivo(g.getCartaObiettivo()));					//obiettivo personale del giocatore
			g.incrementaPunteggio(g.getCampoPersonale().controllaObiettivo(tavolo.getObiettiviComuni().get(0)));	//primo obiettivo comune
			g.incrementaPunteggio(g.getCampoPersonale().controllaObiettivo(tavolo.getObiettiviComuni().get(1)));	//secondo obiettivo comune
		}
	}
	
	/***
	 * getter del tavolo da gioco
	 * @return il tavolo da gioco
	 */
	public TavoloDaGioco getTavolo() {
		return tavolo;
	}
	
	/***
	 * getter del numero dei giocatori
	 * @return il numero dei giocatori
	 */
	public int getnGiocatori() {
		return nGiocatori;
	}

	/***
	 * getter del menu' manager
	 * @return il menu' manager
	 */
	public MenuManager getMg() {
		return mg;
	}
}
