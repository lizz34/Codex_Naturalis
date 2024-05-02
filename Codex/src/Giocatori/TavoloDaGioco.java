package Giocatori;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Carte.*;

public class TavoloDaGioco {

	public ArrayList<Carta> mazzoCarteStarter;

	public ArrayList<CartaRisorsa> mazzoCarteRisorsa;

	public ArrayList<CartaOro> mazzoCarteOro;
	
	private ArrayList<CartaObiettivo> mazzoCarteObiettivo;

	private int contaCarte = 0;

	private ArrayList<CartaRisorsa> carteRisorsaBanco;
	private ArrayList<CartaOro> carteOroBanco;

	public TavoloDaGioco() {

		//Creazione mazzo carteStarter

		mazzoCarteStarter = new ArrayList<Carta>();
		contaCarte = 0;

		try {
			File carteStarter = new File("src\\carteStarter.txt"); // apro file carteStarter
			Scanner scanner = new Scanner(carteStarter);

			contaCarte = 0;

			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); // salvo riga per riga in data
				// System.out.println(data.length());

				String divisorio[] = data.split("-"); // divido l'intera riga per "-"
				// System.out.println(divisorio.length);

				// ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore
				// col, Disegno dis[]) )
				Angolo angoli[] = new Angolo[8];
				Disegno dis[] = new Disegno[3];

				for (int j=0; j<(divisorio.length-1); j++) { // scorro la riga, ora divisa in 9 elementi (8angoli,
																	// 1per disegni dietro)
					String[] infoAngolo = divisorio[j].split(","); // divido ogni elemento in due sottoelementi
					if (infoAngolo[0].equals("true")) { // se true devo creare l'angolo (ricordiamo Angolo(boolean
														// fronte, Disegno disegno) )
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

				mazzoCarteStarter.add(new Carta(angoli, Colore.giallo, dis)); // colore giallo per le starter (?)

				contaCarte++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		//Creazione mazzo carte Risorsa

		mazzoCarteRisorsa = new ArrayList<CartaRisorsa>();
		contaCarte = 0;

		try {
			File carteRisorsa = new File("src\\carteRisorse.txt"); // apro file carteStarter
			Scanner scanner = new Scanner(carteRisorsa);

			contaCarte = 0;

			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); // salvo riga per riga in data
				// System.out.println(data.length());

				String divisorio[] = data.split("-"); // divido l'intera riga per "-"
				// System.out.println(divisorio.length);

				// ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore
				// col, Disegno dis[]) )
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
				for (int j = 2; j < divisorio.length - 1; j++) { // scorro la riga, ora divisa in 9 elementi (8angoli,
																	// 1per disegni dietro)
					String[] infoAngolo = divisorio[j].split(","); // divido ogni elemento in due sottoelementi
					if (infoAngolo[0].equals("true")) { // se true devo creare l'angolo (ricordiamo Angolo(boolean
														// fronte, Disegno disegno) )
						Disegno disegno = null;
						boolean fronte;

						if (j < divisorio.length / 2) // la metà del vettore segna la divisione tra angoli fronte 0-3 e
														// angoli retro 4-7
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

				String[] infoDisegni = divisorio[divisorio.length - 1].split(","); // divido i tre disegni nell'ultimo
																					// segmento
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

				contaCarte++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Creazione mazzo carte Oro

		mazzoCarteOro = new ArrayList<CartaOro>();
		contaCarte = 0;

		try {
			File carteOro = new File("src\\carteOro.txt"); // apro file carteStarter
			Scanner scanner = new Scanner(carteOro);

			contaCarte = 0;

			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); // salvo riga per riga in data
				// System.out.println(data.length());

				String divisorio[] = data.split("-"); // divido l'intera riga per "-"
				// System.out.println(divisorio.length);

				// ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore
				// col, Disegno dis[]) )
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
					col = Colore.giallo; // giallo se errore (impossibile perchè risorsa hanno i 4 colori elencati
											// sopra)
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
				for (int j = 4; j < divisorio.length - 1; j++) { // scorro la riga, ora divisa in 9 elementi (8angoli,
																	// 1per disegni dietro)
					String[] infoAngolo = divisorio[j].split(","); // divido ogni elemento in due sottoelementi
					if (infoAngolo[0].equals("true")) { // se true devo creare l'angolo (ricordiamo Angolo(boolean
														// fronte, Disegno disegno) )
						Disegno disegno = null;
						boolean fronte;

						if (j < divisorio.length / 2) // la metà del vettore segna la divisione tra angoli fronte 0-3 e
														// angoli retro 4-7
							fronte = true;
						else
							fronte = false; // FIXME !!!!

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

				String[] infoDisegni = divisorio[divisorio.length - 1].split(","); // divido i tre disegni nell'ultimo
																					// segmento
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

				contaCarte++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		//creazione mazzo carte obiettivo
		mazzoCarteObiettivo = new ArrayList<CartaObiettivo>();
			mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni due piume"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni due boccette"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni due pergamene"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Tre punti se si hanno una piuma, una boccetta e una pergamena"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni tre farfalle"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni tre lupi"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni tre foglie"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Due punti per ogni tre funghi"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Tre punti se si ha la seguente disposizione: due carte verticali di colore viola con l’angolo in alto a sinistra che combacia con l’angolo in basso a destra di una carta blu"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Tre punti se si ha la seguente disposizione: due carte verticali di colore blu con l'angolo in alto a destra che combacia con l'angolo in basso a sinistra di una carta rossa"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Tre punti se si ha la seguente disposizione: due carte verticali verdi dove l'angolo in basso a sinistra combacia con l'angolo in alto a destra di una carta viola"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Tre punti se si ha la seguente disposizione: due carte verticali rosse dove l'angolo in basso a destra combacia con l'angolo in alto a sinistra di una carta verde"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Due punti se si ha la seguente disposizione: se si hanno tre carte di colore viola disposte diagonalmente, dove la carta sinistra sarà quella più alta e quella a destra la più bassa"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Due punti se si ha la seguente disposizione: se si hanno tre carte di colore blu, dove la carta sinistra sarà la più bassa e quella destra la più alta"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Due punti se si ha la seguente disposizione: se si hanno tre carte di colore verde disposte diagonalmente, dove la carta sinistra sarà quella più alta e quella a destra la più bassa"));
			mazzoCarteObiettivo.add(new CartaObiettivo("Due punti se si ha la seguente disposizione: se si hanno tre carte di colore rosso, dove la carta sinistra sarà la più bassa e quella destra la più alta"));

		//aggiunta delle carte di partenza che ci sono sul tavolo di gioco
		carteRisorsaBanco = new ArrayList<CartaRisorsa>();
		carteOroBanco = new ArrayList<CartaOro>();

		Random random = new Random();
		int randomNum;
		boolean inserito;

		randomNum = random.nextInt(mazzoCarteRisorsa.size());
		carteRisorsaBanco.add((CartaRisorsa) mazzoCarteRisorsa.get(randomNum));
		mazzoCarteRisorsa.remove(randomNum);
		
		do {
			randomNum = random.nextInt(mazzoCarteRisorsa.size());

			if (!carteRisorsaBanco.contains((CartaRisorsa) mazzoCarteRisorsa.get(randomNum))) {
				carteRisorsaBanco.add((CartaRisorsa) mazzoCarteRisorsa.get(randomNum));
				mazzoCarteRisorsa.remove(randomNum);
				System.out.println("Elemento aggiunto con successo");
				inserito = true;
			} else {
				System.out.println("Elemento già presente, non aggiunto");
				inserito = false;
			}
		} while (inserito == false);

		randomNum = random.nextInt(mazzoCarteOro.size());
		carteOroBanco.add((CartaOro) mazzoCarteOro.get(randomNum));
		mazzoCarteOro.remove(randomNum);

		do {
			randomNum = random.nextInt(mazzoCarteOro.size());

			if (!carteOroBanco.contains((CartaOro) mazzoCarteOro.get(randomNum))) {
				carteOroBanco.add((CartaOro) mazzoCarteOro.get(randomNum));
				mazzoCarteOro.remove(randomNum);
				//System.out.println("Elemento aggiunto con successo");
				inserito = true;
			} else {
				System.out.println("Errore - elemento già presente, non aggiunto");
				inserito = false;
			}
		} while (inserito == false);
	}

	/***
	 * estrae una carta risorsa dal mazzo e la posiziona sul tavolo da gioco
	 * dopo che quella che c'era prima viene pescata da un giocatore
	 * @return true se la carta viene pescata, false se il mazzo è vuoto
	 */
	public boolean giraCartaRisorsa() {
		Random random = new Random();
		int randomNum;
		boolean inserito;
		
		do {
			randomNum = random.nextInt(mazzoCarteRisorsa.size());

			if (!carteRisorsaBanco.contains((CartaRisorsa) mazzoCarteRisorsa.get(randomNum))) {
				carteRisorsaBanco.add((CartaRisorsa) mazzoCarteRisorsa.get(randomNum));
				//System.out.println("Elemento aggiunto con successo");
				inserito = true;
			} else {
				System.out.println("Errore - elemento già presente, non aggiunto");
				inserito = false;
			}
		} while (inserito == false);

		return true;
	}

	/***
	 * estrae una carta oro dal mazzo e la posiziona sul tavolo da gioco
	 * dopo che quella che c'era prima viene pescata da un giocatore
	 * @return true se la carta viene pescata, false se il mazzo è vuoto
	 */
	public boolean giraCartaOro() {
		Random random = new Random();
		int randomNum;
		boolean inserito;

		do {
			randomNum = random.nextInt(mazzoCarteOro.size());

			if (!carteOroBanco.contains((CartaOro) mazzoCarteOro.get(randomNum))) {
				carteOroBanco.add((CartaOro) mazzoCarteOro.get(randomNum));
				System.out.println("Elemento aggiunto con successo");
				inserito = true;
			} else {
				System.out.println("Elemento già presente, non aggiunto");
				inserito = false;
			}
		} while (inserito == false);

		return true;
	}
	
	/***
	 * prima condizione di fine partita: caso che finiscono le carte nei mazzi e sul tavolo di gioco
	 * @return true se tutte le carte sono finite, false se c'è almeno una carta ancora prendibile dal tavolo di gioco
	 */
	public boolean condEndGame1() {
		if(mazzoCarteRisorsa.isEmpty() && mazzoCarteOro.isEmpty() && carteRisorsaBanco.isEmpty() && carteOroBanco.isEmpty())
			return true;

		return false;
	}
	
	/***
	 * Funzione per pescare una carta risorsa
	 * @return ritorna la carta pescata
	 */
	public CartaRisorsa pescaCartaRisorsa() {
		Random random = new Random();
		int randomNum;
		CartaRisorsa cartaPescata;
		
		randomNum = random.nextInt(mazzoCarteRisorsa.size());
		cartaPescata = mazzoCarteRisorsa.get(randomNum);
		mazzoCarteRisorsa.remove(randomNum);
		
		return cartaPescata;
	}
	
	/***
	 * Funzione per pescare una carta oro
	 * @return ritorna la carta pescata
	 */
	public CartaOro pescaCartaOro() {
		Random random = new Random();
		int randomNum;
		CartaOro cartaPescata;
		
		randomNum = random.nextInt(mazzoCarteOro.size());
		cartaPescata = mazzoCarteOro.get(randomNum);
		mazzoCarteOro.remove(randomNum);
		
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
	
	
	//FIXME spostare nel main
	/*public Vector<Carta> pescaCarteIniziali() {
		Vector<Carta> carte = new Vector<Carta>();
		int random = (int) (Math.random() * mazzoCarteOro.size());
		carte.add(mazzoCarteOro.remove(random));
		random = (int) (Math.random() * mazzoCarteRisorsa.size());
		carte.add(mazzoCarteRisorsa.remove(random));
		random = (int) (Math.random() * mazzoCarteRisorsa.size());
		carte.add(mazzoCarteRisorsa.remove(random));
		return carte;
	}*/
}
