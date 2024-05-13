package Menu;

import Ecccezioni.CardPlacementException;
import Giocatori.*;

/***
 * opzione del menu per visualizzare a console il proprio punteggio
 */
public class VisualizzaPunteggi implements MenuOption{
	/***
	 * costruttore della classe che gestisce l'opzione del menu per visualizzare a console il punteggio di un giocatore
	 */
	public VisualizzaPunteggi() {
	}

	@Override
	/***
	 * stampa sulla console il punteggio del giocatore selezionato
	 */
	public void execute(Giocatore g) throws CardPlacementException {
		System.out.println("Hai: " + g.getPunteggio() + " punti");

	}
	
	public String toString() {
		return "visualizza il tuo punteggio";
	}
	
}