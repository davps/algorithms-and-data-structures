package datastructures.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class used for Priority Queue tests.
 * Each message have a priority 
 * @author David Perez
 *
 * @param <T>
 */
class Node implements Comparable<Node>{

	/**
	 * Unique id
	 */
	private final int id;
	
	/**
	 * Priority level. Bigger value means higher priority
	 */
	private final int priority;
	
	/**
	 * The payload that have a priority. In other custom class implementations
	 * this could be an object, for example.
	 */
	private final String message;
	
	public int getId() {
		return this.id;
	}
	
	public String getMessage() {
		return this.message;
	}

	public Node(int id, int priority, String message) {
		this.id = id;		
		this.priority = priority;
		this.message = message;
	}

	@Override
	public int compareTo(Node o) {
		if(this.priority > o.priority) {
			return 1;
		}else if(this.priority == o.priority) {
			return 0;
		}else { //this.priority < o.priority
			return -1;
		}
	}
	
}

/**
 * A heap is a complete binary tree. The heap have this property: 
 * for each node u, the priority
 * of the content at u is at least as high as for all's u childrens.
 * <p>
 * A good summary can be found.
 * <a href="http://www-bcf.usc.edu/~dkempe/CS104/10-24.pdf"> here </a>
 * <p>
 * For an interactive visualization visit:
 * <ul>
 * 	<li><a href="https://visualgo.net/en/heap">Visualgo</a></li>
 *  <li><a href="https://www.cs.usfca.edu/~galles/visualization/Heap.html">Data 
 *  structure visualizations</a></li>
 * </ul>
 * My implementation was inspired on
 * <a href="https://gist.github.com/davps/276e3853d85954a8c314f34353f27f9a">this</a>
 * 
 * I recommend reading the wikipedia 
 * <a href="https://en.wikipedia.org/wiki/Heap_(data_structure)">wikipedia</a> 
 * <a href="https://en.wikipedia.org/wiki/Binary_heap">page</a>
 * as the last step of the learning process
 * 
 * @author David Perez
 *
 * @param <T>
 */
public class MaxHeap<T extends Comparable<T>> {
	
	/**
	 * Heap array
	 */
	private List<T> heap;
	
	/**
	 * On the constructor I create a heap array and setup it to start from index 1
	 */
	public MaxHeap() {
		this.heap = new ArrayList<>();

		//null because we will not use the index zero 
		//for the heap array to make the math simpler
		this.heap.add(null); 
	}
	
	/**
	 * Create a heap from an array.
	 * Runtime O(N*log(N))
	 * @param arr
	 * @throws Exception
	 */
	public void heapify(final T... arr) throws Exception {
		if(!isEmpty()) {
			throw new Exception("this method works only if the heap is empty");
		}
		if(arr == null) {
			return;
		}
		
		for(int i = 0; i < arr.length; i++) {
			insert(arr[i]);
		}
	}

	/**
	 * Verify if the heap is empty
	 * Runtime O(1)
	 * @return true if the heap is empty, otherwise, false.
	 */
	public boolean isEmpty() {
		return last() == 0;
	}
	
	/**
	 * Get the size of the heap
	 * @return number of nodes of the heap
	 */
	public int size() {
		return last();
	}
		
	/**
	 * Insert item to tail of the the heap array.
	 * Runtime: O(log(N))
	 * @param item Object to be inserted. Implements {@code Comparable}
	 * @throws Exception 
	 */
	public void insert(final T item) throws Exception {
		//add the data to the tail of the heap (last node of the tree) in order to
		//always have a complete binary tree so the heap property is always maintained
		this.heap.add(item);
				
		//now I need to bubble up the inserted item to fix the heap property
		try {
			siftUp(last());
		} catch (Exception e) {
			throw new Exception("Insert error " + e.getMessage());
		}
				
	}

	/**
	 * Find the maximum item of the heap
	 * @return the root node of the heap tree
	 */
	public T findMax() {
		return this.heap.get(1);
	}

