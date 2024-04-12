package pedine;

import scacchiera.Colore;
import scacchiera.Scacchiera;

public class Alfiere extends Pedina{

	public Alfiere(Colore colore, int riga, int colonna) {
		super(colore, riga, colonna);
		
	}

	@Override
	public void move(Scacchiera scacchiera, int rigaDestinazione, int colonnaDestinazione) {
		
		
	}
	
	public String toString() {
		return "Alfiere"+this.getColore();
	}
}

