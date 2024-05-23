package Giocatori;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import Carte.*;
import obiettivi.*;

public class TavoloDaGioco {

	private Giocatore giocatori[];
	//mazzi di carte
	private List<Carta> mazzoCarteStarter;
	private List<CartaRisorsa> mazzoCarteRisorsa;
	private List<CartaOro> mazzoCarteOro;
	private List<CartaObiettivo> mazzoCarteObiettivo;
	//carte disponibili sul tavolo da gioco
	private List<CartaRisorsa> carteRisorsaBanco;
	private List<CartaOro> carteOroBanco;
	private final List <CartaObiettivo> carteObiettivoBanco;

	/***
	 * Costruttore della classe TavoloDaGioco
	 * Istanzia tutte le carte nei vari mazzi di gioco
	 * Gira le prime due carte risorsa e le prime due carte oro sul tavolo da gioco
	 * e estrae i due obiettivi casuali per tutti i giocatori
	 */
	public TavoloDaGioco(int nGiocatori) {
	
		//Creazione mazzo carteStarter
		mazzoCarteStarter = new ArrayList<Carta>();
		try {
			File carteStarter = new File("src\\carteStarter.txt"); // apro file carteStarter
			Scanner scanner = new Scanner(carteStarter);

			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); // salvo riga per riga in data
				// System.out.println(data.length());

				String divisorio[] = data.split("-"); // divido l'intera riga per "-"
				// System.out.println(divisorio.length);

				// ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore col, Disegno dis[]) )
				Angolo angoli[] = new Angolo[8];
				Disegno dis[] = new Disegno[3];

				for (int j=0; j<(divisorio.length-1); j++) { // scorro la riga, ora divisa in 9 elementi (8angoli, // 1per disegni dietro)													
					String[] infoAngolo = divisorio[j].split(","); // divido ogni elemento in due sottoelementi
					if (infoAngolo[0].equals("true")) { // se true devo creare l'angolo (ricordiamo Angolo(boolean fronte, Disegno disegno) )
						Disegno disegno = null;
						boolean fronte;

						if (j<(divisorio.length/2)) // la metà del vettore segna la divisione tra angoli fronte 0-3 e angoli retro 4-7
							fronte = true;
						else
							fronte = false;

						switch(infoAngolo[1]) { // assegno angolo
						case "foglia":
							disegno = Disegno.foglia;
							break;
						case "farfalla":
							disegno = Disegno.farfalla;
							break;
						case "lupo":
							disegno = Disegno.lupo;
							break;
						case "fungo":
							disegno = Disegno.fungo;
							break;
						case "null":
							disegno = null;
							break;
						default: // se si toglie il case: null --> va tolto anche il default
							System.out.println("Errore lettura disegno 1 - Starter");
							disegno = null;
							break;
						}
						angoli[j] = new Angolo(fronte, disegno);
						
					} 
					else
						angoli[j] = null;
				}

				String[] infoDisegni = divisorio[divisorio.length-1].split(","); // divido i tre disegni nell'ultimo segmento
				for (int j=0; j<(dis.length); j++) {
					switch(infoDisegni[j]) {
					case "lupo":
						dis[j] = Disegno.lupo;
						break;
					case "farfalla":
						dis[j] = Disegno.farfalla;
						break;
					case "fungo":
						dis[j] = Disegno.fungo;
						break;
					case "foglia":
						dis[j] = Disegno.foglia;
						break;
					case "null":
						dis[j] = null;
						break;
					default:
						System.out.println("Errore lettura disegno 2 - starter");
						dis[j] = null;
						break;
					}
				}

				mazzoCarteStarter.add(new Carta(angoli, Colore.giallo, dis)); // colore giallo per le starter
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		//Creazione mazzo carte Risorsa
		mazzoCarteRisorsa = new ArrayList<CartaRisorsa>();
		try {
			File carteRisorsa = new File("src\\carteRisorse.txt"); // apro file carteStarter
			Scanner scanner = new Scanner(carteRisorsa);

			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); // salvo riga per riga in data
				// System.out.println(data.length());

				String divisorio[] = data.split("-"); // divido l'intera riga per "-"
				// System.out.println(divisorio.length);

				// ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore col, Disegno dis[]) )
				Colore col;
				Angolo angoli[] = new Angolo[8];
				Disegno dis[] = new Disegno[3];

				switch (divisorio[1]) {
				case "rosso":
					col = Colore.rosso;
					break;
				case "blu":
					col = Colore.blu;
					break;
				case "verde":
					col = Colore.verde;
					break;
				case "viola":
					col = Colore.viola;
					break;
				default:
					System.out.println("Errore lettura colore");
					col = Colore.giallo; // giallo se errore (impossibile perchè risorsa hanno i 4 colori elencati sopra)
					break;
				}

				int contaAngoli = 0;
				for (int j = 2; j < divisorio.length - 1; j++) { // scorro la riga, ora divisa in 9 elementi (8angoli, 1per disegni dietro)
					String[] infoAngolo = divisorio[j].split(","); // divido ogni elemento in due sottoelementi
					if (infoAngolo[0].equals("true")) { // se true devo creare l'angolo (ricordiamo Angolo(boolean fronte, Disegno disegno) 
						Disegno disegno = null;
						boolean fronte;

						if (j < divisorio.length / 2) // la metà del vettore segna la divisione tra angoli fronte 0-3 e angoli retro 4-7
							fronte = true;
						else
							fronte = false;

						switch ((String) infoAngolo[1]) { // assegno angolo
						case "foglia":
							disegno = Disegno.foglia;
							break;
						case "farfalla":
							disegno = Disegno.farfalla;
							break;
						case "lupo":
							disegno = Disegno.lupo;
							break;
						case "fungo":
							disegno = Disegno.fungo;
							break;
						case "pergamena":
							disegno = Disegno.pergamena;
							break;
						case "piuma":
							disegno = Disegno.piuma;
							break;
						case "boccetta":
							disegno = Disegno.boccetta;
							break;
						case "null":
							disegno = null;
							break;
						default: // se si toglie il case: null --> va tolto anche il default
							System.out.println("Errore lettura disegno 1 - risorsa");
							disegno = null;
							break;
						}

						angoli[contaAngoli] = new Angolo(fronte, disegno);
					} else
						angoli[contaAngoli] = null;

					contaAngoli++;
				}

				String[] infoDisegni = divisorio[divisorio.length - 1].split(","); // divido i tre disegni nell'ultimo segmento
				for (int j = 0; j < dis.length; j++) {
					switch (infoDisegni[j]) {
					case "lupo":
						dis[j] = Disegno.lupo;
						break;
					case "farfalla":
						dis[j] = Disegno.farfalla;
						break;
					case "fungo":
						dis[j] = Disegno.fungo;
						break;
					case "foglia":
						dis[j] = Disegno.foglia;
						break;
					case "null":
						dis[j] = null;
						break;
					default:
						System.out.println("Errore lettura disegno 2 - risorsa");
						dis[j] = null;
						break;
					}
				}

				mazzoCarteRisorsa.add(new CartaRisorsa(angoli, col, dis, (Integer.parseInt(divisorio[0])))); // colore giallo per le starter																						// (?)
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Creazione mazzo carte Oro
		mazzoCarteOro = new ArrayList<CartaOro>();
		try {
			File carteOro = new File("src\\carteOro.txt"); // apro file carteStarter
			Scanner scanner = new Scanner(carteOro);

			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); // salvo riga per riga in data
				// System.out.println(data.length());

				String divisorio[] = data.split("-"); // divido l'intera riga per "-"
				// System.out.println(divisorio.length);

				// ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore col, Disegno dis[]) )
				Colore col;
				Disegno criterioPunti;
				Disegno disRichieste[] = new Disegno[5];
				Angolo angoli[] = new Angolo[8];
				Disegno dis[] = new Disegno[3];

				switch (divisorio[1]) {
				case "rosso":
					col = Colore.rosso;
					break;
				case "blu":
					col = Colore.blu;
					break;
				case "verde":
					col = Colore.verde;
					break;
				case "viola":
					col = Colore.viola;
					break;
				default:
					System.out.println("Errore lettura colore");
					col = Colore.giallo; // giallo se errore (impossibile perchè risorsa hanno i 4 colori elencati sopra)
					break;
				}

				switch (divisorio[2]) {
				case "boccetta":
					criterioPunti = Disegno.boccetta;
					break;
				case "piuma":
					criterioPunti = Disegno.piuma;
					break;
				case "pergamena":
					criterioPunti = Disegno.pergamena;
					break;
				case "angoloSovrapposto":
					criterioPunti = Disegno.angoloSovrapposto;
					break;
				case "null":
					criterioPunti = null;
					break;
				default:
					System.out.println("Errore lettura criterio assegnazione punti");
					criterioPunti = null;
					break;
				}

				String[] infoRichieste = divisorio[3].split(",");
				for (int j = 0; j < 5; j++) {
					switch (infoRichieste[j]) {
					case "foglia":
						disRichieste[j] = Disegno.foglia;
						break;
					case "farfalla":
						disRichieste[j] = Disegno.farfalla;
						break;
					case "lupo":
						disRichieste[j] = Disegno.lupo;
						break;
					case "fungo":
						disRichieste[j] = Disegno.fungo;
						break;
					case "null":
						disRichieste[j] = null;
						break;
					default:
						System.out.println("Errore lettura richieste per posizionare carta");
						disRichieste[j] = null;
						break;
					}
				}

				int contaAngoli = 0;
				for (int j = 4; j < divisorio.length - 1; j++) { // scorro la riga, ora divisa in 9 elementi (8angoli, 1per disegni dietro)
					String[] infoAngolo = divisorio[j].split(","); // divido ogni elemento in due sottoelementi
					if (infoAngolo[0].equals("true")) { // se true devo creare l'angolo (ricordiamo Angolo(boolean fronte, Disegno disegno) )
						Disegno disegno = null;
						boolean fronte;

						if (j < divisorio.length / 2) // la metà del vettore segna la divisione tra angoli fronte 0-3 e angoli retro 4-7
							fronte = true;
						else
							fronte = false;

						switch (infoAngolo[1]) { // assegno angolo
						case "foglia":
							disegno = Disegno.foglia;
							break;
						case "farfalla":
							disegno = Disegno.farfalla;
							break;
						case "lupo":
							disegno = Disegno.lupo;
							break;
						case "fungo":
							disegno = Disegno.fungo;
							break;
						case "pergamena":
							disegno = Disegno.pergamena;
							break;
						case "piuma":
							disegno = Disegno.piuma;
							break;
						case "boccetta":
							disegno = Disegno.boccetta;
							break;
						case "null":
							disegno = null;
							break;
						default: // se si toglie il case: null --> va tolto anche il default
							System.out.println("Errore lettura disegno 1 - oro");
							disegno = null;
							break;
						}

						angoli[contaAngoli] = new Angolo(fronte, disegno);

					} else
						angoli[contaAngoli] = null;

					contaAngoli++;
				}

				String[] infoDisegni = divisorio[divisorio.length - 1].split(","); // divido i tre disegni nell'ultimo segmento
				for (int j = 0; j < dis.length; j++) {
					switch (infoDisegni[j]) {
					case "lupo":
						dis[j] = Disegno.lupo;
						break;
					case "farfalla":
						dis[j] = Disegno.farfalla;
						break;
					case "fungo":
						dis[j] = Disegno.fungo;
						break;
					case "foglia":
						dis[j] = Disegno.foglia;
						break;
					case "null":
						dis[j] = null;
						break;
					default:
						System.out.println("Errore lettura disegno 2 - oro");
						dis[j] = null;
						break;
					}
				}

				mazzoCarteOro.add(
						new CartaOro(angoli, col, dis, (Integer.parseInt(divisorio[0])), disRichieste, criterioPunti));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		//creazione mazzo carte obiettivo
		//indici da 0 a 7: obiettivo sul numero di figure
		//indici da 8 a 15: obiettivo sulla disposizione delle carte
		mazzoCarteObiettivo = new ArrayList<CartaObiettivo>();
		
		mazzoCarteObiettivo.add(new ObiettivoFigure("Due punti per ogni due piume", 2, Disegno.piuma, null, null, 2));
		mazzoCarteObiettivo.add(new ObiettivoFigure("Due punti per ogni due boccette", 2, Disegno.boccetta, null, null, 2));
		mazzoCarteObiettivo.add(new ObiettivoFigure("Due punti per ogni due pergamene", 2, Disegno.pergamena, null, null, 2));
		mazzoCarteObiettivo.add(new ObiettivoFigure("Tre punti se si hanno una piuma, una boccetta e una pergamena", 3, Disegno.piuma, Disegno.boccetta, Disegno.pergamena, 1));
		mazzoCarteObiettivo.add(new ObiettivoFigure("Due punti per ogni tre farfalle", 2, Disegno.farfalla, null, null, 3));
		mazzoCarteObiettivo.add(new ObiettivoFigure("Due punti per ogni tre lupi", 2, Disegno.lupo, null, null, 3));
		mazzoCarteObiettivo.add(new ObiettivoFigure("Due punti per ogni tre foglie", 2, Disegno.foglia, null, null, 3));
		mazzoCarteObiettivo.add(new ObiettivoFigure("Due punti per ogni tre funghi", 2, Disegno.fungo, null, null, 3));
		mazzoCarteObiettivo.add(new ObiettivoVerticale("Tre punti se si ha la seguente disposizione: due carte verticali di colore viola con l’angolo in alto a sinistra che combacia con l’angolo in basso a destra di una carta blu", 3, Colore.viola, Colore.blu, "verticale inferiore", "destra"));
		mazzoCarteObiettivo.add(new ObiettivoVerticale("Tre punti se si ha la seguente disposizione: due carte verticali di colore blu con l'angolo in alto a destra che combacia con l'angolo in basso a sinistra di una carta rossa", 3, Colore.blu, Colore.rosso, "verticale inferiore", "sinistra"));
		mazzoCarteObiettivo.add(new ObiettivoVerticale("Tre punti se si ha la seguente disposizione: due carte verticali verdi dove l'angolo in basso a sinistra combacia con l'angolo in alto a destra di una carta viola", 3, Colore.verde, Colore.viola, "verticale superiore", "sinistra"));
		mazzoCarteObiettivo.add(new ObiettivoVerticale("Tre punti se si ha la seguente disposizione: due carte verticali rosse dove l'angolo in basso a destra combacia con l'angolo in alto a sinistra di una carta verde", 3, Colore.rosso, Colore.verde, "verticale superiore", "destra"));
		mazzoCarteObiettivo.add(new ObiettivoDiagonale("Due punti se si ha la seguente disposizione: se si hanno tre carte di colore viola disposte diagonalmente, dove la carta sinistra sarà quella più alta e quella a destra la più bassa", 2, Colore.viola, "destra"));
		mazzoCarteObiettivo.add(new ObiettivoDiagonale("Due punti se si ha la seguente disposizione: se si hanno tre carte di colore blu disposte diagonalmente, dove la carta sinistra sarà la più bassa e quella destra la più alta", 2, Colore.blu, "sinistra"));
		mazzoCarteObiettivo.add(new ObiettivoDiagonale("Due punti se si ha la seguente disposizione: se si hanno tre carte di colore verde disposte diagonalmente, dove la carta sinistra sarà quella più alta e quella a destra la più bassa", 2, Colore.verde, "destra"));
		mazzoCarteObiettivo.add(new ObiettivoDiagonale("Due punti se si ha la seguente disposizione: se si hanno tre carte di colore rosso disposte diagonalmente, dove la carta sinistra sarà la più bassa e quella destra la più alta", 2, Colore.rosso, "sinistra"));
		
		//aggiunta delle carte di partenza che ci sono sul tavolo di gioco
		carteRisorsaBanco = new ArrayList<CartaRisorsa>();
		carteOroBanco = new ArrayList<CartaOro>();
		carteObiettivoBanco = new ArrayList<CartaObiettivo>();

		carteRisorsaBanco.add(giraCartaRisorsa());
		carteRisorsaBanco.add(giraCartaRisorsa());
		
		carteOroBanco.add(giraCartaOro());
		carteOroBanco.add(giraCartaOro());
		
		//istanziamento delle due carte obiettivo presenti sul tavolo da gioco
		carteObiettivoBanco.add(pescaCartaObiettivo());
		carteObiettivoBanco.add(pescaCartaObiettivo());
		
		
		//Istanziamento giocatori
		this.giocatori = new Giocatore[nGiocatori];
		for(int i=0; i<giocatori.length; i++) {
			List<Carta> manoIniziale = new ArrayList<Carta>();
			manoIniziale.add(giraCartaRisorsa());
			manoIniziale.add(giraCartaRisorsa());
			manoIniziale.add(giraCartaOro());
			giocatori[i] = new Giocatore(pescaCartaObiettivo(), pescaCartaStarter(), manoIniziale);
		}
	}	
	
