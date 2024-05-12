package Main;

import Giocatori.*;

public class GameManager {

	private TavoloDaGioco tavolo;
	private int nGiocatori;
	
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
	}
	
	public TavoloDaGioco getTavolo() {
		return tavolo;
	}
	
	public int getnGiocatori() {
		return nGiocatori;
	}
	
	
	
}
