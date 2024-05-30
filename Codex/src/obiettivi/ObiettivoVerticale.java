package obiettivi;

import java.util.HashSet;
import java.util.Set;

import Carte.Colore;
import Giocatori.CampoDaGioco;

public class ObiettivoVerticale extends CartaObiettivo implements ControlloObiettivo {
	
	private final Colore colore1; //colore carte in verticale
	private final Colore colore2; //colore carta singola
	private final String tipo; //verticale superiore o verticale inferiore (2 carte verticali in alto / 2 carte verticali in basso)
	private final String lato; //il lato su cui è la carta diagonale (destra o sinistra)

	public ObiettivoVerticale(String obiettivo, int punti, Colore colore1, Colore colore2, String tipo, String lato) {
		super(obiettivo, punti);
		this.colore1 = colore1;
		this.colore2 = colore2;
		this.tipo = tipo;
		this.lato = lato;
	}

	@Override
	public int calcoloObiettivo(CampoDaGioco campoPersonale) {
		int modColonna = 0;
		int modRiga1 = 0;
		int modRiga2 = 0;
		int punteggio = 0;
		int count = 0;
		
		switch(lato) {
			case "destra":
				modColonna = -1;
				modRiga1 = +1;
				modRiga2 = +2;
			break;
			case "sinistra":
				modColonna = +1;
				modRiga1 = +1;
				modRiga2 = +2;
			break;
				
		}
		
		switch(tipo) {
			case "verticale superiore":
				count = this.verticaleSuperiore(modColonna, modRiga1, modRiga2, campoPersonale);
				punteggio = this.punti * count;
			break;
			case "verticale inferiore":
				count = this.verticaleInferiore(modColonna, modRiga1, modRiga2, campoPersonale);
				punteggio = this.punti * count;
			break;
		}
		return punteggio;
	}
	
	private int verticaleSuperiore(int modColonna, int modRiga1, int modRiga2, CampoDaGioco c) {
		Set<Integer> index = new HashSet<Integer>();
		int tris=0;
		
		for(int i = 0; i < c.getnRigheTabella(); i++) {	//scorre righe tabella
			for(int j = 0; j < c.getnColonneTabella(); j++) {	//scorre colonna tabella
				if(c.getCampoPersonale()[i][j]!=null) {
					if(c.getCampoPersonale()[i][j].getColore().equals(this.colore1)) {
						//trovata carta con colore che corrisponde con quello della carta verticale piu' in alto
						if(c.getCampoPersonale()[i+modRiga1][j]!=null && c.getCampoPersonale()[i+modRiga2][j+modColonna]!=null) {
							if(c.getCampoPersonale()[i+modRiga1][j].getColore().equals(this.colore1) && 
								c.getCampoPersonale()[i+modRiga2][j+modColonna].getColore().equals(this.colore2)) {
								if(c.controllaIndici(index, i, j, i+modRiga1, j, i+modRiga2, j+modColonna)) {
									//trovate due carte che soddisfano i requisiti di disposizione e colore
									index.add(i);
									index.add(j);
									index.add(i+modRiga1);
									index.add(j);
									index.add(i+modRiga2);
									index.add(j+modColonna);
									tris++;
								}
							}
						}
					}
				}
			}
		}
		
		return tris;
	}
	
	private int verticaleInferiore(int modColonna, int modRiga1, int modRiga2, CampoDaGioco c) {
		Set<Integer> index = new HashSet<Integer>();
		int tris=0;
		
		for(int i = 0; i < c.getnRigheTabella(); i++) {	//scorre righe tabella
			for(int j = 0; j < c.getnColonneTabella(); j++) {	//scorre colonna tabella
				if(c.getCampoPersonale()[i][j] != null) {
					if(c.getCampoPersonale()[i][j].getColore().equals(this.colore2)) {
						//trovata carta con un colore che corrisponde con il colore della carta non in verticale
						if(c.getCampoPersonale()[i+modRiga1][j+modColonna]!=null && c.getCampoPersonale()[i+modRiga2][j+modColonna]!=null) {
							if(c.getCampoPersonale()[i+modRiga1][j+modColonna].getColore().equals(this.colore1) &&
								c.getCampoPersonale()[i+modRiga2][j+modColonna].getColore().equals(this.colore1)) {
								if(c.controllaIndici(index, i, j, i+modRiga1, j+modColonna, i+modRiga2, j+modColonna)) {
									//sono state trovate due carte, poste in verticale secondo la disp richiesta, con il colore richiesto
									index.add(i);
									index.add(j);
									index.add(i+modRiga1);
									index.add(j+modColonna);
									index.add(i+modRiga2);
									index.add(j+modColonna);
									tris++;
								}
							}
						}
					}
				}
			}
		}
		return tris;
	}

	public String getLato() {
		return lato;
	}
	
	public String getTipo() {
		return tipo;
	}

	public Colore getColore2() {
		return colore2;
	}

	public Colore getColore1() {
		return colore1;
	}

}
