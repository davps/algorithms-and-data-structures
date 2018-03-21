package datastructures.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxHeap {
	public static void main(String[] args) {
		
		List<Integer> mockHeadA = new ArrayList<Integer>();
		MaxHeap h = new MaxHeap();
		h.setHeapForTest(mockHeadA);
		h.insert(10);
		AssertEqual(mockHeadA.get(0), null);
		AssertEqual(mockHeadA.get(1), 10);
		h.insert(20);
		AssertEqual(mockHeadA, Arrays.asList(new Integer[] {null, 20, 10}));
		
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

}