	/***
	 * controlla la prima condizione di fine partita: caso che finiscono le carte nei mazzi e sul tavolo di gioco
	 * @return true se tutte le carte sono finite, false se c'è almeno una carta ancora prendibile dal tavolo di gioco
	 */
	public boolean condEndGame1() {
		if(mazzoCarteRisorsa.isEmpty() || mazzoCarteOro.isEmpty() || carteRisorsaBanco.isEmpty() || carteOroBanco.isEmpty())
			return true;

		return false;
	}
	
	/***
	 * controlla la seconda condizione per la fine della partita: almeno un giocatore ha raggiunto i 20 punti
	 * @return true se un giocatore ha 20 punti, false in caso contrario
	 */
	public boolean condEndGame2() {
		for(Giocatore g: giocatori) {
			if (g.getPunteggio() >= 20) {
				return true;
			}
			else {
				return false;
			}
		}
		
		return false;
	}
	
	/***
	 * controlla la terza condizione per la fine della partita: verifica che tutti i giocatori abbiano giocato lo stesso numero di turni
	 * @return true se condizione rispettata, false in caso contrario
	 */
	public boolean condEndGame3() {
		List<Integer> cont = new ArrayList<Integer>();
		int condVerificata=0;
		
		for(int i=0; i<this.getGiocatori().length; i++) {
			cont.add(this.getGiocatori()[i].getTurniGiocati());
		}
		
		for(int c : cont) {
			if(!(c==Collections.max(cont)))
				condVerificata++;
		}
		
		if(condVerificata==0)
			return true;
		else
			return false;
	}
	
