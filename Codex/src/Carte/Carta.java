package Carte;

import java.util.Arrays;

public class Carta {
	
	//vettore degli 8 angoli della carta (0-3 angoli davanti, 4-7 angoli dietro)
	private final Angolo angoli[];
	protected final Colore colore;
	boolean fronte; //true=carta giocata con fronte visibile, false=carta giocata con il retro
	//per tutte le carte diverse dalle starter l'unico disegno presente sul retro viene messo in posizione zero
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
	
    @Override
    public boolean equals(Object o) {
    	
    	if (this == o) return true; //se uguali --> true
        if (o == null || getClass() != o.getClass()) return false; //se classe diversa --> false
        Carta carta = (Carta) o;
        return (this.fronte==carta.fronte && Arrays.equals(this.angoli, carta.angoli) && this.colore==carta.colore && Arrays.equals(this.disegni, carta.disegni));
    }
    
    @Override
    public int hashCode() {
        int result = 0;
        result += (colore == null ? 0 : colore.hashCode());
        result += Boolean.hashCode(this.fronte);
        result += Arrays.hashCode(this.angoli);
        result += Arrays.hashCode(this.disegni);
        return result;
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
	 * restituisce uno specifico angolo della carta
	 * @param nAngolo il numero dell'angolo sulla carta (0-3 angoli fronte, 4-7 angoli retro)
	 * @return l'oggetto angolo specificato
	 */
	public Angolo getSpecifiAngolo(int nAngolo) {
		return angoli[nAngolo];
	}
	
	/***
	 * @override del metodo toString
	 */
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

	public String stampa() {
		return this.toString();
	}
}
