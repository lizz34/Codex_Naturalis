package obiettivi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Carte.Disegno;
import Giocatori.CampoDaGioco;

public class ObiettivoFigure extends CartaObiettivo implements ControlloObiettivo {
	private final Disegno disegno1;
	private final Disegno disegno2;
	private final Disegno disegno3;
	private final int numDisegni;

	public ObiettivoFigure(String obiettivo, int punti, Disegno disegno1, Disegno disegno2, Disegno disegno3, int numDisegni) {
		super(obiettivo, punti);
		this.disegno1 = disegno1;
		this.disegno2 = disegno2;
		this.disegno3 = disegno3;
		this.numDisegni = numDisegni;
	}

	@Override
	/**
	 * calcola la carta obiettivo facendo il conteggio di quante figure ci sono sul campo da gioco
	 * il tipo di figure da guardare e il numero sono specificati come atributo della carta.
	 * @param c: il campo da gioco sul quale controllare la carta obiettivo
	 * @return il punteggio che va sommato ai punti del giocatore se l'obiettivo e' soddisfatto 
	 */
	public int calcoloObiettivo(CampoDaGioco c) {
		int contatore [] = new int [7];
		c.contaFigure(contatore);
		int punteggio = 0;
		
		if(disegno2 == null && disegno3 == null) {
			switch(this.disegno1) {
				case boccetta:
					punteggio = (contatore[4] / this.numDisegni) * this.punti;
				break;
				case piuma:
					punteggio = (contatore[5] / this.numDisegni) * this.punti;
				break;
				case pergamena:
					punteggio = (contatore[6] / this.numDisegni) * this.punti;
				break;
				case farfalla:
					punteggio = (contatore[2] / this.numDisegni) * this.punti;
				break;
				case foglia:
					punteggio = (contatore[1] / this.numDisegni) * this.punti;
				break;
				case lupo:
					punteggio = (contatore[0] / this.numDisegni) * this.punti;
				break;
				case fungo:
					punteggio = (contatore[3] / this.numDisegni) * this.punti;
				break;
				default:
				break;
			}
		}
		else {
			List <Integer> temp = new ArrayList <Integer>();
			
			temp.add(contatore[4]); //boccetta
			temp.add(contatore[5]); //piuma
			temp.add(contatore[6]); //pergamena 
			
			//moltiplica il modificatore di punteggio per il valore minore tra tre disegni che necessita
			//(vuol dire che sicuramente gli altri ne hanno di piu' e non bisogna fare ulteriori controlli)
			punteggio = this.punti * Collections.min(temp);
			temp.clear();
		}
		return punteggio;
	}
	
	/**
	 * getter del numero di disegni necessari
	 * @return il numero dei disegni
	 */
	public int getNumDisegni() {
		return numDisegni;
	}

	/**
	 * getter del primo disegno della carta obiettivo
	 * @return il primo disegno
	 */
	public Disegno getDisegno1() {
		return disegno1;
	}

	/**
	 * getter del secondo disegno della carta obiettivo se presente
	 * @return il secondo disegno
	 */
	public Disegno getDisegno2() {
		return disegno2;
	}

	/**
	 * getter del terzo disegno della carta obiettivo se presente
	 * @return il terzo obiettivo
	 */
	public Disegno getDisegno3() {
		return disegno3;
	}
}
