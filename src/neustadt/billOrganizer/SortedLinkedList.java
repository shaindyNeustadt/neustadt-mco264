package neustadt.billOrganizer;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class SortedLinkedList<T extends Comparable<T>> extends LinkedList<T> implements Serializable{

public boolean insert(T data){
	Iterator<T> it = super.iterator();
	T curr = null;
	int index = -1;
	while(it.hasNext()){
		index++;
		curr = it.next();
		if(curr.compareTo(data) == 0){
		throw new DuplicateDataException();	
		}
		if(curr.compareTo(data) > 0){
			super.add(index, data);
			return true;
		}
	}
	super.addLast(data);
	return true;
}

public void insert(T data, Comparator<T> comparator){
	Iterator<T> it = super.iterator();
	T curr = null;
	int index = -1;
	while(it.hasNext()){
		index++;
		curr = it.next();
		if(comparator.compare(curr, data) > 0){
			super.add(index, data);
			return;
		}
	}
	super.addLast(data);
	}

public int find(T data){
	Iterator<T> it = super.iterator();
	T curr = null;
	int index = -1;
	while(it.hasNext()){
		index++;
		curr = it.next();
		if(curr.compareTo(data) == 0){
		return index;	
		}
		if(curr.compareTo(data) > 0){
			throw new NotFoundException();
		}
	}
	throw new NotFoundException();
}

public int find(int ID){
	Iterator<Bill> it = (Iterator<Bill>) super.iterator();
	Bill curr = null;
	int index = -1;
	while(it.hasNext()){
		index++;
		curr = it.next();
		if(curr.getID().compareTo(ID) == 0){
		return index;	
		}
		//if(curr.getID().compareTo(ID) > 0){
		//	return -1;
		//}
	}
	throw new NotFoundException();
}
}