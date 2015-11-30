package neustadt.doubleLinkedList;

public class UseDoubleLinkedList {

	// Created to test the DoubleLinkedList class and ReverseIterator methods

	public static void main(String[] args) throws NotFoundException {
		DoubleLinkedList<Integer> dll = new DoubleLinkedList<Integer>();
		dll.insert(5);
		dll.insert(10);
		dll.insert(2);
		dll.insert(15);
		dll.insert(2);
		dll.insert(25);
		dll.insert(30);
		dll.insert(25);
		dll.insert(20);
		dll.insert(1);
		dll.remove(15);
		dll.remove(2);
		dll.remove(1);
		
		System.out.println(dll.find(5));
		
		try{
			System.out.println(dll.find(1));
		}
		catch(NotFoundException ex){
			System.out.println("Not found");
		}
		try{
			dll.remove(14);
		}
		catch(NotFoundException ex){
			System.out.println("Not found");
		}
		ReverseIterator<Integer> ri = dll.iterator();
		while (ri.hasNext()) {
			System.out.print(ri.next() + " ");
		}
	}
}
