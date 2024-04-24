import Carte.Carta;

public class Giocatore {
	private int punteggio;
	private Carta carte[];
	//TODO: collegamento a classe obiettivo quando verrà creata la classe
	//private Obiettivo cartaObiettivo;
	
	public Giocatore(int punteggio, Carta carte[] /*Obiettivo obiettivo*/) {
		//le 3 carte che il giocatore ha sul banco di partenza gli vengono assegnate in maniera random dal mazzo nel main
		//al momento dell'avvio della partita
		this.punteggio = punteggio;
		
		for(int i = 0; i < 3; i++) {
			this.carte[i] = carte[i];
		}
	}
	
	//funzione che permettere di scegliere la prossima carta da giocare dalle 3 che possiede il giocatore
	public Carta scegliCarta() {
		Carta cartaScelta = null;
		
		//TODO : visualizzazione delle carte
		//scelta della carta e rimozione della carta dal vettore
		//scelta di una nuova carta dalle 4 che ci sono disponibili sul tavolo di gioco
		
		//TODO: chiamata alla funzione del main che permette di prendere una delle 4 carte presenti sul tavolo di gioco
		
		return cartaScelta;
	}

	//getter e setter
	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	public Carta[] getCarte() {
		return carte;
	}

	public void setCarte(Carta[] carte) {
		this.carte = carte;
	}
	
	
	
	
	//matrice
	Carta campoDaGioco [][];
	int final nRigheTabellone=50;
	int final ncolonneTabellone=50;
	
	public giocatore() {
		campoDaGioco = new Carta [nRigheTabellone][ncolonneTabellone];
		for(int i=0; i<nRigheTabellone; i++) {
			for(int j=0; j<ncolonneTabellone; j++) {
				campoDaGioco[i][j] = null;
			}
		}
		//campoDaGioco[(int(nRigheTabellone/2))-1][(int(ncolonneTabellone/2))-1]= //carta starter
	}
}
