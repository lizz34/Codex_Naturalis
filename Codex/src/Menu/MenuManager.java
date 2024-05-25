package Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import Ecccezioni.CardPlacementException;
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
        System.out.println("\n\n\n\nMenu:");
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
     * funzione che esegue il calcolo dei punti finali dei giocatori, aggiungendo al loro punteggio quello dei vari obiettivi, se conseguiti
     */
    public void calcoloClassifica() {
    	//TODO dove mettiamo conteggio carte obiettivo? qua o game manager?
    /*	int punti;
    	
    	for(int i=0; i<this.tavolo.getGiocatori().length; i++){
    		punti=0;
    		
    		for(CartaObiettivo o : this.tavolo.getObiettiviComuni()) {
    			punti+=this.tavolo.getGiocatori()[i].getCampoPersonale().controllaObiettivo(o);
    		}
    		
    		punti+=this.tavolo.getGiocatori()[i].getCampoPersonale().controllaObiettivo(this.tavolo.getGiocatori()[i].getCartaObiettivo());
    		
    		this.tavolo.getGiocatori()[i].incrementaPunteggio(punti);
    		System.out.println("Punti che vengono assegnati al giocatore " +i+ ": "+punti);
    		System.out.println("Punti del giocatore "+i+": "+this.tavolo.getGiocatori()[i].getPunteggio());
    		
    	}*/
    	
    	this.stampaClassifica();
    }
    
    /***
     * funzione che stampa la classifica finale della partita
     */
    public void stampaClassifica() {
    	
    	Set<Integer> punteggi = new TreeSet<>(); //TreeSet non ammette duplicati al suo interno e inoltre i suoi elementi sono sempre ordinati
    	
    	for(Giocatore g : this.tavolo.getGiocatori()) {
    		punteggi.add(g.getPunteggio());
    	}
    	
    	for(int p : punteggi) {
    		for(Giocatore g : this.tavolo.getGiocatori()) {
    			if(p==g.getPunteggio())
    				System.out.println(getClass().toString() + " " + p); //TODO pensare ad una stampa migliore con anche il nome del giocatore
    		}
    	}
    	
    }
    
}
