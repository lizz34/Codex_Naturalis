package Giocatori;
import Carte.*;

public class CampoDaGioco {
	private Carta campoPersonale[][];
	private final int nRigheTabella=50;
	private final int nColonneTabella=50;
	
	/***
	 * costruttore del campo da gioco dell'utente
	 * @param cartaStarter che viene assegnata all'utente all'inizio della partita
	 */
	public CampoDaGioco(Carta cartaStarter) {
		//generazione della matrice di gioco
		this.campoPersonale = new Carta[this.nRigheTabella][this.nColonneTabella];
		
		for(int i=0; i<this.nRigheTabella; i++) {
			for(int j=0; j<this.nColonneTabella; j++) {
				this.campoPersonale[i][j] = null;
			}
		}
		
		this.campoPersonale[(this.nRigheTabella/2)-1][(this.nColonneTabella/2)-1]=cartaStarter;
	}
	
	//la carta starter é nella posizione 24x24
	
	/***
	 * funzione per posizionare la carta sulla matrice
	 * @param carta: che vuoi posizionare
	 * @param nRiga: num di riga su cui é posizionata la carta che vuoi sovrappore (da 0 a 49)
	 * @param nColonna:  num di colonna su cui é posizionata la carta che vuoi sovrapporre (da 0 a 49)
	 * @param nAngolo: num dell'angolo su cui vuoi posizionare la carta (da 0 a 7 in senso orario fronte e retro)
	 * @return ritorna false se la carta non puo' essere posizionata, true se viene posizionata
	 */
	public boolean posizionaCarta(Carta carta, int nRiga, int nColonna, int nAngolo) {
		if(campoPersonale[nRiga][nColonna]!=null && (nAngolo>=0 && nAngolo<8)) {
			if(campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).getOccupato()==false && controllaCarta(carta)==true) {
				switch (nAngolo) {
				case 0,4:
					if(campoPersonale[nRiga-1][nColonna-1] == null) {
						campoPersonale[nRiga-1][nColonna-1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 1,5:
					if(campoPersonale[nRiga-1][nColonna+1] == null) {
						campoPersonale[nRiga-1][nColonna+1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 2,6:
					if(campoPersonale[nRiga+1][nColonna+1] == null) {
						campoPersonale[nRiga+1][nColonna+1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 3,7:
					if(campoPersonale[nRiga+1][nColonna-1] == null) {
						campoPersonale[nRiga+1][nColonna-1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
						
				}
				
			}
			
		}
		
		System.out.println("Non puoi posizionare la carta in quella posizione");
		return false;
	}
	
	/***
	 * controlla se la carta è di tipo risorsa o oro; nel secondo caso richiama la funzione controllaCarta(CartaOro carta)
	 * @param carta: carta che si vuole posizionare
	 * @return ritorna true se la carta è posizionabile, false in caso contrario
	 */
	public boolean controllaCarta(Carta carta) {
		if(carta instanceof CartaRisorsa) {
			return true;
		}
		else if(carta instanceof CartaOro) {
			return controllaCarta((CartaOro)carta);
		}
		
		System.out.println("Errore identificazione carta");
		return false;
	}
	
	/***
	 * controlla se la carta oro passata è posizionabile
	 * @param carta: carta che si vuole posizionare
	 * @return ritorna true se la carta è posizionabile, false in caso contrario
	 */
	/*METTERE I CONTATORI IN UN VETTORE?
	 * pro: meno righe di codice e possibilità di implementare una funzione che sostituisce i 3 switch e evita la ripetizione di codice
	 * contro: ogni pos del vettore corrisponde ad un tipo: 0-->lupo 1-->foglia .... (non è molto intuitivo (però si può spiegare a commento))
	 * NB: parlo di vettore poichè in java non si può passare un primitivo per riferimento
	 */
	public boolean controllaCarta(CartaOro carta) {
		if(!(carta instanceof CartaOro))
			return false;
		
		int contaLupi=0;
		int contaFoglie=0;
		int contaFarfalle=0;
		int contaFunghi=0;
		//figure?
		int lupiRichiesti=0;
		int foglieRichieste=0;
		int farfalleRichieste=0;
		int funghiRichiesti=0;
		
		for(int i=0; i<this.nRigheTabella; i++) {
			for(int j=0; j<this.nColonneTabella; j++) { //scorrimento della matrice
				if(this.campoPersonale[i][j] != null) { //se troviamo una carta..
					if(campoPersonale[i][j].getFronte()==true) { //se è giocata di fronte..
						for(int k=0; k<4; k++) {
							if(campoPersonale[i][j].getSpecifiAngolo(k).getOccupato() == false) { //se l'angolo non è occupato e quindi il disegno si può vedere..
								switch(campoPersonale[i][j].getSpecifiAngolo(k).getDisegno()) { //contiamo il disegno incrementando un contatore
								case lupo:
									contaLupi++;
									break;
								case foglia:
									contaFoglie++;
									break;
								case farfalla:
									contaFarfalle++;
									break;
								case fungo:
									contaFunghi++;
									break;
								default: //case null
									break;
								}
							}
						}
					}
					else { //invece se è giocata con il retro a vista controllo gli angoli sul retro (4-7)
						for(int k=4; k<8; k++) {
							if(campoPersonale[i][j].getSpecifiAngolo(k).getOccupato() == false) { 
								switch(campoPersonale[i][j].getSpecifiAngolo(k).getDisegno()) {
								case lupo:
									contaLupi++;
									break;
								case foglia:
									contaFoglie++;
									break;
								case farfalla:
									contaFarfalle++;
									break;
								case fungo:
									contaFunghi++;
									break;
								default: //case null
									break;
								}
							}
						}
					}
				}
			}
		}
		
		
		for(int i=0; i<carta.getDisegnoRichieste().length; i++) {
			if(carta.getDisegnoRichieste()[i]!=null) {
				switch(carta.getDisegnoRichieste()[i]) {
				case lupo:
					lupiRichiesti++;
					break;
				case foglia:
					foglieRichieste++;
					break;
				case farfalla:
					farfalleRichieste++;
					break;
				case fungo:
					funghiRichiesti++;
					break;
				default:
					System.out.println("Default");
					break;
				}
			}
			
		}
		
		if(lupiRichiesti<=contaLupi && foglieRichieste<=contaFoglie && farfalleRichieste<=contaFarfalle && funghiRichiesti<=contaFunghi) {
			System.out.print("Puoi posizionare la carta perche' le richieste di posizionamento sono soddisfatte");
			return true;
		}
		else {
			System.out.print("Per posizionare la carta servono: ");
			if(lupiRichiesti!=0)
				System.out.print(lupiRichiesti + " lupi ");
			if(foglieRichieste!=0)
				System.out.print(foglieRichieste + " foglie ");
			if(farfalleRichieste!=0)
				System.out.print(farfalleRichieste + " farfalle ");
			if(funghiRichiesti!=0)
				System.out.print(funghiRichiesti + " funghi ");
			
			System.out.print("\nInvece sul campo hai: ");
			if(lupiRichiesti!=0)
				System.out.print(contaLupi + " lupi ");
			if(foglieRichieste!=0)
				System.out.print(contaFoglie + " foglie ");
			if(farfalleRichieste!=0)
				System.out.print(contaFarfalle + " farfalle ");
			if(funghiRichiesti!=0)
				System.out.print(contaFunghi + " funghi ");
			System.out.println("\n");
			return false;
		}
	}
	
	/***
	 * stampa della matrice ossia l'area di gioco del giocatore
	 */
	public void stampaCampoDaGioco() {
		for(int i=0; i<nRigheTabella; i++) {
			System.out.println("---------");
			for(int j=0; j<nColonneTabella; j++) {
				
				if(campoPersonale[i][j]!=null) {
					System.out.println("|   ");
					System.out.println(campoPersonale[i][j].toString());
					System.out.println("   |");
				}
			}
		}
	}
}