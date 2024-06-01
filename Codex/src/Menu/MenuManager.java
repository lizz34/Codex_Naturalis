package Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Eccezioni.CardPlacementException;

import java.util.Set;
import java.util.TreeSet;

import Giocatori.*;

public class MenuManager {
	
	private Map <Integer, MenuOption> opzioni;  //mappa per gestire le opzioni del menu (chiave + elemento)
	TavoloDaGioco tavolo;

	public MenuManager(TavoloDaGioco t) {
		//istanzia la mappa per le opzioni
		this.opzioni = new HashMap<>();
		this.tavolo = t;
		
		//istanzia nuovi oggetti per le opzioni
		PosizionaCarta posCarta = new PosizionaCarta(t);
		CarteTavolo cTavolo = new CarteTavolo(t);
		VisualizzaMatrice vMatrice = new VisualizzaMatrice();
		CarteMano cMano = new CarteMano();
		VisualizzaPunteggio vPunti = new VisualizzaPunteggio(t);
		ReverseCard rCarta = new ReverseCard();
		VisualizzaObiettivi vObiettivi = new VisualizzaObiettivi(t);
		
		//inserimento delle opzioni
		opzioni.put(1, posCarta);
		opzioni.put(2, cTavolo);
		opzioni.put(3, vMatrice);
		opzioni.put(4, cMano);
		opzioni.put(5, rCarta);
		opzioni.put(6, vPunti);
		opzioni.put(7, vObiettivi);
	}
    
    /***
     * visualizza a schermo il menu con tutte le sue opzioni
     */
    public void displayMenu() {
        System.out.println("Menu:");
        // for generalizzato per stampare a schermo tutte le opzioni del menu
        for (Entry<Integer, MenuOption> o : opzioni.entrySet()) {
            System.out.println(o.getKey() + ". " + o.getValue());
        }
    }
    
    /***
     * esegue l'opzione del menu' che é stata scelta
     * @param g: il giocatore che ha scelto l'opzione
     * @param scelta: il numero intero che corrisponde all'opzione scelta
     * @return true se l'opzione é stata eseguita correttamente, false in caso contrario
     * @throws CardPlacementException
     */
    public boolean esegui(Giocatore g, int scelta){
    	MenuOption opzione = opzioni.get(scelta);
    	
    	if(opzione != null) {
    		try {
				opzione.execute(g);
				return true;
			} catch (CardPlacementException e) {
				return false;
			}
    	}
    	else {
    		return false;
    	}
    }
    
    /***
     * funzione che stampa la classifica finale della partita
     */
    public void stampaClassifica() {
    	
    	Set<Integer> punteggi = new TreeSet<>(); //TreeSet non ammette duplicati al suo interno e inoltre i suoi elementi sono sempre ordinati
    	
    	for(Giocatore g : this.tavolo.getGiocatori()) {
    		punteggi.add(g.getPunteggio());
    	}
    	
    	System.out.println("\nClassifica dei punteggi: \n");
    	for(int p : punteggi) {
    		for(Giocatore g : this.tavolo.getGiocatori()) {
    			if(p==g.getPunteggio())
    				System.out.println(g.getNickname() + ": " + p + " punti");
    		}
    	}
    	
    }
    
}
