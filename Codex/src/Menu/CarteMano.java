package Menu;

import Ecccezioni.CardPlacementException;
import Giocatori.*;

/***
 * classe per gestire l'opzione del menu di visualizzare le carte che un giocatore ha in mano
 */
public class CarteMano implements MenuOption {
	private Giocatore giocatore;
	
	/***
	 * costruttore della classe che permette al giocatore di vedere le carte che ha nella propria mano
	 * @param g: il giocatore di cui si vogliono visualizzare le carte
	 */
	public CarteMano(Giocatore g) {
		this.giocatore = g;
	}

	@Override
	/***
	 * restituisce le carte che il giocatore selezionato ha nella propria mano
	 * (da inserire in un System.out.println())
	 */
	public void execute() throws CardPlacementException {
		giocatore.getMano();

	}

}
