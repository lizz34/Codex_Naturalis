package obiettivi;

import Carte.Colore;
import Giocatori.CampoDaGioco;

public class ObiettivoVerticale extends CartaObiettivo implements ControlloObiettivo {
	
	private final Colore colore1; //colore carte in verticale
	private final Colore colore2; //colore carta singola
	private final String tipo; //verticale superiore o verticale inferiore (2 carte verticali in alto / 2 carte verticali in basso)

	public ObiettivoVerticale(String obiettivo, int punti, Colore colore1, Colore colore2, String tipo) {
		super(obiettivo, punti);
		this.colore1 = colore1;
		this.colore2 = colore2;
		this.tipo = tipo;
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

	@Override
	public int calcoloObiettivo(CampoDaGioco campoPersonale) {
		// TODO Auto-generated method stub
		return 0;
	}

}
