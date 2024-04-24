package Carte;

import java.util.Random;

public class CarteObiettivo {
	public static void main(String[] args) {
    
        // Array contenente le carte obiettivo
       String[] obiettivi = {"Due punti per ogni due piume",
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
      
       {

        Random rand = new Random();

                int primo = rand.nextInt(15);
        int secondo = 0;
        
        do {
            primo = rand.nextInt(15);
        } while ( secondo == primo); // Assicuro che non escano due carte obiettivo uguali 

                System.out.println("Carte obiettivo:");
        switch (primo) {
            case 0:
                System.out.println("1. " + obiettivi[0]);
                break;
            case 1:
                System.out.println("1. " + obiettivi[1]);
                break;
            case 2:
                System.out.println("1. " + obiettivi[2]);
                break;
            case 3:
                System.out.println("1. " + obiettivi[3]);
                break;
            case 4:
                System.out.println("1. " + obiettivi[4]);
                break;
            case 5:
                System.out.println("1. " + obiettivi[5]);
                break;
            case 6:
                System.out.println("1. " + obiettivi[6]);
                break;
            case 7:
                System.out.println("1. " + obiettivi[7]);
                break;
            case 8:
                System.out.println("1. " + obiettivi[8]);
                break;
            case 9:
                System.out.println("1. " + obiettivi[9]);
                break;
            case 10:
                System.out.println("1. " + obiettivi[10]);
                break;
            case 11:
                System.out.println("1. " + obiettivi[11]);
                break;
            case 12:
                System.out.println("1. " + obiettivi[12]);
                break;
            case 13:
                System.out.println("1. " + obiettivi[13]);
                break;
            case 14:
                System.out.println("1. " + obiettivi[14]);
                break;
            case 15:
                System.out.println("1. " + obiettivi[15]);
                break;
                
                
            
        }

        switch (secondo) {
        case 0:
            System.out.println("2. " + obiettivi[0]);
            break;
        case 1:
            System.out.println("2. " + obiettivi[1]);
            break;
        case 2:
            System.out.println("2. " + obiettivi[2]);
            break;
        case 3:
            System.out.println("2. " + obiettivi[3]);
            break;
        case 4:
            System.out.println("2. " + obiettivi[4]);
            break;
        case 5:
            System.out.println("2. " + obiettivi[5]);
            break;
        case 6:
            System.out.println("2. " + obiettivi[6]);
            break;
        case 7:
            System.out.println("2. " + obiettivi[7]);
            break;
        case 8:
            System.out.println("2. " + obiettivi[8]);
            break;
        case 9:
            System.out.println("2. " + obiettivi[9]);
            break;
        case 10:
            System.out.println("2. " + obiettivi[10]);
            break;
        case 11:
            System.out.println("2. " + obiettivi[11]);
            break;
        case 12:
            System.out.println("2. " + obiettivi[12]);
            break;
        case 13:
            System.out.println("2. " + obiettivi[13]);
            break;
        case 14:
            System.out.println("2. " + obiettivi[14]);
            break;
        case 15:
            System.out.println("2. " + obiettivi[15]);
            break;
        
        }
        
            
    }



}}
