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
	 * On this case, we support just two characters to simplify 
	 * the development, debugging and drawing
	 */
	private static int ALPHABET_SIZE = 2;
	
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
		Object value;
		
		/**
		 * Array that will store the links to the child nodes of the tree.
		 */
		Node[] next = new Node[ALPHABET_SIZE];
	}

	public static void main(String[] args) {
		

	}

}
