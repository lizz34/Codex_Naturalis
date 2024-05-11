package Menu;

import java.util.HashMap;
import java.util.Map;

import Ecccezioni.CardPlacementException;

public class MenuManager {
	
	private Map <Integer, MenuOption> opzioni;  //mappa per gestire le opzioni del menu (chiave + elemento)

	public MenuManager() {
		//istanzia la mappa per le opzioni
		this.opzioni = new HashMap<>();
	}
	
	/***
	 * aggiunge una nuova opzione alla mappa del menu
	 * @param chiave: la chiave identificativa dell'opzione (un numero intero)
	 * @param opzione: l'opzione da aggiungere
	 */
    public void aggiungiOpzione(int chiave, MenuOption opzione) {
        opzioni.put(chiave, opzione);
    }
    
    /***
     * visualizza a schermo il menu con tutte le sue opzioni
     */
    public void displayMenu() {
        System.out.println("Menu: \n");
        // for generalizzato per stampare a schermo tutte le opzioni del menu
        for (Map.Entry<Integer, MenuOption> o : opzioni.entrySet()) {
            System.out.println(o.getKey() + ". " + o.getValue().getClass().getSimpleName());
        }
    }
    
    public void run(int scelta) throws Exception {
    	//FIXME è da finire non credo che vada neanchè per bontà divina
    	MenuOption opzioneScelta = opzioni.get(scelta);
    	
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
    }

}
