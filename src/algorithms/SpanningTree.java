package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;



public class SpanningTree {
	private class PQ{
		private PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
	
		public void enqueue(Edge[] edges) {
			for(Edge edge: edges) {
				if(!queue.contains(edge)) {
					queue.add(edge);				
				}
			}
		}
		
		public boolean isEmpty() {
			return queue.isEmpty();
		}
		
		public Edge dequeue() {
			return queue.remove();
		}
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("PQ = ");
			if(this.queue.isEmpty()) {
				sb.append("{}");
			}
			for(Edge el: this.queue) {
				sb.append(el);
			}
			return sb.toString();
		}
	}	
	
	private class T{
				
		private Set<TElement> t = new HashSet<TElement>();
	
		private class TElement implements Comparable<TElement>{
			private Vertex vertex;
		
			public Edge linkedWithEdge;
			
			public int getVertexValue() {
				return this.vertex.getValue();
			}
			
			public TElement(Vertex vertex, Edge linkedWithEdge) {
				this.vertex = vertex;
				this.linkedWithEdge = linkedWithEdge;
			}
			
			@Override 
			public int compareTo(TElement o) {
				if(vertex.getValue() > o.vertex.getValue()) {
					return 1;
				}else if(vertex.getValue() < o.vertex.getValue()) {
					return -1;
				}else {
					return 0;
				}
			}
			
			@Override
			public String toString() {
				StringBuffer sb = new StringBuffer();
				sb.append("{");
				sb.append(vertex);
				sb.append(", ");
				sb.append(linkedWithEdge);
				sb.append("}");
				return sb.toString();
			}
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				TElement other = (TElement) obj;
				if (vertex == null) {
					if (other.vertex != null)
						return false;
				} else if (!vertex.equals(other.vertex))
					return false;
				return true;
			}
			
		}
		
		
		public void add(Vertex vertex, Edge edge) {
			t.add(new TElement(vertex, edge));
		}
		
		public boolean has(int value) {
			Iterator<TElement>iterator = t.iterator();
			while(iterator.hasNext()) {
				TElement element = iterator.next();
				if(element.getVertexValue() == value) {
					return true;
				}
			}
			return false;
		}
	
		public boolean contains(Vertex v) {
			return has(v.getValue());
		}
	
		public Vertex[] getVertices() {
			List<Vertex> vertexs = new ArrayList<Vertex>();
			for(TElement element: this.t) {
				vertexs.add(element.vertex);
			}
			Vertex[] arr = new Vertex[vertexs.size()];
			return vertexs.toArray(arr);
		}
		
		public Edge[] getEdges() {
			List<Edge> edges = new ArrayList<Edge>();
			for(TElement element: this.t) {
				if(element.linkedWithEdge != null) {
					edges.add(element.linkedWithEdge);
				}
			}
			Edge[] arr = new Edge[edges.size()];
			edges.toArray(arr);
			return arr;
		}
		
		public int weight() {
			int w = 0;
			for(TElement el: this.t) {
				if(el.linkedWithEdge != null) {
					w = w + el.linkedWithEdge.getWeight();				
				}
			}
			return w;
		}
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("T = ");
			if(this.t.isEmpty()) {
				sb.append("{}");
			}
			Iterator<TElement> it = this.t.iterator();
			while(it.hasNext()) {
				TElement element = it.next();
				sb.append(element + ", ");
			}
			return sb.toString();
		}
	
	}

	private class Vertex{
		
		private int value;
		
		private Set<Vertex> adjacents = new HashSet<Vertex>();
		
		private Set<Edge> edges = new HashSet<Edge>();
		
		public int getValue() {
			return this.value;
		}
		
		public Vertex(int value) {
			this.value = value;
		}
		
		public void addAdjacent(Vertex v) {
			this.adjacents.add(v);
		}
		
		public void addEdge(int w, Vertex v) throws Exception {
			this.edges.add(new Edge(w, v, this));
		}
		
		public Edge[] getEdges() {
			Edge[] arr = new Edge[this.edges.size()];
			return this.edges.toArray(arr);
		}
		
		public Edge[] getEdgesExcept(Edge edge) {
			Edge[] arr = new Edge[this.edges.size()];
			this.edges.toArray(arr);
			Edge[] arrExcept = new Edge[arr.length-1];
			int j = 0;
			for(int i = 0; i < arr.length; i++) {
				if(!arr[i].equals(edge)) {
					arrExcept[j] = arr[i];
					j++;
				}
			}
			return arrExcept;
		}	
	
		@Override
		public String toString() {
			return "v("+this.value+")";
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Vertex other = (Vertex) obj;
			if (value != other.value)
				return false;
			return true;
		}
	}
	
	
	private class Edge implements Comparable<Edge>{
		private int weight;
	
		private Vertex vertexA;
		
		private Vertex vertexB;
		
		public Vertex toAnalize; //reference to vA or vB
		
		public Edge(int weight, Vertex vertex1, Vertex vertex2) throws Exception {
			this.weight = weight;		
			this.toAnalize = vertex1;
			
			if(vertex1.equals(vertex2)) {
				throw new Exception("Cannot create vertex because both parameters are the same vertex object");
			}	
			
			//I need to order this so the equals function will work as expected
			//vertex with lower value goes always first, so it is easy to compare,
			//i.e., there is a permutability property for the params a and b of this constructor.
			if(vertex1.getValue() < vertex2.getValue()) {
				this.vertexA = vertex1;
				this.vertexB = vertex2;			
			}else {
				this.vertexA = vertex2;
				this.vertexB = vertex1;
			}
		}
		
		@Override
		public int compareTo(Edge o){
			if(this.getWeight() > o.getWeight()) {
				return 1;
			}else if(this.getWeight() < o.getWeight()) {
				return -1;
			}else {
				return 0;
			}
		}
	
		@Override
		public String toString() {
			return "e(w:"+this.weight+", ["+this.vertexA+", "+this.vertexB+"])";
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			if (vertexA == null) {
				if (other.vertexA != null)
					return false;
			} else if (!vertexA.equals(other.vertexA))
				return false;
			if (vertexB == null) {
				if (other.vertexB != null)
					return false;
			} else if (!vertexB.equals(other.vertexB))
				return false;
			if (weight != other.weight)
				return false;
			return true;
		}
	
		public int getWeight() {
			return this.weight;
		}
		
	}	
	
	public T findMinimum(Vertex startVertex) {
		T minSpanningTree = new T();
		PQ priorityQueue = new PQ();
		Vertex s = startVertex;
		minSpanningTree.add(s, null);
		//System.out.println(minSpanningTree);
		//System.out.println("Enqueue edges connected to "+s+" in "+priorityQueue);
		priorityQueue.enqueue(s.getEdges()); //enqueue edges connected to s in pq
		//System.out.println("Now we have " + priorityQueue);
		while(!priorityQueue.isEmpty()) {
			Edge edge = priorityQueue.dequeue();
			//System.out.println(edge + " is removed from PQ. Now "+priorityQueue);
			Vertex vertex = edge.toAnalize;
			if(!minSpanningTree.contains(vertex)) {
				//System.out.println("" + v + " is not in " + minSpanningTree);				
				minSpanningTree.add(vertex, edge); // T = T U {v,e}
				//System.out.println("T = T U {v,e}. => Vertex " + v + " and the edge" + edge + "are added into T. Now " + minSpanningTree);
				priorityQueue.enqueue(vertex.getEdgesExcept(edge)); //enqueue edges connected to v
				//System.out.println("edges " + v.getEdgesExcept(edge) +"are added to PQ. Now "+ priorityQueue);
			}
		}
		
		System.out.println("MST:");
		for(Edge el: minSpanningTree.getEdges()) {
			System.out.println(el);
		}
		for(Vertex el: minSpanningTree.getVertices()) {
			System.out.println(el);
		}
		System.out.println("with weight = " + minSpanningTree.weight());
			
		return minSpanningTree;
	}	
	
	public static void main(String[] args){
		//
		//Minimum spanning tree MST with Prism's algorithm
		//
		//Grows a MST from a starting source vertex until it spans
		//the entire graph.
		//Runtime: O(E*log(V)).
		//Requires:
		//	- A priority queue data structure with binary heap to 
		//	  dynamically order the currently considered edges based on
		//	  increasing weight.
		//  - Adjacency list data structures for the fast neighbor 
		//    enumeration of a vertex
		//  - A boolean array to help in checking cycle.
		

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

		SpanningTree mst = new SpanningTree();

		Vertex v0 = mst.new Vertex(0);
		Vertex v1 = mst.new Vertex(1);
		Vertex v2 = mst.new Vertex(2);
		Vertex v3 = mst.new Vertex(3);
		v0.addAdjacent(v1);
		v0.addAdjacent(v2);
		v0.addAdjacent(v3);
		v1.addAdjacent(v0);
		v1.addAdjacent(v3);
		v2.addAdjacent(v0);
		v2.addAdjacent(v3);
		v3.addAdjacent(v2);
		v3.addAdjacent(v0);
		v3.addAdjacent(v1);
		try {
			v0.addEdge(3, v1);
			v0.addEdge(2, v2);
			v0.addEdge(3, v3);
			v1.addEdge(3, v0);
			v1.addEdge(2, v3);
			v2.addEdge(2, v0);
			v2.addEdge(3, v3);
			v3.addEdge(3, v0);
			v3.addEdge(2, v1);
			v3.addEdge(3, v2);			
//			v0.addEdge(1, v1);
//			v0.addEdge(2, v2);
//			v0.addEdge(1, v3);
//			v1.addEdge(1, v0);
//			v1.addEdge(1, v3);
//			v2.addEdge(2, v0);
//			v2.addEdge(3, v3);
//			v3.addEdge(1, v0);
//			v3.addEdge(1, v1);
//			v3.addEdge(3, v2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mst.findMinimum(v0);
		
		Vertex vertex1 = mst.new Vertex(1);
		Vertex vertex2 = mst.new Vertex(1);
		System.out.println("vertex equal: "+ vertex1.equals(vertex2));
		
		try {
			Edge e1 = mst.new Edge(10, v0, v1);
			Edge e2 = mst.new Edge(10, v1, v0);
			System.out.println("edge equal: " + e1.equals(e2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
