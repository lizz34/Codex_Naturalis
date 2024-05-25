package Menu;

import Eccezioni.CardPlacementException;
import Giocatori.*;

/***
 * opzione del menu per visualizzare a console il proprio punteggio
 */
public class VisualizzaPunteggio implements MenuOption{
	private TavoloDaGioco tavolo;
	/***
	 * costruttore della classe che gestisce l'opzione del menu per visualizzare a console il punteggio di un giocatore
	 */
	public VisualizzaPunteggio(TavoloDaGioco tavolo) {
		this.tavolo = tavolo;
	}

	@Override
	/***
	 * stampa sulla console il punteggio del giocatore selezionato
	 * @param g: il giocatore che ha selezionato l'opzione
	 * @throws CardPlacementException
	 */
	public void execute(Giocatore g) throws CardPlacementException {
		for(int i=0 ; i<this.tavolo.getGiocatori().length; i++) {
			System.out.println("Giocatore " +(i+1) + ": " + this.tavolo.getGiocatori()[i].getPunteggio() + " punti");
		}
	}
	
	/***
	 * @return il nome esteso dell'opzione del menu
	 */
	public String toString() {
		return "Visualizza il tuo punteggio";
	}
	
}