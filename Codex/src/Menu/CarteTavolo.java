package Menu;

import Carte.CartaOro;
import Carte.CartaRisorsa;
import Eccezioni.CardPlacementException;
import Giocatori.Giocatore;
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
	 * mostra le carte che ci sono sul tavolo da gioco disponibili per essere pescate
	 * @param g: il giocatore che ha selezionato l'opzione
	 * @throws CardPlacementException
	 */
	public void execute(Giocatore g) throws CardPlacementException {
		
		System.out.println("Carte risorsa che puoi pescare:");
		for(CartaRisorsa c: tavolo.getCarteRisorsaBanco()) {
			System.out.println(c.toString() + "\n\n");
		}
		
		System.out.println("\nCarte oro che puoi pescare:");
		for(CartaOro c: tavolo.getCarteOroBanco()) {
			System.out.println(c.toString() + "\n\n");
		}
	}
	
	/***
	 * @return il nome esteso dell'opzione del menu
	 */
	public String toString() {
		return "Guarda le carte che puoi pescare";
	}

}
