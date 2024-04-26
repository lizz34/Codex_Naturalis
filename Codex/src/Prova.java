import Carte.CartaObiettivo;

public class Prova {

   
        // Crea un'istanza di CartaObiettivo
        CartaObiettivo cartaObiettivo = new CartaObiettivo();

        // Estrai due obiettivi casuali dalla classe CartaObiettivo
        String[] dueObiettivi = cartaObiettivo.ottieniDueObiettiviCasuali();

        // Estrai casualmente uno dei due obiettivi
        int indiceCasuale = (int) (Math.random() * 2); // Genera un numero casuale tra 0 e 1
        String obiettivoCasuale = dueObiettivi[indiceCasuale];

        // Stampa l'obiettivo estratto per la prova
        System.out.println("Obiettivo estratto per la prova: " + obiettivoCasuale);
    }

