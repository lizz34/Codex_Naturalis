package Menu;

import Ecccezioni.CardPlacementException;
import Giocatori.*;

/***
 * opzione del menu per visualizzare a console la propria area di gioco
 */
public class VisualizzaMatrice implements MenuOption {

	/***
	 * costruttore della classe che gestisce l'opzione del menu per visualizzare a console l'area di gioco di un giocatore
	 */
	public VisualizzaMatrice() {
	}

	@Override
	/***
	 * stampa sulla console la matrice del giocatore selezionato
	 */
	public void execute(Giocatore g) throws CardPlacementException {
		g.getCampoPersonale().stampaCampoDaGioco();
	}
	
	public String toString() {
		return "visualizza la tua area di gioco";
	}

}
