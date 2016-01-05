package neustadt.billOrganizer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Bill implements Comparable<Bill>, Serializable{
	static int lastID = 0;
	
	private Integer billID;
	private String vendorName;
	private Double amountDue;
	private GregorianCalendar dateDue;
	private BillType billType;
	
	public Bill(Scanner readFile){
		this.vendorName = readFile.next();
		this.amountDue = readFile.nextDouble();
		this.dateDue = validateDate(readFile.next());
		this.billType = ValidateBillType(readFile.next());
		this.billID = ++lastID;
	}
	
	public Bill(String vendorName, Double amountDue, String dateDue, String billType){
		if(vendorName == null || vendorName.equals("") || amountDue == null || amountDue <= 0) throw new InvalidDataException();
		this.vendorName = vendorName;
		this.amountDue = amountDue;
		this.dateDue = validateDate(dateDue);
		this.billType = ValidateBillType(billType);
		this.billID = ++lastID;
	}
	
	//when read in from Serializable file, to reset the lastID
	public static void setLastID(int id){
		lastID = id;
	}

	@Override
	public int compareTo(Bill bill) {
		return this.billID.compareTo(bill.billID);
	}
	
	public String getVendorName() {
		return vendorName;
	}

	public Double getAmountDue() {
		return amountDue;
	}

	public GregorianCalendar getDateDue() {
		return dateDue;
	}

	public BillType getBillType() {
		return billType;
	}
	public Integer getID(){
		return billID;
	}
	
	private GregorianCalendar validateDate(String date){
		String[] dateArray = date.split("/");
		if(dateArray.length != 3){
			throw new InvalidDataException();
		}
		int year = Integer.parseInt(dateArray[2]);
		int month = Integer.parseInt(dateArray[0]) -1;
		int day = Integer.parseInt(dateArray[1]);
		return new GregorianCalendar(year, month, day);
	}
	
	private BillType ValidateBillType(String billType){
		for(BillType b: BillType.values()){
			if(billType.equalsIgnoreCase(b.toString())){
				return b;
			}
		}
		throw new InvalidDataException();
	}
	
	public String toString(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		StringBuilder billInfo = new StringBuilder();
		billInfo.append("\nBill ID: " + billID);
		billInfo.append(" Vendor Name: " + vendorName);
		billInfo.append(" Amount Due: " + amountDue);
		billInfo.append(" Date: " + dateFormat.format(dateDue.getTime()));
		billInfo.append(" Bill Type: " + billType);
		return billInfo.toString();
	}
}
