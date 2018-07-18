package datastructures.linear;

/**
 * My linked list implementation
 * 
 * @author David Perez
 *
 * @param <T>
 */
public class LinkedList <T> {
	/**
	 * The head node of the linked list
	 */
	private LinkedListNode first;
	
	/**
	 * The linked list node
	 * @author david
	 *
	 */
	class LinkedListNode{
		
		/**
		 * A reference to the next node of the linked list
		 */
		LinkedListNode next;
		
		/**
		 * The node data
		 */
		T data;		
		
		LinkedListNode(final T data){
			this.data = data;
		}
	}
		
	/**
	 * Appends the specified element to the end of this list.
	 * @param data
	 */
	public boolean add(final T data) {
		if(data == null) {
			return false;
		}
		
		final LinkedListNode end = new LinkedListNode(data);

		if(this.first == null) {
			this.first = end;
			return true;
		}
		
		LinkedListNode node = this.first;
		while(node.next != null) {
			node = node.next;
		}
		node.next = end;
		return true;
	}
	
	/**
	 * Removes the first occurrence of the specified element 
	 * from this list, if it is present.
	 * @param data
	 * @return
	 */
	private boolean remove(final String data) {
		if(this.first == null) {
			return false;
		}
		
		if(this.first.data.equals(data)) {
			this.first = this.first.next;
			return true;
		}
		
		LinkedListNode node = this.first;
		while(node.next != null) {
			if(node.next.data.equals(data)) {
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
	private T peek() {
		if(this.first == null) {
			return null;
		}

		return this.first.data;
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
		

		LinkedListNode node = this.first;
		counter++;

		while(node.next != null) {
			counter++;
			node = node.next;
		}
		
		return counter;
	}

	/**
	 * Returns true if this linked list contains no elements.
	 * @return
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Main class. Used for testing.
	 * @param args
	 */
	public static void main(final String[] args) {
		
		/**
		 * Tests
		 */
		final LinkedList<String> list = new LinkedList<String>();
		
		expectTrue(list.isEmpty(), "List is empty");

		expectTrue(!list.remove("a random item"), "Try to remove an item when the list is empty");
		expectTrue(list.peek() == null, "Returns null from peek() when list is empty");
		expectTrue(!list.add(null), "Expect failure of null additions");
		
		final String hi = "Hello, world!";
		list.add(hi);
		
		expectEqual(list.peek(), hi, "Root to be " + hi);
		expectEqual(list.size(), 1, "and size 1");
		
		final String item = "one more item";
		list.add(item);
		list.add("test 3");
		expectTrue(list.size() == 3, "List have 3 items");
		
		expectTrue(list.remove(hi), "remove root");
		
		expectTrue(list.remove(list.peek()), "remove another item");
		expectEqual(list.size(), 1, "and now size is 1");
		
		list.add("new item");
		list.add(item);
		list.remove(item);
		expectEqual(list.size(), 2, "removal of non-first item");
		
		expectTrue(!list.isEmpty(), "List is not empty");
		
		expectTrue(!list.remove("random item"), "Try to remove an item that did not exist");
		
	}
	
	/**
	 * Testing helper
	 * 
	 * @param condition
	 * @param message
	 */
	private static void expectTrue(final boolean condition, final String message) {
		if(condition) {
			System.out.println("Success: " + message);
		}else {
			System.err.println("Error: " + message);
		}
	}

	/**
	 * Testing helper
	 * 
	 * @param expected
	 * @param toBe
	 * @param msg
	 */
	private static void expectEqual(final int expected, final int toBe, final String msg) {
		if(expected == toBe) {
			System.out.println("Success:" + msg);
		}else {
			System.err.println("Error:" + msg + ". Expected " + toBe + 
					" but got " + expected);
		}		
	}

	/**
	 * Testing helper
	 * 
	 * @param expected
	 * @param toBe
	 * @param msg
	 */
	private static void expectEqual(final String expected, final String toBe, final String msg) {
		if(expected.equals(toBe)) {
			System.out.println("Success:" + msg);
		}else {
			System.err.println("Error:" + msg + ". Expected " + toBe + 
					" but got " + expected);
		}
		
	}

}
