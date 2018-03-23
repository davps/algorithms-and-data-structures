package datastructures.sorting.bydigit;

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
