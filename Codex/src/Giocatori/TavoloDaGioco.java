package Giocatori;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Carte.*;

public class TavoloDaGioco {
	
	public Carta mazzoCarteStarter[];
	
	public Carta mazzoCarteRisorsa[];
	
	public Carta mazzoCarteOro[];

	private int contaCarte;
	
	private ArrayList<CartaRisorsa> carteRisorsaBanco;
	private ArrayList<CartaOro> carteOroBanco;
	
	
	public TavoloDaGioco() {
		
		//Creazione mazzo carte Starter
		mazzoCarteStarter = new Carta [6];
		contaCarte=0;
			
		try {
			File carteStarter = new File("C:\\Users\\Raul\\Desktop\\progettoUni\\Codex\\src\\carteStarter.txt"); //apro file carteStarter
			Scanner scanner = new Scanner(carteStarter);
			
			contaCarte=0;
			
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); //salvo riga per riga in data
				//System.out.println(data.length());
				
				String divisorio[] = data.split("-"); //divido l'intera riga per "-"
				//System.out.println(divisorio.length);
				
				//ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore col, Disegno dis[]) )
				Angolo angoli[] = new Angolo[8];
				Disegno dis[] = new Disegno[3];
				
				for(int j=0; j<divisorio.length-1; j++) { //scorro la riga, ora divisa in 9 elementi (8angoli, 1per disegni dietro)
					String[] infoAngolo = divisorio[j].split(","); //divido ogni elemento in due sottoelementi
					if(infoAngolo[0].equals("true")) { //se true devo creare l'angolo (ricordiamo Angolo(boolean fronte, Disegno disegno) )
						Disegno disegno = null;
						boolean fronte;
						
						if(j<divisorio.length/2) //la metà del vettore segna la divisione tra angoli fronte 0-3 e angoli retro 4-7
							fronte=true;
						else
							fronte=false;
						
						switch(infoAngolo[1]) { //assegno angolo
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
							disegno=null;
							break;
						default: //se si toglie il case: null --> va tolto anche il default
							System.out.println("Errore lettura disegno 1 - Starter");
							disegno = null;
							break;
						}
						
						angoli[j] = new Angolo(fronte, disegno);
					}
					else
						angoli[j] = null;
					
				}
				
				String[] infoDisegni = divisorio[divisorio.length-1].split(","); //divido i tre disegni nell'ultimo segmento
				for(int j=0; j<dis.length; j++) {
					switch(infoDisegni[j]) {
					case "lupo": 
						dis[j]=Disegno.lupo;
						break;
					case "farfalla":
						dis[j]=Disegno.farfalla;
						break;
					case "fungo":
						dis[j]=Disegno.fungo;
						break;
					case "foglia":
						dis[j]=Disegno.foglia;
						break;
					case "null":
						dis[j]=null;
						break;
					default:
						System.out.println("Errore lettura disegno 2 - starter");
						dis[j]=null;
						break;
					}
				}
				
				
				mazzoCarteStarter[contaCarte] = new Carta(angoli, Colore.giallo, dis); //colore giallo per le starter (?)
				
				contaCarte++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		
		//Creazione mazzo carte Risorsa
		
		mazzoCarteRisorsa = new CartaRisorsa [40];
		contaCarte=0;
			
		try {
			File carteRisorsa = new File("C:\\Users\\Raul\\Desktop\\progettoUni\\Codex\\src\\carteRisorse.txt"); //apro file carteStarter
			Scanner scanner = new Scanner(carteRisorsa);
			
			contaCarte=0;
			
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); //salvo riga per riga in data
				//System.out.println(data.length());
				
				String divisorio[] = data.split("-"); //divido l'intera riga per "-"
				//System.out.println(divisorio.length);
				
				//ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore col, Disegno dis[]) )
				Colore col;
				Angolo angoli[] = new Angolo[8];
				Disegno dis[] = new Disegno[3];
				
				switch(divisorio[1]) {
				case "rosso":
					col=Colore.rosso;
					break;
				case "blu":
					col=Colore.blu;
					break;
				case "verde":
					col=Colore.verde;
					break;
				case "viola":
					col=Colore.viola;
					break;
				default: 
					System.out.println("Errore lettura colore");
					col=Colore.giallo; //giallo se errore (impossibile perchè risorsa hanno i 4 colori elencati sopra)
					break;
				}
				
				int contaAngoli=0;
				for(int j=2; j<divisorio.length-1; j++) { //scorro la riga, ora divisa in 9 elementi (8angoli, 1per disegni dietro)
					String[] infoAngolo = divisorio[j].split(","); //divido ogni elemento in due sottoelementi
					if(infoAngolo[0].equals("true")) { //se true devo creare l'angolo (ricordiamo Angolo(boolean fronte, Disegno disegno) )
						Disegno disegno = null;
						boolean fronte;
						
						if(j<divisorio.length/2) //la metà del vettore segna la divisione tra angoli fronte 0-3 e angoli retro 4-7
							fronte=true;
						else
							fronte=false;
						
						switch((String)infoAngolo[1]) { //assegno angolo
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
							disegno=null;
							System.out.println("sono dentro");
							break;
						default: //se si toglie il case: null --> va tolto anche il default
							System.out.println("Errore lettura disegno 1 - risorsa");
							disegno = null;
							break;
						}
						
						angoli[contaAngoli] = new Angolo(fronte, disegno);
					}
					else
						angoli[contaAngoli] = null;
					
					contaAngoli++;
				}
				
				String[] infoDisegni = divisorio[divisorio.length-1].split(","); //divido i tre disegni nell'ultimo segmento
				for(int j=0; j<dis.length; j++) {
					switch(infoDisegni[j]) {
					case "lupo": 
						dis[j]=Disegno.lupo;
						break;
					case "farfalla":
						dis[j]=Disegno.farfalla;
						break;
					case "fungo":
						dis[j]=Disegno.fungo;
						break;
					case "foglia":
						dis[j]=Disegno.foglia;
						break;
					case "null":
						dis[j]=null;
						break;
					default:
						System.out.println("Errore lettura disegno 2 - risorsa");
						dis[j]=null;
						break;
					}
				}
				
				
				mazzoCarteRisorsa[contaCarte] = new CartaRisorsa(angoli, col, dis, (Integer.parseInt(divisorio[0]))); //colore giallo per le starter (?)
				
				contaCarte++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		
		
		
		//Creazione mazzo carte Oro
		
		mazzoCarteOro = new CartaOro [40];
		contaCarte=0;
			
		try {
			File carteOro = new File("C:\\Users\\Raul\\Desktop\\progettoUni\\Codex\\src\\carteOro.txt"); //apro file carteStarter
			Scanner scanner = new Scanner(carteOro);
			
			contaCarte=0;
			
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine(); //salvo riga per riga in data
				//System.out.println(data.length());
				
				String divisorio[] = data.split("-"); //divido l'intera riga per "-"
				//System.out.println(divisorio.length);
				
				//ora devo assegnare e allocare la carta (ricordiamo Carta(Angolo ang[], Colore col, Disegno dis[]) )
				Colore col;
				Disegno criterioPunti;
				Disegno disRichieste[] = new Disegno[5];
				Angolo angoli[] = new Angolo[8];
				Disegno dis[] = new Disegno[3];
				
				switch(divisorio[1]) {
				case "rosso":
					col=Colore.rosso;
					break;
				case "blu":
					col=Colore.blu;
					break;
				case "verde":
					col=Colore.verde;
					break;
				case "viola":
					col=Colore.viola;
					break;
				default: 
					System.out.println("Errore lettura colore");
					col=Colore.giallo; //giallo se errore (impossibile perchè risorsa hanno i 4 colori elencati sopra)
					break;
				}
				
				switch(divisorio[2]) {
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
				for(int j=0; j<5; j++) {
					System.out.println(infoRichieste[j] +"    ");
				}
				for(int j=0; j<5; j++) {
					switch(infoRichieste[j]) {
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
						disRichieste[j]=null;
						break;
					default:
						System.out.println("Errore lettura richieste per posizionare carta");
						disRichieste[j] = null;
						break;
					}
				}
				
				int contaAngoli=0;
				for(int j=4; j<11; j++) { //scorro la riga, ora divisa in 9 elementi (8angoli, 1per disegni dietro)
					String[] infoAngolo = divisorio[j].split(","); //divido ogni elemento in due sottoelementi
					if(infoAngolo[0].equals("true")) { //se true devo creare l'angolo (ricordiamo Angolo(boolean fronte, Disegno disegno) )
						Disegno disegno = null;
						boolean fronte;
						
						if(j<divisorio.length/2) //la metà del vettore segna la divisione tra angoli fronte 0-3 e angoli retro 4-7
							fronte=true;
						else
							fronte=false;   //FIXME !!!!
						
						switch(infoAngolo[1]) { //assegno angolo
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
							disegno=null;
							break;
						default: //se si toglie il case: null --> va tolto anche il default
							System.out.println("Errore lettura disegno 1 - oro");
							disegno = null;
							break;
						}
						
						angoli[contaAngoli] = new Angolo(fronte, disegno);
						
					}
					else
						angoli[contaAngoli] = null;
					System.out.println(" valore !!! " + contaAngoli);
					contaAngoli++;
				}
				
				String[] infoDisegni = divisorio[divisorio.length-1].split(","); //divido i tre disegni nell'ultimo segmento
				for(int j=0; j<dis.length; j++) {
					switch(infoDisegni[j]) {
					case "lupo": 
						dis[j]=Disegno.lupo;
						break;
					case "farfalla":
						dis[j]=Disegno.farfalla;
						break;
					case "fungo":
						dis[j]=Disegno.fungo;
						break;
					case "foglia":
						dis[j]=Disegno.foglia;
						break;
					case "null":
						dis[j]=null;
						break;
					default:
						System.out.println("Errore lettura disegno 2 - oro");
						dis[j]=null;
						break;
					}
				}
				
				
				mazzoCarteOro[contaCarte] = new CartaOro(angoli, col, dis, (Integer.parseInt(divisorio[0])), disRichieste, criterioPunti); 
				
				contaCarte++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		
		
		
		carteRisorsaBanco = new ArrayList<CartaRisorsa>();
		carteOroBanco = new ArrayList<CartaOro>();
		
		Random random = new Random();
		int randomNum;
		boolean inserito;
		
		randomNum = random.nextInt(40);
		carteRisorsaBanco.add((CartaRisorsa) mazzoCarteRisorsa[randomNum]);
		//.delete(randomNum)
		
		do {
			randomNum = random.nextInt(40);
			
			if (!carteRisorsaBanco.contains((CartaRisorsa) mazzoCarteRisorsa[randomNum])) {
		        carteRisorsaBanco.add((CartaRisorsa) mazzoCarteRisorsa[randomNum]);
		        System.out.println("Elemento aggiunto con successo");
		        inserito=true;
		    } 
		    else {
		        System.out.println("Elemento già presente, non aggiunto");
		        inserito=false;
		    }
		}while(inserito==false);
		
		
		
		randomNum = random.nextInt(40);
		carteOroBanco.add((CartaOro) mazzoCarteOro[randomNum]);
		//.delete(randomNum)
		
		do {
			randomNum = random.nextInt(40);
			
			if (!carteOroBanco.contains((CartaOro) mazzoCarteOro[randomNum])) {
				carteOroBanco.add((CartaOro) mazzoCarteOro[randomNum]);
		        System.out.println("Elemento aggiunto con successo");
		        inserito=true;
		    } 
		    else {
		        System.out.println("Elemento già presente, non aggiunto");
		        inserito=false;
		    }
		}while(inserito==false);
       
	}
	
	public boolean giraCartaRisorsa() {
		Random random = new Random();
		int randomNum;
		boolean inserito;
		//if mazzoCarteRisorsa è vuoto ritorno false 
		
		do {
			randomNum = random.nextInt(40);
			
			if (!carteRisorsaBanco.contains((CartaRisorsa) mazzoCarteRisorsa[randomNum])) {
		        carteRisorsaBanco.add((CartaRisorsa) mazzoCarteRisorsa[randomNum]);
		        System.out.println("Elemento aggiunto con successo");
		        inserito=true;
		    } 
		    else {
		        System.out.println("Elemento già presente, non aggiunto");
		        inserito=false;
		    }
		}while(inserito==false);
		
		return true;
	}
	
	public boolean giraCartaOro() {
		Random random = new Random();
		int randomNum;
		boolean inserito;
		//if mazzoCarteOro è vuoto ritorno false 
		
		do {
			randomNum = random.nextInt(40);
			
			if (!carteOroBanco.contains((CartaOro) mazzoCarteOro[randomNum])) {
				carteOroBanco.add((CartaOro) mazzoCarteOro[randomNum]);
		        System.out.println("Elemento aggiunto con successo");
		        inserito=true;
		    } 
		    else {
		        System.out.println("Elemento già presente, non aggiunto");
		        inserito=false;
		    }
		}while(inserito==false);
		
		return true;
	}
	
	
	
}
