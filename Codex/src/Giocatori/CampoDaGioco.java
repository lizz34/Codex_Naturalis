package Giocatori;
import Carte.*;

public class CampoDaGioco {
	Carta campoPersonale[][];
	final int nRigheTabella=50;
	final int nColonneTabella=50;
	
	public CampoDaGioco(Carta cartaStarter) {
		//generazione della matrice di gioco
		campoPersonale = new Carta [nRigheTabella][nColonneTabella];
		
		for(int i=0; i<nRigheTabella; i++) {
			for(int j=0; j<nColonneTabella; j++) {
				campoPersonale[i][j] = null;
			}
		}
		
		campoPersonale[(nRigheTabella/2)-1][(nColonneTabella/2)-1]=cartaStarter;
	}
	
	public boolean posizionaCarta(Carta carta, int nRiga, int nColonna, int nAngolo) { //nAngolo è il numero dell'angolo (0-7) a cui attaccare la carta
		if(campoPersonale[nRiga][nColonna]!=null && (nAngolo>=0 && nAngolo<8)) { //controllo base
			if(campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).getOccupato() == false) { //controllo se l'angolo considerato è libero
				switch (nAngolo) {
				case 0,4:
					if(campoPersonale[nRiga-1][nColonna-1] == null) {
						campoPersonale[nRiga-1][nColonna-1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 1,5:
					if(campoPersonale[nRiga-1][nColonna+1] == null) {
						campoPersonale[nRiga-1][nColonna+1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 2,6:
					if(campoPersonale[nRiga+1][nColonna+1] == null) {
						campoPersonale[nRiga+1][nColonna+1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 3,7:
					if(campoPersonale[nRiga+1][nColonna-1] == null) {
						campoPersonale[nRiga+1][nColonna-1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
						
				}
				
			}
			
		}
		
		System.out.println("Errore oppure non puoi posizionare la carta in quella posizione");
		return false;
	}
	
	
	public void stampaCampoDaGioco() { //??????
		for(int i=0; i<nRigheTabella; i++) {
			System.out.println("---------");
			for(int j=0; j<nColonneTabella; j++) {
				
				if(campoPersonale[i][j]!=null) {
					System.out.println("|   ");
					System.out.println(campoPersonale[i][j].toString());
					System.out.println("   |");
				}
			}
		}
	}
}