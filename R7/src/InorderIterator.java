import java.util.Iterator;

/**
 * Stub file for students to fill in. CSU CS200 Fall 2014 Lab 7.
 * @author David Newman (Fall 2010), modified AEH (Fall 2014)
 * @edited Sadie Henry, CS200 Recit 10 *
 * @param <E>
 */
public class InorderIterator<E> implements Iterator<E> {
	
	/**
	 * global variable for a referenced-based queue of tree nodes
	 */
	QueueReferenceBased<TreeNode<E>> inqueue = new QueueReferenceBased<TreeNode<E>>();
	
	/**
	 * global variable for a binary tree
	 */
	private BinaryTree<E> binTree;
	
	
	/**
	 * Construct a new iterator object.
	 * @param binTree
	 */
	public InorderIterator(BinaryTree<E> binTree) {
		
		this.binTree = binTree; 
		setInorder();
	}
	

	/**
	 * Return true iff the iterator has more objects yet to return.
	 * @see java.util.Iterator#hasNext()
	 * @return true if queue has another item, fals if it does not
	 */
	@Override
	public boolean hasNext() {
		return !inqueue.isEmpty();
	}

	/**
	 * Return the first object that has not yet been returned.
	 * @see java.util.Iterator#next()
	 * @return next item in the queue
	 */
	@Override
	public E next() {
		return inqueue.dequeue().getItem();
	}

	/**
	 * This is an illegal operation for this iterator.
	 * @see java.util.Iterator#remove()
	 * @return exception
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Helper method for inorder iteration
	 * Put the correct order of nodes onto the queue
	 */
	public void setInorder() {
		inqueue.dequeueAll();
		inorder(binTree.root);
	}
	
	/**
	 * Adds items to the queue in inorder order
	 * @param treeNode
	 */
	private void inorder(TreeNode<E> treeNode) {
		if(treeNode != null){
			inorder(treeNode.getLeft());
			inqueue.enqueue(treeNode);
			inorder(treeNode.getRight());
		}
	}
}
