package neustadt.linkedList;

import java.io.Serializable;
import java.util.Iterator;

import neustadt.billOrganizer.Bill;



public class LinkedListIterator <T extends Serializable & Comparable<T>> implements Iterator<T>, Serializable{
//implements Iterator<T>{
	private Node<T> ip; //reference to last Node referenced as iterator iterated through the list
	private Node<T> h; //reference to head of list
	
	public LinkedListIterator(Node<T> head){
		//when first instantiated both point to the head
		//head is the head of the LinkedList for which this
		//Iterator is being created
		ip=  head;
		this.h = head;
	}

	
	public void reset(){
		//start again from beginning of the list
		ip =h;
	}
	
	public boolean hasNext(){
		//assumes the LinkedList head will reference data
		if (ip != null) return true;
		else return false;
		
	}
	
	public T next(){
		T temp = ip.getData();
		ip = ip.getNext(); //move further along in the list
		return temp;  //return data the current Node is referencing
	}
	
	public void set(T item){
		ip.setData(item);
	}
	
	}