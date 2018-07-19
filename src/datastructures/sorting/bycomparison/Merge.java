package datastructures.sorting.bycomparison;

/**
 * Merge sort algorithm
 * 
 * Runtime: O( N * log(N) )
 * Memory: It depends
 * 
 * @author David Perez
 *
 */
public class Merge {

	/**
	 * Public interface of the merge sort algorithm
	 * @param array
	 * @return
	 */
	public static int[] mergeSort(int[] array) {
		int low = 0;
		int high = array.length - 1;
		mergeSort(array, low, high);
		return array;
	}
	
	/**
	 * Iterative method for merge sort
	 * @param array
	 * @param low
	 * @param high
	 */
	private static void mergeSort(int[] array, int low, int high){		
		if(low >= high) {
			return;
		}
		
		int middle = (high + low) / 2;
		mergeSort(array, low, middle);
		mergeSort(array, middle + 1, high);
		merge(array, low, middle, high);
		
	}
	
	/**
	 * Base case
	 * @param array
	 * @param low
	 * @param middle
	 * @param high
	 * @return
	 */
	private static int[] merge(int[] array, int low, int middle, int high) {
		int N = high - low + 1;
		int [] temp = new int[N];
		
		int left = low;
		int right = middle + 1;
		int current = 0;
		
		while(left <= middle && right <= high) {			
			if(array[left] <= array[right]) {
				temp[current] = array[left];
				left++;
			}else {
				temp[current] = array[right];
				right++;
			}
			current++;
		}
		
		while(left <= middle) {
			temp[current] = array[left];
			left++;
		}
		
		while(right <= high) {
			temp[current] = array[right];
			right++;			
		}
		
		for(int i = 0; i < temp.length; i++) {
			array[low+i] = temp[i];
		}
		
		return array;
		
	}

	/**
	 * Tests
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Unit test the base case
		 */
		
		int[] array4 = new int[] {27, 38, 3, 43, 9, 50, 10};
		int low4 = 0;
		int high4 = 3;
		int middle4 = (high4 + low4) / 2;
		expectEqual(new int[] {3, 27, 38, 43, 9, 50, 10}, 
				merge(array4, low4, middle4, high4),
				"base case");
		
		/**
		 * Acceptance test
		 */
		int [] unsorted = {38, 27, 43, 3, 9, 50, 10};
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
				StringBuffer sb = new StringBuffer();
				sb.append("\nError: It is not sorted [");
				for(int item : received) {
					sb.append(item).append(", ");
				}
				sb.append("] ").append(message);
				System.err.println(sb.toString());
				return;
			}
		}
		
		System.out.println("Success: " + message);
		
	}

	
}
