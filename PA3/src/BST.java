
public class BST {
	
	//Instance variables
	private Term root;
	private int count;
	
	/* constructor which initializes an instance variable called "root" 
	as null and an instance variable called "count" as 0.*/
	public BST(){
		root = null;
		count = 0;
	}

	//returns the number of unique words in the document (i.e., count).
	public int size(){
		return count;
	}

	/*adds a new Term or increments frequencies if the term 
	already exists in the BST.*/
	public void add(String documentName, String word){
		
	}

	/*returns the Term object for the word. 
	If printDepth is true, then get should keep track of how 
	deep in the tree it finds word and print out the value 
	at the end in the form " At depth 1" (At is preceded by 2 spaces). 
	If the word is not found, it should print the deepest level that it checked.*/
	public Term get(String word, Boolean printDepth){
		
		return null;
	}

	
	public void delete(String word){
		
	}
	/*which destructively modifies the BST so that the Term indicated by 
	word is no longer in the BST, but the BST is otherwise intact. 
	Follow the convention/algorithm described in class and the text for how to delete.*/

}
