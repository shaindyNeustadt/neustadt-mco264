package neustadt.binaryTree;

public class UseBTree {
//Tests the insertRecur, removeVal, get, balanceTreeRecur,  and balanceTree Methods
	public static void main(String[] args) {
		BalancedBinaryTree<String> myData= new BalancedBinaryTree<String>();
		myData.insertRecur("D");
		myData.insertRecur("H");
		myData.insertRecur("G");
		myData.insertRecur("F");
		myData.insertRecur("I");
		myData.insertRecur("A");
		myData.insertRecur("C");
		myData.insertRecur("X");
		System.out.println("Traverse the Tree:");
		myData.traverse();
		myData.removeVal("X");
		System.out.println("Traverse after deleting 'X'");
		myData.traverse();
		
		System.out.println("PRINT 'X' LETTER IF EXISTS");
		System.out.println(myData.get("X"));
		System.out.println("PRINT 'A' LETTER IF EXISTS");
		System.out.println(myData.get("A"));
		
		System.out.println("PreOrder Before Balance");
		myData.traversePreOrder();	
		
		myData.balanceTreeRecur();
		System.out.println("PreOrder After Balance Tree Recursive");
		myData.traversePreOrder();	
				
		myData = new BalancedBinaryTree<String>();
		myData.insertRecur("D");
		myData.insertRecur("H");
		myData.insertRecur("G");
		myData.insertRecur("F");
		myData.insertRecur("I");
		myData.insertRecur("A");
		myData.insertRecur("C");
		System.out.println("PreOrder Before Balance");
		myData.traversePreOrder();	
		
		myData.balanceTreeRecur();
		System.out.println("PreOrder After Balance Tree Iterative");
		myData.traversePreOrder();	
		
	}

}