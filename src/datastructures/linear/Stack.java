package datastructures.linear;

public class Stack <T> {
	
	private int size() {
		return 0;
	}

	private boolean add(String first) {
		return false;		
	}

	private boolean remove(String string) {
		return false;
	}

	private boolean isEmpty() {
		return false;
	}

	private T peek() {
		return null;
	}

	/**
	 * Tests
	 * @param args
	 */
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		
		assertTrue(stack.peek() == null, 
			"peek() return null when trying to retrieve the top of the queue but the queue is empty");

		assertTrue(stack.isEmpty(), "isEmpty() returns true when the stack is empty");
		assertTrue(!stack.remove("hello"), "remove() returns false when trying to remove from an empty stack");
				
		String first = "first";
		assertTrue(stack.add(first), "add() to stack");
		stack.add("second");
		String hi = "third, hi";
		stack.add(hi);

		assertTrue(!stack.isEmpty(), "isEmpty() returns false when stack is not empty");		
		assertTrue(!stack.remove(null) && stack.size() == 3, "remove() returns false when trying to remove null from an stack");
		
		
		assertTrue(stack.peek().equals(hi), 
				"peek() return "+hi+" when trying to retrieve the top of the queue");
		
		assertTrue(stack.remove(first), "remove() the bottom of the stack");
		assertTrue(stack.remove(hi), "remove() the top of the stack");

	}

	private static void assertTrue(boolean expectation, String message) {
		
	}


}
