package datastructures.sorting.bycomparison;

/**
 * Merge sort algorithm
 * 
 * Runtime: O( N * log(N) )
 * Memory: Depends
 * 
 * @author David Perez
 *
 */
public class Merge {

	private static int[] mergeSort(int[] unsorted) {
		return new int[unsorted.length];
	}
	
	public static void main(String[] args) {
		int [] unsorted = {38, 27, 43, 3, 9, 82, 10};
		int [] sorted = {3, 9, 10, 27, 38, 43, 50};
		expectEqual(sorted, mergeSort(unsorted), "Returns an unsorted array");
	}

	private static void expectEqual(int[] expected, int[] received, String message) {
		if(expected.length != received.length) {
			System.err.println("Error: Output side is different");
			return;
		}
		
		for(int i = 0; i < expected.length; i++) {
			if(expected[i] != received[i]) {
				System.err.println("Error: It is not sorted");
				return;
			}
		}
		
		System.out.println("Success");
		
	}

	
}
