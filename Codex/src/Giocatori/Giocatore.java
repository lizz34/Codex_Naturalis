package Giocatori;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import Carte.CartaObiettivo;
import Carte.*;

public class Giocatore {
		
        CartaObiettivo cartaObiettivo = new CartaObiettivo();
        String[] dueObiettivi = cartaObiettivo.ottieniDueObiettiviCasuali();
        
        // Estrai casualmente uno dei due obiettivi
        int indiceCasuale = (int) (Math.random() * 2); /
        String obiettivoCasuale = dueObiettivi[indiceCasuale];
        
        // Stampa l'obiettivo estratto per la prova
        //System.out.println("Obiettivo estratto per la prova: " + obiettivoCasuale);
        
        
		
	
	private int punteggio;
	//private Carta carte[];
	Vector<Carta> carte = new Vector<>();
	final private CarteObiettivo cartaObiettivo;
	final private Carta cartaStarter;
	//variabili della matrice
	CampoDaGioco campoPersonale;
	
	
	public Giocatore(Carta carte[], /*CarteObiettivo obiettivo,*/ Carta cartaStarter) {
		//al momento dell'avvio della partita al giocatore vengono assegnate tramite il main 
		//la carta obiettivo (che non cambierà mai)
		//le 3 carte di partenza da usare
		//la carta starter
		this.punteggio = 0;
		//this.cartaObiettivo = obiettivo;
		this.cartaStarter = cartaStarter;
		
		for(int i = 0; i < 3; i++) {
			this.carte.add(manoIniziale[i]);
		}
		
		campoPersonale = new CampoDaGioco(this.cartaStarter);
	}
	
	
	//funzione che permettere di scegliere la prossima carta da giocare dalle 3 che possiede il giocatore
	public Carta scegliCarta() {
		Carta cartaScelta = null;
		int scelta = 0;
		Scanner sc =  new Scanner();
		
		//cicli per chiedere all'utente di scegliere la prossima carta che vuole giocare
		do {
			for(int i = 0; i < 3; i++) {
				System.out.println(i++ + ": " + carte[i].toString() + " \n");
			}
			
			System.out.println();
			System.out.println("Inserisci il numero della carta che vuoi posizionare sul tuo tavolo di gioco: ");
			
			try {
				scelta = sc.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("Input non valido. Inserisci un numero da 1 a 3.");
				sc.nextLine();
			}
			
			if(scelta < 1 || scelta > 3) {
				System.out.println("Inserisci un numero da tra 1 e 3 per scegliere la tua carta.");
			}
			else {
				cartaScelta = carte[scelta];
			}
		}
		while(cartaScelta== null);
		
		//stampa la matrice per permettere al giocatore di scegliere dove posizionare la carta
		campoPersonale.stampaCampoDaGioco();
		
		//TODO: codice per posizionare la carta nella matrice
		
		//rimozione della carta scelta dal vettore di carte del giocatore
		carte.remove(scelta);
		
		//TODO: chiamata alla funzione del main che permette di:
		//vedere le 4 carte presenti sul tavolo di gioco
		//sceglierne una ed aggiungerla al proprio tavolo
		sc.close();
		return cartaScelta;
	}
	

	//getter e setter
	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	public Carta[] getCarte() {
		return carte;
	}

	public void setCarte(Carta[] carte) {
		this.carte = carte;
	}
	
	public CarteObiettivo getCartaObiettivo() {
		return cartaObiettivo;
	}

	public Carta getCartaStarter() {
		return cartaStarter;
	
}
}