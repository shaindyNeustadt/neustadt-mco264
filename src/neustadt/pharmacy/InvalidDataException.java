package neustadt.pharmacy;

public class InvalidDataException extends RuntimeException {
public InvalidDataException(){
	super("Invalid data entered.");
}
}
