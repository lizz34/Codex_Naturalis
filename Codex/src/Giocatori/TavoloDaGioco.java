package Giocatori;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import Carte.*;

public class TavoloDaGioco {

	private Giocatore giocatori[];
	private List<Carta> mazzoCarteStarter;
	private List<CartaRisorsa> mazzoCarteRisorsa;
	private List<CartaOro> mazzoCarteOro;
	private List<CartaObiettivo> mazzoCarteObiettivo;
	private List<CartaRisorsa> carteRisorsaBanco;
	private List<CartaOro> carteOroBanco;

	/***
	 * Costruttore della classe TavoloDaGioco Istanzia tutte le carte nei vari mazzi
	 * di gioco Gira le prime due carte risorsa e le prime due carte oro sul tavolo
	 * da gioco
	 */
	public TavoloDaGioco(int nGiocatori) {

		// Istanziamento giocatori
		this.giocatori = new Giocatore[nGiocatori];

		// Creazione mazzo carteStarter
		mazzoCarteStarter = new ArrayList<Carta>();
		try {
			File carteStarter = new File("src\\carteStarter.txt"); // apro file carteStarter
			Scanner scanner = new Scanner(carteStarter);

			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); // salvo riga per riga in data
				// System.out.println(data.length());
				mazzoCarteStarter.add(CartaStarter.parseString(data));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Creazione mazzo carte Risorsa

		mazzoCarteRisorsa = new ArrayList<CartaRisorsa>();
		try {
			File carteRisorsa = new File("src\\carteRisorse.txt"); // apro file carteStarter
			Scanner scanner = new Scanner(carteRisorsa);

			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); // salvo riga per riga in data
				// System.out.println(data.length());

				mazzoCarteRisorsa.add(CartaRisorsa.parseString(data)); // (?)
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

				mazzoCarteOro.add(CartaOro.parseString(data));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// creazione mazzo carte obiettivo
		mazzoCarteObiettivo = new ArrayList<CartaObiettivo>();
		mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni due piume"));
		mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni due boccette"));
		mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni due pergamene"));
		mazzoCarteObiettivo.add(new CartaObiettivo("Tre punti se si hanno una piuma, una boccetta e una pergamena"));
		mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni tre farfalle"));
		mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni tre lupi"));
		mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni tre foglie"));
		mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni tre funghi"));
		mazzoCarteObiettivo.add(new CartaObiettivo(
				"Tre punti se si ha la seguente disposizione: due carte verticali di colore viola con l’angolo in alto a sinistra che combacia con l’angolo in basso a destra di una carta blu"));
		mazzoCarteObiettivo.add(new CartaObiettivo(
				"Tre punti se si ha la seguente disposizione: due carte verticali di colore blu con l'angolo in alto a destra che combacia con l'angolo in basso a sinistra di una carta rossa"));
		mazzoCarteObiettivo.add(new CartaObiettivo(
				"Tre punti se si ha la seguente disposizione: due carte verticali verdi dove l'angolo in basso a sinistra combacia con l'angolo in alto a destra di una carta viola"));
		mazzoCarteObiettivo.add(new CartaObiettivo(
				"Tre punti se si ha la seguente disposizione: due carte verticali rosse dove l'angolo in basso a destra combacia con l'angolo in alto a sinistra di una carta verde"));
		mazzoCarteObiettivo.add(new CartaObiettivo(
				"Due punti se si ha la seguente disposizione: se si hanno tre carte di colore viola disposte diagonalmente, dove la carta sinistra sarà quella più alta e quella a destra la più bassa"));
		mazzoCarteObiettivo.add(new CartaObiettivo(
				"Due punti se si ha la seguente disposizione: se si hanno tre carte di colore blu, dove la carta sinistra sarà la più bassa e quella destra la più alta"));
		mazzoCarteObiettivo.add(new CartaObiettivo(
				"Due punti se si ha la seguente disposizione: se si hanno tre carte di colore verde disposte diagonalmente, dove la carta sinistra sarà quella più alta e quella a destra la più bassa"));
		mazzoCarteObiettivo.add(new CartaObiettivo(
				"Due punti se si ha la seguente disposizione: se si hanno tre carte di colore rosso, dove la carta sinistra sarà la più bassa e quella destra la più alta"));

		// aggiunta delle carte di partenza che ci sono sul tavolo di gioco
		carteRisorsaBanco = new ArrayList<CartaRisorsa>();
		carteOroBanco = new ArrayList<CartaOro>();

		carteRisorsaBanco.add(giraCartaRisorsa());
		carteRisorsaBanco.add(giraCartaRisorsa());

		carteOroBanco.add(giraCartaOro());
		carteOroBanco.add(giraCartaOro());

	}

	/***
	 * controlla la prima condizione di fine partita: caso che finiscono le carte
	 * nei mazzi e sul tavolo di gioco
	 * 
	 * @return true se tutte le carte sono finite, false se c'è almeno una carta
	 *         ancora prendibile dal tavolo di gioco
	 */
	public boolean condEndGame1() {
		if (mazzoCarteRisorsa.isEmpty() || mazzoCarteOro.isEmpty() || carteRisorsaBanco.isEmpty()
				|| carteOroBanco.isEmpty())
			return true;

		return false;
	}

	/***
	 * controlla la seconda condizione per la fine della partita: almeno un
	 * giocatore ha raggiunto i 20 punti
	 * 
	 * @return true se un giocatore ha 20 punti, false in caso contrario
	 */
	public boolean condEndGame2() {
		for (Giocatore g : giocatori) {
			if (g.getPunteggio() == 20) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	/***
	 * estrae una carta risorsa dal mazzo e la posiziona sul tavolo da gioco dopo
	 * che quella che c'era prima viene pescata da un giocatore
	 * 
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
		// il controllo sull'esaurimento del mazzo viene effettuato all'inizio di ogni
		// turno del giocatore
	}

	/***
	 * Funzione per pescare una carta risorsa da quelle sul tavolo
	 * 
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
	 * estrae una carta oro dal mazzo e la posiziona sul tavolo da gioco dopo che
	 * quella che c'era prima viene pescata da un giocatore
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
	 * @return intero mazzo delle carte risorsa ancora disponibili
	 */
	public List<CartaRisorsa> getMazzoCarteRisorsa() {
		return mazzoCarteRisorsa;
	}

	/***
	 * getter carte risorsa disponibili sul tavolo di gioco
	 * 
	 * @return ritorna le carte risorsa pescabili dal giocatore, quelle che si
	 *         trovano sul tavolo di gioco
	 */
	public List<CartaRisorsa> getCarteRisorsaBanco() {
		return carteRisorsaBanco;
	}

	/***
	 * getter carte oro disponibili sul tavolo di gioco
	 * 
	 * @return ritorna le carte oro pescabili dal giocatore, quelle che si trovano
	 *         sul tavolo di gioco
	 */
	public List<CartaOro> getCarteOroBanco() {
		return carteOroBanco;
	}

}
