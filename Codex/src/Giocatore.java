import Carte.Carta;
import Carte.CarteObiettivo;

public class Giocatore {
	private int punteggio;
	private Carta carte[];
	final private CarteObiettivo cartaObiettivo;
	final private Carta cartaStarter;
	//variabili della matrice
	Carta campoDaGioco [][];
	final int nRigheTabellone=50;
	final int ncolonneTabellone=50;
	
	public Giocatore(int punteggio, Carta carte[], CarteObiettivo obiettivo, Carta cartaStarter) {
		//al momento dell'avvio della partita al giocatore vengono assegnate tramite il main 
		//la carta obiettivo (che non cambierà mai)
		//le 3 carte di partenza da usare
		//la carta starter
		this.punteggio = punteggio;
		this.cartaObiettivo = obiettivo;
		this.cartaStarter = cartaStarter;
		
		for(int i = 0; i < 3; i++) {
			this.carte[i] = carte[i];
		}
		
		//generazione della matrice di gioco
		campoDaGioco = new Carta [nRigheTabellone][ncolonneTabellone];
		for(int i=0; i<nRigheTabellone; i++) {
			for(int j=0; j<ncolonneTabellone; j++) {
				campoDaGioco[i][j] = null;
			}
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
	
	public CarteObiettivo getCartaObiettivo() {
		return cartaObiettivo;
	}

	public Carta getCartaStarter() {
		return cartaStarter;
	}
	
	
	
	
		//campoDaGioco[(int(nRigheTabellone/2))-1][(int(ncolonneTabellone/2))-1]= //carta starter
	
}
