package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


/**
 * My implementation of the Prim's algorithm to find
 * the minimum spanning tree.
 * @author David Perez
 *
 */
public class MinimumSpanningTree {
	
	/**
	 * A class that handle my Minimum Spanning Tree.
	 * I've used the Set interface for the implementation to avoid 
	 * repetition of the items of my set.
	 * @author David Perez
	 *
	 */
	private class Tree{
		/**
		 * Used the LinkedHashSet to maintains the orden of insertion.
		 * Useful for debugging so I know the order the elements where
		 * inserted.
		 */
		private final transient Set<TreeElement> treeSet = new LinkedHashSet<TreeElement>(); 

		/**
		 * I created this new class so I can organize my {@code Tree#tree}
		 * ordered by the {@code TreeElement#vertex}.
		 * @author David Perez
		 */
		private class TreeElement{
			
			/**
			 * The vertex added to the Minimum Spanning Tree
			 */
			final private Vertex vertex;
			
			/**
			 * The edge added to the Minimum Spanning Tree
			 */
			final private Edge edge;
			
			/**
			 * Get the value of the vertex that is being added to the Minimum
			 * Spanning Tree
			 */
			private int getVertexValue() {
				return this.vertex.getValue();
			}
			
			/**
			 * A new element for the Minimum Spanning Tree should be 
			 * constructed with one vertex and one edge
			 * @param vertex
			 * @param edge
			 */
			public TreeElement(final Vertex vertex, final Edge edge) {
				this.vertex = vertex;
				this.edge = edge;
			}
			
			/**
			 * For uniqueness, I'll use only the vertex value, it doesn't
			 * matter what edge was added with it.
			 */
			@Override
			public int hashCode() {
				return this.getVertexValue();
			}
			
			/**
			 * Calculated based on the vertex only because my
			 * algorithm ask if that vertex was already added to the tree
			 * and if not, it add it (independenly of the edge associated
			 * with it on its {@code TreeElement} instance.
			 */
			@Override
			public boolean equals(final Object obj) {
				if(this == obj) {
					return true;
				}
				if(obj == null) {
					return false;
				}
				if(getClass() != obj.getClass()) {
					return false;
				}
				TreeElement other = (TreeElement) obj;
				if(vertex == null) {
					if(other.vertex != null) {
						return false;
					}
				}else if(!vertex.equals(other.vertex)) {
					return false;
				}
				
				return true;
			}

			/**
			 * My custom string, with a notation useful for debugging.
			 */
			@Override
			public String toString() {
				final StringBuffer sb = new StringBuffer();
				sb.append("{").append(this.vertex).append(", ").append(this.edge).append("}");
				return sb.toString();
			}

		}

		/**
		 * {@inheritDoc Tree#add(Vertex, Edge)}
		 * This is used only for initialization of the iteration, 
		 * that's why the edge is not required.
		 * @param vertex
		 */
		public void add(final Vertex vertex) {
			add(vertex, null);
		}
		
		/**
		 * Add a {@code TreeElement} instance to the tree.
		 * @param vertex
		 * @param edge
		 */
		public void add(final Vertex vertex, final Edge edge) {
			this.treeSet.add(new TreeElement(vertex, edge));
		}

		/**
		 * Verify if a given vertex already was included on the tree.
		 * @param vertex
		 * @return
		 */
		public boolean contains(final Vertex vertex) {
			return this.treeSet.contains(new TreeElement(vertex, null));
		}

		/**
		 * Quantity of vertexs on my tree.
		 * @return
		 */
		public int size() {
			return this.treeSet.size();
		}
		
		/**
		 * Get the edges of the Minimum Spanning Tree
		 * @return
		 */
		public Edge[] getEdges(){
			final List<Edge> list = new ArrayList<Edge>();
			for(TreeElement item : this.treeSet) {
				if(item.edge != null) {
					list.add(item.edge);
				}
			}
			final Edge[] arr = new Edge[list.size()];
			return list.toArray(arr);
		}
		
			
		/**
		 * {@inheritDoc TreeElement#toString()}
		 */
		@Override
		public String toString() {
			final StringBuffer sb = new StringBuffer();
			sb.append("T = { ");
			for(TreeElement item : this.treeSet) {
				sb.append(item);
			}
			sb.append(" }");
			return sb.toString();
		}

		/**
		 * Calculate the weight of the Minimum Spanning Tree
		 * @return
		 */
		public int weight() {
			int weight = 0;
			for(final Edge item : getEdges()) {
				weight = weight + item.weight;
			}
			return weight;
		}

	}

