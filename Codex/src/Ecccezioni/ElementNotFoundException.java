package Ecccezioni;

public class ElementNotFoundException extends RuntimeException {
	/**
	 * eccezione che viene lanciata nel caso in cui un elemento non venga trovato
	 */
	private static final long serialVersionUID = 1L;

	public ElementNotFoundException() {
        super();
    }

    public ElementNotFoundException(String message) {
        super(message);
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }
}
