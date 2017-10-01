// Palindrome.java
// Author: NameHere
// Date: DateHere
// Class: cs200
// R3
public class Palindrome {

	public static void generatePalindrome(String s,int index)
	{
		
		//string length 
		int stringLength = s.length();
		
		//create a new stack
		Stack stack = new Stack();

		//add the characters to the stack in order
		for(index = 0; index < stringLength; index++){
			//push the character to the stack
			stack.push(s.charAt(index));
		}
		
		//add the characters from the stack back onto the string
		for(index = 0; index < stringLength; index++){
			s += stack.pop();
		}
		
		System.out.println(s);
		
	}
	
	
	public static void main(String[] args) {
		generatePalindrome("sadie", 0);

	}

	

}
