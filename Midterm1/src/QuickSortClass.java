

// This code will compile with warnings about unchecked exceptions

public class QuickSortClass {

	public static void choosePivot(CS200ArrayList theArray,
			int first, int last) {
		// ---------------------------------------------------------
		// Chooses a pivot for quicksort's partition algorithm and
		// swaps it with the first item in an array.
		// Precondition: theArray[first..last] is an array;
		// first <= last.
		// Postcondition: theArray[first] is the pivot.
		// ---------------------------------------------------------
		// default is to take the element in the first position

		if(theArray.size() > 1){

			TermMT1 tempItem;

			//sample first position
			TermMT1 firstSample = theArray.get(first);

			//sample middle position
			int middlePosition = (int)Math.floor((last+first)/2);
			TermMT1 middleSample = theArray.get(middlePosition);

			//sample last position
			TermMT1 lastSample = theArray.get(last);

			//test firstSample
			if(firstSample.compareTo(middleSample) > 0 && firstSample.compareTo(lastSample) < 0 || 
					(firstSample.compareTo(lastSample) > 0 && firstSample.compareTo(middleSample) < 0)){
				tempItem = theArray.get(first);
				theArray.set(first, firstSample);
				theArray.set(first, tempItem);
			}

			//test middleSample
			else if(middleSample.compareTo(firstSample) > 0 && middleSample.compareTo(lastSample) < 0 || 
					(middleSample.compareTo(lastSample) > 0 && middleSample.compareTo(firstSample) < 0)){
				tempItem = theArray.get(middlePosition);
				theArray.set(first, middleSample);
				theArray.set(middlePosition, tempItem);
			}

			//test lastSample
			else if((lastSample.compareTo(firstSample) > 0 && lastSample.compareTo(middleSample) < 0) ||
					(lastSample.compareTo(middleSample) > 0 && lastSample.compareTo(firstSample) < 0)){
				tempItem = theArray.get(last);
				theArray.set(first, lastSample);
				theArray.set(last, tempItem);
			}
			
			else if(firstSample.equals(lastSample) || firstSample.equals(middleSample)){
				tempItem = theArray.get(first);
				theArray.set(first, firstSample);
				theArray.set(first, tempItem);
			}
			
			else if(middleSample.equals(firstSample) || middleSample.equals(lastSample)){
				tempItem = theArray.get(middlePosition);
				theArray.set(first, middleSample);
				theArray.set(middlePosition, tempItem);
			}
		}
		

	} // end choosePivot

	public static int partition(CS200ArrayList theArray,
			int first, int last) {
		// ---------------------------------------------------------
		// Partitions an array for quicksort.
		// Precondition: theArray[first..last] is an array;
		// first <= last.
		// Postcondition: Returns the index of the pivot element of
		// theArray[first..last]. Upon completion of the method,
		// this will be the index value lastS1 such that
		// S1 = theArray[first..lastS1-1] < pivot
		// theArray[lastS1] == pivot
		// S2 = theArray[lastS1+1..last] >= pivot
		// Calls: choosePivot.
		// ---------------------------------------------------------
		// temporary array for sorting
		TermMT1[] tempArray = new TermMT1[theArray.size()];
		// reference to the first open position in S2
		int s2 = tempArray.length - 1;
		// reference to the first open position in S1
		int s1 = 0;
		// place pivot in theArray[first]
		choosePivot(theArray, first, last);
		TermMT1 pivot = theArray.get(first); // reference pivot
		// move one item at a time until unknown region is empty
		for (int firstUnknown = first + 1; firstUnknown <= last; firstUnknown++) {
			// Invariant: theArray[first+1..lastS1] < pivot
			// move item from unknown to proper region
			if (theArray.get(firstUnknown).compareTo(pivot) < 0) {
				// item from unknown belongs in S1
				tempArray[s1] = theArray.get(firstUnknown);
				s1++;
			} 
			else{
				// item from unknown belongs in S2
				tempArray[s2] = theArray.get(firstUnknown);
				s2--;
			}
		}

		// put pivot in the temporary array between S1 and S2
		tempArray[s1] = pivot;

		// copy temporary array into theArray
		for(int i = 0; i < tempArray.length; i++){
			theArray.set(i, tempArray[i]);
		}

		return s1;
	} // end partition

	public static void quickSort(CS200ArrayList theArray,
			int first, int last) {
		// ---------------------------------------------------------
		// Sorts the items in an array into ascending order.
		// Precondition: theArray[first..last] is an array.
		// Postcondition: theArray[first..last] is sorted.
		// Calls: partition.
		// ---------------------------------------------------------
		int pivotIndex;
		if (first < last) {
			// create the partition: S1, Pivot, S2
			pivotIndex = partition(theArray, first, last);
			// sort regions S1 and S2
			quickSort(theArray, first, pivotIndex-1);
			quickSort(theArray, pivotIndex+1, last);
		} // end if
	} // end quickSort

} // end SortsClass