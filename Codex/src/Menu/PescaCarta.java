package Menu;

import Ecccezioni.CardPlacementException;
import Giocatori.Giocatore;
import Giocatori.TavoloDaGioco;

//classe per l'opzione del menu: pescare una nuova carta dal tavolo di gioco
//può essere richiamata solamente dopo che il giocatore ha posizionato una delle sue carte sul suo campo da gioco
public class PescaCarta implements MenuOption {
	private String tipoCarta; // il tipo di carta che si vuole pescare
	private int indexCarta; //l'indice della carta che si vuole pescare nel mazzo di carte sul tavolo (1 o 2)
	private Giocatore giocatore; //il giocatore che compie l'azione
	private TavoloDaGioco tavolo;

	/***
	 * istanzia un nuovo oggetto del menu per pescare una nuova carta da aggiungere al proprio mazzo.
	 * viene richiamato solo dopo che il giocatore ha posizionato in modo corretto una carta sul suo campo da gioco.
	 * i controlli sull'input vengono fatti dalla classe chiamante.
	 * @param tc: il tipo di carta che il giocatore vuole pescare (oro/risorsa)
	 * @param ic: l'indice della carta nel mazzo delle carte sul tavolo da gioco (1 o 2)
	 * @param g: il giocatore che sta effettuando l'azione
	 * @param t: il tavolo da gioco su cui si sta svolgendo la partita
	 */
	public PescaCarta(String tc, int ic, Giocatore g, TavoloDaGioco t) {
		this.tipoCarta = tc;
		this.indexCarta = ic;
		this.giocatore = g;
		this.tavolo = t;
	}

	@Override
	/***
	 * pesca una carta da quelle presenti sul tavolo di gioco
	 * e la aggiunge al mazzo personale del giocatore
	 */
	public void execute() throws CardPlacementException {
		if(tipoCarta.equals("oro")) {
			//carta di tipo oro
			giocatore.setMano(tavolo.pescaCartaOro(indexCarta-1));
		}
		else if(tipoCarta.equals("risorsa")) {
			//carta di tipo risorsa
			giocatore.setMano(tavolo.pescaCartaRisorsa(indexCarta-1));
		}
	}
}
