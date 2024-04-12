package pedine;
import javax.swing.*;
import java.util.*;
public class Try {
	
	/* Questo è un semplice programma di esempio e non utilizza lo stile più corretto per i programmi Swing. */
	public static final int LARGHEZZA=300; //dimensioni in pixel della finestra
	public static final int ALTEZZA=200;
	public static void main (String args[]) {
	//una prima finestra
	JFrame finestral=new JFrame (); // (1) 
	finestral. setSize (LARGHEZZA, ALTEZZA) ; // (2)
	JLabel etichettal=new JLabel ("Non chiudermi!"); // (3)
	finestral.add (etichettal); // (4)
	finestral. setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE) ; // (5)
	finestral. setVisible (true); // (6)
	//una seconda finestra: qualche piccola variante
	/*JErame finestra2=new JFrame ("Ho un titolo!") ;
	finestra2. setBounds (200, 200, 300,200) ;
	JLabel etichetta2=new JLabel ("Chiudimi tranquillamente") ;
	finestra2. add (etichetta2) ;
	finestra2. setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // (7)
	finestra2. setVisible (true);*/
	}
}
