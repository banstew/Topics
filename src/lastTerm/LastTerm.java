package lastTerm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class LastTerm {
	
	/**
	 * 
	 * @author Ben Stewart
	 * 
	 * Caluculates the last term of a Digital Form Factor and returns its number in the sequence
	 * 
	 */

	public static void main(String[] args){
		try {
			FileReader fr = new FileReader("src/lastTerm/test.txt");

			Scanner scan = new Scanner(fr);

			HashMap<Integer, Integer> numbers = new HashMap<Integer, Integer>();
			String input = scan.next();
			int inputInt = Integer.parseInt(input);
			int caseNumber =1;
			int terms = 0;
			while (!(inputInt==0)){//inputInt=0 is the exit condition
				if (inputInt>2000){ //if the input is greater than 2000, shout
					System.out.println("input number is too large:" + inputInt + "\n");
					input = scan.next();
					inputInt = Integer.parseInt(input);
				}else if (inputInt<0){ //if the input is negative, shout
					System.out.println("your input was: " + inputInt + ", negative numbers are not allowed \n");
					input = scan.next();
					inputInt = Integer.parseInt(input);
				}else if (numbers.containsKey(inputInt)){//if numbers contains inputInt as a key
					terms++;
					if(inputInt!=numbers.get(inputInt)){//if inputInt is not the last term of the DDF
						inputInt = numbers.get(inputInt);
					}else{// if inputInt is the last term of the DDF
						System.out.println("Case "+ caseNumber+ ": " + terms + " terms \n");
						terms = 0;
						caseNumber++;
						input = scan.next();
						inputInt = Integer.parseInt(input);
					}
				}else{ // if inputInt is not a key in numbers
					int factor = 1;
					int factorValue = 0;
					while (factor<=inputInt){
						if (inputInt%factor==0){
							if (factor>=10){
								int tmpFactor = factor;
								while (tmpFactor>=1){
									factorValue += tmpFactor%10;
									tmpFactor = tmpFactor/10;
								}
							}else{
								factorValue+=factor;
							}
						}
						factor++;
					}
					terms++;
					if(inputInt!=factorValue){
						numbers.put(inputInt, factorValue);
						inputInt = factorValue;
					}else{
						numbers.put(inputInt, factorValue);
						System.out.println("Case "+ caseNumber+ ": " + terms + " terms \n");
						terms = 0;
						caseNumber++;
						input = scan.next();
						inputInt = Integer.parseInt(input);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
