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
	int id;
	int priority;
	String message;
	
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
 * For an interactive visualization visit
 * <a href="https://visualgo.net/en/heap">Visualgo</a>
 * @author David Perez
 *
 * @param <T>
 */
public class MaxHeap<T extends Comparable<T>> {
	
	/**
	 * Heap array
	 */
	List<T> heap;
	
	public MaxHeap() {
		this.heap = new ArrayList<>();

		//null because we will not use the index zero 
		//for the heap array to make the math simpler
		this.heap.add(null); 
	}
	
	/**
	 * Create a heap from an array
	 * @param arr
	 * @throws Exception
	 */
	public void create(T[] arr) throws Exception {
		if(!isEmpty()) throw new Exception("this method works only if the heap is empty");
		if(arr == null) return;
		
		for(int i = 0; i < arr.length; i++) {
			insert(arr[i]);
		}
	}

	/**
	 * Verify if the heap is empty
	 * @return true if the heap is empty, otherwise, false.
	 */
	public boolean isEmpty() {
		return (last() == 0);
	}
		
	/**
	 * Insert item to tail of the the heap array.
	 * Runtime: O(1)
	 * @param t Object to be inserted. Implements {@code Comparable}
	 * @throws Exception 
	 */
	public void insert(T t) throws Exception {
		//add the data to the tail of the heap (last node of the tree) in order to
		//always have a complete binary tree so the heap property is always maintained
		this.heap.add(t);
				
		//now I need to bubble up the inserted item to fix the heap property
		try {
			bubbleUp(last());
		} catch (Exception e) {
			throw new Exception("Insert error " + e.getMessage());
		}
				
	}

	/**
	 * Remove the maximum value from the heap
	 * @return the maximum value
	 * @throws Exception
	 */
	public T deleteMax() throws Exception {
		if(isEmpty()) throw new Exception("Heap is emtpy");
		
		T max = this.heap.get(1); //get a reference the max node (root)
		swap(1, last()); //move the last node as root
		this.heap.remove(last()); //then delete the max;
		
		sortHeap();
		
		return max;
	}

	/**
	 * Sort the heap with O(N*log(N)) runtime.
	 * @throws Exception
	 */
	public void sortHeap() throws Exception {
		if(isEmpty()) return;
		
		for(int i = 1; i <= last(); i++) {
			bubbleDown(i);
		}
	}
	
	/**
	 * Bubble up a node.
	 * @param i index of the node
	 * @throws Exception
	 */
	private void bubbleUp(int i) throws Exception{
		checkIndexRange(i);
		
		if(isRoot(i)) return; //to stop the recursion
		
		int parent = getParent(i);
		T parentNode = this.heap.get(parent);
		T node = this.heap.get(i);
		if(node.compareTo(parentNode) > 0) {
			swap(parent, i);
			bubbleUp(parent);
		}
	}

	/**
	 * Swap elements of the heap array
	 * @param a index of the first element
	 * @param b index of the second element
	 * @throws Exception 
	 */
	private void swap(int a, int b) throws Exception {
		if(a < 1 || b < 1) throw new Exception("Index out of boundary for swap("+a+","+b+")");
		T temp = this.heap.get(a);
		this.heap.set(a, heap.get(b));
		this.heap.set(b, temp);
	}

	/**
	 * Get a parent node.
	 * @param i Index of the node to process
	 * @return Index of the parent node, or zero if not exist
	 */
	private int getParent(int i) throws Exception {
		if(i < 1) throw new Exception("Index out of boundary a["+i+"], cannot get the parent for this.");	
		
		int p = i / 2;
		return p;
	}

	/**
	 * Verify if a node is the root of the tree
	 * @param i the index of the node
	 * @return
	 */
	private boolean isRoot(int i) {
		return i == 1;
	}

	/**
	 * Get the index of the last node of the heap
	 * @return The index of the node
	 */
	private int last() {
		int N = this.heap.size() - 1;
		return N;
	}

