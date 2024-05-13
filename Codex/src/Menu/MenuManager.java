package Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import Ecccezioni.CardPlacementException;
import Giocatori.*;

public class MenuManager {
	
	private Map <Integer, MenuOption> opzioni;  //mappa per gestire le opzioni del menu (chiave + elemento)
	TavoloDaGioco tavolo;

	public MenuManager(TavoloDaGioco t) {
		//istanzia la mappa per le opzioni
		this.opzioni = new HashMap<>();
		this.tavolo = t;
		
		PosizionaCarta posCarta = new PosizionaCarta(t);
		CarteTavolo cTavolo = new CarteTavolo(t);
		VisualizzaMatrice vMatrice = new VisualizzaMatrice();
		CarteMano cMano = new CarteMano();
		VisualizzaPunteggi vPunti = new VisualizzaPunteggi();
		ReverseCard rCarta = new ReverseCard();
		
		//inserimento delle opzioni
		opzioni.put(1, posCarta);
		opzioni.put(2, cTavolo);
		opzioni.put(3, vMatrice);
		opzioni.put(4, cMano);
		opzioni.put(5, vPunti);
		opzioni.put(6, rCarta);
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
    
    public boolean esegui(Giocatore g, int scelta) {
    	MenuOption opzione = opzioni.get(scelta);
    	
    	if(opzione != null) {
    		try {
				opzione.execute(g);
				return true;
			} catch (CardPlacementException e) {
				e.printStackTrace();
				return false;
			}
    	}
    	else {
    		return false;
    	}
    }
    
    /*public void run(int scelta) throws Exception {
    	//FIXME è da finire non credo che vada neanchè per bontà divina
    	Scanner sc = new Scanner(System.in);
    	boolean continua = true;
    	
    	while(continua) {
    		///mostra a schermo il menu con le sue opzioni
    		displayMenu();
    		
    		System.out.println("inserisci la tua scelta: ");
    		int sceltaUtente = sc.nextInt();
    	}
    	String opzioneScelta = opzioni.get(opzioneScelta);
    	
    	if(opzioneScelta != null) {
    		try {
    			opzioneScelta.execute();
    		}
    		catch(CardPlacementException e) {
    			//TODO gestiore l'eccezione idk cosa fare whelp
    		}
    	}
    	else {
    		throw new Exception();
    	}
    }*/

}
