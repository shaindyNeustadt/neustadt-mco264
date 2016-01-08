package neustadt.billOrganizer;

import java.io.Serializable;
import java.util.Comparator;
import neustadt.linkedList.LinkedList;
import neustadt.linkedList.Node;
 

public class SortedLinkedList<T extends Serializable & Comparable<T>> extends LinkedList<T> implements Serializable{
	
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
	if(currNode!= null && currNode.compareTo(newNode) == 0){
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

public T find(T data){
	Node<T> curr = head;
	while(curr != null){
		if(curr.getData().compareTo(data) == 0){
			return curr.getData();	
			}
		curr = curr.getNext();
	}
	throw new NotFoundException();
}
}