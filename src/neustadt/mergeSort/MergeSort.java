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

	public void generateNums(int quantity) throws FileNotFoundException {
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
		return merge(i-1, i, i+1);
	}

	private String merge(int firstFileNum, int secondFileNum, int storeFileNum)
			throws FileNotFoundException {
		
		if (firstFileNum == 0) {
			return secondFileNum + "sorted.txt";
		}
	
		Scanner file1 = new Scanner(new File(firstFileNum + "sorted.txt"));
		Scanner file2 = new Scanner(new File(secondFileNum + "sorted.txt"));
	
		PrintWriter writer = new PrintWriter(storeFileNum + "sorted.txt");

		int num1 = file1.nextInt();
		int num2 = file2.nextInt();
		
		while (num1 != -1 && num2 != -1) {
			if (num1 <= num2) {
				writer.print(num1 + " ");
				if(file1.hasNext()){
				num1 = file1.nextInt();
				}
				else{
					num1 = -1;
				}
			} else {
				writer.print(num2 + " ");
				if(file2.hasNext()){
				num2 = file2.nextInt();
				}
				else{
					num2 = -1;
				}
			}
		}
		if (num1 == -1) {
			writer.print(num2 + " ");
			while (file2.hasNext())
			{
				writer.print(file2.nextInt() + " ");
			}
		} else {
			writer.print(num1 + " ");
			while (file1.hasNext()) {
				writer.print(file1.nextInt() + " ");
			}
		}
		writer.close();
		file1.close();
		file2.close();
		return merge(firstFileNum -1, storeFileNum, firstFileNum);
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
