package datastructures.sorting.bydigit;

/**
 * Implementation of the R-way trie algorithm
 * <p>
 * Userful learning resources I've used:
 * <ul>
 *   <li>
 *   	<a href="https://algs4.cs.princeton.edu/lectures/52Tries.pdf">
 *   	From Princeton, with implementation example</a>
 *   </li>
 *   <li>
 *   	<a href="https://www.cs.cmu.edu/~fp/courses/15122-f10/lectures/18-tries.pdf">
 *   	From CMU</a>
 *   </li>
 *   <li>
 *   	<a href="https://www.cs.cmu.edu/~avrim/451f11/recitations/rec0921.pdf">
 *   	More from CMU</a>
 *   </li>
 *   <li>
 *   	<a href="https://medium.com/basecs/trying-to-understand-tries-3ec6bede0014">
 *   	And this nice blog post</a>
 *   </li>
 * </ul>
 *   <p>
 *   Also, to visualize the test cases I've used a graph drawing tool called graphviz:
 *   <br>
 *   <br>
 *   <a href="http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html">
 *   Run online</a>
 *   <a href="https://graphviz.gitlab.io/_pages/pdf/dotguide.pdf">Documentation</a>
 *   
 *   
 * @author David Perez
 *
 */
public class TrieST {
	
	/**
	 * The size of the alphabet to be supported
	 * For example, ALPHABET_SIZE = 256 for ASCII characters
	 * On this case, we support just two characters:
	 *    => 'a' (at index 0)
	 *    => 'b' (at index 1)
	 *  to simplify the development, debugging and drawing
	 */
	private static final int ALPHABET_SIZE = 2;
	
	/**
	 * The root node of the trie tree.
	 * Which by default have null values and an empty array 
	 * with size {@code TrieST#ALPHABET_SIZE} for the links
	 */
	private final Node root = new Node();

	private static final int CONST_A = 0;
	private static final int CONST_B = 1;	
	private static final char CONST_VAL_A = 'a';
	private static final char CONST_VAL_B = 'b';
	
	/**
	 * The node of the Trie
	 * @author david
	 *
	 */
	private class Node{
		/**
		 * The value associated with the key. 
		 * It works like a hash table, where for each key we lookup
		 * for its associated value
		 */
		private Object value;
		
		/**
		 * Array that will store the links to the child nodes of the tree.
		 */
		public final Node[] next = new Node[ALPHABET_SIZE];

		public Object getValue() {
			return value;
		}

		public void setValue(final Object value) {
			this.value = value;
		}

	}
	
	/**
	 * Insert an entry to the trie
	 * @param key
	 * @param value
	 * @throws Exception 
	 */
	public void insert(final String key, final int value) throws Exception {
		insert(this.root, key, value, 0);
	}
	
	/**
	 * {@inheritDoc TrieST#insert(String, int)}
	 * @param base
	 * @param key
	 * @param value
	 * @param index
	 * @return
	 */
	private Node insert(final Node node, final String key, final int value, final int index) {
		Node base = node;
		
		if(base == null) {
			base = new Node();
		}
		
		//ends the recursion when encounter the node of the
		//last character after following the links
		if(index == key.length()) { 
			base.setValue(value);
			return base;
		}
		
		final char character = key.charAt(index);
		final int child = childIndex(character);
		base.next[child] = insert(base.next[child], key, value, index+1);
		
		return base;
	}
	
	/**
	 * Get the index of the child node for the given character
	 * @param character
	 * @return
	 */
	private int childIndex(final char character) {
		if(character == CONST_VAL_A) {
			return 0;
		}else if(character == CONST_VAL_B) {
			return 1;
		}
		
		//send this value because it will fail when used because 
		//we didn't support other characters
		return -1; 
	}

