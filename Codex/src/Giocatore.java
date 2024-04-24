import java.util.Random;

import Carte.*;

public class Giocatore {
	private int punteggio;
	private Carta carte[];
	final private CarteObiettivo cartaObiettivo;
	final private Carta cartaStarter;
	//variabili della matrice
	CampoDaGioco campoPersonale;
	
	
	public Giocatore(int punteggio, Carta carte[], CarteObiettivo obiettivo, Carta cartaStarter) {
		//al momento dell'avvio della partita al giocatore vengono assegnate tramite il main 
		//la carta obiettivo (che non cambierà mai)
		//le 3 carte di partenza da usare
		//la carta starter
		this.punteggio = punteggio;
		this.cartaObiettivo = obiettivo;
		this.cartaStarter = cartaStarter;
		
		for(int i = 0; i < 3; i++) {
			this.carte[i] = carte[i];
		}
		
		campoPersonale = new CampoDaGioco(this.cartaStarter);
		
	}
	public static String estraiCartaObiettivoCasuale(String[] obiettivi) {
        Random rand = new Random();
        int indice = rand.nextInt(obiettivi.length);
        return obiettivi[indice];
    } //non riuscendo a richaimare l'array già essitente l'ho ritrascritto 

    public static void main(String[] args) {
        // Array contenente le carte obiettivo
        String[] obiettivi = {"Due punti per ogni due piume",
                "Due punti per ogni due boccette",
                "Due punti per ogni due pergamene",
                "Tre punti se si hanno una piuma, una boccetta e una pergamena",
                "Due punti per ogni tre farfalle",
                "Due punti per ogni tre lupi",
                "Due punti per ogni tre foglie",
                "Due punti per ogni tre funghi",
                "Tre punti se si ha la seguente disposizione: due carte verticali di colore viola con l’angolo in alto a sinistra che combacia con l’angolo in basso a destra di una carta blu",
                "Tre punti se si ha la seguente disposizione: due carte verticali di colore blu con l'angolo in alto a destra che combacia con l'angolo in basso a sinistra di una carta rossa",
                "Tre punti se si ha la seguente disposizione: due carte verticali verdi dove l'angolo in basso a sinistra combacia con l'angolo in alto a destra di una carta viola",
                "Tre punti se si ha la seguente disposizione: due carte verticali rosse dove l'angolo in basso a destra combacia con l'angolo in alto a sinistra di una carta verde",
                "Due punti se si ha la seguente disposizione: se si hanno tre carte di colore viola disposte diagonalmente, dove la carta sinistra sarà quella più alta e quella a destra la più bassa",
                "Due punti se si ha la seguente disposizione: sì si hanno tre carte di colore blu, dove la carta sinistra sarà la più bassa e quella destra la più alta",
                "Due punti se si ha la seguente disposizione: se si hanno tre carte di colore verde disposte diagonalmente, dove la carta sinistra sarà quella più alta e quella a destra la più bassa",
                "Due punti se si ha la seguente disposizione: sì si hanno tre carte di colore rosso, dove la carta sinistra sarà la più bassa e quella destra la più alta"
            };

        // Estrai una carta obiettivo casuale
        String cartaObiettivo = estraiCartaObiettivoCasuale(obiettivi);
        System.out.println("Carta obiettivo estratta: " + cartaObiettivo);
    }

	
	//funzione che permettere di scegliere la prossima carta da giocare dalle 3 che possiede il giocatore
	public Carta scegliCarta() {
		Carta cartaScelta = null;
		
		//TODO : visualizzazione delle carte
		//scelta della carta e rimozione della carta dal vettore
		//scelta di una nuova carta dalle 4 che ci sono disponibili sul tavolo di gioco
		
		//TODO: chiamata alla funzione del main che permette di prendere una delle 4 carte presenti sul tavolo di gioco
		
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
