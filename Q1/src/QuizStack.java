
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.util.Stack;

public class QuizStack {
  
  static public void main(String[] args) {
	StackArrayBased aStack = new StackArrayBased();
	//StackReferenceBased aStack = new StackReferenceBased();
    Integer item; 
    Object temp;
    String command;
    
	if (args.length > 0) {
	try { 
		Scanner filein = new Scanner(new File(args[0]));
		while (filein.hasNext()) {
			//format is command int as in push 5
			command = filein.next();
			//System.out.println(command);
			switch (command) {
			case "push": aStack.push(filein.nextInt()); break;
			case "pop": temp = aStack.pop(); break;
			case "popall": aStack.popAll(); break;
			case "print": System.out.println(aStack); break;
			}
		}
		filein.close();
	} catch (FileNotFoundException e1) {
		System.out.println("Error: File " + args[0] + " cannot be found.");
	}
	}
	
	} // end main
  
} // end TestStack
