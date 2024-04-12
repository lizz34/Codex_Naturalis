package pedine;

import scacchiera.Colore;
import scacchiera.Scacchiera;

public abstract class Pedina {
	
	private final Colore colore;
	private int riga;
	private int colonna;
	
	public Pedina(Colore colore, int riga, int colonna) {
		this.colore=colore;
		this.riga=riga;
		this.colonna=colonna;
	}

	public abstract void move(Scacchiera scacchiera, int rigaDestinazione, int colonnaDestinazione);
	
	
	
	public Colore getColore() {
		return colore;
	}
	
	
}
