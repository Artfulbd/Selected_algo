package sort.devideAndConquer;

public class MyQuickSort {
	public static void quickSort(int[] ar) {
		doQuickSort(ar, 0, ar.length - 1);
	}
	
	public static void doQuickSort(int[] ar, int left, int right) {
		if(left >= right) return;
		int piv = ar[(left + right) / 2] ;
		
		System.out.print(" pivot:"+ piv);
		
		int x = partition(ar, left, right, piv);		
		doQuickSort(ar, left, x-1);
		doQuickSort(ar, x, right);
		
		System.out.print("\nDuring sort: ");
		for(int a: ar)System.out.print(a + " ");
		
		
	}
	
	public static int partition(int[] ar, int left, int right, int piv) {
		while(left <= right) {
			
			while(ar[left] < piv)left++;
			while(ar[right] > piv)right--;
			
			if(left <= right) {
				int hold = ar[left];
				ar[left] = ar[right];
				ar[right] = hold;
				left++;	right--;
			}
		}
		return left;		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ar = {3, 44, 38, 5, 47, 15, 36, 26, 9, 13};
		
		
		System.out.print("Before sort: ");
		for(int a: ar)System.out.print(a + " ");
		System.out.println("\n");
		
		quickSort(ar);
		System.out.print("\n\nEfter sort: ");
		for(int a: ar)System.out.print(a + " ");
		
		

	}

}
