package datastructures.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> {
	
	/**
	 * Heap array
	 */
	List<T> heap;
	
	public MaxHeap() {
		this.heap = new ArrayList<>();
		this.heap.add(null); //because we will not use the index zero for the heap array
	}
	
	/**
	 * Insert item to the heap.
	 * @param t Object to be inserted. Implements {@code Comparable}
	 */
	public void insert(T t) {
		//add the data to the tail of the heap (last node of the tree) in order to
		//always have a balanced tree
		this.heap.add(t);
		
		
		//now I need to bubble up the inserted item
		this.bubbleUp(last());
		
	}

	/**
	 * Get the index of the last node of the heap
	 * @return The index of the node
	 */
	private int last() {
		int N = this.heap.size() - 1;
		return N;
	}


	private void bubbleUp(int n) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Set the heap for testing purposes using inversion of control.
	 * This is useful because in this way we can access to the internal state
	 * of the instance for testing purposes.
	 * @param mockHeap The mock heap that will override the original heap of the instance
	 */
	public void setHeapForTest(List<T> mockHeap) {
		this.heap = mockHeap;
		if(this.heap.size() == 0) {
			this.heap.add(null);//fill index 0 if required
		}
	}
	
	public static void main(String[] args) {
				
		List<Integer> mockHeadA = new ArrayList<Integer>();
		MaxHeap<Integer> h = new MaxHeap<Integer>();
		AssertEqual(h.last(), 0, "size of the heap");
		h.setHeapForTest(mockHeadA);
		AssertEqual(h.last(), 0, "size of the heap");
		h.insert(10);
		AssertTrue(mockHeadA.get(0) == null, "First item of arraylist should be null because we start from index 1");
		AssertEqual(mockHeadA.get(1), 10, "Insert to root, no bubble up");
		AssertEqual(h.last(), 1, "size of the heap");
				
		h.insert(20);
		AssertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 20, 10}), "Insert and one bubble up");
		AssertEqual(h.last(), 2, "size of the heap");
		
		h.insert(50);
		AssertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 50, 10, 20}), "bubble up 50");
		
		h.insert(70);
		AssertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 70, 50, 20, 10}), 
				"insert and bubble up 70");
		
		h.insert(5);
		AssertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 70, 50, 20, 10, 5}), 
				"insert and dont bubble up");
		
		h.insert(12);
		AssertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 70, 50, 20, 10, 5, 12}), 
				"insert and dont bubble up");
		
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

	//List<Integer> is fine (we don't need generics) because we will just test 
	//with integers
	private static void AssertEqual(List<Integer> a, List<Integer> b, String msg) {
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
