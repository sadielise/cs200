// PA2
// Authors: Sadie Henry, Melinda Ryan 
// Date: Sep 22, 2014
// Class: CS200

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PA2 {

	public static void main(String[] args) 
	{
		//arraylist for the file names
		ArrayList<String> fileNames = new ArrayList<String>();
		//arraylist for words on which you should run whichPages
		ArrayList<String> whichPagesWords = new ArrayList<String>();
		//int variable for number of stopWords
		int stopWords = 0;


		try{
			//Read in the file
			Scanner scanFile = new Scanner(new File(args[0]));

			//tell the scanner to scan file names until the flag
			while(true){

				//scan in the line
				String s = scanFile.nextLine();

				if(s.equals("*EOFs*")){
					break;
				}

				else{
					fileNames.add(s);
				}
			}

			//get the number of stop words
			stopWords = scanFile.nextInt();

			//eat the extra newline
			scanFile.nextLine();

			//read in the words for whichPages
			while(scanFile.hasNext()){
				String s = scanFile.nextLine();                        
				whichPagesWords.add(s.toLowerCase());
			}			
		}

		catch(Exception e){
			System.out.println("Error: " + e);
			System.exit(0);
		}

		//create the WebPages object
		WebPages webPages = new WebPages();

		//add the new pages
		for(int i = 0; i < fileNames.size(); i++){
			webPages.addPage(fileNames.get(i));
		}

		//print terms
		webPages.printTerms();

		//remove stop words
		webPages.pruneStopWords(stopWords);

		//print terms again
		webPages.printTerms();

		//run whichPages method
		for(int i = 0; i < whichPagesWords.size(); i++){
			String[] array = webPages.whichPages(whichPagesWords.get(i));

			//String to be printed for each whichPages word
			String s = whichPagesWords.get(i);

			//if the word isn't found, return word not found
			if(array == null){
				s += " not found";
			}

			//otherwise, return the word and the pages it occurs on
			else{
				//String to print
				s += " in pages: ";

				for(int j = 0; j < array.length-1; j++){
					s += array[j] + ", ";
				}

				s += array[array.length-1];
			}

			System.out.println(s);
		}
	}
}