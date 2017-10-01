
/**
 * Test harness for CSU CS200 Fall 2014 Lab 7
 * @author David Newman (Fall 2010), modified AEH (Fall 2014)
 *
 */
public class BinaryTreeIteratorTester {

	/**
	 * Test an in-order binary tree iterator.
	 * @param args
	 */
	public static void main(String[] args) {
		
		BinaryTree<String> testTree = new BinaryTree<String>("*");
		BinaryTree<String> temp1 = new BinaryTree<String>("+");
		temp1.attachLeft("42");
		temp1.attachRight("3");
		BinaryTree<String> temp2 = new BinaryTree<String>("-");
		temp2.attachLeft("86");
		temp2.attachRight("19");
		testTree.attachLeftSubtree(temp1);
		testTree.attachRightSubtree(temp2);
		
		//TODO: Once you implement the, uncomment the
		// code below and test it.
		
		InorderIterator<String> iter1 = new InorderIterator<String>(testTree);
		System.out.print(iter1.next());		
		while (iter1.hasNext()) {
			System.out.print(", " + iter1.next());
		}
		System.out.println(); 
	}

}
