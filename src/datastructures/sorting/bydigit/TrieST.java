package datastructures.sorting.bydigit;

/**
 * Implementation of the R-way trie algorithm
 * <p>
 * Userful learning resources I've used:
 * <ul>
 *   <li>
 *   	<a href="https://algs4.cs.princeton.edu/lectures/52Tries.pdf">From Princeton, with implementation example</a>
 *   </li>
 *   <li>
 *   	<a href="https://www.cs.cmu.edu/~fp/courses/15122-f10/lectures/18-tries.pdf"> From CMU</a>
 *   </li>
 *   <li>
 *   	<a href="https://www.cs.cmu.edu/~avrim/451f11/recitations/rec0921.pdf"> More from CMU</a>
 *   </li>
 *   <li>
 *   	<a href="https://medium.com/basecs/trying-to-understand-tries-3ec6bede0014">And this nice blog post</a>
 *   </li>
 * </ul>
 *   <p>
 *   Also, to visualize the test cases I've used a graph drawing tool called graphviz:
 *   <br>
 *   <br>
 *   <a href="http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html">Run online</a>
 *   <a href="https://graphviz.gitlab.io/_pages/pdf/dotguide.pdf>Documentation</a>
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
	private static int ALPHABET_SIZE = 2;
	
	/**
	 * The root node of the trie tree.
	 * Which by default have null values and an empty array 
	 * with size {@code TrieST#ALPHABET_SIZE} for the links
	 */
	private Node root = new Node();
	
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
		Object value = null;
		
		/**
		 * Array that will store the links to the child nodes of the tree.
		 */
		Node[] next = new Node[ALPHABET_SIZE];
	}
	
	/**
	 * Insert an entry to the trie
	 * @param key
	 * @param value
	 */
	public void insert(String key, int value) {
		throw new Error("NOT IMPLEMENTED");
	}
	
	private static int CONST_A = 0;
	private static int CONST_B = 1;

	/**
	 * Test cases:
	 * -This algorithm was developed with the TDD methodology (write tests first, then
	 *  implement the code that passes the tests). TDD is suitable for this case because
	 *  the requirements are well known.
	 * -Without external dependencies
	 * -Everything is on this single file (no make it easy to reproduce in codepad, for example)
	 * -I use digraph - dot (graphviz) language to represent the graph, an online 
	 *  renderer can be found here http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html
	 * @param args
	 */
	private static String msgA = "Follow links corresponding to each character in the key, ";	
	private static String msgA1 = "encounter a null link, then create a new node";
	public static void main(String[] args) {
		System.out.println("Put test cases");
		
		TrieST t = new TrieST();


		/*
		digraph{
		  base->"node_1 ch=a val=777"->null_3
		  base->null_2
		  "node_1 ch=a val=777"->null_4
		}
		 */
		baseCaseTestBeforePut(t.root);
		t.insert("a", 777);
		baseCaseTestAfterPut(t.root, 777);

		/*
		digraph{
		  "node_1 ch=a val=777"->null_4
		  "node_1 ch=a val=777"->"node_2 ch=b val=888"->null_5
		  "node_2 ch=b val=888"->null_6
		}
		 */		
		baseCaseTestBeforePut(t.root.next[CONST_A]);
		t.insert("ab", 888);
		baseCaseTestAfterPut(t.root.next[CONST_A], 888);
		
		/*after those two puts we have: (dont' worry about the null with different ids):
			digraph{
			  base->"node_1 ch=a val=777"->null_3
			  base->null_2
			  "node_1 ch=a val=777"->"node_2 ch=b val=888"->null_5
			  "node_2 ch=b val=888"->null_6
			}
		*/
		Assert((int)t.root.next[CONST_A].next[CONST_B].value == 888, "Links are correct");
	}
	
	private static void baseCaseTestBeforePut(Node base) {
		/* My base case, the instance is empty
		digraph{
		  base->null_1
		  base->null_2
		}
		*/		
		Assert(base.next[CONST_A] == null, msgA + msgA1);
		Assert(base.next[CONST_B] == null, msgA + msgA1);
	}

	private static void baseCaseTestAfterPut(Node base, int value) {
		/*My base case, after PUT, the instance now have a single letter
		digraph{
		  base->"node_1 ch=a val=777"->null_3
		  base->null_2
		  "node_1 ch=a val=777"->null_4
		}
		 */
		Node node_1 = base.next[CONST_A];
		Assert(node_1 != null, msgA + msgA1);
		Assert(base.next[CONST_B] == null, msgA + msgA1);
		Assert(node_1.next[CONST_A] == null, msgA + msgA1);
		Assert(node_1.next[CONST_B] == null, msgA + msgA1);
		
		String msgA2 = "encounter the last character of the key, then set value on that node";
		Assert((int)node_1.value == value, msgA + msgA2);
		
	}

	private static void Assert(boolean assertion, String msg) {
		if(assertion == true) {
			System.out.println("Success: "+msg);
		}else {
			System.err.println("Error: "+msg);
		}
		
	}
	
	
}
