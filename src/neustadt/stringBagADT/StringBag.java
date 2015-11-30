package neustadt.stringBagADT;

import java.util.Random;

public class StringBag implements StringBagInterface {

	private String[] bag;
	private int lastIndex = -1;

	// StringBag constructor
	public StringBag(int size) {
		bag = new String[size];
	}

	// if not full adds a String element to bag
	public void insert(String element) {
		lastIndex++;
		bag[lastIndex] = element;
	}

	// returns if the bag is full
	public boolean isFull() {
		if (lastIndex + 1 == bag.length) {
			return true;
		}
		return false;
	}

	// returns if bag is empty
	public boolean isEmpty() {
		if (lastIndex == -1) {
			return true;
		}
		return false;
	}

	// removes random element from bag and returns element
	public String remove() {
		Random random = new Random();
		int number = random.nextInt(lastIndex + 1);
		String selected = bag[number];
		while (number < lastIndex) {
			bag[number] = bag[++number];
		}
		bag[lastIndex] = null;
		lastIndex--;
		return selected;
	}

	// clears bag
	public void clear() {
		for (int i = 0; i <= lastIndex; i++) {
			bag[i] = null;
		}
		lastIndex = -1;
	}

	// returns formatted bag name and contents
	public String toString() {
		StringBuilder bagInfo = new StringBuilder();

		for (int i = 0; i <= lastIndex; i++) {
			bagInfo.append("\n" + (i + 1) + ". " + bag[i]);
		}
		return bagInfo.toString();
	}
}