package neustadt.stringBagADT;

public class StringBagTest {

	public static void main(String[] args) {
		// instantiates a new StringBag with a size of 10
		StringBag test = new StringBag(10);

		// insert 3 items into the StringBag and tests the
		// StringBag.toStringMethod
		System.out.println("A StringBag of three elements");
		test.insert("apples");
		test.insert("bunnies");
		test.insert("carrots");
		System.out.println(test.toString());

		// test the StringBag.clear method,
		// the test.toString method returns null
		// test.isEmpty() returns false
		System.out.println("Empties StringBag");
		test.clear();
		System.out.println(test.toString());
		System.out.println("This StringBag is empty: " + test.isEmpty());

		// inserts 8 String elements into the StringBag
		System.out.println("Inserts String elements into StringBag");
		test.insert("helicopters");
		test.insert("igloos");
		test.insert("jack in the box");
		test.insert("kangaroos");
		test.insert("lollipops");
		test.insert("monkeys");
		test.insert("nosh");
		test.insert("octopus");

		// while test.isFull returns false inserts more elements (2)
		while (!test.isFull()) {
			test.insert("potatoes");
		}
		System.out.println(test.toString());

		// test the test.remove method and displays what remains in the bag
		// while test.isEmpty is false
		System.out.println("\nThe removed Element: " + test.remove());
		System.out.println("The rest of the StringBag: " + test.toString());

		// test the test.remove method and displays what remains in the bag
		System.out.println("\nThe removed Element: " + test.remove());
		System.out.println("The rest of the StringBag: " + test.toString());

		// tests the test.isEmpty method returns false
		System.out.println("\nThis StringBag is empty: " + test.isEmpty());

		// removes the remaining elements
		System.out.println("Remove the remaining elements");
		while (!test.isEmpty()) {
			System.out.println(test.remove());
		}

		// tests the test.isEmpty method returns true
		System.out.println("\nThis StringBag is empty: " + test.isEmpty());

	}
}
