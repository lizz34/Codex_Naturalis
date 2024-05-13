package Main;

import Giocatori.*;
import Menu.MenuManager;

//classe per gestire tutto il gioco
public class GameManager {

	private TavoloDaGioco tavolo;
	private int nGiocatori;
	private MenuManager mg;
	
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
		}
		catch(NumberFormatException e) {
			approvato=false;
		}
		
		return approvato;
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
