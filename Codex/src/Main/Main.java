package Main;
import Carte.*;
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//LETTURA DA FILE PER LA CREAZIONE DEL MAZZO DELLE CARTE STARTER 
		Carta mazzoCarteStarter[] = new Carta [6];
		int contaCarteStarter=0;
			
		try {
			File carteStarter = new File("C:\\Users\\Raul\\Desktop\\progettoUni\\Codex\\src\\carteStarter.txt"); //apro file carteStarter
			Scanner scanner = new Scanner(carteStarter);
			
			contaCarteStarter=0;
			
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
					if(divisorio[1].equals("true")) { //se true devo creare l'angolo (ricordiamo Angolo(boolean fronte, Disegno disegno) )
						Disegno disegno;
						boolean fronte; 
						if(j<divisorio.length/2) //la metà del vettore segna la divisione tra angoli fronte 0-3 e angoli retro 4-7
							fronte=true;
						else
							fronte=false;
						
						switch(infoAngolo[2]) { //assegno angolo
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
						default:
							System.out.println("Errore lettura disegno");
							disegno=null;
							break;
						}
						
						angoli[j] = new Angolo(fronte, disegno);
					}
					else
						angoli[j] = null;
					
				}
				mazzoCarteStarter[contaCarteStarter] = new Carta(angoli, Colore.giallo, dis); //da aggiungere i tre disegni nel retro
				
				contaCarteStarter++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		for(int i=0; i<6; i++) {
			System.out.println(mazzoCarteStarter);
		}
		//---------

	}


}
