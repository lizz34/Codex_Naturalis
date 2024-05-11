package Menu;

import Carte.CartaOro;
import Carte.CartaRisorsa;
import Ecccezioni.CardPlacementException;
import Giocatori.TavoloDaGioco;

/***
 * opzione del menu per visualizzare le carte che sono presenti sul tavolo da gioco
 */
public class CarteTavolo implements MenuOption {
	TavoloDaGioco tavolo;
	
	/***
	 * costruttore dell'opzione del menu per visualizzare le carte presenti sul tavolo da gioco
	 * @param t: il tavolo da gioco su cui si sta svolgendo la partita
	 */
	public CarteTavolo(TavoloDaGioco t) {
		this.tavolo = t;
	}

	@Override
	/***
	 * usa il metodo toString per stampare le carte sul tavolo
	 */
	public void execute() throws CardPlacementException {
		this.toString();

	}
	
	/***
	 * toString dell'opzione del menu per visualizzare le carte presenti sul tavolo da gioco.
	 * da utilizzare al posto del metodo execute()
	 */
	public String toString() {
		String stringa = "carte risorsa disponibili: \n";
		
		for(CartaRisorsa c: tavolo.getCarteRisorsaBanco()) {
			stringa += c.toString() + "\n";
		}
		stringa+= "Carte oro che puoi pescare: \n";
		for(CartaOro c: tavolo.getCarteOroBanco()) {
			stringa += c.toString() + "\n";
		}
		
		return stringa;
	}

}
