package relativePaths;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RelativePathsOld {

	/**
	 * @author Ben Stewart
	 * 
	 * Simple class that takes two directories as input and returns the valid input to change directories
	 * 
	 * The first input is a path P
	 * The second input is the current directory
	 * 
	 */
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("src/relativePaths/test.txt");
			Scanner scan = new Scanner(fr);

			String input = scan.next();

			int caseNumber = 1;

			while(scan.hasNext()){
				String p = null;
				String c = null;
				String s = null;
				while(s==null){
					if (p == null){
						p = input;
					}else if (c == null){
						c = input;
					}
					if (p!=null&&c!=null){
						int i;
						int j;
						boolean absolute=false;
						boolean diffDir=false;
						String currentDir = c;
						String givenDir = p;
						List<String> givenArray = new ArrayList<String>(); //p
						List<String> currentArray = new ArrayList<String>(); //c
						
						ArrayList<String> dots = new ArrayList<String>();

						dots.add(".");

						if (givenDir.startsWith("/", 0)){ //if the given path is absolute
							givenDir = givenDir.substring(1);
							absolute = true;
						}
						currentDir = currentDir.substring(1);
						String[] g = givenDir.split("/"); //breaks the string into an array of the given directories
						String[] ca = currentDir.split("/");
						Collections.addAll(givenArray, g); //cause ArrayLists are easier to work with
						Collections.addAll(currentArray, ca);

						givenArray.removeAll(dots); //remove all . from the array
						if(givenArray.isEmpty()){ //if P was of the form '.' or '/.' remove the '.'  
							givenArray.add("");
						}

						if (givenArray.contains("..")){ //if the given dir contains '..' initially remove '..' and the entry before it
							for (i=0;i<givenArray.size();i++){
								if (givenArray.get(i).equals("..")&&i!=0){
									givenArray.remove(i);
									givenArray.remove(i-1);
									if (i==1){
										absolute = true; //the given path was not absolute, but became that way from a '..'
									}
									i-=2;
								}else if(givenArray.get(i).equals("..")&&i==0){ //if the given dir starts with '..' remove it
									if (givenArray.size()!=1){
										givenArray.remove(0);
									}else{
										givenArray.set(0,"");
									}
								}
							}
						}
						for (i=0; i<currentArray.size(); i++){
							for (j=0; j<givenArray.size();j++){
								s="";
								if (j==i&&givenArray.get(j).equals(currentArray.get(i))&&!diffDir){ //P and C start in the same directory
									if(j!=givenArray.size()-1||i!=currentArray.size()-1){
										while(j<givenArray.size()&&i<currentArray.size()&&givenArray.get(j).equals(currentArray.get(i))){
											givenArray.remove(j);
											currentArray.remove(i);
											diffDir=true;
										}
									}
									if (j==givenArray.size()&&i==currentArray.size()
											||givenArray.get(0).equals("")&&currentArray.get(0).equals("")){ //P and C are the same
										s=".";
										break;
									}
								}
								if (j>=givenArray.size()-1&&i>=currentArray.size()-1&&absolute){ //if p is an absolute path and has nothing in common with c
									if (!currentArray.isEmpty()&&!currentArray.get(0).equals("")&&absolute){
										for(int k = 0;k<=i;k++){
											s += "..";
											if(k<currentArray.size()-1||k<givenArray.size()){
												s+="/";
											}
										}
									}
									if (!givenArray.isEmpty()&&!givenArray.get(0).equals("")){ //add the non-similar values in P to S
										for(int k = 0;k<=j;k++){
											s+=givenArray.get(k);
											if(k!=givenArray.size()-1){
												s+="/";
											}
										}
									}
								}else if(j>=givenArray.size()-1&&i>=currentArray.size()-1&&!absolute){ //P is not absolute and has nothing in common with C
									if(!currentArray.isEmpty()&&!currentArray.get(0).equals("")&&diffDir){
										for(int k = 0;k<=i;k++){
											s += "..";
											if(k!=currentArray.size()-1||k!=givenArray.size()){
												s+="/";
											}
										}
									}
									for(int k = 0;k<=j;k++){
										s+=givenArray.get(k);
										if(k!=givenArray.size()-1){
											s+="/";
										}
									}
								}
							}
						}
					}
					if(scan.hasNext()){
						input=scan.next();
					}
				}
				System.out.println("Case "+caseNumber+":");
				System.out.println("   P = " + p);
				System.out.println("   C = "+ c);
				System.out.println("   S = "+ s + "\n");
				caseNumber++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
