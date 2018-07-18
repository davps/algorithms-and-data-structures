package datastructures.linear;

/**
 * My implementation of the queue data structure
 * @author David Perez
 *
 * @param <T>
 */
public class Queue <T> {
	
	/**
	 * First item of the queue (the candidate for dequeue)
	 */
	private QueueNode<T> first;
	
	/**
	 * A reference to the last queued item 
	 */
	private QueueNode<T> last;
	
	/**
	 * The node of the queue
	 * @author David Perez
	 *
	 * @param <T>
	 */
	private static class QueueNode<T>{
		/**
		 * The data handled by the node
		 */
		private T data;
		
		/**
		 * A reference to the next node
		 */
		private QueueNode<T> next;
		
		/**
		 * Construct node from data
		 * @param data
		 */
		public QueueNode(final T data) {
			this.data = data;
		}
	}
	
	/**
	 * Returns true only if the queue is empty.
	 * @return
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Queue an item
	 * @param data
	 * @return
	 */
	public boolean queue(final T data) {
		if(data == null) {
			return false;
		}
		
		QueueNode<T> node = new QueueNode<T>(data);
		
		if(this.first == null) {
			this.first = node;
			this.last = node;
			return true;
		}		
		
		this.last.next = node;
		this.last = node;
		return true;
	}

	/**
	 * Dequeue an item
	 * @return
	 */
	public T dequeue() {
		if(this.first == null) {
			return null;
		}
		
		T item = this.first.data;
		this.first = this.first.next;
		
		if(this.first == null) {
			this.last = null;
		}
		return item;
	}

	/**
	 * Calculate the size of the queue
	 * @return
	 */
	public int size() {
		if(this.first == null) {
			return 0;
		}
		
		int i = 1;
		QueueNode<T> node = this.first;
		while(node.next != null) {
			i++;
			node = node.next;
		}
		
		return i;
	}
		
	public static void main(final String[] args) {
		final Queue<String> queue = new Queue<String>();
		
		expectTrue(queue.size() == 0, "size() returns 0 when the queue is empty");
		expectTrue(queue.dequeue() == null, "return null when queue is empty");
		expectTrue(!queue.queue(null), "Cannot queue null");
		
		final String item1 = "item1";
		final String item2 = "item2";
		final String item3 = "item3";
		expectTrue(queue.queue(item1), "queue first item");
		expectTrue(queue.queue(item2), "queue second item");
		queue.queue(item3);
		expectTrue(queue.size() == 3 && !queue.isEmpty(), "size() is 3 after 3 additions");
		queue.dequeue().equals(item3);
		queue.dequeue().equals(item2);
		queue.dequeue().equals(item1);
		expectTrue(queue.isEmpty(), "queue is empty");
		
	}

	private static void expectTrue(final boolean expectation, final String message) {
		if(expectation) {
			System.out.println("Success: " + message);
		}else {
			System.err.println("Error: " + message);
		}
	}


}
