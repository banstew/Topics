package relativePaths;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//TODO S has the fewest possible characters
//TODO case 2,26,35,36
public class RelativePaths {

	/**
	 * @author Ben Stewart
	 * 
	 * Simple class that takes two directories as input and returns the valid input to change directories
	 * 
	 * The first input is a path P
	 * The second input is the current directory
	 */

	static int caseNumber = 1;
	static String p;
	static String c;
	static StringBuffer s = new StringBuffer();
	static ArrayList<String> givenArray = new ArrayList<String>(); //P
	static ArrayList<String> currentArray = new ArrayList<String>(); //C

	public static void main(String[] args) {
		try{
			FileReader fr = new FileReader("src/relativePaths/test.txt");
			Scanner scan = new Scanner(fr);
			p=scan.nextLine();
			do{
				c=scan.nextLine();
				if(p.equals("")||c.equals("")||!c.startsWith("/")){	//if the input file starts with a new line or
					break;											//there is a new line after p is given or C is not absolute!
				}

				normalize();
				findS();
				finished();
				
				if(scan.hasNextLine()){
					p=scan.nextLine();
				}
			}while(!p.equals(""));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	//////////////////////////////////////////////normalize//////////////////////////////////////////////////////////
	private static void normalize() {
		/**
		 * method to normalize P and C so they are both absolute paths with no '.' or '..'
		 * 
		 * the method also removes all of the left most similarities between C and P in preparation for the findS method
		 */

		if (!p.startsWith("/")){ //if the given path is absolute
			Collections.addAll(givenArray, c.split("/"));
			if(givenArray.size()>1){
				givenArray.remove(0);
			}
		}
		Collections.addAll(givenArray, p.split("/")); //breaks P into an array of its elements and adds that to given Array
		if(givenArray.size()>1&&p.startsWith("/")){
			givenArray.remove(0);
		}

		Collections.addAll(currentArray, c.split("/"));
		if(currentArray.size()>1){
			currentArray.remove(0);
		}
		while(givenArray.contains(".")||currentArray.contains(".")){ //remove all '.' from given array and current array
			if(givenArray.contains(".")){
				givenArray.remove(".");
			}
			if(currentArray.contains(".")){
				currentArray.remove(".");
			}
		}
		int upDir=0;
		while(givenArray.contains("..")){
			if(givenArray.indexOf("..")==0){//if the .. is at the beginning of P and C is / just remove it
				givenArray.remove(givenArray.indexOf(".."));
			}else if(givenArray.indexOf("..")==1&&givenArray.size()==2){ 
				givenArray.clear();
			}else{//else remove the .. and the element before it
				givenArray.subList(givenArray.indexOf("..")-1, givenArray.indexOf("..")+1).clear();
				if(!p.startsWith("/")){
					upDir++;
				}
			}
		}
 		while(currentArray.contains("..")){
			if(currentArray.indexOf("..")==0){//if the .. is at the beginning of the path just remove it
				currentArray.remove(currentArray.indexOf(".."));
			}else if(currentArray.indexOf("..")==1&&currentArray.size()==2){ 
				currentArray.clear();
			}else{//else remove the .. and the element before it
				currentArray.subList(currentArray.indexOf("..")-1, currentArray.indexOf("..")+1).clear();
			}
		}	
		while(upDir>0){
			if(upDir<=currentArray.size()){
				if(givenArray.size()==0||currentArray.get(currentArray.size()-upDir).equals(givenArray.get(0))
						||upDir==currentArray.size()&&!currentArray.get(0).equals(givenArray.get(0))){
					upDir=0;
				}else if(!currentArray.get(0).equals(givenArray.get(0))&&upDir<currentArray.size()){
					currentArray.subList(upDir+1, currentArray.size()).clear();
					upDir=0;
				}else if(currentArray.size()>0&&!currentArray.get(currentArray.size()-upDir).equals(givenArray.get(0))){
					givenArray.remove(currentArray.get(currentArray.size()-upDir-1));
					currentArray.remove(currentArray.size()-upDir-1); 
					upDir--;
				}else{
					upDir--;
				}
			}else{
				upDir=currentArray.size();
			}
		}
		while(!givenArray.isEmpty()&&!currentArray.isEmpty()&&
				givenArray.get(0).equals(currentArray.get(0))){ //remove all of the leftmost elements in P and C that are the same
			givenArray.remove(0);
			currentArray.remove(0);
		}
	}

	//////////////////////////////////////////////findS//////////////////////////////////////////////////////////////////////
	private static void findS() { //computes the value of S
		if(givenArray.isEmpty()&&currentArray.isEmpty()){ //if C and P are both the same after normalization
			s.append(".");
		}else{
			while(!currentArray.isEmpty()){
				s.append("..");
				currentArray.remove(0);
				if (currentArray.size()+givenArray.size()!=0){ //if the item removed was not the last 
					s.append("/");
				}
			}
			while(!givenArray.isEmpty()){
				s.append(givenArray.get(0));
				givenArray.remove(0);
				if (givenArray.size()!=0){ //if the item removed was not the last 
					s.append("/");
				}
			}
		}
	}

	////////////////////////////////////////////finished//////////////////////////////////////////////////////////////////////////
	private static void finished(){
		System.out.println("Case "+caseNumber+":");
		System.out.println("   P = " + p);
		System.out.println("   C = "+ c);
		System.out.println("   S = "+ s + "\n");

		caseNumber++;
		resetGlobals();
	}

	/////////////////////////////////////////////reset/////////////////////////////////////////////////////////////////////////////
	private static void resetGlobals() {
		s.delete(0, s.length()+1);
		c="arbitrary non-empty string";
		givenArray = new ArrayList<String>();
		currentArray = new ArrayList<String>();
	}
}
