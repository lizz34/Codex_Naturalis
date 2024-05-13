package Main;

import Giocatori.*;
import Menu.MenuManager;

public class GameManager {

	private TavoloDaGioco tavolo;
	private int nGiocatori;
	private MenuManager mg;
	
	public GameManager() {
		this.nGiocatori=0;
	}
	
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

	public void createTable(int nGiocatori) {
		this.tavolo = new TavoloDaGioco(nGiocatori);
		this.mg = new MenuManager(tavolo);
	}
	
	public TavoloDaGioco getTavolo() {
		return tavolo;
	}
	
	public int getnGiocatori() {
		return nGiocatori;
	}

	public MenuManager getMg() {
		return mg;
	}
	
	
	
}
