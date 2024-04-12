package pedine;

import scacchiera.Colore;
import scacchiera.Scacchiera;

public class Re extends Pedina{

	public Re(Colore colore, int riga, int colonna) {
		super(colore, riga, colonna);
		
	}
	
	@Override
	public void move(Scacchiera scacchiera, int rigaDestinazione, int colonnaDestinazione) {
		
	}
	
	public String toString() {
		return "Re"+this.getColore();
	}
}
