package pedine;

import scacchiera.Colore;
import scacchiera.Scacchiera;

public class Pedone extends Pedina{

	public Pedone(Colore colore, int riga, int colonna) {
		super(colore, riga, colonna);
		
	}

	@Override
	public void move(Scacchiera scacchiera, int rigaDestinazione, int colonnaDestinazione) {
		if(this.getColore()==Colore.BIANCO && (rigaDestinazione-this.getRiga()==1)) {
			if(scacchiera.isEmpty(rigaDestinazione, colonnaDestinazione) || 
					scacchiera.getPedina(rigaDestinazione, colonnaDestinazione).getColore()==Colore.NERO) {
				scacchiera.removePedina(this.getRiga(), this.getColonna());
				this.setColonna(colonnaDestinazione);
				this.setRiga(rigaDestinazione);
			}
		}
		
	}
	
	public String toString() {
		return "Pedone"+this.getColore();
	}
}
