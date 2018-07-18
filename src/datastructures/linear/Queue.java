package datastructures.linear;

public class Queue <T> {
	
	public static void main(String[] args) {
		Queue<String> queue = new Queue<String>();
		
		expectTrue(queue.size() == 0, "size() returns 0 when the queue is empty");
		expect(queue.dequeue() == null, "return null when queue is empty");
		
		String item1 = "item1";
		String item2 = "item2";
		String item3 = "item3";
		queue.queue(item1);
		queue.queue(item2);
		queue.queue(item3);
		expectTrue(queue.size() == 3 && !queue.isEmpty(), "size() is 3 after 3 additions");
		queue.dequeue().equals(item3);
		queue.dequeue().equals(item2);
		queue.dequeue().equals(item1);
		expectTrue(queue.isEmpty(), "queue is empty");
		
	}
	
}
