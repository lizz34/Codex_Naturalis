package pedine;

import scacchiera.Colore;
import scacchiera.Scacchiera;

public class Cavallo extends Pedina{

	public Cavallo(Colore colore, int riga, int colonna) {
		super(colore, riga, colonna);
		
	}

	@Override
	public void move(Scacchiera scacchiera, int rigaDestinazione, int colonnaDestinazione) {
		
		
	}
	
	public String toString() {
		return "Cavallo"+this.getColore();
	}

}
