package Menu;

import Carte.Carta;
import Ecccezioni.CardPlacementException;
import Giocatori.*;

/***
 * classe per gestire l'opzione del menu di visualizzare le carte che un giocatore ha in mano
 */
public class CarteMano implements MenuOption {
	
	/***
	 * costruttore della classe che permette al giocatore di vedere le carte che ha nella propria mano
	 * @param g: il giocatore di cui si vogliono visualizzare le carte
	 */
	public CarteMano() {
	}

	@Override
	public void execute(Giocatore g) throws CardPlacementException {
		// TODO Auto-generated method stub
		System.out.println("Ecco le carte che hai in mano:");
		for(Carta c: g.getMano()) {
			System.out.println(c.toString() + "\n");
		}
	}
	
	public String toString() {
		return "visualizza le carte che hai in mano";
	}

}
