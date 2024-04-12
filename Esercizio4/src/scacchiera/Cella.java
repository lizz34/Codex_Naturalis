package scacchiera;

import pedine.Pedina;

public class Cella {
	
	//se null non c'è nessuna pedina
	private Pedina pedina;
	
	public Cella() {
		this.pedina=null;
	}
	
	public boolean isEmpty() {
		if(this.pedina==null) 
			return true;
		else
			return false;
	}

	public Pedina getPedina() {
		return pedina;
	}

	public void setPedina(Pedina pedina) {
		this.pedina = pedina;
	}
	
}
