package Main;

import java.util.List;
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
	 * @return false se l'input non é un intero, true se va tutto bene
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
		sc = new Scanner(System.in);
		
		for(int i=0; i<this.getnGiocatori(); i++) {
			System.out.println(this.getTavolo().getGiocatori()[i].getNickname() + " questa è la carta starter che ti è stata assegnata:");
			System.out.println(this.getTavolo().getGiocatori()[i].getCartaStarter());
			System.out.println("\ne questo è il retro:");
			this.getTavolo().getGiocatori()[i].getCampoPersonale().trovaCarta(24,24).setFronte(false);
			System.out.println(this.getTavolo().getGiocatori()[i].getCartaStarter());
			this.getTavolo().getGiocatori()[i].getCampoPersonale().trovaCarta(24,24).setFronte(true);
			
			do {
				System.out.println("\nDesideri giocarla di retro? (si/no)");
				buffer = sc.nextLine();
				buffer = buffer.toLowerCase();
				buffer.trim();
			}while(!(buffer.equals("si") || buffer.equals("no")));
			
			if(buffer.equals("si"))
				this.getTavolo().getGiocatori()[i].getCampoPersonale().trovaCarta(24,24).setFronte(false);
			else
				this.getTavolo().getGiocatori()[i].getCampoPersonale().trovaCarta(24,24).setFronte(true);
		}
		
		return true;
	}
	
	/***
	 * crea un nuovo tavolo di gioco sul quale si svolgerà la partita
	 * @param nGiocatori: il numero di giocatori che giocano (da 2 a 4)
	 */
	public void createTable(int nGiocatori, List<String> nicknames) {
		//crea un nuovo tavolo da gioco
		this.tavolo = new TavoloDaGioco(nGiocatori, nicknames);
		//crea un nuovo menu' manager per la scelta delle opzioni utente
		this.mg = new MenuManager(tavolo);
	}
	
	/***
	 * calcola a fine partita i punti da aggiungere ad ogni giocatore nel caso in cui abbiano completato la loro
	 * carta obiettivo personale e/o le due carte obiettivo comuni sul tavolo da gioco
	 */
	public void calcoloPuntiObiettivi() {
		for(Giocatore g: tavolo.getGiocatori()) {
			if(g.getCartaObiettivo().calcoloObiettivo(g.getCampoPersonale()) >0) {
				g.incrementaPunteggio(g.getCartaObiettivo().calcoloObiettivo(g.getCampoPersonale()));					//obiettivo personale del giocatore
				System.out.println(g.getNickname() + "hai completato l'obiettivo personale:\n" + g.getCartaObiettivo().toString() + "\n\n");
			}
			
			if(tavolo.getObiettiviComuni().get(0).calcoloObiettivo(g.getCampoPersonale()) > 0){
				g.incrementaPunteggio(tavolo.getObiettiviComuni().get(0).calcoloObiettivo(g.getCampoPersonale()));		//primo obiettivo comune
				System.out.println(g.getNickname() + " hai completato il primo obiettivo comune:\n" + tavolo.getObiettiviComuni().get(0).toString() + "\n\n");
			}
			
			if(tavolo.getObiettiviComuni().get(1).calcoloObiettivo(g.getCampoPersonale()) > 0){
				g.incrementaPunteggio(tavolo.getObiettiviComuni().get(1).calcoloObiettivo(g.getCampoPersonale()));		//secondo obiettivo comune
				System.out.println(g.getNickname() + " hai completato il secondo obiettivo comune:\n" + tavolo.getObiettiviComuni().get(1).toString() + "\n\n");
			}
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
