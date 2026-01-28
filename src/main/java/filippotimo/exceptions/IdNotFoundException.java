package filippotimo.exceptions;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(long id) {
        super("Il record con id = " + id + " non è stato trovato o non è presente");
    }
}
