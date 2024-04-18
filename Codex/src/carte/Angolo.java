package Carte;

public class Angolo {
	private final boolean fronte;
	private boolean occupato;
	private final Disegno disegno;
	
	public Angolo(boolean fronte, boolean occupato, Disegno disegno) {
		this.fronte = fronte;
		this.occupato = occupato; 
		this.disegno = disegno;
	}

	public boolean getFronte() {
		return fronte;
	}

	public boolean getOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	public Disegno getDisegno() {
		return disegno;
	}
	
}
