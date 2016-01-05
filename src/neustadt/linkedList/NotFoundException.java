package neustadt.linkedList;

public class NotFoundException extends RuntimeException{
	public NotFoundException(){
		super("data not found");
	}

}