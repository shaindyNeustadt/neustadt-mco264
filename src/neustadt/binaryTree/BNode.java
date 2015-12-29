package neustadt.binaryTree;

import java.io.Serializable;

public class BNode<T extends Comparable<T>> implements Comparable<BNode<T>>,
		Serializable {
	private T data;
	private BNode<T> lc; // link to left child
	private BNode<T> rc; // link to right child

	// constructor
	public BNode() {
		data = null;
		lc = rc = null;
	}

	public BNode(T data) {
		this.data = data;
		lc = rc = null;
	}

	public BNode<T> getLC() {
		return lc;
	}

	public BNode<T> getRC() {
		return rc;
	}

	public T getData() {
		return data;
	}

	public void setLC(BNode<T> nextNode) {
		lc = nextNode;
	}

	public void setRC(BNode<T> nextNode) {
		rc = nextNode;
	}

	public void setData(T value) {
		data = value;
	}

	public int compareTo(BNode<T> aNode) {
		return data.compareTo(aNode.getData());
	}
}