package neustadt.pharmacy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class PharmaceuticalCo {

	private String companyCode;
	private String companyName;
	private String phoneNumber;

	public PharmaceuticalCo(String companyCode, String name, String phoneNumber) {
		if (companyCode == null || companyCode.equals("") || name == null
				|| name.equals("") || phoneNumber.length() != 10)
			throw new InvalidDataException();
		this.companyCode = companyCode;
		this.companyName = name;
		this.phoneNumber = phoneNumber;
	}

	public PharmaceuticalCo(Scanner readFile) throws FileNotFoundException {
		this.companyCode = readFile.next();
		this.phoneNumber = readFile.next();
		this.companyName = readFile.nextLine().trim();
	}

	public PharmaceuticalCo(RandomAccessFile raFile, Long location)
			throws IOException {
		raFile.seek(location);
		this.companyCode = raFile.readUTF().trim();
		this.phoneNumber = raFile.readUTF();
		this.companyName = raFile.readUTF().trim();
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setPhoneNumber(String newNumber) {
		if (newNumber.length() != 10)
			throw new InvalidDataException();
		this.phoneNumber = newNumber;
	}

	public void writeToFile(RandomAccessFile raFile, Long location)
			throws IOException {
		raFile.seek(location);
		raFile.writeUTF(String.format("%-4s", this.companyCode));
		raFile.writeUTF(String.format("%-10s", this.phoneNumber));
		raFile.writeUTF(String.format("%-25s", this.companyName));
	}

	public int compareTo(PharmaceuticalCo pc) {
		return this.companyCode.compareToIgnoreCase(pc.getCompanyCode());
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o instanceof PharmaceuticalCo) {
			PharmaceuticalCo pc = (PharmaceuticalCo) o;
			return (this.companyCode.compareTo(pc.companyCode) == 0);
		} else
			return false;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nCompany Name: " + companyName);
		builder.append(" Company Code: " + companyCode);
		builder.append(" Phone Number: " + phoneNumber);
		return builder.toString();
	}
}