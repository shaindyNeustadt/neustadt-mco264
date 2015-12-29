package neustadt.binaryTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> {
	private BNode<T> root;
	private boolean found; // used by remove methods

	public BinaryTree() {
		root = null; // empty tree
	}

	public boolean insert(T data) {
		BNode<T> curr = root;
		BNode<T> parent = root;
		BNode<T> newNode;
		// iterative approach to inserting data
		if (root == null) {
			// nothing in tree yet
			root = new BNode(data);

		} else {
			while (curr != null) {
				if (data.compareTo(curr.getData()) < 0) {
					// data is less than data in current Node
					// go down left branch
					parent = curr;
					curr = curr.getLC();

				} else if (data.compareTo(curr.getData()) > 0) {
					// data is greater than data in current Node
					// go down right branch
					parent = curr;
					curr = curr.getRC();

				} else
					return false; // duplicate value

			}
			// found the right place
			newNode = new BNode<T>(data);
			// should it be a left child or a right child?
			if (newNode.compareTo(parent) < 0) {
				parent.setLC(newNode);

			} else
				parent.setRC(newNode);

		}
		return true;

	}

	public void traverse() {
		// using iterative approach

		// iterative inOrder traversal of the BST – notice how much more
		// complicated it is
		// we maintain our own Stack in order to be able to retrace our steps
		Stack<BNode<T>> stack = new Stack<BNode<T>>();
		BNode<T> tree; // sub tree of the binary tree
		if (root == null)
			System.out.println("\n tree is empty");
		tree = root;
		stack.push(tree); // put root on the stack
		// System.out.println( "\npushing " + tree.getData() +
		// "onto the stack");
		while (tree != null) { // go down the left branch,push the node on to
								// the stack to be revisited
			tree = tree.getLC();
			while (tree != null) {
				stack.push(tree);
				// System.out.println( "\npushing " + tree.getData() +
				// "onto the stack");
				tree = tree.getLC();
			}
			// made it all the way down the leftmost branch
			// pop the last node off the stack
			if (!stack.empty()) {
				tree = stack.pop();
				// System.out.println("\npopped " + tree.getData() +
				// "off the stack");
				System.out.println(tree.getData()); // display the value at the
													// current node

			}

			tree = tree.getRC(); // now start down the right branch of that node
			if (tree != null)
				stack.push(tree); // has a right branch so push it on the stack
			else { // we are done processing this node, took care of left and
					// right branches

				while (!stack.empty() && tree == null) {
					tree = stack.pop(); // get next node to complete processing
					System.out.println(tree.getData()); // complete left branch,
														// now write out the
														// data
					// System.out.println( "\npopped " + tree.getData() +
					// "off the stack");
					tree = tree.getRC(); // visit the right branch of this node

				}
				if (tree != null)
					stack.push(tree); // push the right branch node onto the
										// stack
			}

		} // end outer while
	}

	// recursive insert method
	public boolean insertRecur(T data) {
		BNode<T> tree = root;
		if (root == null) {
			root = new BNode<T>(data);
			return true;
		} else {
			return insertTryAgain(tree, data);
		}
	}

	private boolean insertTryAgain(BNode<T> root, T data) {
		if (data.compareTo(root.getData()) < 0) {
			// this data value belongs in left branch
			if (root.getLC() == null) {
				// left child is empty, insert data right there
				root.setLC(new BNode<T>(data));
				return true;
			} else {// find next available spot along left branch
				return insertTryAgain(root.getLC(), data);

			}
		} else

		if (data.compareTo(root.getData()) > 0) {
			// this data value belongs in right branch
			if (root.getRC() == null) {
				root.setRC(new BNode<T>(data));
				return true;

			} else { // find next available spot along right branch
				return insertTryAgain(root.getRC(), data);

			}

		}

		else
			return false; // duplicate value

	}

	public boolean removeVal(T value) {
		// to remove a value must start searching for it at the root
		root = removeNode(value, root);
		return found; // return value in instance variable set by private method
	}

	private BNode<T> removeNode(T value, BNode<T> tree) {
		// looks for value in the subtree
		if (tree == null)
			found = false;
		else if (value.compareTo(tree.getData()) < 0)
			// recursive call further down the left side of tree
			// might have to reset links if a node further down
			// is set to null
			tree.setLC(removeNode(value, tree.getLC()));
		else if (value.compareTo(tree.getData()) > 0)
			// recursive call further down the right side of the tree
			// might have to reset links if a node further down
			// is set to null
			tree.setRC(removeNode(value, tree.getRC()));
		else { // found the value , now remove that data from
				// the tree
			tree = removeData(tree);
			found = true;

		}
		return tree;
	}

	private BNode<T> removeData(BNode<T> tree) {
		// case 1 and 2: subtree just has one child branch so return that
		// branch and link that branch to previous
		// part of tree, basically eliminating the BNode
		// in which the data was found
		if (tree.getLC() == null)
			return tree.getRC();
		else if (tree.getRC() == null)
			return tree.getLC();
		else { // data is in a BNode that has two children.
				// It is too complicated to remove this type of Node
				// Instead do the following:
				// a. Replace the data in that BNode with data that
				// logically precedes that data - this data will be found in
				// a leaf BNode
				// b. eliminate the leaf BNode by reinvoking the
				// removeNode() method
			T data = findPredecessor(tree.getLC());
			tree.setData(data);
			tree.setLC(removeNode(data, tree.getLC()));
			return tree;

		}

	}

	private T findPredecessor(BNode<T> tree) {
		// the Node that contains data that precedes a Node
		// can be found by going down till one hits the right most leaf
		// of its left branch
		while (tree.getRC() != null) {
			tree = tree.getRC();

		}
		return tree.getData();
	}

	// recursive traversals
	public void traversePreOrder() {
		System.out.println(root.getData());
		traverseP(root.getLC());
		traverseP(root.getRC());

	}

	private void traverseP(BNode<T> root) {
		if (root == null)
			return; // anchor case
		System.out.println(root.getData());
		traverseP(root.getLC());
		traverseP(root.getRC());

	}

	public ArrayList<T> traverseInOrder() {
		ArrayList<T> listInOrder = new ArrayList<T>();
		traverseI(root.getLC(), listInOrder);
		listInOrder.add(root.getData());
		System.out.println(root.getData());
		traverseI(root.getRC(), listInOrder);
		return listInOrder;
	}

	private void traverseI(BNode<T> root, ArrayList<T> listInOrder) {
		if (root == null)
			return; // anchor case
		traverseI(root.getLC(), listInOrder);
		listInOrder.add(root.getData());
		System.out.println(root.getData());
		traverseI(root.getRC(), listInOrder);
	}

	// if found, returns the data of the element, otherwise returns null
	public T get(T data) {
		return getElement(data, root);
	}

	private T getElement(T data, BNode<T> tree) {
		if (tree == null) {
			return null;
		}
		if (data.compareTo(tree.getData()) < 0) {
			return getElement(data, tree.getLC());
		} else if (data.compareTo(tree.getData()) > 0) {
			return getElement(data, tree.getRC());
		} else
			return tree.getData();
	}

	public void balanceTree() {
		ArrayList<T> traversedTree = traverseInOrder();
		int index = (traversedTree.size() - 1) / 2;
		T midValue = traversedTree.get(index);
		root = new BNode<T>(midValue);

		balanceT(index, traversedTree);

		/*
		 * System.out.println("MID: " + mid); insert(traversedTree.get(mid));
		 * System.out.println("Remove: " + (mid)); insert(traversedTree.get(mid
		 * + index + 1)); System.out.println("Remove: " + (mid + index + 1));
		 */
	}

	private void balanceT(int index, ArrayList<T> traversedTree) {
		HashSet<Integer> used = new HashSet<Integer>();
		used.add(index);

		int mid = index;
		while (mid > 0) {
			mid /= 2;
			int temp = mid;
			int increment = mid + 1;
			do {
				if (!used.contains(temp)) {
					System.out.println("MID: " + temp);
					insert(traversedTree.get(temp));
					used.add(temp);
				}
				temp += increment;
			} while (temp < traversedTree.size());
		}
	}
}