package beevaria;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Beevaria {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			FileReader fr = new FileReader("src/beevaria/test.txt");
			Scanner scan = new Scanner(fr);

			String input = scan.next();
			//int prison = 1;
			//int guardsNeeded = 1;
			
			while(!input.equals(-1)){
				//int size = Integer.parseInt(input);
				//int backSlashDiag = size;
				//int forwardSlashDiag = size - 1;
				//int verticle = backSlashDiag+forwardSlashDiag;
				//int hexNumber = size*(size*2-1)-1;
				
				input = scan.next();
				while(!input.equals(-1)){
					
					
					
					
					input = scan.next();
				}
				System.out.println("");
				//prison++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
