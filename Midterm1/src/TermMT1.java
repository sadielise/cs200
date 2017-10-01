/*
 * Keeps track of frequency counts for each term across the document set
 * Modified from PA2 to not include the docsList or docFrequency
 * and include only the necessary methods
 */
public class TermMT1 {
	// for each unique term found, 
	String name;  // track its name
	int totalFrequency; // total count of frequency across docs
	
	/*
	 * constructor supports test harness creation of an arrayList to sort
	 */
	public TermMT1(String chars, int num) {
		// sets the two instance variables
		name = chars;
		totalFrequency = num;
	}

	/*
	 * accessor method for name field
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * compares the names in two different TermMT1 objects
	 * results in a low to high sort
	 */
	public int compareTo(TermMT1 other) {
		// compare term names
		return name.compareToIgnoreCase(other.getName());
	}
	
	/*
	 * prints both the name and the totalFrequency
	 */
	public String toString() {
		return name + ": " + totalFrequency;
	}

}