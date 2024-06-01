package Main;

import Menu.MenuManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String buffer;
		GameManager gm = new GameManager();
		List<String> nicknames = new ArrayList<String>();
		
		//inserimento del numero dei giocatori
		do {
			System.out.println("Digitare il numero di giocatori (da 2 a 4) che parteciperanno alla partita: ");
			buffer = sc.nextLine();
		}while(!gm.insertNumGiocatori(buffer));
		
		for(int i=0; i<gm.getnGiocatori(); i++) {
			System.out.println("Digita il nickname del giocatore n"+(i+1));
			buffer = sc.nextLine();
			nicknames.add(buffer);
		}
		
		//creazione tavolo di gioco
		gm.createTable(gm.getnGiocatori(), nicknames);
		
		//codice per giocare la carta starter sul retro	
		gm.rovesciaStarter(buffer);
		

		int turnoGiocatore=0;
		int scelta=0;

		//creazione di un nuovo menu' manager
		MenuManager mg = gm.getMg();
		
		//controllo delle condizioni per la fine della partita
		while((gm.getTavolo().condEndGame2()==false || gm.getTavolo().condEndGame3()==false) && gm.getTavolo().condEndGame1()==false) {
			System.out.println("\nTurno del giocatore " + (gm.getTavolo().getGiocatori()[turnoGiocatore].getNickname()));
			
			//variabile booleana per capire se il turno puo' passare al giocatore successivo
			//diventa true solo nel caso in cui la carta scelta dal giocatore sia stata correttamente posizionata nella sua matrice
			boolean continua = false;
			//menu operazioni che un giocatore può fare
			do {
				
				do{
					//System.out.println(ConsoleColors.RED + "prova rosso" +  ConsoleColors.RESET + "prova normale");
					mg.displayMenu();
					
					System.out.println("Inserisci la tua scelta: ");
					
					//inserimento della scelta da parte dell'utente
					buffer = sc.nextLine();
					try {
						scelta = Integer.parseInt(buffer);
					}
					catch(NumberFormatException e) {
						System.out.println("Errore: l'input non è un numero intero");
					}								
				}while(scelta<1 || scelta>7);
				
				//l'utente ha scelto un'opzione correttamente
				if(mg.esegui(gm.getTavolo().getGiocatori()[turnoGiocatore], scelta)) {
					//l'opzione scelta é stata eseguita correttamente 
					continua = true;
				}
				else {
					//c'é stato un errore nell'esecuzione di un opzione
					//l'unica opzione che potrebbe lanciare un errore che non é stato gestito é quella per posizionare una carta
					System.out.println("Errore: prova di nuovo");
					continua = false;
				}
				System.out.println("\n\n\n\n");
			//il giocatore continua il suo turno finché non posiziona una carta e
			//solo nel caso in cui il posizionamento non abbia restituito errori
			}while(scelta!= 1 || continua == false);
			
			gm.getTavolo().getGiocatori()[turnoGiocatore].incrementaTurniGiocati();

			//DE-COMMENTARE PER AVERE IL CICLO DEI GIOCATORI
			if(turnoGiocatore<(gm.getTavolo().getGiocatori().length-1))
				turnoGiocatore++;
			else
				turnoGiocatore=0;
			
			if(gm.getTavolo().condEndGame1() == true) {
				System.out.println("La partita è terminata perchè uno dei mazzi ha esaurito le carte disponibili da pescare\n");
			}
			if(gm.getTavolo().condEndGame2()==true && gm.getTavolo().condEndGame3()==false) {
				System.out.println("La partita sta per terminare perchè un giocatore ha raggiunto i 20 punti\nAd alcuni giocatori spetta ancora un turno prima di terminare la partita\n");
			}
		}
		//PARTITA FINITA!!	
		
		gm.calcoloPuntiObiettivi();		//calcolo punteggi delle carte obiettivo
		mg.stampaClassifica(); 		//calcola classifica e la stampa
		
		sc.close();
	}
}