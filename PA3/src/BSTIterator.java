import java.util.Iterator;
import java.util.Iterator;


public class BSTIterator<T> implements Iterator<T> {



	// a queue tracks the order for visiting the tree nodes
	QueueReferenceBased<TreeNode<T>> inqueue = new QueueReferenceBased<TreeNode<T>>();
	private BinaryTree<T> binTree;


	public BSTIterator(BinaryTree<T> binTree) {

		this.binTree = binTree; 
		setInorder();
	}


	/* (non-Javadoc)
	 * Return true iff the iterator has more objects yet to return.
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return !inqueue.isEmpty();
	}

	/* (non-Javadoc)
	 * Return the first object that has not yet been returned.
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
		return inqueue.dequeue().getItem();
	}

	/* (non-Javadoc)
	 * This is an illegal operation for this iterator.
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/*
	 * Put the correct order of nodes onto the queue
	 */
	public void setInorder() {
		inqueue.dequeueAll();
		inorder(binTree.root);
	}
	private void inorder(TreeNode<T> treeNode) {
		if(treeNode != null){
			inorder(treeNode.getLeft());
			inqueue.enqueue(treeNode);
			inorder(treeNode.getRight());
		}
	}


}
