package datastructures.linear;

public class Stack <T> {
	private static class StackNode<T>{
		private T data;
		private StackNode<T> next;
		
		public StackNode(T data) {
			this.data = data;
		}
	}
	
	private StackNode<T> top;
	
	private int size() {
		if(this.top == null) {
			return 0;
		}
		
		int i = 1;
		StackNode<T> node = this.top;
		while(node.next != null) {
			i++;
			node = node.next;
		}
		return i;
	}

	private boolean add(T data) {
		if(data == null) {
			return false;
		}
		
		StackNode<T> node = new StackNode<T>(data);
		
		if(this.top == null) {
			this.top = node;
		}
		
		node.next = this.top;
		this.top = node;
		return true;
	}

	private boolean remove(T data) {
		if(data == null) {
			return false;
		}
		
		if(this.top == null) {
			return false;
		}
		
		this.top = this.top.next;
		return true;
	}

	private boolean isEmpty() {
		return this.top == null;
	}

	private T peek() {
		if(this.top == null) {
			return null;
		}
		return this.top.data;
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
		if(expectation) {
			System.out.println("Success: " + message);
		}else {
			System.err.println("Error: " + message);
		}
	}


}
