package Menu;
import Ecccezioni.CardPlacementException;
import Giocatori.*;
import obiettivi.CartaObiettivo;

/***
 * opzione del menu per visualizzare a console il proprio punteggio
 */
public class VisualizzaObiettivi implements MenuOption{
	
	private TavoloDaGioco tavolo;
	/***
	 * costruttore della classe che gestisce l'opzione del menu per visualizzare a console il punteggio di un giocatore
	 */
	public VisualizzaObiettivi(TavoloDaGioco tavolo) {
		this.tavolo = tavolo;
	}
	
	@Override
	/***
	 * stampa sulla console gli obiettivi del giocatore selezionato
	 * @param g: il giocatore che ha selezionato l'opzione
	 * @throws CardPlacementException
	 */
	public void execute(Giocatore g) throws CardPlacementException {
		System.out.println("Obiettivi comuni:");
		for(CartaObiettivo c : this.tavolo.getObiettiviComuni()) {
			System.out.print(c.getObiettivo()+"\n");
		}
		
		System.out.println("\nObiettivo privato:\n"+g.getCartaObiettivo().getObiettivo() + "\n");
	}
	
	/***
	 * @return il nome esteso dell'opzione del menu
	 */
	public String toString() {
		return "Visualizza gli obiettivi";
	}
	
}
