import java.util.Random;

/** purpose of this class is to make random numbers and random strings
 * 
 * @author patmore
 *
 */
public class Randoms {
	long seed;
	Random rand;
	char alphabet[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
					   'n','o','p','q','r','s','t','u','v','w','x','y','z' };
	
	public Randoms(){
		seed = System.currentTimeMillis();
		rand = new Random(seed);
	}
	public int rand_int(int maxval){ return rand.nextInt(maxval); }
	public char rand_letter(){
		int index = rand_int(26);
		return alphabet[index];
	}
	public String rand_word(int length){
		char letters[] = new char[length];
		for(int i = 0; i < length; i++){
			letters[i] = rand_letter();
		}
		String out = new String(letters);
		return out;
	}
    public int[] rand_gen(int size){
    	//long seed = System.currentTimeMillis();
    	//Random rand = new Random(seed);
    	int[] data = new int[size];
    	int maxNum = size * 3;
    	for(int i = 0; i < data.length; i++){
    		data[i] = rand.nextInt(maxNum);
    	}
    	return data;
    }
}
