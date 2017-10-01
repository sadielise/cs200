// PA2
// Authors: Sadie Henry, Melinda Ryan 
// Date: Sep 22, 2014
// Class: CS200

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WebPages 
{
	//Instance variable for ArrayList of Terms
	private BST termIndex;
	public static int count;

	//initializes a new index, an ArrayList of Term
	public WebPages()
	{
		termIndex = new BST();
	}

	//reads in the page in filename, divides it into words as before 
	//and adds those words and their counts to the termIndex. 
	public void addPage(String filename)
	{
		try
		{
			//read line-by-line through the file to get words
			Scanner readFile = new Scanner(new File(filename));
			while(readFile.hasNextLine())
			{
				//read in a line
				String line = readFile.nextLine();

				//remove HTML tags from the line
				line = stripHTML(line);

				//delimit by everything but letters, numbers, ', and <>
				Scanner readLine = new Scanner(line).useDelimiter("[^\\w'<>]+");

				while(readLine.hasNext())
				{
					//set the line to lowercase
					String word = readLine.next().toLowerCase();

					//add the word to the TermIndex
					addToTermIndex(word, filename);					

				}

				readLine.close();
			}

			readFile.close();
		}
		catch(IOException e)
		{
			System.out.println("Error: Unable to read file");
		}

	}

	//prints on a separate line each word followed by two spaces followed by its 
	//frequency in the order in which it is stored in the ArrayList (as in PA1).
	public void printTerms()
	{
		System.out.println(toListString());	
	}

	//returns required output string for arraylist
	public String toListString()
	{

		//check for an empty arraylist
		if(termIndex.size() == 0)
			return "Error: Empty List";

		//loop through arraylist to add each to the string
		String outputString = "WORDS\n";
		for(Term t: termIndex)
		{
			outputString += (t.getName() + "\n");
		}

		return outputString; //+ "\nNUMBER OF WORDS: " + termIndex.size();
	}

	//method to strip HTML tags out of a string
	public String stripHTML(String a)
	{
		return a.replaceAll("<.*?>", "");
	}

	//adds words to the term tree
	public void addToTermIndex(String word, String document)
	{
		termIndex.add(document, word);
	}

	public String[] whichPages(String word){

		//make a new term to compare to the term index
		Term newTerm = new Term(word);
		
		//search through term index
		if(termIndex.contains(newTerm)){

			//get the index of the term
			int index = termIndex.indexOf(newTerm);
			
			//get the listOfFileNames for that term
			ArrayList<String> arrayList = termIndex.get(index).getListOfFileNames();
			
			//copy array list to string array
			String[] stringArray = new String[arrayList.size()];
			for(int i = 0; i < arrayList.size(); i++){
				stringArray[i] = arrayList.get(i);
			}
			
			//return array
			return stringArray;
			
		}
		
		else{
			return null;
		}
		
	}







}