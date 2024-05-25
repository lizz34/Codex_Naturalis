package Menu;

import Eccezioni.CardPlacementException;
import Giocatori.Giocatore;
import Giocatori.TavoloDaGioco;

//classe per l'opzione del menu: pescare una nuova carta dal tavolo di gioco
//può essere richiamata solamente dopo che il giocatore ha posizionato una delle sue carte sul suo campo da gioco
public class PescaCarta implements MenuOption {

	/***
	 * istanzia un nuovo oggetto del menu per pescare una nuova carta da aggiungere al proprio mazzo.
	 * viene richiamato solo dopo che il giocatore ha posizionato in modo corretto una carta sul suo campo da gioco.
	 * i controlli sull'input vengono fatti dalla classe chiamante.
	 */
	public PescaCarta() {

	}

	@Override
	public void execute(Giocatore g) throws CardPlacementException {
	}
	
	/***
	 * pesca una nuova carta da quelle disponibili sul tavolo da gioco da aggiungere alla mano del giocatore
	 * @param g: il giocatore che sta pescando la carta
	 * @param tipoCarta: il tipo di carta che si vuole pescare (oro o risorsa)
	 * @param indexCarta: l'indice della carta nel mazzo di carte disponibili sul tavolo da gioco (1 o 2)
	 * @param tavolo: il tavolo da gioco su cui si sta svolgendo la partita
	 * @throws CardPlacementException
	 */
	public void pesca(Giocatore g, String tipoCarta, int indexCarta, TavoloDaGioco tavolo) throws CardPlacementException {
		if(tipoCarta.equals("oro")) {
			//carta di tipo oro
			g.setMano(tavolo.pescaCartaOro(indexCarta-1));
		}
		else if(tipoCarta.equals("risorsa")) {
			//carta di tipo risorsa
			g.setMano(tavolo.pescaCartaRisorsa(indexCarta-1));
		}
		
		new VisualizzaMatrice().execute(g);
	}
}
