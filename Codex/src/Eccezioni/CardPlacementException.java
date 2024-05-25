package Eccezioni;

//eccezione lanciata nel caso in cui ci sia un errore che concerne la posizione di una carta
//esempio: la carta non può essere posizionata, la carta cercata nella matrice non è stata trovata...
public class CardPlacementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CardPlacementException() {
		// TODO Auto-generated constructor stub
	}

	public CardPlacementException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CardPlacementException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CardPlacementException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CardPlacementException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
