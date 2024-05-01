package Carte;

import java.util.Random;

public class CarteObiettivo {
	
		
    String[] obiettivi = {
            "Due punti per ogni due piume",
            "Due punti per ogni due boccette",
            "Due punti per ogni due pergamene",
            "Tre punti se si hanno una piuma, una boccetta e una pergamena",
            "Due punti per ogni tre farfalle",
            "Due punti per ogni tre lupi",
            "Due punti per ogni tre foglie",
            "Due punti per ogni tre funghi",
            "Tre punti se si ha la seguente disposizione: due carte verticali di colore viola con l’angolo in alto a sinistra che combacia con l’angolo in basso a destra di una carta blu",
            "Tre punti se si ha la seguente disposizione: due carte verticali di colore blu con l'angolo in alto a destra che combacia con l'angolo in basso a sinistra di una carta rossa",
            "Tre punti se si ha la seguente disposizione: due carte verticali verdi dove l'angolo in basso a sinistra combacia con l'angolo in alto a destra di una carta viola",
            "Tre punti se si ha la seguente disposizione: due carte verticali rosse dove l'angolo in basso a destra combacia con l'angolo in alto a sinistra di una carta verde",
            "Due punti se si ha la seguente disposizione: se si hanno tre carte di colore viola disposte diagonalmente, dove la carta sinistra sarà quella più alta e quella a destra la più bassa",
            "Due punti se si ha la seguente disposizione: se si hanno tre carte di colore blu, dove la carta sinistra sarà la più bassa e quella destra la più alta",
            "Due punti se si ha la seguente disposizione: se si hanno tre carte di colore verde disposte diagonalmente, dove la carta sinistra sarà quella più alta e quella a destra la più bassa",
            "Due punti se si ha la seguente disposizione: se si hanno tre carte di colore rosso, dove la carta sinistra sarà la più bassa e quella destra la più alta"
    };


    // Metodo per estrarre due frasi casuali diverse dall'array degli obiettivi
    String[] ottieniDueObiettiviCasuali() {
        Random rand = new Random();
        String[] obiettiviCasuali = new String[2];

        int primoIndice = rand.nextInt(obiettivi.length);
        int secondoIndice;
        do {
            secondoIndice = rand.nextInt(obiettivi.length);
        } while (secondoIndice == primoIndice);

        obiettiviCasuali[0] = obiettivi[primoIndice];
        obiettiviCasuali[1] = obiettivi[secondoIndice];

        return obiettiviCasuali;
    
}
    
  
}
