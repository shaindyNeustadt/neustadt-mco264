package neustadt.billOrganizer;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

import neustadt.linkedList.ListEmptyException;
import neustadt.linkedList.NotFoundException;

public class PriorityQueue<T extends Comparable<T> & Serializable> {
	private SortedLinkedList<T> list;
	private Comparator<T> comparator;
	private BillCriteria criteria;

	public PriorityQueue(BillCriteria criteria) {
		this.criteria = criteria;
		setComparator();
		this.list = new SortedLinkedList<T>();
	}

	public void enqueue(T data) {
		list.insert(data, comparator);
	}

	public T dequeue() throws ListEmptyException, NotFoundException {
		T data = list.getFirst().getData();
		list.remove(data);
		return data;
	}

	public T peek() {
		return list.getFirst().getData();
	}

	public void remove(T data) throws ListEmptyException, NotFoundException {
		list.remove(data);
	}

	public String toString() {
		return list.toString();
	}

	private void setComparator() {
		switch (criteria) {
		case BILLDUEDATE:
			comparator = (Comparator<T>) new BillDateComparator();
			break;
		case BILLAMOUNT:
			comparator = (Comparator<T>) new BillAmountComparator();
			break;
		case BILLTYPE:
			comparator = (Comparator<T>) new BillTypeComparator();
			break;
		}
	}

	public Iterator<T> getIterator(){
		return list.iterator();
	}
	public BillCriteria getCriteria() {
		return criteria;
	}
}
