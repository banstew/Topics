package bandwidth;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class bandwidth {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			FileReader fr = new FileReader("src/bandwidth/test.txt");
			Scanner scan = new Scanner(fr);

			String input = scan.next();

			StringBuffer graph = new StringBuffer();
			//int bandwidth = 0;
			while(!input.equals("$")){
				while(graph.length()>1){
					
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
