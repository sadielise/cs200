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
	private ArrayList <Term> termIndex;
	public static int count;

	//initializes a new index, an ArrayList of Term
	public WebPages()
	{
		termIndex = new ArrayList<Term>();
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

	//removes n terms from the index
	public void pruneStopWords(int n)
	{
		//sort on totalFrequency
		mergesort(termIndex, 0);
		System.out.println("Copies: " + count);
		
		//remove n elements from end of list
		for(int i = 0; i < n; i++)
		{
			termIndex.remove(termIndex.size() - 1);
		}
		
		//sort on words
		mergesort(termIndex, 1);
		System.out.println("Copies: " + count + "\n");
	
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

	//adds words to the arraylist 
	public void addToTermIndex(String word, String document)
	{
		int index;
		Term t = new Term(word);

		//if list is empty, add term
		if(termIndex.size() == 0)
		{
			termIndex.add(t);
			t.incFrequency(document);
		}



		//if list not empty, run binary search to find correct location for word
		else
		{
			index = binarySearch(termIndex, word, 0, termIndex.size() - 1);
			if(index != -1)
			{
				termIndex.add(index, t);
				t.incFrequency(document);
			}
			else
				//increase the frequency of the item contained at indexOf t
				termIndex.get(termIndex.indexOf(t)).incFrequency(document);

		}


	}

	//searches arraylist for correct location to store word 
	//returns -1 if word exists
	public int binarySearch(ArrayList<Term> termIndex, String word, int first, int last)
	{
		int mid ;
		while(first <= last)
		{
			//find middle of the list
			mid = (first + last) / 2; 

			//check to search the bottom half
			if(word.compareTo((termIndex.get(mid)).getName()) < 0)
				last = mid - 1;

			//check to search the top half
			else if(word.compareTo((termIndex.get(mid)).getName()) > 0)
				first = mid + 1;

			//otherwise if word already exists
			else if (word.compareTo((termIndex.get(mid)).getName()) == 0)
				return -1;
		}
		return first;
	}

	/*
	 *Code for mergesort taken from:
	 * Prichard, Janet and Carrano, Frank M. (2010)
	 * Mergesort (pp. 529-531). Data Abstraction and Problem 
	 * Solving with Java: Walls and Mirrors. 
	 */

	public static void mergesort(ArrayList<Term> theArrayList, int key)
	{
		//make temp arraylist
		count = 0;
		ArrayList <Term> tempArrayList = (ArrayList<Term>) theArrayList.clone();
		mergesort(theArrayList, tempArrayList, 0, theArrayList.size() - 1, key);

	}
	private static void merge(ArrayList<Term> theArrayList, ArrayList<Term> tempArrayList, 
			int first, int mid, int last, int key)
	{

		int first1 = first;
		int last1 = mid;
		int first2 = mid + 1;
		int last2 = last;
		int index = first1; 
		//create comparison int
		int compare;

		//While both subArrayLists are not empty, copy
		//the smaller item into the temporary ArrayList
		while((first1 <= last1) && (first2 <= last2))
		{
			
			//check key
			if(key == 1)
				compare = theArrayList.get(first1).getName().compareTo(theArrayList.get(first2).getName());
			else
				compare = theArrayList.get(first1).getTotalFrequency()-(theArrayList.get(first2).getTotalFrequency());
			
			if(compare <= 0)
			{

				tempArrayList.set(index, theArrayList.get(first1));
				first1++;
				count ++;
			}
			else
			{

				tempArrayList.set(index, theArrayList.get(first2));
				first2++;
			}

			index++;
		}

		//finish off the first subArrayList if necessary
		while(first1 <= last1)
		{
			tempArrayList.set(index, theArrayList.get(first1));
			first1++;
			index++;
		}

		//finish off the second subArrayList if necessary
		while(first2 <= last2)
		{
			tempArrayList.set(index, theArrayList.get(first2));
			first2++;
			index++;

		}

		//copy the result back into the original ArrayList
		for(index = first; index <= last; ++index)
		{
			theArrayList.set(index, tempArrayList.get(index));
		}

	}

	public static void mergesort(ArrayList <Term> theArrayList, ArrayList <Term> tempArrayList, 
			int first, int last, int key)
	{
		
		if(first < last)
		{
			//sort each half
			int mid = (first + last)/2;
			//sort left half
			mergesort(theArrayList, tempArrayList, first, mid, key);
			//sort right  half
			mergesort(theArrayList, tempArrayList, mid+1, last, key);

			//merge two halves
			merge(theArrayList, tempArrayList, first, mid, last, key);
		}
	
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