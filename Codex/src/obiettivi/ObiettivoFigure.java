package obiettivi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Carte.Disegno;
import Giocatori.CampoDaGioco;

public class ObiettivoFigure extends CartaObiettivo implements ControlloObiettivo {
	private final Disegno disegno1;
	private final Disegno disegno2;
	private final Disegno disegno3;
	private final int numDisegni;

	public ObiettivoFigure(String obiettivo, int punti, Disegno disegno1, Disegno disegno2, Disegno disegno3, int numDisegni) {
		super(obiettivo, punti);
		this.disegno1 = disegno1;
		this.disegno2 = disegno2;
		this.disegno3 = disegno3;
		this.numDisegni = numDisegni;
	}

	@Override
	public int calcoloObiettivo(CampoDaGioco campoPersonale) { //TODO da finire
		int punti=0;
		
		int contatore [] = new int [7];
		List <Integer> temp = new ArrayList <Integer>();
		campoPersonale.contaFigure(contatore);  //conta le figure presenti sul campo di gioco
		
		switch(super.index) {
		case 0: //2 punti x 2 piume
			punti = 2 * (contatore[5] / 2);
			break;
		case 1: //2 punti x 2 boccette
			punti = 2 * (contatore[4] / 2);
			break;
		case 2: //2 punti x 2 pergamene
			punti = 2 * (contatore[6] / 2);
			break;
		case 3: //3 punti x 1 boccetta, 1 piuma, 1 pergamena
			temp.add(contatore[4]); //boccetta
			temp.add(contatore[5]); //piuma
			temp.add(contatore[6]); //pergamena 
			
			//moltiplica il modificatore di punteggio per il valore minore tra tre disegni che necessita
			//(vuol dire che sicuramente gli altri ne hanno di piu' e non bisogna fare ulteriori controlli)
			punti = 3 * Collections.min(temp);
			temp.clear();			
			break;
		case 4: //2 punti x 3 farfalle
			punti = 2 * (contatore[2] / 3);
			break;
		case 5: //2 punti x 3 lupi
			punti = 2 * (contatore[0] / 3);
			break;
		case 6: //2 punti x 3 foglie
			punti = 2 * (contatore[1] / 3);
			break;
		case 7: //2 punti x 3 funghi
			punti = 2 * (contatore[3] / 3);
			break;
		}
		
		
	}
	
	public int getNumDisegni() {
		return numDisegni;
	}

	public Disegno getDisegno1() {
		return disegno1;
	}

	public Disegno getDisegno2() {
		return disegno2;
	}

	public Disegno getDisegno3() {
		return disegno3;
	}
}
