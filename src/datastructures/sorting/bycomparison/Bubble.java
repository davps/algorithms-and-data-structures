package datastructures.sorting.bycomparison;

/**
 * Bubble sort algorithm
 * Runtime: O(N*N)
 * Memory: O(1)
 * 
 * @author David Perez
 *
 */
public class Bubble {

	public static int[] sort(int[] arr) {
		int n = arr.length;
		
		for(int i = 0; i < (n-1); i++) {
			for(int j = 0; j < (n-i-1); j++) {
				if(arr[j]>arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = {64,25,12,22,11};
		int[] sorted = sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(sorted[i]);
		}

	}

}
