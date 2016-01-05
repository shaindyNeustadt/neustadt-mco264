package neustadt.linkedList;

public class ListEmptyException extends RuntimeException{
	   public ListEmptyException(){
		   super("list empty");
	   }
	}