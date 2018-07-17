package datastructures.linear;

public class LinkedList <T> {
	private Node first;
	
	class Node{
		Node next = null;
		String value;
		Node(String value){
			this.value = value;
		}
	}
		
	/**
	 * Appends the specified element to the end of this list.
	 * @param value
	 */
	public boolean add(String value) {
		if(value == null) {
			return false;
		}
		
		Node end = new Node(value);

		if(this.first == null) {
			this.first = end;
			return true;
		}
		
		Node node = this.first;
		while(node.next != null) {
			node = node.next;
		}
		node.next = end;
		return true;
	}
	
	/**
	 * Removes the first occurrence of the specified element from this list, if it is present.
	 * @param index
	 * @return
	 */
	private boolean remove(String value) {
		if(this.first == null) {
			return false;
		}
		
		if(this.first.value.equals(value)) {
			this.first = this.first.next;
			return true;
		}
		
		Node node = this.first;
		while(node.next != null) {
			if(node.next.value.equals(value)) {
				node.next = node.next.next;
				return true;
			}
			node = node.next;
		}
		
		return false;
	}

	/**
	 * Retrieves, but does not remove, the head (first element) of this list.
	 * @return
	 */
	private String peek() {
		if(this.first == null) {
			return null;
		}

		return this.first.value;
	}

	/**
	 * Returns the number of elements in this list.
	 * @return
	 */
	public int size() {
		int counter = 0;
		
		if(this.first == null) {
			return counter;
		}
		

		Node node = this.first;
		counter++;

		while(node.next != null) {
			counter++;
			node = node.next;
		}
		
		return counter;
	}

	private static void expectTrue(boolean condition, String message) {
		if(condition) {
			System.out.println("Success: " + message);
		}else {
			System.err.println("Error: " + message);
		}
	}

	public static void main(String[] args) {
		
		/**
		 * Aceptance tests
		 */
		LinkedList<String> list = new LinkedList<String>();
		
		expectTrue(list.size() == 0, "List is empty");

		String hi = "Hello, world!";
		list.add(hi);
		
		expectEqual(list.peek(), hi, "Root to be " + hi);
		expectEqual(list.size(), 1, "and size 1");
		
		String item = "one more item";
		list.add(item);
		list.add("test 3");
		expectTrue(list.size() == 3, "List have 3 items");
		
		expectTrue(list.remove(hi), "remove root");
		
		expectTrue(list.remove(list.peek()), "remove another item");
		expectEqual(list.size(), 1, "and now size is 1");
		
	}

	private static void expectEqual(int expected, int toBe, String msg) {
		if(expected == toBe) {
			System.out.println("Success:" + msg);
		}else {
			System.err.println("Error:" + msg + ". Expected " + toBe + 
					" but got " + expected);
		}		
	}

	private static void expectEqual(String expected, String toBe, String msg) {
		if(expected.equals(toBe)) {
			System.out.println("Success:" + msg);
		}else {
			System.err.println("Error:" + msg + ". Expected " + toBe + 
					" but got " + expected);
		}
		
	}

}
