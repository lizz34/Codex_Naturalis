package obiettivi;

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
	public int calcoloObiettivo(CampoDaGioco campoPersonale) {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getNumDisegni() {
		return numDisegni;
	}

	public Disegno getDisegno1() {
		return disegno1;
	}

	public Disegno getDisegno2() {
		return disegno2;
	}

	public Disegno getDisegno3() {
		return disegno3;
	}
}
