import java.util.ArrayList;
//Stack.java
//Author: NameHere
//Date: DateHere
//Class: cs200
//R3

public class Stack {
    private ArrayList<Character> list;
    
    //constructor
    public Stack()
    {
    	list=new ArrayList<Character>();
    }
	
    // returns true if the stack is empty otherwise it returns false.
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	public void push(char toPush)
	{
		//TODO: This method should push the character: toPush to the top of the stack.
		list.add(0, toPush);
	
	}
	
	public char pop()
	{
		//TODO: This method should remove the top character from the stack and return it.
		char c = list.get(0);
		
		list.remove(0);

		return c;
	}
	
	public char peek()
	{
		//TODO: This method should return the top character from the stack but not remove it.
		char c = list.get(0);

		return c;
	}
	
	public static void main(String[] args) {
		Stack s = new Stack();
		System.out.println("Adding characters: a, b, and c to stack.");
		s.push('a');
		s.push('b');
		s.push('c');
		
		System.out.println("Peeking at the to character of the stack (Should be c).");
		System.out.println(s.peek());
		System.out.println("Popping all characters from the stack.");
		while(!s.isEmpty())
		{
			System.out.println(s.pop());
		}
		
		
		

	}

}
