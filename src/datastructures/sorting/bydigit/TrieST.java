package datastructures.sorting.bydigit;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

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
	Node root = new Node();
	
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
	 * @throws Exception 
	 */
	public void insert(String key, int value) throws Exception {
		insert(this.root, key, value, 0);
	}
	
	private Node insert(Node base, String key, int value, int d) {
		if(base == null) base = new Node();
		
		//ends the recursion when encounter the node of the
		//last character after following the links
		if(d == key.length()) { 
			base.value = value;
			return base;
		}
		
		char character = key.charAt(d);
		int child = index(character);
		base.next[child] = insert(base.next[child], key, value, d+1);
		
		return base;
	}
	
	/**
	 * Get the index of the child node for the given character
	 * @param character
	 * @return
	 */
	private int index(char character) {
		if(character == 'a') {
			return 0;
		}else if(character == 'b') {
			return 1;
		}
		
		//send this value because it will fail when used because we didn't support other characters
		return -1; 
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
		Assert((int)t.root.next[CONST_A].next[CONST_B].value == 888, "Links are correct");
	}
	
	private static void baseCaseTestBeforePut(Node base) {
		/* My base case, the instance is empty
		http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html?graph=%09%09digraph%7B%0A%09%09%20%20base-%3Enull_1%0A%09%09%20%20base-%3Enull_2%0A%09%09%7D%0A		 
		digraph{
		  base->null_1
		  base->null_2
		}
		*/		
		Assert(base.next[CONST_A] == null, msgA + msgA1);
		Assert(base.next[CONST_B] == null, msgA + msgA1);
	}

	private static void baseCaseTestAfterPut(Node base, int value, int charInUse, int charNotUsed, String msg) {
		/*My base case, after PUT, the instance now have a single letter
		http://www.samsarin.com/project/dagre-d3/latest/demo/interactive-demo.html?graph=%09%09digraph%7B%0A%09%09%20%20base-%3E%22node_1%20ch%3Da%20val%3D777%22-%3Enull_3%0A%09%09%20%20base-%3Enull_2%0A%09%09%20%20%22node_1%20ch%3Da%20val%3D777%22-%3Enull_4%0A%09%09%7D%0A
		digraph{
		  base->"node_1 ch=a val=777"->null_3
		  base->null_2
		  "node_1 ch=a val=777"->null_4
		}
		 */
		Node node_1 = base.next[charInUse];
		Assert(node_1 != null, msgA + msgA1 + " - " + msg + " A ");
		Assert(base.next[charNotUsed] == null, msgA + msgA1 + " - " + msg + " B ");
		Assert(node_1.next[charInUse] == null, msgA + msgA1 + " - " + msg + " C ");
		Assert(node_1.next[charNotUsed] == null, msgA + msgA1 + " - " + msg + " D ");
		
		String msgA2 = "encounter the last character of the key, then set value on that node";
		Assert((int)node_1.value == value, msgA + msgA2 + " - " + msg + " E ");
		
	}

	private static void Assert(boolean assertion, String msg) {
		if(assertion == true) {
			System.out.println("Success: "+msg);
		}else {
			System.err.println("Error: "+msg);
		}
		
	}
	
	
}
