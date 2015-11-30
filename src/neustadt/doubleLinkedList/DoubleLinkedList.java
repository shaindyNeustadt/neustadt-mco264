package neustadt.doubleLinkedList;

import java.io.Serializable;

public class DoubleLinkedList<T extends Serializable & Comparable<T>> {

	private DoubleLinkNode<T> head;
	private DoubleLinkNode<T> tail;

	public DoubleLinkedList() {
		this.head = null;
		this.tail = null;
	}

	public void insert(T data) {
		DoubleLinkNode<T> newNode = new DoubleLinkNode(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else if (head.getData().compareTo(data) >= 0) {
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		} else {
			DoubleLinkNode<T> prevNode = null;
			DoubleLinkNode<T> currNode = head;
			while (currNode != null) {
				if (currNode.getData().compareTo(data) < 0) {
					prevNode = currNode;
					currNode = currNode.getNext();
				} else {
					prevNode.setNext(newNode);
					newNode.setPrev(prevNode);
					newNode.setNext(currNode);
					currNode.setPrev(newNode);
					return;
				}
			}
			prevNode.setNext(newNode);
			newNode.setPrev(prevNode);
			tail = newNode;
		}
	}
	
	public T find(T data) throws NotFoundException{
		DoubleLinkNode<T> currNode = head;
		while(currNode!= null){
			if(currNode.getData().equals(data)){
				return currNode.getData();
			}
			if(currNode.getData().compareTo(data)> 0){
				break;
			}
			currNode = currNode.getNext();
		}
		throw new NotFoundException();
	}
	
	public void remove(T data) throws NotFoundException{
		DoubleLinkNode<T> currNode = head;
		DoubleLinkNode<T> prevNode = head;
		while(currNode!= null){
			if(currNode.getData().equals(data)){
				if(currNode == head){
					head = head.getNext();
					head.setPrev(null);
					return;
				}
				else if(currNode == tail){
					tail = prevNode;
					return;
				}
				else{
					prevNode.setNext(currNode.getNext());
					currNode.getNext().setPrev(prevNode);
					return;
				}
			}
			else{
			prevNode = currNode;
			currNode = currNode.getNext();
			}
			}
		throw new NotFoundException();
	}

	public ReverseIterator<T> iterator() {
		return new ReverseIterator<T>(tail);
	}
}