package Main;

import Menu.MenuManager;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String buffer;
		GameManager gm = new GameManager();
		
		do {
			System.out.println("Digitare il numero di giocatori (da 2 a 4) che parteciperanno alla partita: ");
			buffer = sc.nextLine();
		}while(!gm.insertNumGiocatori(buffer));
		
		gm.createTable(gm.getnGiocatori());
		
		
		int turnoGiocatore=0;
		int scelta=0;
		//e' sicuramente da ricontrollare il codice, TODO commentare e controllare le eccezioni 
		MenuManager mg = gm.getMg();
		
		while(gm.getTavolo().condEndGame1()==false && gm.getTavolo().condEndGame2()==false) {
			System.out.println("\nTurno del giocatore " + (turnoGiocatore+1));
			
			//variabile booleana per capire se il turno puo' passare al giocatore successivo
			//diventa true solo nel caso in cui la carta scelta dal giocatore sia stata correttamente posizionata nella sua matrice
			boolean continua = false;
			//menu operazioni che un giocatore può fare
			do {
				
				do{
					mg.displayMenu();
					
					System.out.println("inserisci la tua scelta: ");
					
					buffer = sc.nextLine();
					try {
						scelta = Integer.parseInt(buffer);
					}
					catch(NumberFormatException e) {
						System.out.println("Errore: l'input non è un numero intero");
					}								
				}while(scelta<1 || scelta>6);
				
				//l'utente ha scelto un'opzione correttamente
				mg.esegui(gm.getTavolo().getGiocatori()[turnoGiocatore], scelta);
				
				
			//FIXME da sistemare la condizione per uscire da questo while
			}while(continua == false);
			
			/*//DE-COMMENTARE PER AVERE IL CICLO DEI GIOCATORI
			if(turnoGiocatore<(gm.getTavolo().getGiocatori().length-1))
				turnoGiocatore++;
			else
				turnoGiocatore=0;
			*/
		}
		sc.close();
	}
}