	/**
	 * Test cases:
	 * -This algorithm was developed with the TDD methodology (write tests first, then
	 *  implement the code that passes the tests). TDD is suitable for this case because
	 *  the requirements are well known.
	 * -Without external dependencies
	 * -Everything is on this single file (no make it easy to reproduce in codepad, for example)
	 * -I use digraph - dot (graphviz) language to represent the graph, an online 
	 *  renderer can be found here 
	 *  http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html
	 * -I violates the Law of Demeter of the instance on my tests, because I want 
	 *  to test the internal states.
	 * @param args
	 */
	private static String msgA = "Follow links corresponding to each character in the key, ";	
	private static String msgA1 = "encounter a null link, then create a new node";
	public static void main(String[] args) {
		System.out.println("Put test cases");
		
		final TrieST t = new TrieST();


		/*
		http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html?graph=%09%09digraph%7B%0A%09%09%20%20root-%3E%22node_1%20ch%3Da%20val%3D777%22-%3Enull_3%0A%09%09%20%20root-%3Enull_2%0A%09%09%20%20%22node_1%20ch%3Da%20val%3D777%22-%3Enull_4%0A%09%09%7D%0A
		digraph{
		  root->"node_1 ch=a val=777"->null_3
		  root->null_2
		  "node_1 ch=a val=777"->null_4
		}
		 */
		baseCaseTestBeforePut(t.root);
		try {
			t.insert("a", 777);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		baseCaseTestAfterPut(t.root, 777, CONST_A, CONST_B, " 777 ");

		/*
		http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html?graph=%09%09digraph%7B%0A%09%09%20%20%22node_1%20ch%3Da%20val%3D777%22-%3Enull_4%0A%09%09%20%20%22node_1%20ch%3Da%20val%3D777%22-%3E%22node_2%20ch%3Db%20val%3D888%22-%3Enull_5%0A%09%09%20%20%22node_2%20ch%3Db%20val%3D888%22-%3Enull_6%0A%09%09%7D%0A
		digraph{
		  "node_1 ch=a val=777"->null_4
		  "node_1 ch=a val=777"->"node_2 ch=b val=888"->null_5
		  "node_2 ch=b val=888"->null_6
		}
		 */		
		baseCaseTestBeforePut(t.root.next[CONST_A]);
		try {
			t.insert("ab", 888);
		} catch (Exception e) {
			e.printStackTrace();
		}
		baseCaseTestAfterPut(t.root.next[CONST_A], 888, CONST_B, CONST_A,  " 888 ");
		
		/*after those two puts we have: (dont' worry about the null with different ids):
			http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html?graph=%09%09digraph%7B%0A%09%09%20%20%22node_1%20ch%3Da%20val%3D777%22-%3Enull_4%0A%09%09%20%20%22node_1%20ch%3Da%20val%3D777%22-%3E%22node_2%20ch%3Db%20val%3D888%22-%3Enull_5%0A%09%09%20%20%22node_2%20ch%3Db%20val%3D888%22-%3Enull_6%0A%09%09%7D%0A		 
			digraph{
			  base->"node_1 ch=a val=777"->null_3
			  base->null_2
			  "node_1 ch=a val=777"->"node_2 ch=b val=888"->null_5
			  "node_2 ch=b val=888"->null_6
			}
		*/
		assertTrue((int)t.root.next[CONST_A].next[CONST_B].getValue() == 888, "Links are correct");
	}
	
	private static void baseCaseTestBeforePut(final Node base) {
		/* My base case, the instance is empty
		http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html?graph=%09%09digraph%7B%0A%09%09%20%20base-%3Enull_1%0A%09%09%20%20base-%3Enull_2%0A%09%09%7D%0A		 
		digraph{
		  base->null_1
		  base->null_2
		}
		*/		
		assertTrue(base.next[CONST_A] == null, msgA + msgA1);
		assertTrue(base.next[CONST_B] == null, msgA + msgA1);
	}

	private static void baseCaseTestAfterPut(final Node base, final int value, final int charInUse, 
			final int charNotUsed, final String msg) {
		/*My base case, after PUT, the instance now have a single letter
		http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html?graph=%09%09digraph%7B%0A%09%09%20%20base-%3E%22node_1%20ch%3Da%20val%3D777%22-%3Enull_3%0A%09%09%20%20base-%3Enull_2%0A%09%09%20%20%22node_1%20ch%3Da%20val%3D777%22-%3Enull_4%0A%09%09%7D%0A
		digraph{
		  base->"node_1 ch=a val=777"->null_3
		  base->null_2
		  "node_1 ch=a val=777"->null_4
		}
		 */
		final Node node_1 = base.next[charInUse];
		String separator = " - ";
		assertTrue(node_1 != null, msgA + msgA1 + separator + msg + " A ");
		assertTrue(base.next[charNotUsed] == null, msgA + msgA1 + separator + msg + " B ");
		assertTrue(node_1.next[charInUse] == null, msgA + msgA1 + separator + msg + " C ");
		assertTrue(node_1.next[charNotUsed] == null, msgA + msgA1 + separator + msg + " D ");
		
		String msgA2 = "encounter the last character of the key, then set value on that node";
		assertTrue((int)node_1.getValue() == value, msgA + msgA2 + separator + msg + " E ");
		
	}

	private static void assertTrue(final boolean pass, final String msg) {
		if(pass) {
			System.out.println("Success: "+msg);
		}else {
			System.err.println("Error: "+msg);
		}
		
	}
	
	
}
