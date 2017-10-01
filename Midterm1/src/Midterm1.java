

public class Midterm1 {

	public static void main(String[] args) {
		CS200ArrayList testArray = new CS200ArrayList();
		
		// add some Terms to the testArray
		testArray.add(new TermMT1("a",10));
		testArray.add(new TermMT1("b",20));
		testArray.add(new TermMT1("c",30));
		testArray.add(new TermMT1("d",40));
		testArray.add(new TermMT1("e",50));
		
		// check that it worked
		System.out.println(testArray);
		QuickSortClass.quickSort(testArray, 0, testArray.size()-1);
		System.out.println(testArray);
		
	}

}
