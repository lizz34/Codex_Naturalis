package Menu;

import Ecccezioni.CardPlacementException;
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
