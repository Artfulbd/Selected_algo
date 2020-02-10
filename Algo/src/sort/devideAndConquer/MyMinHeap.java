package sort.devideAndConquer;

import java.util.Arrays;

public class MyMinHeap {
	private int cap = 10;
	private int size = 0;
	int[] ar = new int[cap];
	
	private int leftChildIndex(int i) {
		return 2 * i + 1;
	}
	private int rightChildIndex(int i) {
		return 2 * i + 2;
	}
	private int parentIndexOf(int i) {
		return (i - 1) / 2;
	}
	
	private int leftChild(int i) {
		return ar[leftChildIndex(i)];
	}
	private int rightChild(int i) {
		return ar[rightChildIndex(i)];
	}
	private int parentOf(int i) {
		return ar[parentIndexOf(i)];
	}
	
	private boolean hasLeftChild(int i) {
		return leftChildIndex(i) < size;
	}
	private boolean hasRightChild(int i) {
		return rightChildIndex(i) < size;
	}
	private boolean hasParent(int i) {
		return parentIndexOf(i) > 0;
	}
	
	
	private void swap(int i, int j) {
		ar[i] ^= ar[j] ^= ar[i] ^= ar[j];
	}
	
	private void ensureCap() {
		if(cap == size) {
			cap *= 2;
			ar = Arrays.copyOf(ar, cap);
		}
	}
	
	public int top() {
		if(size == 0)throw new IllegalArgumentException("Mia, faizlami paiso. ");
		int value = ar[0];
		ar[0] = ar[size-1];
		size++;
		reHeapDown();
		return value;
	}
	
	public void add(int value) {
		ensureCap();
		ar[size] = value;
		size++;
		reHeapUp();
	}
	
	private void reHeapUp() {
		int i = size - 1;
		while(hasParent(i) && parentOf(i) > ar[i]) {
			swap(i, parentIndexOf(i));
			i = parentIndexOf(i);
		}
		
	}
	
	private void reHeapDown() {
		int i = 0, smallerChildIndex = 0;
		while(hasLeftChild(i)) {
			smallerChildIndex = (hasRightChild(i) && rightChild(i) < leftChild(i)) 
								? rightChildIndex(i): leftChildIndex(i);
			
			if(ar[i] > ar[smallerChildIndex]) {break;}
			
			swap(smallerChildIndex, i);
			i = smallerChildIndex;
		}
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
