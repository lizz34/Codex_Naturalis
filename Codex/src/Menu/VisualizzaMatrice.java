package Menu;

import Ecccezioni.CardPlacementException;
import Giocatori.*;

/***
 * opzione del menu per visualizzare a console la propria area di gioco
 */
public class VisualizzaMatrice implements MenuOption {

	/***
	 * costruttore della classe che gestisce l'opzione del menu per visualizzare a console l'area di gioco di un giocatore
	 */
	public VisualizzaMatrice() {
	}

	@Override
	/***
	 * stampa sulla console la matrice del giocatore selezionato
	 * @param g: il giocatore che ha selezionato l'opzione
	 * @throws CardPlacmentException
	 */
	public void execute(Giocatore g) throws CardPlacementException{
		g.getCampoPersonale().stampaCampoDaGioco();
		
		//aggiunge il numero di figure che sono presenti sul campo da gioco
		//per facilitare il posizionamento delle carte al giocatore
		int cont[] = new int[7]; //0=lupi; 1=foglie; 2=farfalle; 3=funghi; 4=boccetta; 5=piuma; 6=pergamena
		g.getCampoPersonale().contaFigure(cont);
		System.out.print("Sul campo hai: ");
		if(cont[0]!=0)
			System.out.print(cont[0] + " lupi ");
		if(cont[1]!=0)
			System.out.print(cont[1] + " foglie ");
		if(cont[2]!=0)
			System.out.print(cont[2] + " farfalle ");
		if(cont[3]!=0)
			System.out.print(cont[3] + " funghi ");
		if(cont[4]!=0)
			System.out.print(cont[3] + " boccetta ");
		if(cont[5]!=0)
			System.out.print(cont[3] + " piuma ");
		if(cont[6]!=0)
			System.out.print(cont[3] + " pergamena ");
		System.out.println("\n");
		
	}
	
	/***
	 * @return il nome esteso dell'opzione del menu
	 */
	public String toString() {
		return "Visualizza la tua area di gioco";
	}

}
