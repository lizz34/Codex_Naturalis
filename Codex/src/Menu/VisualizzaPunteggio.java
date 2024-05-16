package Menu;

import Ecccezioni.CardPlacementException;
import Giocatori.*;

/***
 * opzione del menu per visualizzare a console il proprio punteggio
 */
public class VisualizzaPunteggio implements MenuOption{
	/***
	 * costruttore della classe che gestisce l'opzione del menu per visualizzare a console il punteggio di un giocatore
	 */
	public VisualizzaPunteggio() {
	}

	@Override
	/***
	 * stampa sulla console il punteggio del giocatore selezionato
	 * @param g: il giocatore che ha selezionato l'opzione
	 * @throws CardPlacementException
	 */
	public void execute(Giocatore g) throws CardPlacementException {
		System.out.println("Hai: " + g.getPunteggio() + " punti");

	}
	
	/***
	 * @return il nome esteso dell'opzione del menu
	 */
	public String toString() {
		return "Visualizza il tuo punteggio";
	}
	
}