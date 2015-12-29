package neustadt.billOrganizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {
		BillOrganizer organizer = null;
		Scanner keyboard = new Scanner(System.in);

		System.out
				.println("Has this application previously been set up? (Y/N)");
		char answer = keyboard.next().charAt(0);
		if (answer == 'Y' || answer == 'y') {
			try {
				organizer = new BillOrganizer("bills.ser");
			} catch (FileNotFoundException ex) {
				System.out.println("Could not set up records. File not found.");
				System.exit(1);
			} catch (InvalidDataException ex) {
				System.out.println("Could not set up records. Invalid Data.");
				System.exit(1);
			} catch (ClassNotFoundException ex) {
				System.out
						.println("Could not set up records. Inconsistant Class format.");
				System.exit(1);
			} catch (IOException ex) {
				System.out.println("Could not set up records. I/O Exception.");
				System.exit(1);
			}
		} else {
			try {
				Scanner readFile = new Scanner(new File("bills.txt"));
				organizer = new BillOrganizer(readFile);
			} catch (FileNotFoundException ex) {
				System.out.println("Could not set up records. File not found.");
				System.exit(1);
			}
		}
		int choice;
		do {
			choice = menu(keyboard);

			switch (choice) {
			case 0:
				System.out.println("Exiting application.....Good Bye!");
				try {
					organizer.closeOrganizer();
					System.exit(0);
				} catch (IOException ex) {
					System.out
							.println("Can not shut down properly. Contact IT.");
				}
				break;
			case 1:
				System.out.println("Add a bill:");
				System.out.println("Vendor Name:");
				String vendorName = keyboard.next();
				System.out.println("Amount Due:");
				double amountDue = keyboard.nextDouble();
				keyboard.nextLine();
				System.out.println("Due Date: (MM/dd/YYYY)");
				String date = keyboard.next();
				System.out
						.println("Bill Type: \nCLOTHING, EDUCATION, FOOD, GROCERIES, PHONE, TRAVEL, UTILITIES");
				String billType = keyboard.next();
				organizer
						.insert(new Bill(vendorName, amountDue, date, billType));
				System.out.println("Bill Total: $" + organizer.totalBills());
				break;
			case 2:
				System.out.println("View bills by:");
				int index = comparator(keyboard);
				System.out.println(organizer.getQueue(index));
				break;
			case 3:
				System.out.println("Pay next bill by:");
				BillCriteria criteria = null;
				int criteria1 = comparator(keyboard);
				switch(criteria1){
				case 0:
					criteria = BillCriteria.BILLDUEDATE;
					break;
				case 1:
					criteria = BillCriteria.BILLTYPE;
					break;
				case 2:
					criteria = BillCriteria.BILLAMOUNT;
					break;
				}
				System.out.print("Paid Bill:");
				System.out.println(organizer.payNextBill(criteria));
				System.out.println("Bill Total: $" + organizer.totalBills());
				break;
			case 4:
				System.out.println("Pay bill by bill ID:");
				System.out.println("Bill ID:");
				int id = keyboard.nextInt();
				keyboard.nextLine();
				System.out.print("Paid Bill:");
				try{
				System.out.println(organizer.removeBillByID(id));
				System.out.println("Bill Total: $" + organizer.totalBills());
				} catch(NotFoundException ex){
					System.out.println(" Bill not found.");
				}				
				break;
			}
		} while (choice != 0);

	}

	private static int menu(Scanner keyboard) {
		int choice = 0;
		do {
			System.out.println("\n1. Add a bill \n" + "2. View bills\n"
					+ "3. Pay next bill\n" + "4. Pay bill by bill ID\n"
					+ "Enter 0 to exit");
			choice = keyboard.nextInt();
			keyboard.nextLine();
		} while (choice < 0 || choice > 4);
		return choice;
	}

	private static int comparator(Scanner keyboard) {
		int choice;
		do {
			System.out.println("1. Date\n" + "2. Bill type\n"
					+ "3. Amount Due");
			choice = keyboard.nextInt();
			keyboard.nextLine();
		} while (choice < 1 || choice > 3);
		return choice - 1;
	}
}