	/***
	 * estrae una carta risorsa dal mazzo e la posiziona sul tavolo da gioco
	 * dopo che quella che c'era prima viene pescata da un giocatore
	 * @return true se la carta viene estratta, false se il mazzo è vuoto
	 */
	public CartaRisorsa giraCartaRisorsa() {
		Random random = new Random();
		int randomNum;
		CartaRisorsa cartaPescata;
		
		randomNum = random.nextInt(mazzoCarteRisorsa.size());
		cartaPescata = mazzoCarteRisorsa.get(randomNum);
		mazzoCarteRisorsa.remove(randomNum);
		
		return cartaPescata; 
		//il controllo sull'esaurimento del mazzo viene effettuato all'inizio di ogni turno del giocatore 
	}
	
	/***
	 * Funzione per pescare una carta risorsa da quelle sul tavolo
	 * @param index: l'indice della carta risorsa nella lista di carte risorsa
	 * @return ritorna la carta pescata
	 */
	public CartaRisorsa pescaCartaRisorsa(int index) {
		CartaRisorsa cartaPescata;
		
		cartaPescata = carteRisorsaBanco.get(index);
		carteRisorsaBanco.remove(index);
		carteRisorsaBanco.add(giraCartaRisorsa());
		
		return cartaPescata;
	}
	
	
	/***
	 * estrae una carta oro dal mazzo e la posiziona sul tavolo da gioco
	 * dopo che quella che c'era prima viene pescata da un giocatore
	 * @return true se la carta viene estratta, false se il mazzo è vuoto
	 */
	public CartaOro giraCartaOro() {
		Random random = new Random();
		int randomNum;
		CartaOro cartaPescata;
		
		randomNum = random.nextInt(mazzoCarteOro.size());
		cartaPescata = mazzoCarteOro.get(randomNum);
		mazzoCarteOro.remove(randomNum);
		
		return cartaPescata;
	}
	
