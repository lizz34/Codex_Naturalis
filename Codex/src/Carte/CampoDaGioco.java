import Carte.Carta;

public class CampoDaGioco {
	Carta campoPersonale[][];
	final int nRigheTabella=50;
	final int ncolonneTabella=50;
	
	public CampoDaGioco(Carta cartaStarter) {
		//generazione della matrice di gioco
		campoPersonale = new Carta [nRigheTabella][ncolonneTabella];
		
		for(int i=0; i<nRigheTabella; i++) {
			for(int j=0; j<ncolonneTabella; j++) {
				campoDaGioco[i][j] = null;
			}
		}
		
		campoDaGioco[(int(nRigheTabellone/2))-1][(int(ncolonneTabellone/2))-1]=cartaStarter;
	}
	
	public boolean posizionaCarta(Carta, int coordX, int coordY, Angolo angolo) {
		if(campoPersonale[coordX][coordY]!=null) {
			
		}
		else
			return false;
	}
	
	public boolean stampaCampoDaGioco() {
		
	}
}