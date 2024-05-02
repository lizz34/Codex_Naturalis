package Carte;
import Main.*;

public class Carta {
	
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
	
	public String toString() {
		System.out.println("(occupato | disegno)");
		if(fronte==true) {
			for(int i=0; i<4; i++) {
				if(angoli[i]==null)
					System.out.print((i+1) + "- " + "non esiste" + "\n");
				else
					System.out.print((i+1) + "- " + angoli[i].toString() + "\n");
			}
		}
		else {
			for(int i=4; i<8; i++) {
				if(angoli[i]==null)
					System.out.print((i+1) + "- " + "non esiste" + "\n");
				else
					System.out.print((i+1) + "- " + angoli[i].toString() + "\n");
			}
			System.out.println("Disegni centrali: " +  disegni[0] + " " + disegni[1] + " " + disegni[2]);
		}
		
		return "Colore: " + colore;
	}
	
	public Angolo getSpecifiAngolo(int nAngolo) {
		return angoli[nAngolo];
	}

}
