
public class StackArrayBased implements StackInterface {
	final int MAX_STACK = 50;  // maximum size of stack
	private Object items[];
	private int top;

	
	public StackArrayBased() {
		items = new Object[MAX_STACK]; 
		top = -1; 
	} 

	
	public boolean isEmpty() {
		return top < 0;
	} 

	
	public boolean isFull() {
		return top == MAX_STACK-1;
	} 

	
	public void push(Object newItem) throws StackException {
		if(!isFull()){
			items[top+1] = newItem;
			top++;
		}
		else{
			throw new StackException("StackException on " +
					"push: stack full");
		}
	} 
	
	
	public void popAll() {
		if(!isEmpty()){
			for(int i = top; i > -1; i--){
				pop();
			}
		}
		else{
			throw new StackException("StackException on " +
					"popAll: stack empty");
		}
	} 

	public Object pop() throws StackException {
		if (!isEmpty()) {
			return items[top--];
		}
		else {
			throw new StackException("StackException on " +
					"pop: stack empty");
		} 
	}  

	
	public Object peek() throws StackException {
		if (!isEmpty()) {
			return items[top];
		}
		else {
			throw new StackException("Stack exception on " +
					"peek - stack empty");
		} 
	}  

	
	// added by AEH 9/16/14
	// allows the contents of the Stack to be inspected
	public String toString() {
		String temp = "Stack:";
		if (!isEmpty()) {
			for (int i=top; i>-1; i--){
				temp = temp + "  " + items[i];			  
			}
		}
		return temp;
	}
}  
