package romanPalindromes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class RomanPalindromes {

	/**
	 * @author Ben Stewart
	 * 
	 * Class that reverses a given Roman Numeral, adds that reverse to the beginning of the original and outputs the total
	 * 
	 * 
	 */
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("src/romanPalindromes/test.txt");
			Scanner scan = new Scanner(fr);

			String input = scan.next();
			StringBuffer sb = new StringBuffer(input);
			StringBuffer palindrome = new StringBuffer(input); 
			if (input.length()%2==0){
				palindrome.insert(0, sb.reverse());
			}else{
				palindrome.insert(0, sb.reverse());
				palindrome.deleteCharAt(palindrome.length()/2);
			}
			
			int caseNumber = 1;
			int sum = 0;

			HashMap<String, Integer> romans = new HashMap<String, Integer>();
			romans.put("I", 1);
			romans.put("V", 5);
			romans.put("X", 10);
			romans.put("L", 50);
			romans.put("C", 100);
			romans.put("D", 500);
			romans.put("M", 1000);

			while(scan.hasNext() || sum == 0){
				while(palindrome.length()>1){
					int first = romans.get(palindrome.subSequence(0,1));
					int second = romans.get(palindrome.subSequence(1,2));

					if (first>=second){
						sum += first+second;
					}else{
						sum += second-first;
					}
					palindrome.deleteCharAt(0).trimToSize(); //removes the front most character from the string
				}
				System.out.println("Case "+ caseNumber+ ": total = " + sum + "\n");
				caseNumber++;
				if(scan.hasNext()){
					input = scan.next();
					sb = new StringBuffer(input);
					palindrome = new StringBuffer(input); 
					if (input.length()%2==0){
						palindrome.insert(0, sb.reverse());
					}else{
						palindrome.insert(0, sb.reverse());
						palindrome.deleteCharAt(palindrome.length()/2);
					}
					sum=0;
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public StringBuffer refresh(String input){
		
		StringBuffer sb = new StringBuffer(input);
		StringBuffer palindrome = new StringBuffer(input); 
		if (input.length()%2==0){
			palindrome.insert(0, sb.reverse());
		}else{
			palindrome.insert(0, sb.reverse());
			palindrome.deleteCharAt(palindrome.length()/2);
		}
		
		StringBuffer buffer = null;
		return buffer;
	}
	
}
