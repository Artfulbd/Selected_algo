package sort.devideAndConquer;

public class MyMergeSort {
	public static void merge_sort(int[] ar) {
		do_merge_sort(ar, new int[ar.length], 0, ar.length - 1);
	}
	public static void do_merge_sort(int[] ar, int[] temp, int leftStart, int rightEnd) {
		if(leftStart >= rightEnd)return;
		int mid = (leftStart + rightEnd) / 2;
		do_merge_sort(ar, temp, leftStart, mid);
		do_merge_sort(ar, temp,  mid + 1, rightEnd);
		merge_array(ar, temp,  leftStart, rightEnd);
		
		System.out.print("\nDuring sort: ");
		for(int a: ar)System.out.print(a + " ");
	}
	public static void merge_array(int[] ar,int[] temp, int leftStart, int rightEnd) {
		int leftEnd = (leftStart + rightEnd) / 2;
		int rightStart = leftEnd + 1;
		int left = leftStart, right = rightStart;
		int index = leftStart, size  = rightEnd - leftStart + 1;
		
		while(left <= leftEnd && right <= rightEnd) {
			temp[index] = ar[left] <= ar[right] ? ar[left++] : ar[right++];
			index++;
		}
				
		System.arraycopy(ar, left, temp, index, leftEnd - left + 1);
		System.arraycopy(ar, right, temp, index, rightEnd - right + 1);
		System.arraycopy(temp, leftStart, ar, leftStart, size);
	}
	
	public static void main(String[] saria) {
		int[] ar = {3, 44, 38, 5, 47, 15, 36, 26, 9, 13};
		
		System.out.print("Before sort: ");
		for(int a: ar)System.out.print(a + " ");
		System.out.println("\n");
		
		merge_sort(ar);
		System.out.print("\n\nEfter sort: ");
		for(int a: ar)System.out.print(a + " ");
		
	}

}
