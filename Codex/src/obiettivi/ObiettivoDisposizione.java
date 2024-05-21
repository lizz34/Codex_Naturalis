package obiettivi;

import Carte.Colore;

public class ObiettivoDisposizione extends CartaObiettivo {
	
	//nelle carte a L colore1 é il colore delle carte in verticale, colore2 di quella singola
	private final Colore colore1;
	private final Colore colore2;
	private final String disposizione; //diagonale o a L (?)

	public ObiettivoDisposizione(String obiettivo, int punti, Colore colore1, Colore colore2, String disposizione) {
		super(obiettivo, punti);
		this.colore1 = colore1;
		this.colore2 = colore2;
		this.disposizione = disposizione;
	}

	public Colore getColore1() {
		return colore1;
	}

	public Colore getColore2() {
		return colore2;
	}

	public String getDisposizione() {
		return disposizione;
	}
}
