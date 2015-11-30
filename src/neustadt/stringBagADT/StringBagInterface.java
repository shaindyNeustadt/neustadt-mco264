package neustadt.stringBagADT;

public interface StringBagInterface {

	// if not full adds a String element to bag
	void insert(String element);

	// returns if the bag is full
	boolean isFull();

	// returns if bag is empty
	boolean isEmpty();

	// removes random element from bag and returns element
	String remove();

	// empties bag
	void clear();

	// returns formatted bag name and contents
	String toString();

}
