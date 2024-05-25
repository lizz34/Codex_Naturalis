package Menu;

import java.util.Scanner;

import Eccezioni.CardPlacementException;
import Giocatori.*;

/***
 * opzione del menu per rovesciare una carta, quindi per giocarla con il retro
 */
public class ReverseCard implements MenuOption{
	
	private Scanner sc = new Scanner(System.in);
	private String buffer;
	/***
	 * costruttore della classe che gestisce l'opzione del menu per rovesciare una determinata carta per poi poterla giocare con il retro
	 */
	public ReverseCard() {
	}

	@Override
	/***
	 * permette al giocatore di girare una carta prima di giocarla
	 * @param g: il giocatore che ha selezionato l'opzione
	 * @throws CardPlacementException
	 */
	public void execute(Giocatore g) throws CardPlacementException{
				
		int nCarta=0;
		do {
			System.out.println("Inserisci il numero della carta che vuoi rovesciare (da 1 a 3); \nPremi 0 per annullare l'operazione: ");
			buffer = sc.nextLine();
			try {
				nCarta = Integer.parseInt(buffer);
			}
			catch(NumberFormatException e) {
				System.out.println("Errore: inserisci un numero intero");
			}
		}
		while(nCarta < 0 || nCarta > 3);
		
		if(nCarta>0) {
			if(g.getMano().get(nCarta-1).getFronte() == true)
				g.getMano().get(nCarta-1).setFronte(false);
			else
				g.getMano().get(nCarta-1).setFronte(true);
		}
		
	}
	
	/***
	 * @return il nome esteso dell'opzione del menu
	 */
	public String toString() {
		return "Rovescia una carta della mano";
	}
}


