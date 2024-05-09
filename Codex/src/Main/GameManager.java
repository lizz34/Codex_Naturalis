package Main;

import java.util.Scanner;

import Giocatori.Giocatore;
import Giocatori.TavoloDaGioco;

public class GameManager {

	private TavoloDaGioco tavolo;
	private int nGiocatori;
	private Giocatore[] giocatori;
	
	public GameManager() {
		this.nGiocatori=0;
	}
	
	public boolean insertNumGiocatori(String buffer) {
		boolean approvato=true;
		try {
			this.nGiocatori = Integer.parseInt(buffer);
			this.giocatori = new Giocatore[this.nGiocatori];
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

	public void setTavolo(TavoloDaGioco tavolo) {
		this.tavolo = tavolo;
	}

	public Scanner getSc() {
		return sc;
	}

	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	public int getnGiocatori() {
		return nGiocatori;
	}

	public void setnGiocatori(int nGiocatori) {
		this.nGiocatori = nGiocatori;
	}

	public Giocatore[] getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(Giocatore[] giocatori) {
		this.giocatori = giocatori;
	}
	
	
	
	
}
