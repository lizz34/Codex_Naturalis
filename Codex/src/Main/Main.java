package Main;

import Carte.*;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String buffer;
		
		GameManager gm = new GameManager();
		
		//Inserimento numero giocatori
		do {
			System.out.println("Digitare il numero di giocatori (da 2 a 4) che parteciperanno alla partita: ");
			buffer = sc.nextLine();
		}while(!gm.insertNumGiocatori(buffer));
		
		gm.createTable(gm.getnGiocatori());
		
		
		int turnoGiocatore=0;
		int scelta=0;
		
		//INIZIO DELLA PARTITA
		/*La rotazione dei turni è gestita dalla var turnoGiocatore che parte da 0 e si incrementa fino al numero massimo dei giocatori,
		 * per poi riazzerarsi. In questo modo il vettore giocatori[] scorre dal giocatore 0 al giocare nGiocatori e tutti giocano un turno
		 */
		while(gm.getTavolo().condEndGame1()==false && gm.getTavolo().condEndGame2()==false) {
			System.out.println("\nTurno del giocatore " + (turnoGiocatore+1));
			
			//variabile booleana per capire se il turno puo' passare al giocatore successivo
			//diventa true solo nel caso in cui la carta scelta dal giocatore sia stata correttamente posizionata nella sua matrice
			boolean continua = false;
			//menu operazioni che un giocatore può fare
			do {
				
				do{
					System.out.println("1- Posiziona una carta sull'area di gioco");
					System.out.println("2- Guarda le carte che puoi pescare");
					System.out.println("3- Guarda la tua area di gioco");
					System.out.println("4- Guarda la tua mano");
					buffer = sc.nextLine();
					try {
						scelta = Integer.parseInt(buffer);
					}
					catch(NumberFormatException e) {
						System.out.println("Errore: l'input non è un numero intero");
					}
				}while(scelta<1 || scelta>4);
				
				
				switch(scelta) {
				case 1:
					//posiziona carta dalla mano alla matrice
					//paramentri necessari: carta da posizionare, nRiga, nColonna e nAngolo della carta da sovrapporre
					
					//numero carta da posizionare
					int numCarta=0;
					do {
						System.out.println("Inserisci il numero della carta che vuoi posizionare del tuo mazzo (da 1 a 3): ");
						buffer = sc.nextLine();
						try {
							numCarta = Integer.parseInt(buffer);
						}
						catch(NumberFormatException e) {
							System.out.println("Errore: inserisci un numero intero");
						}
					}
					while(numCarta < 1 || numCarta > 3);
					
					//numero della riga su cui si vuole posizionare la carta
					int nRiga = 0;
					do {
						System.out.println("inserisci il numero della riga della carta su cui vuoi posizionare la nuova carta: ");
						buffer = sc.nextLine();
						try {
							nRiga = Integer.parseInt(buffer);
						}
						catch(NumberFormatException e) {
							System.out.println("Errore: inserisci un numero intero");
						}
					}
					while(nRiga < 0 || nRiga > 49);
					
					//numero della colonna su cui si vuole posizionare la carta
					int nColonna = 0;
					do {
						System.out.println("inserisci il numero della colonna della carta su cui vuoi posizionare la nuova carta: ");
						buffer = sc.nextLine();
						try {
							nColonna = Integer.parseInt(buffer);
						}
						catch(NumberFormatException e) {
							System.out.println("Errore: inserisci un numero intero");
						}
					}
					while(nColonna < 0 || nColonna > 49);
					
					//numero dell'angolo su cui si vuole posizionare la carta
					int nAngolo = 0;
					do {
						System.out.println("inserisci il numero dell'angolo della carta su cui vuoi posizionare la nuova carta: ");
						buffer = sc.nextLine();
						try {
							nAngolo = Integer.parseInt(buffer);
						}
						catch(NumberFormatException e) {
							System.out.println("Errore: inserisci un numero intero");
						}
					}
					while(nAngolo < 1 || nAngolo > 8);

					
					//raccolti correttamente i parametri chiamata alla funzione del giocatore per posizionare la carta
					if(!gm.getTavolo().getGiocatori()[turnoGiocatore].posizionaCarta(numCarta-1, nRiga, nColonna, nAngolo-1)) {
						//se la funzione restituisce false c'é stato un errore nel posizionamento della carta
						System.out.println("errore: riprova a posizionare la carta sul tuo tavolo di gioco di gioco.");
						continua = false;
					}
					else {
						
						String tipoCartaPescata;
						do {
							System.out.println("Inserisci il tipo di carta che vuoi pescare (oro/risorsa): ");
							tipoCartaPescata = sc.nextLine();
							tipoCartaPescata.toLowerCase();
							tipoCartaPescata.trim();
						}while(!(tipoCartaPescata.equals("oro") || tipoCartaPescata.equals("risorsa")));
						
						numCarta=0;
						do {
							System.out.println("Inserisci il numero di carta che vuoi pescare (1/2): ");
							buffer = sc.nextLine();
							try {
								numCarta = Integer.parseInt(buffer);
							}
							catch(NumberFormatException e) {
								System.out.println("Errore: inserisci un numero intero");
							}
						}
						while(numCarta < 1 || numCarta > 2);
						
						if(tipoCartaPescata.equals("oro")) {
							gm.getTavolo().getGiocatori()[turnoGiocatore].setMano(gm.getTavolo().pescaCartaOro(numCarta-1));
						}
						else {
							gm.getTavolo().getGiocatori()[turnoGiocatore].setMano(gm.getTavolo().pescaCartaRisorsa(numCarta-1));
						}
						
						gm.getTavolo().getGiocatori()[turnoGiocatore].getCampoPersonale().stampaCampoDaGioco();
						
						continua = true;
					}
					break;
				case 2:
					//stampa delle carte presenti sul tavolo da gioco che il giocatore puo' pescare
					System.out.println("Carte risorsa che puoi pescare:");
					for(CartaRisorsa c: gm.getTavolo().getCarteRisorsaBanco()) {
						System.out.println(c.toString() + "\n");
					}
					System.out.println("Carte oro che puoi pescare:");
					for(CartaOro c: gm.getTavolo().getCarteOroBanco()) {
						System.out.println(c.toString() + "\n");
					}
					continua = false;
					break;
				case 3:
					//stampa matrice
					gm.getTavolo().getGiocatori()[turnoGiocatore].getCampoPersonale().stampaCampoDaGioco();
					
					continua = false;
					break;
				case 4:
					//stampa della carte che il giocatore ha nella mano
					System.out.println("Ecco le carte che hai in mano:");
					for(Carta c: gm.getTavolo().getGiocatori()[turnoGiocatore].getMano()) {
						System.out.println(c.toString() + "\n");
					}
					
					continua = false;
					break;
				default:
					System.out.println("Errore menu di scelta delle operazioni");
					
					continua = false;
					break;
				}
				
				
			}while(continua == false);
			/*
			if(turnoGiocatore<(gm.getTavolo().getGiocatori().length-1))
				turnoGiocatore++;
			else
				turnoGiocatore=0;
			*/
		}
		
		sc.close();
		
	}
	
}
