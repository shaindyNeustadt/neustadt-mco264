package neustadt.pharmacy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {
		PharmacyList list = null;
		Scanner input = new Scanner(System.in);
		System.out
				.println("Has this application previously been set up? (enter 'Y' or 'N')");
		char setUp = input.nextLine().charAt(0);
		if (setUp == 'n' || setUp == 'N') {
			try {
				list = new PharmacyList();
				list.connectToData("pharmacyRecords.dat");
				Scanner file = new Scanner(new File("pharmacyCompanies.txt"));
				while (file.hasNext()) {
					list.addCompany(new PharmaceuticalCo(file));
				}
				file.close();
			} catch (FileNotFoundException ex) {
				System.out.println("Could not set up records. File not found.");
				System.exit(1);
			} catch (InvalidDataException ex) {
				System.out.println("Could not set up records. Invalid Data.");
				System.exit(1);
			} catch (DuplicateDataException ex) {
				System.out.println("Could not set up records. Duplicate Data.");
				System.exit(1);
			} catch (IOException ex) {
				System.out.println("Could not set up records. I/O Exception.");
				System.exit(1);
			}
		}
		// data was already set up on disk
		else {
			try {
				ObjectInputStream inputStream = new ObjectInputStream(
						new FileInputStream("pharmacyRecords.ser"));
				try {
					list = (PharmacyList) inputStream.readObject();
					list.connectToData("pharmacyRecords.dat");
					inputStream.close();
				} catch (ClassNotFoundException ex) {
					System.out
							.println("The data in the file does not correspond to the class structure that currently exists. Contact IT for further support");
					System.exit(1);
				}
			} catch (IOException ex) {
				System.out
						.println("Could not locate files to restore pharmaceutical records. Contact IT for further support.");
				System.exit(1);
			}
		}
		while (true) {
			int choice = menu(input);
			switch (choice) {
			case 0:
				System.out.println("Exiting application.....Good Bye!");
				try {
					list.closeFile();
					ObjectOutputStream outputStream = new ObjectOutputStream(
							new FileOutputStream("pharmacyRecords.ser"));
					outputStream.writeObject(list);
					outputStream.close();
					System.exit(0);
				} catch (IOException ex) {
					System.out
							.println("Can not shut down properly. Conatact IT.");
				}
				break;
			// add a pharmaceutical company
			case 1:
				System.out.println("Add a pharmaceutical company.");
				System.out.println("Company name:");
				String name = input.nextLine();
				System.out.println("Company code:");
				String code = input.next();
				System.out.println("Phone number:");
				String number = input.next();
				input.nextLine();
				try {
					list.addCompany(code, name, number);
				} catch (DuplicateDataException e) {
					System.out
							.println("Company code or name is a duplicate. Please reenter company information.");
				} catch (IOException e) {
					System.out
							.println("Due to technichal difficulties with data file, can not add company records. Contact IT.");
				} catch (InvalidDataException ex) {
					System.out
							.println("Invalid data entered. Can not add company records.");
				}
				break;
			// remove a company
			case 2:
				System.out.println("Remove a company");
				System.out.println("Company Name:");
				String name1 = input.nextLine();
				System.out.println("Company code:");
				String code1 = input.next();
				input.nextLine();
				try {
					list.removeRecord(code1, name1);
				} catch (NotFoundException ex) {
					System.out
							.println("The requested company records are not on file.");
				}
				break;
			// modify company phone number
			case 3:
				System.out.println("Modify company phone number");
				System.out.println("Company code:");
				String code2 = input.next();
				input.nextLine();
				System.out.println("Phone number:");
				String number1 = input.next();
				input.nextLine();
				try {
					list.modifyCompanyPhone(code2, number1);
				} catch (NotFoundException ex) {
					System.out
							.println("The requested company records are not on file.");
				} catch (IOException e) {
					System.out
							.println("Due to technichal difficulties with data file, can not modify company records. Contact IT.");
				} catch (InvalidDataException ex) {
					System.out
							.println("Invalid data entered. Can not modify company records.");
				}
				break;
			// display company information by company name
			case 4:
				System.out.println("View company information");
				System.out.println("Company name:");
				String companyName = input.nextLine();
				try {
					System.out.println(list.findCompanyName(companyName));
				} catch (NotFoundException ex) {
					System.out
							.println("The requested company records are not on file.");
				} catch (IOException e) {
					System.out
							.println("Due to technichal difficulties with data file, can not display company records. Contact IT.");
				}
				break;
			// display company information by company code
			case 5:
				System.out.println("View company information");
				System.out.println("Company code:");
				String companyCode = input.nextLine();
				try {
					System.out.println(list.findCompanyCode(companyCode));
				} catch (NotFoundException ex) {
					System.out
							.println("The requested company records are not on file.");
				} catch (IOException e) {
					System.out
							.println("Due to technichal difficulties with data file, can not display company records. Contact IT.");
				}
				break;
			// List information about each company on file
			case 6:
				System.out
						.println("View information about each company on file");
				try {
					for (CompanyCodeIndex c : list.getCompanyCodes()) {
						if (c.getIsActive()) {
							System.out.println(new PharmaceuticalCo(list
									.getFile(), c.getLocation()));
						}
					}
				} catch (IOException e) {
					System.out
							.println("Due to technichal difficulties with data file, can not display company records. Contact IT.");
				}
				break;
			}
		}
	}

	private static int menu(Scanner input) {
		int choice = 0;
		do {
			System.out.println("\n1. Add a pharmaceutical company"
					+ "\n2. Remove a company"
					+ "\n3. Modify company phone number"
					+ "\n4. View company information by name"
					+ "\n5. View company information by code"
					+ "\n6. View information about each company on file"
					+ "\nEnter 0 to exit the application");
			choice = input.nextInt();
		} while (choice > 6);
		input.nextLine();
		return choice;
	}
}