	/**
	 * Returns the maximum value from the heap after removing form the heap.
	 * Runtime O(N*log(N)) with the actual sortHeap implementation.
	 * @return the maximum value
	 * @throws Exception
	 */
	public T extractMax() throws Exception {
		if(isEmpty()) {
			throw new Exception("Heap is emtpy");
		}
		
		final T max = this.heap.get(1); //get a reference the max node (root)
		swap(1, last()); //move the last node as root
		this.heap.remove(last()); //then delete the max;
		
		sortHeap();
		
		return max;
	}

	/**
	 * Sort the heap 
 	 * Runtime: O(N*log(N))
	 * @throws Exception
	 */
	public void sortHeap() throws Exception {
		if(isEmpty()) {
			return;
		}
		
		for(int i = 1; i <= last(); i++) {
			siftDown(i);
		}
	}
	
	/**
	 * Bubble up a node as long as needed to restore the heap property.
	 * @param node index of the node
	 * @throws Exception
	 */
	/*
	 * Internally, sometimes I refer to this operation as 'bubble up'
	 */
	private void siftUp(final int node) throws Exception{
		checkIndexRange(node);
		
		if(isRoot(node)) {
			return; //to stop the recursion
		}
		
		final int parent = getParent(node);
		final T parentNode = this.heap.get(parent);
		final T currentNode = this.heap.get(node);
		if(currentNode.compareTo(parentNode) > 0) {
			swap(parent, node);
			siftUp(parent);
		}
	}

	/**
	 * Swap elements of the heap array
	 * @param a index of the first element
	 * @param b index of the second element
	 * @throws Exception 
	 */
	private void swap(final int a, final int b) throws Exception {
		if(a < 1 || b < 1) {
			throw new Exception("Index out of boundary for swap("+a+","+b+")");
		}
		final T temp = this.heap.get(a);
		this.heap.set(a, heap.get(b));
		this.heap.set(b, temp);
	}

	/**
	 * Get a parent node.
	 * @param index Index of the node to process
	 * @return Index of the parent node, or zero if not exist
	 */
	private int getParent(final int index) throws Exception {
		if(index < 1) {
			throw new Exception("Index out of boundary a["+index+"], cannot get the parent for this.");	
		}
		
		/*
		 * We use this division because it is a balanced tree
		 * Note that it takes the integer part of the division
		 */
		final int parent = index / 2;
		return parent;
	}

	/**
	 * Verify if a node is the root of the tree
	 * @param node the index of the node
	 * @return
	 */
	private boolean isRoot(final int node) {
		/*
		 * We use one and not zero because the heap array starts with 1
		 * to simplify the math on the implementation.
		 * this.heap on position zero is null, and not used.
		 */
		return node == 1;
	}

	/**
	 * Get the index of the last node of the heap
	 * @return The index of the node
	 */
	private int last() {
		/*
		 * Minus one because we didn't use the position zero of the heap
		 */
		return (this.heap.size() - 1);
	}

	/**
	 * Bubble down a node as long as needed to restore the heap property.
	 * @param node index of the node
	 * @throws Exception
	 */
	/*
	 * Internally, sometimes I refer to this operation as 'bubble down'
	 */
	private void siftDown(final int node) throws Exception {
		checkIndexRange(node);
		
		if(isLeaf(node)) {
			return; //to stop the recursion
		}

		//by default we compare for bubble down to the left children
		int children = getLeftChildren(node);
		
		//...but
		if(hasRightChildren(children)) {
			final T left = this.heap.get(children);
			final T right = this.heap.get(children+1);
			if(left.compareTo(right) <= 0) {
				//then we will compare for bubble down with
				//the right children (that's why we increase
				//the index by 1)
				children++;
			}
		}
		
		final T childrenNode = this.heap.get(children);
		final T currentNode = this.heap.get(node);
		if(currentNode.compareTo(childrenNode) < 0) {
			swap(children, node);
			siftDown(children);
		}		
	}

