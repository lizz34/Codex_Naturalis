package Main;

import Menu.MenuManager;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String buffer;
		GameManager gm = new GameManager();
		
		//inserimento del numero dei giocatori
		do {
			System.out.println("Digitare il numero di giocatori (da 2 a 4) che parteciperanno alla partita: ");
			buffer = sc.nextLine();
		}while(!gm.insertNumGiocatori(buffer));
		
		//creazione tavolo di gioco
		gm.createTable(gm.getnGiocatori());
		
		
		int turnoGiocatore=0;
		int scelta=0;

		//creazione di un nuovo menu' manager
		MenuManager mg = gm.getMg();
		
		//controllo delle condizioni per la fine della partita
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
					
					//inserimento della scelta da parte dell'utente
					buffer = sc.nextLine();
					try {
						scelta = Integer.parseInt(buffer);
					}
					catch(NumberFormatException e) {
						System.out.println("Errore: l'input non è un numero intero");
					}								
				}while(scelta<1 || scelta>6);
				
					//l'utente ha scelto un'opzione correttamente
					if(mg.esegui(gm.getTavolo().getGiocatori()[turnoGiocatore], scelta)) {
						//l'opzione scelta é stata eseguita correttamente 
						continua = true;
					}
					else {
						//c'é stato un errore nell'esecuzione di un opzione
						//l'unica opzione che potrebbe lanciare un errore che non é stato gestito é quella per posizionare una carta
						System.out.println("errore: prova di nuovo");
						continua = false;
					}

			//il giocatore continua il suo turno finché non posiziona una carta e
			//solo nel caso in cui il posizionamento non abbia restituito errori
			}while(scelta!= 1 && continua == false);
			
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