package datastructures.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Vertex of the Graph
 * 
 * Internally I use a HashMap to store the values, but it is encapsulated
 * complaining to the Law of Demeter, so I can change the usage of the
 * HashMap for another kind of collection if required, without changing
 * the code of the other classes.
 * 
 * @author David Perez
 */
class Node {
	
	/**
	 * The value contained by the graph
	 */
	private final int value;
	
	/**
	 * Mark used to know if a node was already visited
	 * during the search() 
	 */
	private transient boolean visited;
	
	/**
	 * Adjacent vertexs using map to avoid duplicated links.
	 */
	private final Map<Integer, Node> adjacents = new HashMap<Integer, Node>();

	/**
	 * Add one or more adjacent nodes to the actual node.
	 * @param nodes
	 */
	public void addAdjacents(Node...nodes) {
		for(final Node node: nodes) {
			if(!adjacents.containsKey(node.getValue())) {
				adjacents.put(node.getValue(), node);
			}
		}
	}

	/**
	 * Get the adjacent nodes for this node instance
	 * @return
	 */
	public Collection<Node> getAdjacents(){
		return this.adjacents.values();
	}
	
	/**
	 * Get the value of the node
	 * @return
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Mark the node as visited
	 * @return
	 */
	public Node visit() {
		this.visited = true;
		return this;
	}
	
	/**
	 * Ask if the node was already visited
	 * @return
	 */
	public boolean hasVisitedMark() {
		return this.visited;
	}
	
	/**
	 * Construct the node instance with an initial value.
	 * @param value
	 */
	public Node(final int value) {
		this.value = value;
	}
			
}

/**
 * Auxiliary class used as node of the queue.
 * @author david
 *
 */
class QueueNode{
	
	/**
	 * Link to the next node of the queue
	 */
	public transient QueueNode next;
	
	/**
	 * Value of the actual node
	 */
	public transient final Node value;
	
	/**
	 * Construct the instance with an initial value
	 * @param value
	 */
	public QueueNode(final Node value) {
		this.value = value;
	}
}

/**
 * Queue of {@code Node} instances.
 * @author david
 * TODO test the queue
 */
class Queue{
	/**
	 * First element of the queue (FIFO)
	 */
	private transient QueueNode first;
	
	/**
	 * Last element of the queue (FIFO)
	 */
	private transient QueueNode last;
	
	/**
	 * Ask if the queue is empty or not
	 * @return true is the queue is empty, otherwise, return false
	 */
	public boolean isEmpty() {
		return this.first == null;
	}
	
	/**
	 * Insert a {@code Node} to the queue.
	 * @param value
	 */
	public void enqueue(final Node value) {
		final QueueNode node = new QueueNode(value);
		if(this.first == null) {
			this.first = node;
			this.last = first;
			return;
		}
		
		last.next = node;
		last = last.next;
	}
	
	/**
	 * Remove the first item of queue (FIFO) and return it.
	 * @return
	 */
	public Node dequeue() {
		if(this.first == null) {
			return null;
		}
		
		final QueueNode node = this.first;
		this.first = this.first.next;
		
		if(this.first == null) {
			this.last = null;
			return null;
		}
		
		return node.value;
	}
}

public class BFS {
	

	/**
	 * Traverse the graph
	 * @param base
	 * @return The order of the traverse
	 */
	public String search(final Node base) {
		return null;
	}

	
	/**
	 * Test cases
	 * @param args
	 */
	public static void main(String[] args) {
		/*		
			digraph{
			 0->"1 visited=true"
			 0->"2 visited=false"
			 "2 visited=false"->"1 visited=true"
			 "1 visited=true"->null;
		}		
		*/	
		final Node n0 = new Node(0);
		final Node n1 = new Node(1);
		final Node n2 = new Node(2);
		n0.addAdjacents(n1);
		n0.addAdjacents(n2);
		n2.addAdjacents(n1);
		
		final BFS bfs = new BFS();
		final String result = bfs.search(n0);
		final String msg1 = "0->1->2->";
		assertEqual(result, msg1, "BFS " + msg1);
		
		/*
			digraph{
				 0;1;2;3;4;5;
				//visit first children of root node (0)
				0->1 
				//visiting first child (1) of (0)
				1->3
				//visiting first child (3) of (1)
				3->2
				2->1 //skip the visited child (1) 
				3->4 //node (4) have no links, so, end and go up (to parent)
				1->4 //skip visited child (4)
				0->4 //skip visited child (4)
				0->5
			}	
		 */
		final Node x0 = new Node(0);
		final Node x1 = new Node(1);
		final Node x2 = new Node(2);
		final Node x3 = new Node(3);
		final Node x4 = new Node(4);
		final Node x5 = new Node(5);
		x0.addAdjacents(x1, x4, x5);
		x1.addAdjacents(x3, x4);
		x2.addAdjacents(x1);
		x3.addAdjacents(x2);
		final String result2 = bfs.search(x0);
		final String msg2 = "0->1->4->5->3->2->";
		assertEqual(result2, msg1, "BFS " + msg2);
	}

	private static void assertEqual(final String result, final String expect, final String msg) {
		if(result.equals(expect)) {
			System.out.println("Success: "+msg);
		}else {
			System.err.println("Failure: "+msg+". Expected "+expect+" but got "+ result);
		}
	}

}
