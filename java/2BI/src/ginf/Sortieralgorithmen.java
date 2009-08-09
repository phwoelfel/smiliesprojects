package ginf;

import java.util.Random;

public class Sortieralgorithmen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random zuf = new Random();
		int[]arr = new int[20];
		for(int i=0; i<arr.length;i++){
			arr[i]=zuf.nextInt(50);
		}
		
		printArray(arr);
		//bubbleSort(arr, 0, arr.length-1);
		quickSort(arr, 0, arr.length-1);
		System.out.println("-----------");
		printArray(arr);
	}
	
	public static void swap(int[] a, int i, int j) { 
		int x = a[i]; 
		a[i] = a[j]; 
		a[j] = x; 
	}
	
	static void quickSort(int[] a, int l, int r) { 
		if (l < r) { 
			int m = partition(a, l, r); 
			quickSort(a, l, m); 
			quickSort(a, m + 1, r); 
		} 
	} 
	
	static int partition(int[] a, int l, int r) { 
		int x = a[(l + r) / 2]; 
		while (l <= r) { 
			while (x < a[r]) r--; 
			while (a[l] < x) l++; 
			if (r <= l) return r; 
			swap(a, l++, r--); 
		} 
		return r; 
	} 


	
	public static void bubbleSort(int[] a, int l, int r) { 
		for (int i = r; l <= i; i--) 
			for (int j = l; j < i; j++) 
				if ( a[j + 1] < a[j] ) 
					swap(a, j + 1, j); 
	}
	
	public static void printArray(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
}
