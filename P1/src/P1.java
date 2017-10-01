import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class P1 {

	//array list to store words read from file
	private ArrayList<String> tokens = new ArrayList<String>();

	//int to store size of words arraylist
	private int uniqueWords = 0;

	//method to strip HTML tags out of a string
	public String stripHTML(String a){
		return a.replaceAll("<.*?>", "");
	}

	//method to read the file and parse it; returns an array list
	public void readFile(String fileName){

		try{

			//scanner to read in lines
			Scanner scan = new Scanner(new File(fileName));

			//scan each line
			while(scan.hasNext()){

				//read in the line
				String s = scan.nextLine();

				//replace all HTML tags in string w/ the empty string
				s = stripHTML(s);

				//create a new scanner to read in the clean line
				Scanner scan1 = new Scanner(s);

				//delimiter that will get rid of punctuation
				scan1.useDelimiter("[ *\\-,:;!?.]+");

				while(scan1.hasNext()){

					//read in a word
					String t = scan1.next();

					//get rid of white space
					String clean = t.trim();

					//put to lowercase
					clean = clean.toLowerCase();

					//send to be put into the arraylist
					insertString(clean);

				}

				scan1.close();
			}	

			//when finished reading/sorting file, print arraylist
			printList(tokens);

			scan.close();
		}

		catch(Exception e){
			System.err.println("Error: " + e);
			System.exit(0);
		}		
	}

	//method to insert String into arraylist 
	public void insertString(String s){

		//check if array size = 0
		if(tokens.size() == 0){
			//if so, just add the string to the arraylist
			tokens.add(s);			
			//increment the number of unique words
			uniqueWords++;
		}

		else{

			//check to see if the word already exists in the array
			if(!tokens.contains(s)){

				//if it doesn't, find index to insert word using binary search
				int i = search(s, tokens, 0, tokens.size()-1);

				//compare s with the value at the index found in search
				int comp = s.compareTo(tokens.get(i));

				//if the comparison is positive, increase the index and add the string
				if(comp > 0){
					tokens.add(i+1, s);
					uniqueWords++;
				}

				//otherwise, add the string at the current index
				else{
					tokens.add(i, s);
					uniqueWords++;
				}

			}
		}

	}

	public int search(String token, ArrayList<String> list, int low, int high){

		//find midpoint of arraylist
		int middle = (high - low)/2 + low;

		if(high > low){

			//compare the string at midpoint to the token
			int compare = list.get(middle).compareTo(token);

			//if compare value is positive, search first half of array
			if(compare > 0){
				return search(token, list, low, middle);
			}

			//if compare value is negative, search the second half of the array
			else if(compare < 0){
				return search(token, list, middle+1, high);
			}

			//handle the case where there is a match (which shouldn't happen)
			else{
				System.out.println("Error: found a match when I shouldn't have.");
				return -1;
			}
		}

		else {
			return middle;
		}
	}

	public void printList(ArrayList<String> s){

		//per the format, print WORDS
		System.out.println("WORDS");

		//go through the array list and print each object
		for(int i = 0; i < s.size(); i++){
			System.out.println(s.get(i));
		}

		//print number of words
		System.out.println("\nNUMBER OF WORDS: " + uniqueWords);
	}


	public static void main(String[] args){

		P1 test = new P1();

		if(args.length == 0){
			System.out.println("Error: No arguments supplied.");
			System.exit(0);
		}

		else{
			test.readFile(args[0]);
		}


	}


}
