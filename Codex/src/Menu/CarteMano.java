package Menu;

import Carte.Carta;
import Ecccezioni.CardPlacementException;
import Giocatori.*;

/***
 * classe per gestire l'opzione del menu di visualizzare le carte che un giocatore ha in mano
 */
public class CarteMano implements MenuOption {
	
	/***
	 * istanzia un nuovo oggetto del menu' per l'opzione di visualizzare le carte che il giocatore ha in mano
	 */
	public CarteMano() {
	}

	@Override
	/***
	 * mostra le carte che il giocatore ha nella sua mano
	 * @param g: il giocatore che ha scelto l'opzione
	 * @throws CardPlacementException
	 */
	public void execute(Giocatore g) throws CardPlacementException {
		System.out.println("Ecco le carte che hai in mano:");
		for(Carta c: g.getMano()) {
			System.out.println(c.toString() + "\n");
		}
	}
	
	/***
	 * @return il nome esteso dell'opzione del menu
	 */
	public String toString() {
		return "Visualizza le carte che hai in mano";
	}

}
