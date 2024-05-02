package Carte;

public class Carta {
	
	private final Angolo angoli[];
	protected final Colore colore;
	boolean fronte; //true=carta giocata con fronte visibile, false=carta giocata con il retro
	private final Disegno disegni[];
	
	/***
	 * costruttore classe Carta
	 * @param ang vettore con gli 8 angoli che costituiscono una carta
	 * @param col colore della carta
	 * @param dis vettore contenente i max 3 disegni sul retro di ogni carta
	 */
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

	/***
	 * getter del vettore degli 8 angoli
	 * @return ritorna il vettore con gli 8 angoli
	 */
	public Angolo[] getAngoli() {
		return this.angoli;
	}
	
	/***
	 * getter del colore della carta 
	 * @return ritorna il colore della carta
	 */
	public Colore getColore() {
		return this.colore;
	}

	/***
	 * getter fronte/retro a seconda di come è giocata la carta
	 * @return ritorna true se la carta è giocata di fronte (default) - false se di retro
	 */
	public boolean getFronte() {
		return fronte;
	}

	/***
	 * setter per modificare la var fronte, nel caso che la carta venga giocata col retro visibile
	 * @param fronte: true se giocata di fronte - false se giocata di retro
	 */
	public void setFronte(boolean fronte) {
		this.fronte = fronte;
	}

	/***
	 * getter del vettore dei 3 disegni sul retro di ogni carta
	 * @return ritorna il vettore con i 3 disegni sul retro di ogni carta
	 */
	public Disegno[] getDisegni() {
		return disegni;
	}
	
	/***
	 * @override del metodo toString
	 */
	public String toString() {  //VA BENE COME FUNZIONE? PERCHE' SENNO' FACCIAMO STAMPA CARTA
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