	/**
	 * Bubble down a node.
	 * @param i index of the node
	 * @throws Exception
	 */
	private void bubbleDown(int i) throws Exception {
		checkIndexRange(i);
		
		if(isLeaf(i)) return; //to stop the recursion

		int children = getLeftChildren(i);
		
		if(hasRightChildren(children)) {
			T left = this.heap.get(children);
			T right = this.heap.get(children+1);
			if(left.compareTo(right) <= 0) {
				children++;
			}
		}
		
		T childrenNode = this.heap.get(children);
		T node = this.heap.get(i);
		if(node.compareTo(childrenNode) < 0) {
			swap(children, i);
			bubbleDown(children);
		}		
	}

	/**
	 * Check if a node has a right children
	 * @return
	 */
	private boolean hasRightChildren(int leftChildren) {
		return (leftChildren < last());
	}

	/**
	 * Get the left children of a node, if exist
	 * @param i index of the node
	 * @return index of the left child node
	 */
	private int getLeftChildren(int i) {
		int ch = i * 2;
		return ch;
	}

	/**
	 * Verify if a not is a leaf
	 * @param i index of the node
	 * @return
	 */
	private boolean isLeaf(int i) {
		return (i * 2) > last();
	}

	/**
	 * Common exception handlers
	 * @param i
	 * @throws Exception
	 */
	private void checkIndexRange(int i) throws Exception {
		if(i < 1) throw new Exception("Method input error. Index should be major than 1.");
		if(i > last()) throw new Exception("Method input error. Index out of bondaries.");
	}

