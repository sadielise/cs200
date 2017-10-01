import java.util.ArrayList;
import java.util.Arrays;


public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HeapADT<Integer> heap = new HeapADT<Integer>(10);
		heap.heapInsert(1);
		heap.heapInsert(2);
		heap.heapInsert(3);
		heap.heapInsert(5);
		System.out.println("heap is: \n"+heap);
		System.out.println("delete: "+heap.heapDelete());
		System.out.println("delete: "+heap.heapDelete());
		System.out.println("\nnew heap:\n"+heap);
		
		//uncomment this to run heapsort using your heap:
		heapSort(12);
	}
	public static void heapSort(int size){
		Randoms rand = new Randoms();
		int data[] = rand.rand_gen(size);
		HeapADT<Integer> heap = new HeapADT<Integer>(size);
		for(int i = 0; i < data.length; i++){
			heap.heapInsert(data[i]);
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		while(!heap.heapIsEmpty()){
			result.add(heap.heapDelete());
		}
		Integer sorted[] = new Integer[result.size()];
		sorted = result.toArray(sorted);
		System.out.println("Heap Sort test:");
		System.out.println("original: \n"+Arrays.toString(data));
		System.out.println("Reversed Sorted: \n"+Arrays.toString(sorted));
	}
}
