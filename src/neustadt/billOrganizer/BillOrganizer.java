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

import neustadt.linkedList.LinkedListIterator;
import neustadt.linkedList.Node;

public class BillOrganizer {
	ArrayList<PriorityQueue<Bill>> queues;
	SortedLinkedList<Bill> list;

	public BillOrganizer() {
		this.queues = new ArrayList<PriorityQueue<Bill>>();
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLDUEDATE));
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLTYPE));
		queues.add(new PriorityQueue<Bill>(BillCriteria.BILLAMOUNT));

		list = new SortedLinkedList<Bill>();
	}

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
			// for (Bill b : list) {
			bill = iter.next();
			for (PriorityQueue<Bill> q : queues) {
				q.enqueue(bill);
				// }
			}

		}
		Bill.setLastID(bill.getID());
	}

	public BillOrganizer(Scanner readFile) {
		this();
		Bill bill = null;
		while (readFile.hasNext()) {
			bill = new Bill(readFile);
			insert(bill);
		}
		Bill.setLastID(bill.getID());
	}

	public void insert(Bill bill) {
		for (PriorityQueue<Bill> q : queues) {
			q.enqueue(bill);
		}
		list.insert(bill);
	}

	public Bill payNextBill(BillCriteria criteria) {
		// Comparator<Bill> comparator = null;
		Bill bill = null;
		/*
		 * switch(criteria){ case BILLDUEDATE: comparator = new
		 * BillDateComparator(); break; case BILLAMOUNT: comparator = new
		 * BillDateComparator(); break; case BILLTYPE: comparator = new
		 * BillDateComparator(); break; }
		 */
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
		Bill bill = list.find(ID);
		list.remove(bill);
		for (PriorityQueue<Bill> q : queues) {
			q.remove(bill);
		}
		return bill;
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

	public String toString() {
		return list.toString();
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