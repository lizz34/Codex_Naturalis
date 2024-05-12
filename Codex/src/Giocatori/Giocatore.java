package Giocatori;

import java.util.ArrayList;
import java.util.List;

import Carte.*;
import Ecccezioni.ElementNotFoundException;

public class Giocatore {

	private int punteggio;
	private int turniGiocati;
	private List<Carta> mano;
	private CartaObiettivo cartaObiettivo;
	private Carta cartaStarter;
	private CampoDaGioco campoPersonale;

	/***
	 * costruttore della classe Giocatore
	 * @param obiettivo: la carta obiettivo che gli viene assegnata all'inizio della partita
	 * @param cartaStarter: la carta starter con cui inizia a giocare
	 * @param manoIniziale: le 3 carte che il giocatore ha all'inizio della partita
	 */
	public Giocatore(CartaObiettivo obiettivo, Carta cartaStarter, List<Carta> manoIniziale) {
		this.punteggio = 0;
		this.turniGiocati=0;
		this.cartaObiettivo = obiettivo;
		this.cartaStarter = cartaStarter;
		this.mano = new ArrayList<Carta>();
		this.mano = manoIniziale;

		//creazione del campo personale
		campoPersonale = new CampoDaGioco(this.cartaStarter);
	}

	/***
	 * posiziona una carta dalla mano del giocatore nel suo capo da gioco, cancellandola poi dalla sua mano di carte e 
	 * incrementando il punteggio del giocatore nel caso la carta abbia un modificatore di punteggio
	 * @param nCarta l'indice della carta che vuole posizionare
	 * @param nRiga la riga della carta su cui vuole posizionare la sua
	 * @param nColonna la colonna della carta su cui vuole posizionare la sua
	 * @param nAngolo il numero dell'angolo che vuole sovrapporre con la sua carta
	 * @return true se il posizionamento é avvenuto, false se c'é stato un errore
	 */
	public boolean posizionaCarta(int nCarta, int nRiga, int nColonna, int nAngolo) {
		Carta cartaScelta = mano.get(nCarta);
		
		if (campoPersonale.controlloPosizionaCarta(cartaScelta, nRiga, nColonna, nAngolo) == false) {
			return false;
		}
		else {
			//incremento del punteggio del giocatore quando viene posizionata la carta
			
			//se la carta posizionata è una carta risorsa viene solamente incrementato il punteggio
			if(cartaScelta instanceof CartaRisorsa) {
				this.punteggio += ((CartaRisorsa) cartaScelta).getPunti();
			}
			else if(cartaScelta instanceof CartaOro) {
				//controllo se la carta oro ha dei criteri sul conteggio dei suoi punti
				if(((CartaOro) cartaScelta).getCriterioPunti() == null) {
					//la carta non ha nessun criterio per il conteggio dei punti, si incrementa solamente il punteggio
					this.punteggio += ((CartaOro) cartaScelta).getPunti();
				}
				else {
					//la carta oro ha dei criteri per il punteggio da controllare
					Disegno criterio = ((CartaOro) cartaScelta).getCriterioPunti();
					int puntiCarta = ((CartaOro) cartaScelta).getPunti();
					//contatore per contare le figure presenti sul campo di gioco
					//0=lupi; 1=foglie; 2=farfalle; 3=funghi; 4=boccetta; 5=piuma; 6=pergamena
					int contaDisegni [] = new int [7];
					//funzione per contare le figure sul campo
					campoPersonale.contaFigure(contaDisegni);
					
					switch(criterio) {
						case angoloSovrapposto:
							//moltiplicazione dei punti per il numero degli angoli che la carta posizionata ha coperto
							
							try {
								//conteggio del numero  degli angoli
								int countAngoli = 0;
								countAngoli = campoPersonale.angoliSovrapposti(cartaScelta);
								this.punteggio += (puntiCarta * countAngoli);
							}
							catch(ElementNotFoundException e) {
								return false;
							}
						break;
						case boccetta:
							//moltiplicazione dei punti per il numero di boccette sul campo da gioco
							this.punteggio += (puntiCarta * contaDisegni[4]);
						break;
						case piuma:
							//moltiplicazione dei punti per il numero di piume sul campo da gioco
							this.punteggio += (puntiCarta * contaDisegni[5]);
						break;
						case pergamena:
							//moltiplicazione dei punti per il numero di pergamene sul campo da gioco
							this.punteggio += (puntiCarta * contaDisegni[6]);
						break;
						default:
						break;
					}
				}
			}

			//se la carta é stata posizionata correttamente la si rimuove dalla lista delle carte che il giocatore ha in mano
			mano.remove(nCarta);
			return true;
		}
	}

	/***
	 * getter punteggio
	 * @return ritorna il punteggio del giocatore
	 */
	public int getPunteggio() {
		return punteggio;
	}

	/***
	 * setter punteggio
	 * @param punteggio: imposta il punteggio del giocatore
	 */
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	/***
	 * getter mano attuale del giocatore
	 * @return ritorna la mano attuale del giocatore
	 */
	public List<Carta> getMano(){
		return this.mano;
	}
	
	/***
	 * getter di una singola carta dalla propria mano 
	 * @param l'indice della carta nell'arrayList
	 * @return la carta selezionata se esiste, null se la carta non esiste
	 */
	public Carta getCartaMano(int index) {
		if(index>=0 && index<this.mano.size())
			return this.mano.get(index);
		
		return null;
	}
	
	/***
	 * setter mano giocatore: aggiunge una carta alla mano
	 * @param carta: carta che viene aggiunta alla mano del giocatore
	 */
	public void setMano(Carta carta) {
		this.mano.add(carta);
	}
	
	/***
	 * getter carta obiettivo
	 * @return ritorna la carta obiettivo del giocatore
	 */
	public CartaObiettivo getCartaObiettivo() {
		return cartaObiettivo;
	}

	/***
	 * getter carta starter
	 * @return ritorna la carta starter del giocatore
	 */
	public Carta getCartaStarter() {
		return cartaStarter;
	}

	/***
	 * getter turni giocati
	 * @return ritorna il numero di turni giocati dal giocatore
	 */
	public int getTurniGiocati() {
		return this.turniGiocati;
	}
	
	/***
	 * getter del campo di gioco personale del giocatore
	 * @return il campo da gioco
	 */
	public CampoDaGioco getCampoPersonale() {
		return campoPersonale;
	}

	/***
	 * incrementa i turni giocati di +1
	 */
	public void incrementaTurniGiocati() {
		this.turniGiocati++;
	}
	
	/***
	 * funzione per incrementare il punteggio del giocatore di un dato valore
	 * @param increment il numero di punti da aggiungere
	 */
	public void incrementaPunteggio (int increment) {
		this.punteggio += increment;
	}
	
	/***
	 * @override del metodo toString
	 */
	public String toString() {
		System.out.println("Punteggio: " + this.punteggio);
		System.out.println("Carta Starter: ");
		System.out.println(this.cartaStarter.toString());
		System.out.println("CartaObiettivo: ");
		System.out.println(this.cartaObiettivo.toString());
		System.out.println("Mano attuale: ");
		System.out.println(this.mano.toString());
		return " ";
	}
}