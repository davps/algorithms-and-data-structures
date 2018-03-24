package datastructures.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Graph node
 * @author David Perez
 *
 */
class DFSNode {
	/**
	 * Value of the node
	 */
	private final int value;
	
	/**
	 * Adjacent nodes
	 * I use a hash map to avoid duplicated inserts
	 */
	private final Map<Integer, DFSNode> adjacents = new HashMap<Integer, DFSNode>();
	
	/**
	 * Flag to be used on the graph traversal
	 */
	private transient boolean visited;
	
	/**
	 * Get the value of the node
	 * @return
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Mark the node as already visited
	 */
	public void setAsVisited() {
		this.visited = true;
	}
	
	/**
	 * Ask if the node was visited
	 * @return
	 */
	public boolean isVisited() {
		return this.visited;
	}
	
	/**
	 * Get a collection of the adjacent nodes
	 * of the node instance
	 * @return
	 */
	public Collection<DFSNode> getAdjacents() {
		return adjacents.values();
	}
	
	/**
	 * Ask how many adjacent nodes have the node instance
	 * @return
	 */
	public int quantityAdjacents() {
		return this.adjacents.size();
	}
	
	/**
	 * Add adjacent nodes to the node instance
	 * @param nodes
	 */
	public final void addAdjacents(final DFSNode... nodes) {
		for(final DFSNode node : nodes) {
			final int key = node.getValue();
			if(!adjacents.containsKey(key)) {
				adjacents.put(key, node);
			}
		}
	}
	
	/**
	 * Create a node instance by providing the value and 
	 * optionally a list of adjacent nodes.
	 * The adjacent nodes can be set later too, so it would
	 * be possible to insert nodes with circular dependencies
	 * between them
	 * @param value
	 * @param adjacents
	 */
	public DFSNode(final int value, final DFSNode... adjacents) {
		this.value = value;
		for(final DFSNode node : adjacents) {
			addAdjacents(node);
		}
	}
}

/**
 * Deep-first search algorithm
 * @author David Perez
 *
 */
public class DFS {
	
	private String search(final DFSNode base) {		
		if(base.isVisited()) {
			return "";
		}
		
		final StringBuffer path = new StringBuffer();
		path.append(visit(base));
		
		for(final DFSNode node: base.getAdjacents()) {
			if(!node.isVisited()) {
				path.append(search(node));				
			}
		}
		
		return path.toString();
	}	
	
	/**
	 * Visit a given node. In this case, we visit the node to 
	 * stringify its value, which we will concatenate on the 
	 * recursion to print the entire graph on its serach order.
	 * @param base
	 * @return
	 */
	private String visit(final DFSNode base) {
		base.setAsVisited();
		return Integer.toString(base.getValue()) + "->";
	}
	

	/**
	 * Test cases
	 * @param args
	 */
	public static void main(String[] args) {
//		We start at the root and explore each branch completely before moving to the next branch
//		i.e., we go deepth first before we go wide

		/*		
		digraph{
		 base->"1 visited=true"
		 base->"2 visited=false"
		 "2 visited=false"->"1 visited=true"
		 "1 visited=true"->nulos;
		}		
		*/		
		
		DFSNode base = new DFSNode(0);
		DFSNode node1 = new DFSNode(1);
		DFSNode node2 = new DFSNode(2);
		base.addAdjacents(node1, node2);
		node2.addAdjacents(node1);
		
		DFS dfs = new DFS();
		String result = dfs.search(base);
		assertEqual(result, "0->1->2->", "Search 0->1->2");
		
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
		DFSNode n0 = new DFSNode(0);
		DFSNode n1 = new DFSNode(1);
		DFSNode n2 = new DFSNode(2);
		DFSNode n3 = new DFSNode(3);
		DFSNode n4 = new DFSNode(4);
		DFSNode n5 = new DFSNode(5);
		n0.addAdjacents(n1);
		n1.addAdjacents(n3);
		n3.addAdjacents(n2);
		n2.addAdjacents(n1);
		n3.addAdjacents(n4);
		n1.addAdjacents(n4);
		n0.addAdjacents(n4);
		n0.addAdjacents(n5);
		String result1 = dfs.search(n0);
		String expectation = "0->1->3->2->4->5->";
		assertEqual(result1, expectation, "DFS search " + expectation);
	}

	private static void assertEqual(final String result, final String expectation, final String msg) {
		if(result.equals(expectation)) {
			System.out.println("Success: "+msg);
		}else {
			System.err.println("Failure: "+msg+ ". Expected " + expectation + " but got " + result);
		}
		
	}

}
