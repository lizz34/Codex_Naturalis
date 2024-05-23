package obiettivi;

import java.util.HashSet;
import java.util.Set;

import Carte.Colore;
import Giocatori.CampoDaGioco;

public class ObiettivoDiagonale extends CartaObiettivo implements ControlloObiettivo {

	private final Colore colore;
	private final String tipo; //diagonale verso sinistra o diagonale verso destra (partendo dall'alto)
	
	public ObiettivoDiagonale(String obiettivo, int punti, Colore colore, String tipo) {
		super(obiettivo, punti);
		this.colore = colore;
		this.tipo = tipo;
	}

	@Override
	/***
	 * calcola se l'obiettivo per la carta corrente è soddisfatto e restituisce il punteggio che va aggiunto
	 * al punteggio del giocatore
	 * @param c: il campo di gioco sul quale si vuole controllare l'obiettivo
	 * @return il valore dei punti da aggiungere al punteggio del giocatore
	 */
	public int calcoloObiettivo(CampoDaGioco c) {
		int tris=0;
		int modColonna1 = 0;
		int modColonna2 = 0;
		switch(tipo) {
		case "destra":
			modColonna1 = +1;
			modColonna2 = +2;
		break;
		case "sinistra":
			modColonna1 = -1;
			modColonna2 = -2;
		break;
		default:
			tris = 0;
		break;
		}
		
		Set<Integer> index = new HashSet<Integer>();
		
		for(int i = 0; i < c.getnRigheTabella(); i++) { //scorre le righe della tabella
			for(int j = 0; j < c.getnColonneTabella(); j++) {
				if(c.getCampoPersonale()[i][j] != null) {
					if(c.getCampoPersonale()[i][j].getColore().equals(colore)){
						//trovata una carta che ha lo stesso colore che serve nell'obiettivo, controllo sulle carte in diagonale
						if(c.getCampoPersonale()[i+1][j+ modColonna1]!=null && c.getCampoPersonale()[i+2][j+ modColonna2]!=null) {
							if(c.getCampoPersonale()[i+1][j+ modColonna1].getColore().equals(colore) && c.getCampoPersonale()[i+2][j+ modColonna2].getColore().equals(colore)) {
								if(c.controllaIndici(index, i, j, i+1, (j + modColonna1), i+2, (j + modColonna2))) {
									//sono state trovate due carte sulla diagonale verso destra che hanno lo stessso colore
									index.add(i);
									index.add(j);
									index.add(i+1);
									index.add(j+modColonna1);
									index.add(i+2);
									index.add(j+modColonna2);
									tris++;
								}
							}
						}
					}
				}
			}
		}
		return tris * this.getPunti();
	}

	public String getTipo() {
		return tipo;
	}

	public Colore getColore() {
		return colore;
	}

}
