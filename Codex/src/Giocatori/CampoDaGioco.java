package Giocatori;
import Carte.*;
import Ecccezioni.*;

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
	public boolean controllaCarta(CartaOro carta) {
		if(!(carta instanceof CartaOro))
			return false;
		
		int contaFigure[] = new int[7]; //0=lupi; 1=foglie; 2=farfalle; 3=funghi; 4=boccetta; 5=piuma; 6=pergamena
		int contaRichieste[] = new int [4]; //0=lupi; 1=foglie; 2=farfalle; 3=funghi
		
		for(int i=0; i<contaFigure.length; i++)
			contaFigure[i]=0;
		for(int i=0; i<contaRichieste.length; i++)
			contaRichieste[i]=0;
		
		contaFigure(contaFigure);
		
		for(int i=0; i<carta.getDisegnoRichieste().length; i++) {
			if(carta.getDisegnoRichieste()[i]!=null) {
				contatoreDiFigure(contaRichieste, carta.getDisegnoRichieste()[i]);
			}
		}
		
		
		boolean posiziona=true;
		for(int i=0; i<contaRichieste.length; i++) {
			if(contaRichieste[i]<=contaFigure[i] && posiziona == true)
				posiziona=true;
			else
				posiziona=false;
		}
		
		if(posiziona) {
			System.out.print("Puoi posizionare la carta perche' le richieste di posizionamento sono soddisfatte");
			return true;
		}
		else {
			System.out.print("Per posizionare la carta servono: ");
			if(contaRichieste[0]!=0)
				System.out.print(contaRichieste[0] + " lupi ");
			if(contaRichieste[1]!=0)
				System.out.print(contaRichieste[1] + " foglie ");
			if(contaRichieste[2]!=0)
				System.out.print(contaRichieste[2] + " farfalle ");
			if(contaRichieste[3]!=0)
				System.out.print(contaRichieste[3] + " funghi ");
			
			System.out.print("\nInvece sul campo hai: ");
			if(contaRichieste[0]!=0)
				System.out.print(contaFigure[0] + " lupi ");
			if(contaRichieste[1]!=0)
				System.out.print(contaFigure[1] + " foglie ");
			if(contaRichieste[2]!=0)
				System.out.print(contaFigure[2] + " farfalle ");
			if(contaRichieste[3]!=0)
				System.out.print(contaFigure[3] + " funghi ");
			System.out.println("\n");
			return false;
		}
	}
	
	
	/**
	 * conta tutte le figure presenti sul campo da gioco del giocatore
	 * @param contaFigure[]: vettore di interi i cui elementi verrano usati come contatori da incrementare
	 */
	public void contaFigure(int contaFigure[]) {
		for(int i=0; i<this.nRigheTabella; i++) {
			for(int j=0; j<this.nColonneTabella; j++) { //scorrimento della matrice
				if(this.campoPersonale[i][j] != null) { //se troviamo una carta..
					if(campoPersonale[i][j].getFronte()==true) { //se è giocata di fronte..
						for(int k=0; k<4; k++) {
							if(campoPersonale[i][j].getSpecifiAngolo(k).getOccupato() == false) { //se l'angolo non è occupato e quindi il disegno si può vedere..
								contatoreDiFigure(contaFigure, campoPersonale[i][j].getSpecifiAngolo(k).getDisegno()); //incremento contatore nel vettore corrispondente al disegno tramite una funzione apposita
							}
						}
					}
					else { //invece se è giocata con il retro a vista controllo gli angoli sul retro (4-7)
						for(int k=4; k<8; k++) {
							if(campoPersonale[i][j].getSpecifiAngolo(k).getOccupato() == false) { 
								contatoreDiFigure(contaFigure, campoPersonale[i][j].getSpecifiAngolo(k).getDisegno());
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * contatore che incrementa l'elemento del vettore corrispondente al disegno passato in chiamata
	 * @param contatore[]: vettore che contiene i contatori da incrementare
	 * @param dis: disegno che verrà sottoposto a switch
	 */
	public void contatoreDiFigure(int contatore[], Disegno dis) {
		switch(dis) {
		case lupo:
			contatore[0]++;
			break;
		case foglia:
			contatore[1]++;
			break;
		case farfalla:
			contatore[2]++;
			break;
		case fungo:
			contatore[3]++;
			break;
		case boccetta:
			contatore[4]++;
			break;
		case piuma:
			contatore[5]++;
			break;
		case pergamena:
			contatore[6]++;
			break;
		default: //case null
			break;
		}
	}
	
	/***
	 * funzione per contare gli angoli sovrapposti da una carta oro per il conteggio dei punti da assegnare all'utente
	 * @param carta la carta oro da cui partire per contare gli angoli sovrapposti
	 * @return il numero degli angoli che la carta sovrappone
	 */
	public int angoliSovrapposti(Carta carta) {
		int count = 0; //conta gli angoli sovrapposti
		int index[] = null;  //0: riga, 1: colonna
		//coordinate della carta da controllare
		try {
			index = trovaCarta(carta);
			
			//per avere il criterio dei punti la carta oro deve essere giocata di fronte
			//quindi controllo solo gli angoli sul davanti della carta
			if(carta.getAngoli()[0] != null) {
				//controllo angolo in alto a sinistra
				if(trovaCarta((index[0] - 1), (index[1] -1)) != null) {
					//esiste una carta sotto quell'angolo
					count++;
				}
			}
			else if(carta.getAngoli()[1] != null) {
				//controllo angolo in alto a destra
				if(trovaCarta((index[0] -1), (index[1] + 1)) != null) {
					//esiste una carta sotto quell'angolo
					count++;
				}
			}
			else if(carta.getAngoli()[2] != null) {
				//controllo angolo in basso a destra
				if(trovaCarta((index[0] + 1), (index[1] + 1)) != null) {
					//esiste una carta sotto quell'angolo
					count++;
				}
			}
			else if(carta.getAngoli()[3] != null) {
				//controllo angolo in basso a sinistra
				if(trovaCarta((index[0] + 1), (index[1] - 1)) != null) {
					//esiste una carta sotto quell'angolo
					count++;
				}
			}
			
			//finito il conteggio degli angoli sovrapposti
			return count;
		}
		catch(ElementNotFoundException e) {
			//la carta non e' stata trovata nella matrice, lancia l'eccezione
			throw e;
		}
	}
	
	/***
	 * trova le coordinate di una specifica carta nella matrice
	 * @param carta: la carta di cui si vogliono cercare le coordinate
	 * @return un vettore contenente i due indici della matrice in cui si trova la carta
	 */
	public int[] trovaCarta (Carta carta){
		int index [] = new int [2];
		
		//ciclo per scorrere gli elementi della matrice
		for (int i = 0; i < campoPersonale.length; i++) {
			for (int j = 0; j < campoPersonale[i].length; j++) {
				if (campoPersonale[i][j].equals(carta)) {
					index[1] = i;
					index[2] = j;
				}
				else {
					throw new ElementNotFoundException("L'elemento " + carta + " non è stato trovato nella matrice.");
				}
			}
		}
			
		return index;
	}
	
	/***
	 * cerca una carta nella matrice in base alle sue coordinate
	 * @param riga: la riga della carta nella matrice
	 * @param colonna: la colonna della carta nella matrice
	 * @return la carta se e' stata trovata, ElementNotFoundException se la carta non c'é
	 */
	public Carta trovaCarta(int riga, int colonna) {
		Carta cartaTrovata = null;
		
		if(campoPersonale[riga][colonna] == null) {
			//la carta non esiste
			cartaTrovata = null;
		}
		else {
			cartaTrovata = campoPersonale[riga][colonna];
		}
		
		return cartaTrovata;
	}
	
	/**
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