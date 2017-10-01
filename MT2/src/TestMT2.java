

public class TestMT2 {

	public static void main(String[] args) {
		// creating a tree that is in the Prichard text to serve as an example 
	    BinaryTree<Integer> tree3 = new BinaryTree<Integer>(60);
	    tree3.attachLeft(10);

	    // build the tree in Figure 11-10
	    BinaryTree<Integer> tree1 = new BinaryTree<Integer>();
	    tree1.setRootItem(40);
	    tree1.attachLeft(30);
	    tree1.attachRight(20);

	    BinaryTree<Integer> tree2 = new BinaryTree<Integer>();
	    tree2.setRootItem(50);
	    tree2.attachLeft(10);
	    tree2.attachRightSubtree(tree1);

	    BinaryTree<Integer> binTree =   // tree in Figure 11-10
	         new BinaryTree<Integer>(70);


	    // if you want to print out the tree for debugging, here is a way to do it
	    TreeIterator<Integer> btIterator =  
	         new TreeIterator<Integer>(binTree);
	    btIterator.setInorder();

	    while (btIterator.hasNext()) {
	      System.out.println(btIterator.next());
	    }  // end while

	    // here you can place any code for testing the methods you are implementing
	    System.out.println("Number of leaves: " + binTree.numLeaves());
	    System.out.println("Is this a heap? " + binTree.isHeap());
	}

}
