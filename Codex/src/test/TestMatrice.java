/**
 * 
 */
package test;

import Carte.Angolo;
import Carte.Carta;
import Carte.CartaRisorsa;
import Carte.CartaStarter;
import Carte.Colore;
import Carte.Disegno;
import grafica.Matrice;

/**
 * 
 */
public class TestMatrice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int numero = Integer.parseInt("pippo");
		
		System.out.println(numero);
		

		CartaStarter cartaStarter = CartaStarter.parseString("true,fungo-true,foglia-true,lupo-true,farfalla-true,null-true,foglia-true,null-true,farfalla-farfalla,null,null");
		CartaRisorsa cartaRisorsa = CartaRisorsa.parseString("0-blu-true,null-true,lupo-true,lupo-false-true,null-true,null-true,null-true,null-lupo,null,null");
		
		cartaStarter.attaccaCarta(cartaRisorsa, 0);
		
		Matrice.disegnaMatrice(cartaStarter);
	
	
	}

}
