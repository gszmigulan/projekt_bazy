package baza;

public class EmptyResultSetException extends Exception {
    private static final long serialVersionUID = 1L;

    public EmptyResultSetException() {
        super("ResultSet is empty");
    }
}
