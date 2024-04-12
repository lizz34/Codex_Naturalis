package scacchiera;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Gioco gioco = new Gioco();
		
		while(true) {
			System.out.println("Che cosa vuoi fare?");
			System.out.println("START: comincia la partita");
			System.out.println("INSERT, NOME, COGNOME"); //colore assegnato automaticamente
			System.out.println("STAMPA: stampa la scacchiera");
			System.out.println("MUOVI, RIGA ORIGINE, COLONNA ORIGINE,"
					+"RIGA DESTINAZIONE, COLONNA DESTINAZIONE");
			
			
			String input = sc.nextLine();
			String[] splittedInput=input.split(","); //splitto usando la virgola come carattere separatore
			
			if(splittedInput[0].equals("START")) {
				//codice per start
			}
			if(splittedInput[0].equals("INSERT")) {
				//codice per inserire utente
			}
			if(splittedInput[0].equals("STAMPA")) {
				gioco.stampaStato();
			}
			if(splittedInput[0].equals("MUOVI")) {
				//codice per movimento
			}
			
		}
	}

}