	/**
	 * Priority queue used for the Prim's Algorithm.
	 * Ordered by weight of the edges.
	 * @author David Perez
	 *
	 */
	private class MSTPriorityQueue{
		
		/**
		 * Build-in java PriorityQueue.
		 */
		final private PriorityQueue<Edge> queue = new PriorityQueue<Edge>();

		/**
		 * Expose the isEmpty() method of the queue.
		 * {@inheritDoc PriorityQueue#isEmpty()}
		 * @return
		 */
		public boolean isEmpty() {
			return queue.isEmpty();
		}

		/**
		 * Dequeue the Priority queue.
		 * {@inheritDoc PriorityQueue#remove()}
		 * but returns null is the queue is empty.
		 * @return
		 */
		public Edge dequeue() {
			if(this.queue.isEmpty()) {
				return null;
			}
			return this.queue.remove();
		}

		/**
		 * Enqueue que priority queue.
		 * {@inheritDoc PriorityQueue#add(Object)}
		 * but don't queue if the element already exists on the queue
		 * because we don't need duplicated elements for the Prim's algorithm
		 * @param edges
		 */
		public void enqueue(Edge...edges) {
			for(final Edge item: edges) {
				if(!this.queue.contains(item)) { //to avoid insertion of duplicates
					this.queue.add(item);					
				}
			}
		}
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("PQ = { ");
			for(Edge item: this.queue) {
				sb.append(item).append(" ");
			}
			sb.append(" } ");
			return sb.toString();
		}
		
	}
	
	/**
	 * Vertex of the graph.
	 * @author David Perez
	 *
	 */
	private class Vertex{
		/**
		 * Value of the vertex
		 */
		private final int value;
		
		/**
		 * Keep track of the incident edges of this {@code Vertex} instance.
		 */
		private final Set<Edge> edges = new HashSet<Edge>();
		
		/**
		 * Construct the vertex with its value. 
		 * On my implementation, to simplify, 
		 * the uniqueness is determined by its value.
		 * @param value
		 */
		public Vertex(final int value) {
			this.value = value;
		}

		/**
		 * Get the value of the vertex.
		 * @return
		 */
		public int getValue() {
			return this.value;
		}

		/**
		 * Add an incident edge for this vertex instance
		 * @param weight
		 * @param vertex
		 */
		public void addEdge(final int weight, final Vertex vertex) {
			this.edges.add(new Edge(weight, vertex, this));
		}

		/**
		 * Get the list of incident edges of this vertex
		 * @return
		 */
		public Edge[] getEdges() {
			final Edge[] arr = new Edge[this.edges.size()];
			this.edges.toArray(arr);
			return arr;
		}

		/**
		 * Get the list of the incident edges of this vertex
		 * but excluding the given edge.
		 * @param edge
		 * @return
		 */
		public Edge[] getEdgesWithout(final Edge edge) {
			final List<Edge> list = new ArrayList<Edge>();
			for(final Edge item: this.edges) {
				if(!item.equals(edge)) {
					list.add(item);					
				}
			}
			final Edge[] arr = new Edge[list.size()];
			return list.toArray(arr);
		}
		
		/**
		 * The comparison is done by the vertex value only,
		 * it is not necessary to consider the edges.
		 */
		@Override 
		public boolean equals(Object obj) {
			if(obj == this) {
				return true;
			}
			
			if(obj == null) {
				return false;
			}
			
			if(obj.getClass() != getClass()) {
				return false;
			}
			
			final Vertex other = (Vertex) obj;
			if(this.value != other.getValue()) {
				return false;
			}
			
			return true;
		}

		/**
		 * {@inheritDoc Vertex#equals(Object)}
		 */
	    @Override
	    public int hashCode(){
	      int prime = 31;
	      int result = 1;
	      result = result * prime + this.value;
	      return result;
	    }
		
	    @Override
	    public String toString() {
	    	StringBuffer sb = new StringBuffer();
	    	sb.append("v(").append(this.value).append(")");
	    	return sb.toString();
	    }

	}
	
	/**
	 * Edge of the graph.
	 * Implements the Comparable because I need to compare if 
	 * the edges are equal or not, for my priority queue.
	 * @author David Perez
	 *
	 */
	private class Edge implements Comparable<Edge>{
		
		/**
		 * TODO probably I don't need this on my actual implementation, it was
		 * a hack I've used on my previous implementation but the actual one,
		 * that uses {@code Edge#getVertexOtherThan(Vertex)} is more elegant.
		 */
		private final Vertex markedVertex;
		
		/**
		 * Vertex with lower value.
		 * It is sorted in that way by convention, to simplify the comparison.
		 */
		private final Vertex vertexA;
		
		/**
		 * Vertex with higher value. 
		 * It is sorted in that way by convention, to simplify the comparison.
		 */
		private final Vertex vertexB;
		
		/**
		 * Weight of the edge
		 */
		private final int weight;

		/**
		 * The edge must be constructed with two vertexs and the weight.
		 * The vertexs are sorted in that way by convention, 
		 * to simplify the comparison.
		 * @param weight
		 * @param vertex1
		 * @param vertex2
		 */
		public Edge(final int weight, final Vertex vertex1, final Vertex vertex2) {
			this.weight = weight;
			this.markedVertex = vertex1;
			
			if(vertex1.equals(vertex2)) {
				//TODO throw an error because I am creating a vertex with two 
				//instaneces of the same vertex.
			}
			
			if(vertex1.getValue() > vertex2.getValue()) {
				this.vertexA = vertex2;
				this.vertexB = vertex1;
			}else {
				this.vertexA = vertex1;
				this.vertexB = vertex2;
			}
		}
		
		/**
		 * Considering the weight and its vertexs
		 */
		@Override
		public boolean equals(Object obj) {
			if(obj == this) {
				return true;
			}
			
			if(obj == null) {
				return false;
			}
			
			if(obj.getClass() != getClass()) {
				return false;
			}
			
			Edge other = (Edge) obj;
			if(!other.vertexA.equals(this.vertexA)) {
				return false;
			}
			
			if(!other.vertexB.equals(this.vertexB)) {
				return false;
			}
			
			if(other.weight != this.weight) {
				return false;
			}
			
			return true;
		}
		
		/**
		 * Considering the weight and its vertexs
		 */
	    @Override
	    public int hashCode(){
	      int prime = 31;
	      int result = 1;
	      result = prime * result + (this.vertexA != null ? this.vertexA.hashCode() : 0);
	      result = prime * result + (this.vertexB != null ? this.vertexB.hashCode() : 0);
	      result = prime * result + this.weight;
	      return result;
	    }		

	    /**
	     * Get one of the vertexs of the Edge.
	     * Returns the one that is different than the provided vertex.
	     * @param vertex
	     * @return
	     */
		public Vertex getVertexOtherThan(final Vertex vertex) {
			if(this.vertexA.equals(vertex)) {
				return this.vertexB;
			}else {
				return this.vertexA;
			}
		}

		/**
		 * Considering the weight only. For the Priority Queue.
		 */
		@Override
		public int compareTo(Edge obj) {
			if(this.equals(obj)) {
				return 0;
			}else {
				//weigh is used to order the priority queue
				if(this.weight > obj.weight) {
					return 1;
				}else {
					return -1;
				}
			}
		}
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("e(w:").append(this.weight).append(", [")
				.append(this.vertexA).append(" ").append(this.vertexB)
				.append("]");
			return sb.toString();
		}

	}
	
	/**
	 * The Prim's algorithm to calculate the Minimum Spanning Tree.
	 * The algorithm by itself is very simple if we have this data
	 * structure based on {@code PriorityQueue} and implementing the
	 * {@code Comparable} interface on its objects.
	 * @param root Start populating from this vertex
	 * @return The Minimum Spanning Tree object.
	 */
	public Tree calculate(final Vertex root) {
		final Tree tree = new Tree();
		final MSTPriorityQueue queue = new MSTPriorityQueue();
		final Vertex s = root;
		tree.add(s);
		log(tree);
		queue.enqueue(s.getEdges());
		log(queue);
		Vertex vertex = s;
		while(!queue.isEmpty()) {
			log("Queue is not empty");
			Edge edge = queue.dequeue();
			vertex = edge.getVertexOtherThan(vertex);
			//vertex = edge.markedVertex; 
			if(!tree.contains(vertex)) {
				log(vertex + " is in " + tree);
				tree.add(vertex, edge);
				log("so add it to the tree " + tree);
				queue.enqueue(vertex.getEdgesWithout(edge));
				log("and enqueue its edges " + queue);
			}
		}
		return tree;
	}
	
	private void log(Object o) {
		System.out.println(o);		
	}

	/**
	 * My tests. With full test coverage.
	 * @param args
	 */
	public static void main(String[] args) {

		MinimumSpanningTree mst = new MinimumSpanningTree();
		
		/*
		 * Tree class.
		 * tree that contains no duplicate elements 
		 */
		Tree tree = mst.new Tree();
		assertTrue(tree.contains(mst.new Vertex(1000)) == false, "Empty");
		tree.add(mst.new Vertex(10), null);
		assertTrue(tree.contains(mst.new Vertex(10)) == true, "Contains");
		assertTrue(tree.size() == 1, "Right size after 1 addition");
		tree.add(mst.new Vertex(10), null);
		assertTrue(tree.size() == 1, "Right size after adding duplicated item");
		
		/*
		 * equals() on Vertex
		 */
		Vertex v1 = mst.new Vertex(1);
		Vertex v2 = mst.new Vertex(1);
		Vertex v3 = mst.new Vertex(2);
		assertTrue(v1.equals(v2) == true, "Equals Vertex objects");
		assertTrue(v1.equals(v3) != true, "Not equals vertex objects");
		
		/*
		 * addEdge() on Vertex
		 * contains no duplicate edges
		 */
		assertTrue(v1.getEdges().length == 0, "No edges yet");
		v1.addEdge(10, v2);
		assertTrue(v1.getEdges().length == 1, "Added 1 edge successfully");
		v1.addEdge(10, v2);
		assertTrue(v1.getEdges().length == 1,  "Didn't added duplicated edge");
		v1.addEdge(20,  v3);
		assertTrue(v1.getEdges().length == 2, "Added a second edge successfully");
		
		/*
		 * Vertex.getEdgesWithout()
		 */
		Edge[] edges = v1.getEdgesWithout(mst.new Edge(10, v1, v2));
		assertTrue(v1.getEdges().length == 2, "There are two edges for this vertex");
		assertTrue(edges.length == 1, "Returned right quantity of edges without a given Edge. " + edges.length);
		assertTrue(edges[0].equals(mst.new Edge(20, v1, v3)) == true, "Returned right edge");
		
		/*
		 * equals() on Edge
		 */
		Edge e1 = mst.new Edge(10, mst.new Vertex(1), mst.new Vertex(2));
		Edge e2 = mst.new Edge(10, mst.new Vertex(1), mst.new Vertex(2));
		Edge e3 = mst.new Edge(20, mst.new Vertex(1), mst.new Vertex(2));
		Edge e4 = mst.new Edge(10, mst.new Vertex(1), mst.new Vertex(3));
		assertTrue(e1.equals(e2) == true, "Equals Edge objects");
		assertTrue(e2.equals(e3) == false, "Not equals Edge objects. 1");
		assertTrue(e1.equals(e4) == false, "Not equals Edge objects. 2");
		
		/*
		 * Edge.getVertexOtherThan(edge)
		 */
		Vertex vert2 = e1.getVertexOtherThan(mst.new Vertex(1));
		assertTrue(vert2.equals(mst.new Vertex(1)) == false, "Dont get vertex 1");
		assertTrue(vert2.equals(mst.new Vertex(2)) == true, "Get vertex 2");
		
		/*
		 * PQ ADT using Java's built in PriorityQueue (which 
		 * uses Binary heap implementation)
		 */
		MSTPriorityQueue queue = mst.new MSTPriorityQueue();
		assertTrue(queue.isEmpty() == true, "queue start empty");
		queue.enqueue(e1);
		assertTrue(queue.isEmpty() == false, "queue is not empty after first insertion");
		assertTrue(queue.dequeue().equals(e1) == true, "dequeing the only item on the queue");
		queue.enqueue(e1);
		queue.enqueue(e1);
		System.out.println("Actual: " + queue);
		queue.dequeue();
		assertTrue(queue.dequeue() == null, "Enqueued e1 only once because it was a repeated item. " + queue);
		queue.enqueue(e1, e2, e3, e4);
		Edge nextEdge = queue.dequeue();
		assertTrue(nextEdge.equals(e1) || nextEdge.equals(e4), "dequeing e1 or e4 because those have lowest weight. " + e1 + e4);
		queue.dequeue();
		Edge deqEdge = queue.dequeue();
		assertTrue(deqEdge.equals(e3) == true, "because both have same weight but are different edges" + deqEdge);
		queue.dequeue();
		assertTrue(queue.isEmpty() == true, "now queue is empty (I inserted 4 edges but saved only 3 on the queue because one was repeated");
		
		/*
		 * Integration test:
		 */
//		(0)----1---(1)
//		 | \		|
//		 |  \		|
//		 2   \		|
//		 |    \		1
//		 |     1	|
//		 |		\	|
//		 |       \	|
//		 |        \	|
//		(2)----3---(3)

		Vertex ver0 = mst.new Vertex(0);
		Vertex ver1 = mst.new Vertex(1);
		Vertex ver2 = mst.new Vertex(2);
		Vertex ver3 = mst.new Vertex(3);
		ver0.addEdge(3, ver1);
		ver0.addEdge(2, ver2);
		ver0.addEdge(3, ver3);
		ver1.addEdge(3, ver0);
		ver1.addEdge(2, ver3);
		ver2.addEdge(2, ver0);
		ver2.addEdge(3, ver3);
		ver3.addEdge(3, ver0);
		ver3.addEdge(2, ver1);
		ver3.addEdge(3, ver2);			
		
		Tree t = mst.calculate(ver0);
		System.out.println("Minimum spanning tree uses those edges:");
		List<Edge> mstEdges = Arrays.asList(t.getEdges());
		assertTrue(mstEdges.contains(mst.new Edge(2, ver0, ver2)), "contains edge A");
		assertTrue(
				mstEdges.contains(mst.new Edge(3, ver0, ver1)) ||
				mstEdges.contains(mst.new Edge(3, ver2, ver3))
				, "contains edge B");
		assertTrue(mstEdges.contains(mst.new Edge(2, ver1, ver3)) , "contains edge C");
		
		for(Edge item : t.getEdges()) {
			System.out.println(item);
		}
		
		System.out.println("weigth mst =" + t.weight());
		assertTrue(t.weight() == 7, "Minimum weight of example is 7");	
		
		//using the default example from https://visualgo.net/en/mst
		//I recommend debugging and in parallel run the interactive algorithm
		//from the url I am referencing. That's a great way to learn.
		MinimumSpanningTree myMST = new MinimumSpanningTree();
		Vertex vertex0 = myMST.new Vertex(0);
		Vertex vertex1 = myMST.new Vertex(1);
		Vertex vertex2 = myMST.new Vertex(2);
		Vertex vertex3 = myMST.new Vertex(3);
		Vertex vertex4 = myMST.new Vertex(4);
		vertex0.addEdge(75, vertex2);
		vertex0.addEdge(9, vertex1);
		vertex1.addEdge(9, vertex0);
		vertex1.addEdge(95, vertex2);
		vertex1.addEdge(19, vertex3);
		vertex1.addEdge(42, vertex4);
		vertex2.addEdge(75, vertex0);
		vertex2.addEdge(95, vertex1);
		vertex2.addEdge(51, vertex3);
		vertex3.addEdge(19, vertex1);
		vertex3.addEdge(51, vertex2);
		vertex3.addEdge(31, vertex4);
		vertex4.addEdge(42, vertex1);
		vertex4.addEdge(31, vertex3);
		Tree myTree = myMST.calculate(vertex0);
		System.out.println("My Minimum spanning tree uses those edges:");
		List<Edge> myMSTEdges = Arrays.asList(myTree.getEdges());
		assertTrue(myMSTEdges.contains(myMST.new Edge(9, vertex0, vertex1)), "contains edge A");
		assertTrue(myMSTEdges.contains(myMST.new Edge(19, vertex1, vertex3)), "contains edge B");
		assertTrue(myMSTEdges.contains(myMST.new Edge(51, vertex2, vertex3)), "contains edge C");
		assertTrue(myMSTEdges.contains(myMST.new Edge(31, vertex3, vertex4)), "contains edge D");
		assertTrue(myMSTEdges.contains(myMST.new Edge(75, vertex0, vertex2)) == false, "not contains edge ");
		assertTrue(myMSTEdges.contains(myMST.new Edge(95, vertex1, vertex2)) == false, "not contains edge ");
		assertTrue(myMSTEdges.contains(myMST.new Edge(42, vertex1, vertex4)) == false, "not contains edge ");
		
		for(Edge item : myTree.getEdges()) {
			System.out.println(item);
		}
		
		System.out.println("weigth mst =" + myTree.weight());
		assertTrue(myTree.weight() == 110, "Minimum weight of example is 110");	
		
	}

	private static void assertTrue(boolean result, String msg) {
		if(result) {
			System.out.println("Success: " + msg);
		}else {
			System.err.println("Failure: "+ msg);
		}
		
	}

}
