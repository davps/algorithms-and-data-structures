package datastructures.tree;

/**
 * Binary tree traversal implementations:
 * - Pre-order
 * - In-order
 * - Post-order
 * 
 * @author David Perez
 *
 * @param <T>
 */
public class Traversal <T>{

	/**
	 * The node of the binary tree
	 * @author David Perez
	 *
	 * @param <T>
	 */
	private static class TraversalNode<T>{
		
		/**
		 * The data of the node. We "visit" the data of each node
		 */
		final private T data;
		
		/**
		 * Left node
		 */
		private TraversalNode<T> left;
		
		/**
		 * Right node
		 */
		private TraversalNode<T> right;
		
		/**
		 * Constructor used for leaf nodes
		 * @param data
		 */
		public TraversalNode(final T data) {
			this.data = data;
		}
		
		/**
		 * Constructor used for nodes with just left childs.
		 * @param data
		 * @param left
		 */
		public TraversalNode(final T data, final TraversalNode<T> left) {
			this.data = data;
			this.left = left;
		}
		
		/**
		 * Constructor used for nodes with both, left and right childs.
		 * @param data
		 * @param left
		 * @param right
		 */
		public TraversalNode(final T data, final TraversalNode<T> left, final TraversalNode<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		/**
		 * Overriden for pretty print for debugging
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(this.data).append(" -> ");
			return builder.toString();
		}
		
	}
	
	/**
	 * Binary tree in-order traversal
	 * @param node
	 */
	public void inOrderTraversal(final TraversalNode<T> node) {
		if(node == null) {
			return;
		}
		inOrderTraversal(node.left);
		visit(node);
		inOrderTraversal(node.right);
	}
	
	/**
	 * Binary tree pre-order traversal
	 * @param node
	 */
	public void preOrderTraversal(final TraversalNode<T> node) {
		if(node == null) {
			return;
		}
		visit(node);
		preOrderTraversal(node.left);
		preOrderTraversal(node.right);
	}

	/**
	 * Binary tree post-order traversal
	 * @param node
	 */
	private void postOrderTraversal(final TraversalNode<T> node) {
		if(node == null) {
			return;
		}
		postOrderTraversal(node.left);
		postOrderTraversal(node.right);
		visit(node);
	}

	/**
	 * Here goes the code that handles the "visit" to the node.
	 * In this simple case, we just print the node's data
	 * @param node
	 */
	private void visit(final TraversalNode<T> node) {
		System.out.print(node);
	}
	
	/**
	 * Main used to run the code
	 * @param args
	 */
	public static void main(final String[] args) {

		/* Use this node as example:
		digraph {
		 	node[shape=circle]
		    f->d
		    d->b
		    b->a
		    b->c
		    d->e
		    f->j
		    j->g
		    g->i
		    j->k
		}
		*/
		TraversalNode<String> k = new TraversalNode<String>("k");
		TraversalNode<String> i = new TraversalNode<String>("i");
		TraversalNode<String> g = new TraversalNode<String>("g", i);
		TraversalNode<String> j = new TraversalNode<String>("j", g, k);
		TraversalNode<String> e = new TraversalNode<String>("e");
		TraversalNode<String> c = new TraversalNode<String>("c");
		TraversalNode<String> a = new TraversalNode<String>("a");
		TraversalNode<String> b = new TraversalNode<String>("b", a, c);
		TraversalNode<String> d = new TraversalNode<String>("d", b, e);
		TraversalNode<String> f = new TraversalNode<String>("f", d, j);
		
		
		Traversal<String> traversal = new Traversal<String>();
		
		System.out.println("\nPre order");
		traversal.preOrderTraversal(f);
		
		System.out.println("\nIn order");
		traversal.inOrderTraversal(f);
		
		System.out.println("\nPost order");
		traversal.postOrderTraversal(f);
		
	}


}
