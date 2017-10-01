import java.util.ArrayList;

//
public class HeapADT<T extends Comparable<T>> {
	ArrayList<T> mainArray; //main storage
	int size;  //index of next free space
	int maxSize; //max size of heap
	
	public HeapADT(int maxS){
		maxSize = maxS;
		size = 0;
		mainArray = new ArrayList<T>(maxSize);
	}

	//inserts T into the heap
	public void heapInsert(T data){
		
		// add node to next open space in array
		mainArray.add(size, data);
		
		// heapify up the heap if necessary
		heapifyUp(size);
		
		// increment size
		size++;
	}

	//deletes and returns the root node in the heap. The rebuilds the heap as neccessary
	public T heapDelete(){
		
		// get the item in the root to return
		T rootItem = mainArray.get(0);
		
		// copy item from last node into root
		mainArray.set(0, mainArray.get(size-1));
		
		// decrement size
		size--;
		
		// restore the heap property
		heapifyDown(0);
		
		// return root
		return rootItem;		
		
	}
	
	// Recursively swap child with parent if the child is greater than the parent
	public void heapifyUp(int index){
	
		int parent = (int) Math.floor((index-1)/2);
		while(parent >= 0 && mainArray.get(index).compareTo(mainArray.get(parent)) > 0){
			swapItems(index, parent);
			index = parent;
			parent = (index-1)/2;	
		}
	}
	
	// find the largest of the two children and swap with parent if the largest child is larger than the parent.
	public void heapifyDown(int root){
		if(2*root>mainArray.size()-1)
		{
			return;
		}
		
		int childToSwap;
		
		//special case: parent only has one child
		if(2*root+1>mainArray.size()-1)
			childToSwap=2*root;
		else
		{
			if(mainArray.get(root*2).compareTo(mainArray.get(root*2+1))>0)
				childToSwap=root*2;
			else
				childToSwap=root*2+1;
		}
		
		if(mainArray.get(childToSwap).compareTo(mainArray.get(root))>0)
		{
			swapItems(root,childToSwap);
			heapifyDown(childToSwap);
		}
		
	}
	
	public boolean heapIsEmpty(){
		return mainArray.size()==0;
	}
	public void swapItems(int a, int b){
		T temp = mainArray.get(a);
		mainArray.set(a, mainArray.get(b));
		mainArray.set(b, temp);
		
	}
	
	public String toString(){
		String out = "";
		for(int i = 0; i < size; i++){
			out += "[heap node]: "+mainArray.get(i).toString() + "\n";
		}
		return out;
	}
	
	
	public static void main(String args[])
	{
		HeapADT<Integer> h =new HeapADT<Integer>(30);
		h.heapInsert(3);
		h.heapInsert(4);
		h.heapInsert(2);
		h.heapInsert(5);
		h.heapInsert(8);
		System.out.println(h);
		h.heapDelete();
		System.out.println(h);
		h.heapDelete();
		System.out.println(h);
	}
	
}
