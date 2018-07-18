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
	
	/**
	 * Returns the number of items in the stack.
	 * @return
	 */
	public int size() {
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

	/**
	 * Pushes an item onto the top of this stack. 
	 * @param data
	 * @return
	 */
	public boolean push(T data) {
		if(data == null) {
			return false;
		}
		
		StackNode<T> node = new StackNode<T>(data);
		
		if(this.top == null) {
			this.top = node;
			return true;
		}
		
		node.next = this.top;
		this.top = node;
		return true;
	}

	/**
	 * Removes the object at the top of this stack and returns 
	 * that object as the value of this function.
	 * 
	 * @param data
	 * @return
	 */
	public T pop() {
		if(this.top == null) {
			return null;
		}
		
		T item = this.top.data;
		this.top = this.top.next;
		return item;
	}

	/**
	 * Tests if this stack is empty.
	 * @return
	 */
	public boolean isEmpty() {
		return this.top == null;
	}

	/**
	 * Looks at the object at the top of this stack without 
	 * removing it from the stack.
	 * @return
	 */
	public T peek() {
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
		
		assertTrue(stack.size() == 0, "start with size=0");
		assertTrue(!stack.push(null), "Did not acept null additions");
		
		assertTrue(stack.peek() == null, 
			"peek() return null when trying to retrieve the top of the queue but the queue is empty");

		assertTrue(stack.isEmpty(), "isEmpty() returns true when the stack is empty");
		assertTrue(!(stack.pop() == null), "pop() returns null when trying to remove from an empty stack");
				
		String first = "first";
		assertTrue(stack.push(first), "add() to stack");
		assertTrue(stack.size() == 1, "now size is 1");
		String second = "second";
		stack.push(second);
		String hi = "third, hi";
		stack.push(hi);
		assertTrue(stack.size() == 3, "now size is 3");

		assertTrue(!stack.isEmpty(), "isEmpty() returns false when stack is not empty");		
		
		
		assertTrue(stack.peek().equals(hi), 
				"peek() return "+hi+" when trying to retrieve the top of the queue");
		
		assertTrue(stack.pop().equals(hi), "pop() the top of the stack");
		assertTrue(stack.pop().equals(second), "pop() the top of the stack");

	}

	private static void assertTrue(boolean expectation, String message) {
		if(expectation) {
			System.out.println("Success: " + message);
		}else {
			System.err.println("Error: " + message);
		}
	}


}