	/**
	 * Check if a node has a right children
	 * @return
	 */
	private boolean hasRightChildren(final int leftChildren) {
		/*
		 * The comparison is as simple as this because we are
		 * working with a balanced tree, where only the last leaf
		 * node could potentially not have a right brother node
		 */
		return leftChildren < last();
	}

	/**
	 * Get the left children of a node, if exist
	 * @param index index of the node
	 * @return index of the left child node
	 */
	private int getLeftChildren(final int index) {
		/*
		 * We just multiply by two since it is a balanced tree.
		 * Warning: If it is a leaf, the value returned will not be realistic
		 */
		return index * 2;
	}

	/**
	 * Verify if the node isn't a leaf node
	 * @param index index of the node
	 * @return
	 */
	private boolean isLeaf(final int index) {
		/*
		 * A leaf didn't have childrens, i.e., if getLeftChildren()
		 * returns a value major than the size of the heap, then
		 * it in fact didn't exist
		 */
		return (getLeftChildren(index)) > last();
	}

	/**
	 * Common exception handlers
	 * @param index
	 * @throws Exception
	 */
	private void checkIndexRange(int index) throws Exception {
		if(index < 1) {
			throw new Exception("Method input error. Index should be major than 1.");
		}
		if(index > last()) {
			throw new Exception("Method input error. Index out of bondaries.");
		}
	}

	/**
	 * Set the heap for testing purposes using inversion of control.
	 * This is useful because in this way we can access to the internal state
	 * of the instance for testing purposes.
	 * @param mockHeap The mock heap that will override the original heap 
	 * of the instance
	 */
	private void setHeapForTest(final List<T> mockHeap) {
		this.heap = mockHeap;
		if(this.heap.size() == 0) {
			this.heap.add(null);//fill index 0 if required
		}
	}

