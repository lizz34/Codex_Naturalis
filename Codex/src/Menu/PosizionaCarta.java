package Menu;

import Giocatori.*;
import Ecccezioni.*;

public class PosizionaCarta implements MenuOption {

	//cosa serve: index carta da posizionare, num riga colonna e angolo della carta da sovrascrivere
	//il giocatore: posso passarglielo con this o qualcosa di simile da dove richiamo il metodo
	private int indexCarta;
	private int numRiga;
	private int numColonna;
	private int numAngolo;
	private Giocatore giocatore;
	
	/***
	 * istanza un oggetto per posizionare una nuova carta
	 * @param i: l'indice della carta nella lista di carte del giocatore
	 * @param r: il numero di riga della matrice della carta a cui appartiene l'angolo che si vuole sovrapporre
	 * @param c: il numero di colonna della matrice della carta a cui appartiene l'angolo che si vuole sovrapporre
	 * @param a: il numero dell'angolo che si vuole sovrapporre
	 * @param g: il giocatore che sta compiendo l'azione
	 */
	public PosizionaCarta(int i, int r, int c, int a, Giocatore g) {
		//i controlli che i vari numeri siano corretti fatti al momento della raccolta dell'input utente (main/menu manager)
		this.indexCarta = i;
		this.numRiga = r;
		this.numColonna = c;
		this.numAngolo = a;
		this.giocatore = g;
	}
	
	@Override
	public void execute() throws CardPlacementException {
		if(!giocatore.posizionaCarta(indexCarta-1, numRiga, numColonna, numAngolo-1)) {
			//se la funzione restituisce false c'é stato un errore nel posizionamento della carta
			throw new CardPlacementException();
		}
		else {
			//il posizionamento della carta è avvenuto con successo, l'utente deve pescare una nuova carta da quelle presenti sul tavolo da gioco
			//TODO: dovrei richiamare da qua l'oggetto (implica che devo prendere i dati che mi servono da qua con uno scanner)
			//o lasciare fare al metodo che ha chiamato questa opzione del menu (facendo ad esempio un return true)???
		}

	}

}
