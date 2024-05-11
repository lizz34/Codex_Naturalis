package Menu;

import Ecccezioni.CardPlacementException;
import Giocatori.*;

/***
 * opzione del menu per visualizzare a console la propria area di gioco
 */
public class VisualizzaMatrice implements MenuOption {
	
	private Giocatore giocatore;

	/***
	 * costruttore della classe che gestisce l'opzione del menu per visualizzare a console 
	 * l'area di gioco di un giocatore
	 * @param g: il giocatore a cui appartiene la matrice da visualizzare
	 */
	public VisualizzaMatrice(Giocatore g) {
		this.giocatore = g;
	}

	@Override
	/***
	 * stampa sulla console la matrice del giocatore selezionato
	 */
	public void execute() throws CardPlacementException {
		giocatore.getCampoPersonale().stampaCampoDaGioco();
	}

}