	/**
	 * I implemented the test cases on this main function.
	 * There is a full test coverage, without any external dependencies
	 * and on a single file
	 */
	public static void main(String[] args) {
		
		/*
		 * When the heap is empty
		 */
		final List<Integer> mockHeadA = new ArrayList<Integer>();
		final MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
		assertEqual(maxHeap.last(), 0, "size of the heap");
		try {
			maxHeap.siftUp(-1);
			assertTrue(false, "bubbleUp should not work with incorrect indexes");
		}catch(Exception e) {
			assertTrue(true, e.getMessage());
		}
		try {
			maxHeap.siftUp(1);
			assertTrue(false, "bubbleUp should not work with an index out of boundary");
		}catch(Exception e) {
			assertTrue(true, e.getMessage());
		}
		
		maxHeap.setHeapForTest(mockHeadA);		
		assertEqual(maxHeap.last(), 0, "size of the heap");
		assertTrue(maxHeap.isEmpty(), "head is empty");
		
		/*
		 * When there is one node on the heap
		 */
		try {
			maxHeap.insert(10);
		} catch (Exception e1) {
			assertError(e1.getMessage());
		}
		assertTrue(!maxHeap.isEmpty(), "head is not empty");
		assertTrue(mockHeadA.get(0) == null, "First item of arraylist should be null because we start from index 1");
		assertEqual(mockHeadA.get(1), 10, "Insert to root, no bubble up");
		assertEqual(maxHeap.last(), 1, "size of the heap");
		try {
			maxHeap.siftUp(1);
			assertTrue(true, "bubbleUp should not work with an index out of boundary");
		}catch(Exception e) {
			assertTrue(false, e.getMessage());
		}
				
		/*
		 * insert() behavior when there are many nodes on the heap
		 */
		try {
			maxHeap.insert(20);
		} catch (Exception e1) {
			assertError(e1.getMessage());
		}
		assertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 20, 10}), "Insert and one bubble up");
		assertEqual(maxHeap.last(), 2, "size of the heap");
		
		try {
			maxHeap.insert(50);
		} catch (Exception e1) {
			assertError(e1.getMessage());
		}
		assertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 50, 10, 20}), "Insert and bubble up 50");
		
		try {
			maxHeap.insert(70);
		} catch (Exception e1) {
			assertError(e1.getMessage());
		}
		assertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 70, 50, 20, 10}), 
				"insert and bubble up 70");
		
		try {
			maxHeap.insert(5);
		} catch (Exception e1) {
			assertError(e1.getMessage());
		}
		assertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 70, 50, 20, 10, 5}), 
				"insert and dont bubble up");
		
		try {
			maxHeap.insert(12);
		} catch (Exception e1) {
			assertError(e1.getMessage());
		}
		assertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 70, 50, 20, 10, 5, 12}), 
				"insert and dont bubble up");
		
		/*
		 * Queries on the existing tree
		 */
		assertTrue(!maxHeap.isLeaf(1), "not a leaf");
		assertTrue(maxHeap.isLeaf(12), "a leaf");
		assertEqual(maxHeap.getLeftChildren(1), 2, "get left children index");
		assertTrue(maxHeap.hasRightChildren(2), "50 has right children");
		assertTrue(!maxHeap.hasRightChildren(6), "12 has not right children");
		
		try {
			assertEqual(maxHeap.getParent(6), 3, "Get parent of leaf node"); 			
			assertEqual(maxHeap.getParent(4), 2, "Get parent of leaf node"); 			
			assertEqual(maxHeap.getParent(1), 0, "Get parent of leaf node"); 			
		} catch (Exception e) {
			assertError("Something was wrong getting a parent");
		}
		try {
			maxHeap.getParent(-1);
			assertError("Index out of boundary"); 			
		} catch (Exception e) {
			assertSuccess("Something was wrong getting a parent");
		}
		
		/*
		 * Swap on the heap (created a new heap for this case)
		 */
		final List<String> mockHeadB = Arrays.asList(new String[] {null, "a", "b", "c"});
		final MaxHeap<String> xHeap = new MaxHeap<String>();
		xHeap.setHeapForTest(mockHeadB);
		try {
			xHeap.swap(1, 2);
			assertTrue(mockHeadB.get(1).equals("b"), "swap a[1] with a[2]");
			assertTrue(mockHeadB.get(2).equals("a"), "swap a[1] with a[2]");
		} catch (Exception e) {
			assertError("swap error " +e.getMessage());
		}
		try {
			xHeap.swap(-1, 0);
			assertError("swap should trigger but didn't");
		} catch (Exception e) {
			assertSuccess("swap exception triggered correctly " +e.getMessage());
		}
		
		/*
		 * deleteMax() and sortHeap() behavior when the heap is empty
		 * Created a new heap for this case
		 */
		
		//wrap on array list because I'll resize it
		//https://stackoverflow.com/a/2965808
		final List<Integer> mockHeapC = new ArrayList<>(Arrays.asList(new Integer[] {null}));
		final MaxHeap<Integer> heap2 = new MaxHeap<Integer>();
		heap2.setHeapForTest(mockHeapC);
		try {
			heap2.extractMax();	
			assertError("Should not get here ");
		} catch (Exception e) {
			assertSuccess("Cannot get max because heap is empty");
		}
		
		try {
			heap2.sortHeap();
			assertSuccess("Heap is emtpy so it should sort anyways (do nothing).");
		} catch (Exception e2) {
			assertError("Heap is empty so an exception was thrown when trying to sort");
		}
		
		/*
		 * sortHeap() and deleteMax() then there are a single node on the heap
		 */
		try {
			heap2.insert(10);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			heap2.sortHeap();
		} catch (Exception e1) {
			assertEqual(mockHeapC.get(1), 10, "Sort one item");
		}
		int max = -1;
		try {
			max = heap2.extractMax();	
			assertEqual(max, 10, "Returned the max successfully");
			assertEqual(mockHeapC.size(), 1, "Stack is empty");
		} catch (Exception e) {
			assertError("deleteMax Should return the max " + e.getMessage());
		}
		
		/*
		 *deleteMax() when there are a tree 
		 */
		try {
			//heap is empty at this point, now fill with two items
			mockHeapC.add(100);
			mockHeapC.add(200);
			heap2.siftDown(1);
			assertEqual(mockHeapC.get(1), 200, "bubble down the root");
			assertEqual(mockHeapC.get(2), 100, "bubble down the root");
			mockHeapC.add(50); 
			mockHeapC.add(40);//now head=[200,100,50,40]
			mockHeapC.set(1, 30); //now head=[30,100,50,40] => I did this to test the bubble up on this scenario
			heap2.siftDown(1);//now head=[100,40,50,30]
			assertEqual(mockHeapC.get(1), 100, "bubble down the root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int max2 = -1;
		int max3 = -1;
		int max4 = -1;
		try {
			max2 = maxHeap.extractMax();
			max3 = maxHeap.extractMax();
			max4 = maxHeap.extractMax();
		}catch (Exception e) {
			e.printStackTrace();
		}
		assertEqual(max2, 70, "get max");
		assertEqual(max3, 50, "get max");
		assertEqual(max4, 20, "get max");	
		
		final MaxHeap<Integer> heap3 = new MaxHeap<>();
		try {
			heap3.heapify(new Integer[] {});
			heap3.heapify(null);
		} catch (Exception e2) {
			assertError("Should create event with an empty or null array");
		}
		try {
			heap3.heapify(new Integer[] {1,2,3});
			assertEqual(heap3.extractMax(), 3, "create success");
			assertEqual(heap3.extractMax(), 2, "create success");
			assertEqual(heap3.extractMax(), 1, "create success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Priority Queue (PQ) Abstract Data Type (ADT)
		 * with the custom {@Node} class
		 * Applied to manage priorities of attention for patients on a hospital
		 * There are two patients with feber so there is a queue for them
		 */
		final List<Node> hospitalHeap = new ArrayList<>();
		hospitalHeap.add(null);
		hospitalHeap.add(new Node(1, 100, "Patient B with feber"));
		hospitalHeap.add(new Node(2, 50, "a patient with flu"));
		hospitalHeap.add(new Node(3, 200, "a patient with a broken leg on a car accident"));
		hospitalHeap.add(new Node(4, 100, "Patient B with feber"));
		final MaxHeap<Node> patients = new MaxHeap<Node>();
		patients.setHeapForTest(hospitalHeap);
		try {
			patients.sortHeap();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			final Node maxPriority = patients.extractMax();
			final Node mediumPriority = patients.extractMax();
			final Node mediumPriority2 = patients.extractMax();
			final Node lowPriority = patients.extractMax();
			assertEqual(maxPriority.getId(), 3, "pq get max "+maxPriority.getMessage());
			assertTrue(mediumPriority.getId() == 1 || mediumPriority.getId() == 4, "pq get medium "+mediumPriority.getMessage());
			assertTrue(mediumPriority.getId() == 1 || mediumPriority.getId() == 4, "pq get medium "+mediumPriority2.getMessage());
			assertEqual(lowPriority.getId(), 2, "pq get low "+lowPriority.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void assertSuccess(final String msg) {
		assertTrue(true, msg);
	}
	
	private static void assertError(final String msg) {
		assertTrue(false, msg);
	}
	
	private static void assertTrue(final boolean val, final String msg) {
		if(val) {
			printSuccess(msg);
		}else {
			printError(null, null, msg);
		}
	}

	private static void assertEqual(final int a, final int b, final String msg) {
		if(a == b) {
			printSuccess(msg);
		}else {
			printError(a, b, msg);
		}
	}

	private static void assertEqual(final List<Integer> a, final List<Integer> b, final String msg) {
		//List<Integer> is fine (we don't need generics) because we will just test 
		//with integers

		if(a.size() != b.size()) {
			printError(a, b, msg);
			return;
		}
	
		for(int i = 0; i < a.size(); i++) {
			if(a.get(i) != b.get(i)) {
				printError(a, b, msg);
				return;
			}
		}
		
		printSuccess(msg);
	}
	
	private static void printSuccess(final String msg) {
		System.out.println("Success:" + msg);
	}

	private static void printError(final Object expectation, final Object result, final String msg) {
		System.err.println("Fail: " + msg + " Expected " + expectation + " but got " + result);		
	}

}
