package scacchiera;

public class Gioco {
	
	private Giocatore[] giocatori;
	private Scacchiera scacchiera;
	private int turno; //0 primo player / 1 secondo player
	
	public Gioco() {
		this.giocatori= new Giocatore[2];
		this.scacchiera= new Scacchiera();
		this.turno=0;
	}
	
	public void stampaStato() {
		System.out.println(scacchiera.toString()); //il toString non serve (??)
	}
	
	public boolean movePedina(int origineRiga, int origineColonna, int destinazioneRiga,
			int destinazioneColonna) {
		//controllo su tutte le mosse
		if(this.scacchiera.isEmpty(origineRiga, origineColonna)) {
			System.out.println("non c'è nessuna pedina");
			return false;
		}
		else {
			this.scacchiera.getPedina();
		}
		
		
		return true;
	}
}
