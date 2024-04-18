package Carte;

public class Angolo {
	private boolean fronte;
	private boolean occupato;
	private Disegno disegno;
	
	public Angolo(boolean fronte, boolean occupato, Disegno disegno) {
		this.fronte = fronte;
		this.occupato = occupato; 
		this.disegno = disegno;
	}

	public boolean getFronte() {
		return fronte;
	}

	public void setFronte(boolean fronte) {
		this.fronte = fronte;
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

	public void setDisegno(Disegno disegno) {
		this.disegno = disegno;
	}
	
	//prova connessione git
}
