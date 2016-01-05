package neustadt.billOrganizer;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import neustadt.linkedList.LinkedList;
import neustadt.linkedList.Node;
 

public class SortedLinkedList<T extends Serializable & Comparable<T>> extends LinkedList<T> implements Serializable{
/*
@Override
public void insert(T data){
	Iterator<T> iterator = super.iterator();
	Node<T> newNode = new Node<T>(data);
	Node<T> curr = head;
	if(curr == null){
		head = newNode;
		return;
	}
	while(iterator.hasNext()){
		if(curr.getData().compareTo(data) == 0){
		throw new DuplicateDataException();	
		}
		if(curr.getData().compareTo(data) > 0){
			iterator.insert(data);
			return;
		}
		else{
			curr = curr.getNext();
		}
	}
	curr.insert(data);
}
*/
	
@Override
public void insert(T data){
	Node<T> newNode = new Node<T>(data);
	Node<T> prevNode;
	Node<T> currNode = prevNode = head;
	if(head == null){
		head = newNode;
	}
	else{
	while(currNode != null && newNode.compareTo(currNode)>0){
		prevNode = currNode;
		currNode = currNode.getNext();
	}
	if(currNode == newNode){
		throw new DuplicateDataException();	
		}
		if(currNode == head){
			newNode.setNext(head);
			head = newNode;
		}
		else{
			prevNode.setNext(newNode);
			newNode.setNext(currNode);
		}
	}
}
	
/*public void insert(T data, Comparator<T> comparator){
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
*/

public void insert(T data, Comparator<T> comparator){
Node<T> newNode = new Node<T>(data);
Node<T> prevNode;
Node<T> currNode = prevNode = head;
if(head == null){
	head = newNode;
}
else{
while(currNode != null && comparator.compare(data, currNode.getData()) > 0){
	prevNode = currNode;
	currNode = currNode.getNext();
}
	//if(comparator.compare(data, head.getData()) == 0){
	if(currNode == head){
		newNode.setNext(head);
		head = newNode;
	}
	else{
		prevNode.setNext(newNode);
		newNode.setNext(currNode);
	}
}
}

public Bill find(int ID){
	Iterator<Bill> iter = (Iterator<Bill>) iterator();
	Bill curr = null;
	while(iter.hasNext()){
		curr = iter.next();
		if(curr.getID().compareTo(ID) == 0){
		return curr;	
		}
	}
	throw new NotFoundException();
}
}