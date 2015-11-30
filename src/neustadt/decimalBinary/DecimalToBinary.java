package neustadt.decimalBinary;

import java.util.Scanner;
import java.util.Stack;

public class DecimalToBinary {
	private Stack<Integer> stack;

	public DecimalToBinary() {
		this.stack = new Stack<Integer>();
	}

	public String convert(int decimalNum) {
		StringBuilder binaryNum = new StringBuilder();
		while (decimalNum > 0) {
			stack.push(decimalNum % 2);
			decimalNum /= 2;
		}

		while (!stack.isEmpty()) {
			binaryNum.append(stack.pop());
		}
		return binaryNum.toString();
	}

	public static void main(String[] args) {
		DecimalToBinary dtb = new DecimalToBinary();
		Scanner keyboard = new Scanner(System.in);

		do {
			System.out.println("\nEnter a decimal number:");
			int decimal = keyboard.nextInt();

			String binary = dtb.convert(decimal);

			System.out.println("The number in binary is: " + binary);

		} while (true);
	}
}