	/***
	 * Funzione per pescare una carta oro
	 * @param index: l'indice della caraìta nella lista delle carte oro
	 * @return ritorna la carta pescata
	 */
	public CartaOro pescaCartaOro(int index) {
		CartaOro cartaPescata;
		
		cartaPescata = carteOroBanco.get(index);
		carteOroBanco.remove(index);
		carteOroBanco.add(giraCartaOro());
			
		return cartaPescata;
	}
	
	/***
	 * Funzione per pescare una carta starter
	 * @return ritorna la carta pescata
	 */
	public Carta pescaCartaStarter() {
		Random random = new Random();
		int randomNum;
		Carta cartaPescata;
		
		randomNum = random.nextInt(mazzoCarteStarter.size());
		cartaPescata = mazzoCarteStarter.get(randomNum);
		mazzoCarteStarter.remove(randomNum);
		
		return cartaPescata;
	}
	
	/***
	 * Funzione per pescare una carta obiettivo
	 * @return ritorna la carta pescata
	 */
	public CartaObiettivo pescaCartaObiettivo() {
		Random random = new Random();
		int randomNum;
		CartaObiettivo cartaPescata;
		
		randomNum = random.nextInt(mazzoCarteObiettivo.size());
		cartaPescata = mazzoCarteObiettivo.get(randomNum);
		mazzoCarteObiettivo.remove(randomNum);
		
		return cartaPescata;
	}

	/***
	 * getter del mazzo delle carte risorsa
	 * @return intero mazzo delle carte risorsa ancora disponibili
	 */
	public List<CartaRisorsa> getMazzoCarteRisorsa() {
		return mazzoCarteRisorsa;
	}
	/***
	 * getter carte risorsa disponibili sul tavolo di gioco
	 * @return ritorna le carte risorsa pescabili dal giocatore, quelle che si trovano sul tavolo di gioco
	 */
	public List<CartaRisorsa> getCarteRisorsaBanco() {
		return carteRisorsaBanco;
	}

	/***
	 * getter carte oro disponibili sul tavolo di gioco
	 * @return ritorna le carte oro pescabili dal giocatore, quelle che si trovano sul tavolo di gioco
	 */
	public List<CartaOro> getCarteOroBanco() {
		return carteOroBanco;
	}

	public Giocatore[] getGiocatori() {
		return giocatori;
	}
	
	public List<CartaObiettivo> getObiettiviComuni(){
		return this.carteObiettivoBanco;
	}
	
}
