package ApplicationExceptions;

public class ValueAlreadyExistsException extends Exception {
    static final long serialVersionUID = 1L;
    public ValueAlreadyExistsException(String msg){
        super(msg);
    }
}
