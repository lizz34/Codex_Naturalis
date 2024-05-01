package Carte;

import java.util.ArrayList;

public class MazzoCarteObiettivo {
	private ArrayList<CartaObiettivo> carte = new ArrayList<CartaObiettivo>();
	

	public void add(CartaObiettivo co) {

		carte.add(co);
	}


	public CartaObiettivo pescaCarta() {
		int random = (int)(Math.random()*carte.size());
		return carte.remove(random);
	
	}
}
