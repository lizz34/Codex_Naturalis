package Carte;

public abstract class Carta {
	
	private final Angolo angoli[];
	protected final Colore colore;
	boolean fronte; //true=carta giocata con fronte visibile, false=carta giocata con il retro
	private final Disegno disegni[];
	
	
	public Carta(Angolo ang[], Colore col, Disegno dis[]) {
		this.angoli= new Angolo[8];
		this.disegni = new Disegno[3];
		fronte=true;
		
		for(int i=0; i<this.angoli.length; i++) {
			this.angoli[i]=ang[i];
		}
		for(int i=0; i<this.disegni.length; i++) {
			this.disegni[i]=dis[i];
		}
		this.colore=col;
		
		
	}

	public Angolo[] getAngoli() {
		return this.angoli;
	}
	
	public Colore getColore() {
		return this.colore;
	}

	public boolean getFronte() {
		return fronte;
	}

	public void setFronte(boolean fronte) {
		this.fronte = fronte;
	}

	public Disegno[] getDisegni() {
		return disegni;
	}
	
	

}
