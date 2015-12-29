package neustadt.billOrganizer;

import java.util.Comparator;

public class PriorityQueue<T extends Comparable<T>> {
private SortedLinkedList<T> list;
private Comparator<T> comparator;
BillCriteria criteria;

public PriorityQueue(BillCriteria criteria){
	this.criteria = criteria;
setComparator();	
this.list = new SortedLinkedList<T>();

}
	
public void enqueue (T data){
	list.insert(data, comparator);
}

public T dequeue(){
	return list.remove();
}

public T peek(){
	return list.peek();
}

public boolean remove(T data){
	return list.remove(data);
}

public String toString(){
	return list.toString();
}

private void setComparator(){
	switch(criteria){
	case BILLDUEDATE:
		comparator = (Comparator<T>) new BillDateComparator();
		break;
	case BILLAMOUNT:
		comparator = (Comparator<T>) new BillDateComparator();
		break;	
	case BILLTYPE:
		comparator = (Comparator<T>) new BillDateComparator();
		break;
	}
}

public BillCriteria getCriteria(){
	return criteria;
}
}

