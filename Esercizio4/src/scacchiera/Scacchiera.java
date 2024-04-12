package scacchiera;

import pedine.Alfiere;
import pedine.Cavallo;
import pedine.Pedina;
import pedine.Pedone;
import pedine.Torre;

public class Scacchiera {
	
	private Cella[][] celle;
	
	public Scacchiera() {
		celle= new Cella[8][8];
		for(int i=0; i<8; i++) {
			celle[i]=new Cella[8];
			for(int j=0; j<8; j++) {
				celle[i][j]=new Cella();
			}
		}
		celle[0][0].setPedina(new Torre(Colore.BIANCO, 0, 0));
		celle[0][0].setPedina(new Cavallo(Colore.BIANCO, 0, 1));
		celle[0][0].setPedina(new Alfiere(Colore.BIANCO, 0, 2)); //da correggere
		celle[1][0].setPedina(new Pedone(Colore.BIANCO, 1, 0));
	}
	
	public boolean isEmpty(int riga, int colonna) {
		return celle[riga][colonna]==null;
	}
	
	
	/*Builder fa la concatenazione di stringhe*/
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(celle[i][j].isEmpty())
					builder.append("\t");
				else
					builder.append(celle[i][j].getPedina().toString());
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	public Pedina getPedina(int riga, int colonna) {
		// TODO Auto-generated method stub
		return celle[riga][colonna].getPedina();
	}
	
	public void removePedina(int riga, int colonna) {
		celle[riga][colonna]=null;
	}
	
	public void addPedina(int riga, int colonna) {
		celle[riga][colonna].setPedina(this.getPedina(riga, colonna));
	}
}
