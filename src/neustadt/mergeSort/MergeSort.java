package neustadt.mergeSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class MergeSort {

	public static void main(String[] args) throws FileNotFoundException {
		MergeSort mergeSort = new MergeSort();
		Scanner keyboard = new Scanner(System.in);
		int quantity = 0;
		do {
			System.out
					.println("How many Integers would you like to be sorted?");
			quantity = keyboard.nextInt();
		} while (quantity <= 0);
		mergeSort.generateNums(quantity);
		String fileName = mergeSort.sortMerge("numbers.txt");

		Scanner readFile = new Scanner(new File(fileName));
		System.out.println("Sort Completed!");
		while (readFile.hasNext()) {
			System.out.println(readFile.next());
		}
		readFile.close();
		keyboard.close();
	}

	private int quantity;

	public void generateNums(int quantity) throws FileNotFoundException {
		this.quantity = quantity;
		Random generator = new Random();
		PrintWriter writer = new PrintWriter("numbers.txt");
		for (int i = 0; i < quantity; i++) {
			writer.print(generator.nextInt(101) + " ");
		}
		writer.close();
		}

	public String sortMerge(String fileName) throws FileNotFoundException {
		int i = 0;
		PrintWriter writer = null;
		int[] array = new int[10];
		int index = 0;

		Scanner fileReader = new Scanner(new File(fileName));
		while (fileReader.hasNext()) {

			array[index++] = fileReader.nextInt();

			if (index == 10 || !fileReader.hasNext()) {

				writer = new PrintWriter(++i + "sorted.txt");
				
				bubbleSort(array, index);

				for (int y = 0; y < index; y++) {
					writer.print(array[y] + " ");
				}
				writer.close();
				index = 0;
			}
		}

		fileReader.close();
		return merge(1, i);
	}

	private String merge(int firstFileNum, int lastFileNum)
			throws FileNotFoundException {
		
		if (firstFileNum == lastFileNum) {
			return lastFileNum + "sorted.txt";
		}

		int secondFileNum = firstFileNum + 1;

		int[] data = new int[quantity];
		int index = 0;

		Scanner file1 = new Scanner(new File(firstFileNum + "sorted.txt"));
		while (file1.hasNext()) {
			data[index++] = file1.nextInt();
		}

		file1.close();

		int leftIndex = 0;
		int middle1 = index - 1;
		int rightIndex = index;
		int middle2 = index;

		Scanner file2 = new Scanner(new File(secondFileNum + "sorted.txt"));
		while (file2.hasNext()) {
			data[index++] = file2.nextInt();
		}
		file2.close();

		PrintWriter writer = new PrintWriter(secondFileNum + "sorted.txt");

		int right = index - 1;

		while (leftIndex <= middle1 && rightIndex <= right) {
			if (data[leftIndex] <= data[rightIndex]) {
				writer.print(data[leftIndex++] + " ");
			} else {
				writer.print(data[rightIndex++] + " ");
			}
		}
		if (leftIndex == middle2) {
			while (rightIndex <= right) {
				writer.print(data[rightIndex++] + " ");
			}
		} else {
			while (leftIndex <= middle1) {
				writer.print(data[leftIndex++] + " ");
			}
		}
		writer.close();
		return merge(secondFileNum, lastFileNum);
	}

	public static void bubbleSort(int[] numbers, int qty) {
		int temp = 0;

		for (int i = 0; i < qty; i++) {
			for (int j = 1; j < (qty - i); j++) {

				if (numbers[j - 1] > numbers[j]) {
					temp = numbers[j - 1];
					numbers[j - 1] = numbers[j];
					numbers[j] = temp;
				}

			}
		}
	}
}
