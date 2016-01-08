package neustadt.billOrganizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class BillOrganizer {
	ArrayList<PriorityQueue<Bill>> queues;
	SortedLinkedList<Bill> list;

	//no args constructor
	public BillOrganizer() {
		this.queues = new ArrayList<PriorityQueue<Bill>>();
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLDUEDATE));
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLTYPE));
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLAMOUNT));

		list = new SortedLinkedList<Bill>();
	}

	//Constructor from Serializable file
	public BillOrganizer(String fileName) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		this();
		ObjectInputStream inputStream = new ObjectInputStream(
				new FileInputStream(fileName));
		list = (SortedLinkedList<Bill>) inputStream.readObject();
		inputStream.close();

		Iterator<Bill> iter = list.iterator();
		Bill bill = null;
		while (iter.hasNext()) {
			bill = iter.next();
			for(PriorityQueue<Bill> q: queues){
				q.enqueue(bill);
			}
		}
		Bill.setLastID(bill.getID());
	}

	//Constructor that reads in data from textFile
	public BillOrganizer(Scanner readFile) {
		this();
		Bill bill = null;
		while (readFile.hasNext()) {
			bill = new Bill(readFile);
			insert(bill);
		}
	}

	public void insert(Bill bill) {
		for (PriorityQueue<Bill> q : queues) {
			q.enqueue(bill);
		}
		list.insert(bill);
	}

	public Bill payNextBill(BillCriteria criteria) {
		Bill bill = null;
		for (PriorityQueue<Bill> q : queues) {
			if (q.getCriteria().compareTo(criteria) == 0) {
				bill = q.dequeue();
			}
		}
		for (PriorityQueue<Bill> q : queues) {
			if (q.getCriteria().compareTo(criteria) != 0) {
				q.remove(bill);
			}
		}
		list.remove(bill);
		return bill;
	}

	public Bill removeBillByID(int ID) {
		Iterator<Bill> iter = list.iterator();
		while(iter.hasNext()){
		Bill bill = iter.next();
		if(bill.getID() == ID){
			list.remove(bill);
			for (PriorityQueue<Bill> q : queues) {
				q.remove(bill);
				}
			return bill;
		}
		}
		throw new NotFoundException();
		
	}

	public void closeOrganizer() throws FileNotFoundException, IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(
				new FileOutputStream("bills.ser"));
		outputStream.writeObject(list);
		outputStream.close();
	}

	public Double totalBills() {
		Double sum = 0.0;
		Iterator<Bill> iter = list.iterator();
		while (iter.hasNext()) {
			sum += iter.next().getAmountDue();
		}
		return sum;
	}
	
	public PriorityQueue<Bill> getQueue(int index) {
		return queues.get(index);
	}

	public Iterator<Bill> iteratorByDate() {
		return queues.get(0).getIterator();
	}

	public Iterator<Bill> iteratorByAmount() {
		return queues.get(2).getIterator();
	}

	public Iterator<Bill> iteratorByType() {
		return queues.get(1).getIterator();
	}
}