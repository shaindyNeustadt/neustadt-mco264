package neustadt.doubleLinkedList;

import java.io.Serializable;
import java.util.Iterator;

public class ReverseIterator<T extends Serializable & Comparable<T> > implements Iterator<T> {
private DoubleLinkNode<T> currNode;
private DoubleLinkNode<T> head;


public ReverseIterator(DoubleLinkNode<T> head){
	this.head = head;
	this.currNode = head;
}

public void reset(){
	head = null;
	currNode = head;
}

@Override
public boolean hasNext() {
	return (currNode != null);
}

@Override
public T next() {
	T temp = currNode.getData();
	currNode = currNode.getPrev();
	return temp;
}
}