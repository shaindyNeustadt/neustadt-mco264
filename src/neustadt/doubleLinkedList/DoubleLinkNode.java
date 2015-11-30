package neustadt.doubleLinkedList;

import java.io.Serializable;

public class DoubleLinkNode<T extends Serializable & Comparable<T> >
   implements Serializable
{
    
	protected T data;
    protected DoubleLinkNode<T> prev;
    protected DoubleLinkNode<T> next;
    
   public DoubleLinkNode(){
       data = null; prev = null; next = null;
       }
    
    public DoubleLinkNode(T value){
    	data = value;
    	prev = null;
    	next = null;
    }
    
    public void setPrev(DoubleLinkNode<T> aNode){
    	prev = aNode;
    }
    
    public DoubleLinkNode<T> getPrev(){
    	return prev;
    }
    
    public void setNext(DoubleLinkNode<T> aNode){
    	next = aNode;
    }
    
    public DoubleLinkNode<T> getNext(){
    	return next;
    }
    
    public T getData(){
    	return data;
    }
    public int compareTo(T data){
    	return this.data.compareTo(data);
    }
}