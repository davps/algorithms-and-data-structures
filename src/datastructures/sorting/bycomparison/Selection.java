package datastructures.sorting.bycomparison;

/**
 * Selection sort algorithm
 * Runtime: O(N*N)
 * Memory: O(1)
 * 
 * @author David Perez
 *
 */
public class Selection {

	public static int[] sort(int[] arr) {
		int n = arr.length;
		
		for(int i = 0; i < n; i++) {
			int min = arr[i];
			int index = i;
			
			for(int j = (i+1); j < n; j++) {
				if(arr[j] < min) {
					min = arr[j];
					index = j;
				}
			}
			
			if(i != index) {
				int temp = arr[i];
				arr[i] = arr[index];
				arr[index] = temp;
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
