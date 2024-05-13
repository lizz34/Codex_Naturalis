package Menu;

import Ecccezioni.CardPlacementException;
import Giocatori.Giocatore;

//interfaccia per gestire le opzioni del menu
public interface MenuOption {

	/***
	 * esegue l'opzione selezionata
	 * @param g: il giocatore che ha selezionato l'opzione
	 * @throws CardPlacementException 
	 */
	void execute(Giocatore g) throws CardPlacementException;
}
