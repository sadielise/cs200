

import java.util.ArrayList;

/* 
 * Implements a controlled form of ArrayList in which Terms are the
 * data element and counts of sets are maintained.
 * Includes the minimum methods needed to support quickSort
 */
public class CS200ArrayList {
	ArrayList<TermMT1> ourArray;
	int numSets = 0;
	
	public CS200ArrayList () {
		ourArray = new ArrayList<TermMT1>();
	}
	
	public TermMT1 get(int pos) {
		return ourArray.get(pos);
	}
	
	public void add(String name, int freq) {
		ourArray.add(new TermMT1(name, freq));
		numSets++;
	}
	
	public void add(TermMT1 myTerm) {
		ourArray.add(myTerm);
		numSets++;
	}
	
	public void set(int pos, TermMT1 myTerm) {
		ourArray.set(pos, myTerm);
		numSets++;
	}
	
	public int size() {
		return ourArray.size();
	}
	/*
	 * Creates a string of each TermMT1 in the array on a separate line
	 */
	public String toString() {
		String output = "";
		if (ourArray.size() > 0) {
			for (int i = 0; i < ourArray.size(); i++) {
				output += ourArray.get(i).toString() + "\n";
			}
		}
		return output + "Copies: " + numSets;
	}
}