	/**
	 * Set the heap for testing purposes using inversion of control.
	 * This is useful because in this way we can access to the internal state
	 * of the instance for testing purposes.
	 * @param mockHeap The mock heap that will override the original heap of the instance
	 */
	private void setHeapForTest(List<T> mockHeap) {
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
		List<Integer> mockHeadA = new ArrayList<Integer>();
		MaxHeap<Integer> h = new MaxHeap<Integer>();
		AssertEqual(h.last(), 0, "size of the heap");
		try {
			h.bubbleUp(-1);
			AssertTrue(false, "bubbleUp should not work with incorrect indexes");
		}catch(Exception e) {
			AssertTrue(true, e.getMessage());
		}
		try {
			h.bubbleUp(1);
			AssertTrue(false, "bubbleUp should not work with an index out of boundary");
		}catch(Exception e) {
			AssertTrue(true, e.getMessage());
		}
		
		h.setHeapForTest(mockHeadA);		
		AssertEqual(h.last(), 0, "size of the heap");
		AssertTrue(h.isEmpty(), "head is empty");
		
		/*
		 * When there is one node on the heap
		 */
		try {
			h.insert(10);
		} catch (Exception e1) {
			AssertError(e1.getMessage());
		}
		AssertTrue(!h.isEmpty(), "head is not empty");
		AssertTrue(mockHeadA.get(0) == null, "First item of arraylist should be null because we start from index 1");
		AssertEqual(mockHeadA.get(1), 10, "Insert to root, no bubble up");
		AssertEqual(h.last(), 1, "size of the heap");
		try {
			h.bubbleUp(1);
			AssertTrue(true, "bubbleUp should not work with an index out of boundary");
		}catch(Exception e) {
			AssertTrue(false, e.getMessage());
		}
				
		/*
		 * insert() behavior when there are many nodes on the heap
		 */
		try {
			h.insert(20);
		} catch (Exception e1) {
			AssertError(e1.getMessage());
		}
		AssertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 20, 10}), "Insert and one bubble up");
		AssertEqual(h.last(), 2, "size of the heap");
		
		try {
			h.insert(50);
		} catch (Exception e1) {
			AssertError(e1.getMessage());
		}
		AssertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 50, 10, 20}), "Insert and bubble up 50");
		
		try {
			h.insert(70);
		} catch (Exception e1) {
			AssertError(e1.getMessage());
		}
		AssertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 70, 50, 20, 10}), 
				"insert and bubble up 70");
		
		try {
			h.insert(5);
		} catch (Exception e1) {
			AssertError(e1.getMessage());
		}
		AssertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 70, 50, 20, 10, 5}), 
				"insert and dont bubble up");
		
		try {
			h.insert(12);
		} catch (Exception e1) {
			AssertError(e1.getMessage());
		}
		AssertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 70, 50, 20, 10, 5, 12}), 
				"insert and dont bubble up");
		
		/*
		 * Queries on the existing tree
		 */
		AssertTrue(!h.isLeaf(1), "not a leaf");
		AssertTrue(h.isLeaf(12), "a leaf");
		AssertEqual(h.getLeftChildren(1), 2, "get left children index");
		AssertTrue(h.hasRightChildren(2), "50 has right children");
		AssertTrue(!h.hasRightChildren(6), "12 has not right children");
		
		try {
			AssertEqual(h.getParent(6), 3, "Get parent of leaf node"); 			
			AssertEqual(h.getParent(4), 2, "Get parent of leaf node"); 			
			AssertEqual(h.getParent(1), 0, "Get parent of leaf node"); 			
		} catch (Exception e) {
			AssertError("Something was wrong getting a parent");
		}
		try {
			h.getParent(-1);
			AssertError("Index out of boundary"); 			
		} catch (Exception e) {
			AssertSuccess("Something was wrong getting a parent");
		}
		
		/*
		 * Swap on the heap (created a new heap for this case)
		 */
		List<String> mockHeadB = Arrays.asList(new String[] {null, "a", "b", "c"});
		MaxHeap<String> x = new MaxHeap<String>();
		x.setHeapForTest(mockHeadB);
		try {
			x.swap(1, 2);
			AssertTrue(mockHeadB.get(1).equals("b"), "swap a[1] with a[2]");
			AssertTrue(mockHeadB.get(2).equals("a"), "swap a[1] with a[2]");
		} catch (Exception e) {
			AssertError("swap error " +e.getMessage());
		}
		try {
			x.swap(-1, 0);
			AssertError("swap should trigger but didn't");
		} catch (Exception e) {
			AssertSuccess("swap exception triggered correctly " +e.getMessage());
		}
		
		/*
		 * deleteMax() and sortHeap() behavior when the heap is empty
		 * Created a new heap for this case
		 */
		
		//wrap on array list because I'll resize it
		//https://stackoverflow.com/a/2965808
		List<Integer> mockHeapC = new ArrayList<>(Arrays.asList(new Integer[] {null}));
		MaxHeap<Integer> h2 = new MaxHeap<Integer>();
		h2.setHeapForTest(mockHeapC);
		try {
			h2.deleteMax();	
			AssertError("Should not get here ");
		} catch (Exception e) {
			AssertSuccess("Cannot get max because heap is empty");
		}
		
		try {
			h2.sortHeap();
			AssertSuccess("Heap is emtpy so it should sort anyways (do nothing).");
		} catch (Exception e2) {
			AssertError("Heap is empty so an exception was thrown when trying to sort");
		}
		
		/*
		 * sortHeap() and deleteMax() then there are a single node on the heap
		 */
		try {
			h2.insert(10);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			h2.sortHeap();
		} catch (Exception e1) {
			AssertEqual(mockHeapC.get(1), 10, "Sort one item");
		}
		int max = -1;
		try {
			max = h2.deleteMax();	
			AssertEqual(max, 10, "Returned the max successfully");
			AssertEqual(mockHeapC.size(), 1, "Stack is empty");
		} catch (Exception e) {
			AssertError("deleteMax Should return the max " + e.getMessage());
		}
		
		/*
		 *deleteMax() when there are a tree 
		 */
		try {
			//heap is empty at this point, now fill with two items
			mockHeapC.add(100);
			mockHeapC.add(200);
			h2.bubbleDown(1);
			AssertEqual(mockHeapC.get(1), 200, "bubble down the root");
			AssertEqual(mockHeapC.get(2), 100, "bubble down the root");
			mockHeapC.add(50); 
			mockHeapC.add(40);//now head=[200,100,50,40]
			mockHeapC.set(1, 30); //now head=[30,100,50,40] => I did this to test the bubble up on this scenario
			h2.bubbleDown(1);//now head=[100,40,50,30]
			AssertEqual(mockHeapC.get(1), 100, "bubble down the root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int max2 = -1;
		int max3 = -1;
		int max4 = -1;
		try {
			max2 = h.deleteMax();
			max3 = h.deleteMax();
			max4 = h.deleteMax();
		}catch (Exception e) {
			e.printStackTrace();
		}
		AssertEqual(max2, 70, "get max");
		AssertEqual(max3, 50, "get max");
		AssertEqual(max4, 20, "get max");	
		
		MaxHeap<Integer> h3 = new MaxHeap<>();
		try {
			h3.create(new Integer[] {});
			h3.create(null);
		} catch (Exception e2) {
			AssertError("Should create event with an empty or null array");
		}
		try {
			h3.create(new Integer[] {1,2,3});
			AssertEqual(h3.deleteMax(), 3, "create success");
			AssertEqual(h3.deleteMax(), 2, "create success");
			AssertEqual(h3.deleteMax(), 1, "create success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Priority Queue (PQ) Abstract Data Type (ADT)
		 * with the custom {@Node} class
		 * Applied to manage priorities of attention for patients on a hospital
		 * There are two patients with feber so there is a queue for them
		 */
		List<Node> hospitalHeap = new ArrayList<>();
		hospitalHeap.add(null);
		hospitalHeap.add(new Node(1, 100, "Patient B with feber"));
		hospitalHeap.add(new Node(2, 50, "a patient with flu"));
		hospitalHeap.add(new Node(3, 200, "a patient with a broken leg on a car accident"));
		hospitalHeap.add(new Node(4, 100, "Patient B with feber"));
		MaxHeap<Node> patientPriorityManager = new MaxHeap<Node>();
		patientPriorityManager.setHeapForTest(hospitalHeap);
		try {
			patientPriorityManager.sortHeap();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			Node patientMaxPriority = patientPriorityManager.deleteMax();
			Node patientMediumPriority = patientPriorityManager.deleteMax();
			Node patientMediumPriority2 = patientPriorityManager.deleteMax();
			Node patientLowPriority = patientPriorityManager.deleteMax();
			AssertEqual(patientMaxPriority.id, 3, "pq get max "+patientMaxPriority.message);
			AssertTrue(patientMediumPriority.id == 1 || patientMediumPriority.id == 4, "pq get medium "+patientMediumPriority.message);
			AssertTrue(patientMediumPriority.id == 1 || patientMediumPriority.id == 4, "pq get medium "+patientMediumPriority2.message);
			AssertEqual(patientLowPriority.id, 2, "pq get low "+patientLowPriority.message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void AssertSuccess(String msg) {
		AssertTrue(true, msg);
	}
	
	private static void AssertError(String msg) {
		AssertTrue(false, msg);
	}
	
	private static void AssertTrue(boolean val, String msg) {
		if(val == true) {
			printSuccess(null, null, msg);
		}else {
			printError(null, null, msg);
		}
	}

	private static void AssertEqual(int a, int b, String msg) {
		if(a == b) {
			printSuccess(a, b, msg);
		}else {
			printError(a, b, msg);
		}
	}

	private static void AssertEqual(List<Integer> a, List<Integer> b, String msg) {
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
		
		printSuccess(a, b, msg);
	}
	
	private static void printSuccess(Object a, Object b, String msg) {
		System.out.println("Success:" + msg);
	}

	private static void printError(Object a, Object b, String msg) {
		System.err.println("Fail: " + msg + " Expected " + a + " but got " + b);		
	}

}
