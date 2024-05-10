package Menu;

import Ecccezioni.CardPlacementException;

//interfaccia per gestire le opzioni del menu
public interface MenuOption {

	/***
	 * esegue l'opzione selezionata
	 * @throws CardPlacementException 
	 */
	void execute() throws CardPlacementException;
}
